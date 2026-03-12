package in.nic.meghalaya.weather.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.nic.meghalaya.weather.dto.WeatherDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ImdWeatherService {

    private static final String NOWCAST =
        "https://mausam.imd.gov.in/api/nowcast_district_api.php";

    private static final String RAINFALL =
        "https://mausam.imd.gov.in/api/districtwise_rainfall_api.php";

    private static final String WARNINGS =
        "https://mausam.imd.gov.in/api/warnings_district_api.php";

    private final RestTemplate rest = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    private final Map<String, WeatherDTO> store = new HashMap<>();

    private static final String[] DISTRICTS = {
        "RI BHOI",
        "EAST KHASI HILLS",
        "WEST KHASI HILLS",
        "SOUTH WEST KHASI HILLS",
        "EASTERN WEST KHASI HILLS",
        "EAST JAINTIA HILLS",
        "WEST JAINTIA HILLS",
        "NORTH GARO HILLS",
        "EAST GARO HILLS",
        "WEST GARO HILLS",
        "SOUTH GARO HILLS",
        "SOUTH WEST GARO HILLS"
    };

    @PostConstruct
    public void init() {
        refresh();
    }

    @Scheduled(fixedRate = 3 * 60 * 60 * 1000)
    public void refresh() {

        store.clear();

        for (String d : DISTRICTS) {
            WeatherDTO dto = new WeatherDTO();
            dto.setDistrict(d);
            dto.setNowcastSummary("No Weather");
            dto.setNowcastColor("green");
            dto.setDailyRain(0);
            dto.setWeeklyRain(0);
            dto.setRainCategory("NA");
            dto.setForecast5Days(List.of("Normal","Normal","Normal","Normal","Normal"));
            dto.setLastUpdated(LocalDateTime.now());
            store.put(norm(d), dto);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        HttpEntity<Void> request = new HttpEntity<>(headers);

        // ---------- NOWCAST ----------
        try {
            List<Map<String, Object>> nowcast =
                mapper.convertValue(
                    rest.exchange(NOWCAST, HttpMethod.GET, request, Object.class).getBody(),
                    new TypeReference<List<Map<String, Object>>>() {}
                );

            for (Map<String, Object> row : nowcast) {
                String d = norm(row.get("State_District"));
                WeatherDTO dto = store.get(d);
                if (dto != null) {
                    dto.setNowcastSummary(decodeNowcast(row));
                    dto.setNowcastColor(color(row.get("color")));
                }
            }
        } catch (Exception ignored) {}

        // ---------- RAINFALL ----------
        try {
            List<Map<String, Object>> rain =
                mapper.convertValue(
                    rest.exchange(RAINFALL, HttpMethod.GET, request, Object.class).getBody(),
                    new TypeReference<List<Map<String, Object>>>() {}
                );

            for (Map<String, Object> row : rain) {
                if (!"MEGHALAYA".equalsIgnoreCase(safe(row.get("State")))) continue;

                String d = norm(row.get("District"));
                WeatherDTO dto = store.get(d);
                if (dto != null) {
                    dto.setDailyRain(parse(row.get("Daily Actual")));
                    dto.setWeeklyRain(parse(row.get("Weekly Actual")));
                    dto.setRainCategory(mapRainCategory(safe(row.get("Daily Category"))));
                }
            }
        } catch (Exception ignored) {}

        // ---------- WARNINGS (5 DAY) ----------
        try {
            List<Map<String, Object>> warn =
                mapper.convertValue(
                    rest.exchange(WARNINGS, HttpMethod.GET, request, Object.class).getBody(),
                    new TypeReference<List<Map<String, Object>>>() {}
                );

            for (Map<String, Object> row : warn) {
                String d = norm(row.get("District"));
                WeatherDTO dto = store.get(d);
                if (dto != null) {

                    List<String> forecast = List.of(
                        decodeWarn(row.get("Day_1")),
                        decodeWarn(row.get("Day_2")),
                        decodeWarn(row.get("Day_3")),
                        decodeWarn(row.get("Day_4")),
                        decodeWarn(row.get("Day_5"))
                    );

                    dto.setForecast5Days(forecast);
                    dto.setWarningMessage(String.join(", ", forecast));
                }
            }
        } catch (Exception ignored) {}
    }

    public Collection<WeatherDTO> getAll() {
        return store.values();
    }

    // ---------------- helpers ----------------

    private String decodeNowcast(Map<String,Object> row) {
        if ("1".equals(safe(row.get("cat12")))) return "Heavy Rain";
        if ("1".equals(safe(row.get("cat4")))) return "Thunderstorm";
        if ("1".equals(safe(row.get("cat6")))) return "Lightning";
        return "Clear";
    }

    private String decodeWarn(Object code) {
        return switch (safe(code)) {
            case "2" -> "Heavy Rain";
            case "3" -> "Heavy Snow";
            case "4" -> "Thunderstorm / Lightning";
            case "5" -> "Hailstorm";
            case "6" -> "Dust Storm";
            case "9" -> "Heat Wave";
            case "12" -> "Cold Wave";
            case "15" -> "Very Heavy Rain";
            case "17" -> "Extremely Heavy Rain";
            default -> "Normal";
        };
    }

    private String mapRainCategory(String code) {
        return switch (code) {
            case "LE" -> "Large Excess";
            case "E" -> "Excess";
            case "N" -> "Normal";
            case "D" -> "Deficient";
            case "LD" -> "Large Deficient";
            case "NR" -> "No Rain";
            case "ND" -> "No Data";
            default -> "NA";
        };
    }

    private String color(Object o) {
        return "1".equals(safe(o)) ? "green" : "red";
    }

    private String norm(Object o) {
        return safe(o).replace("_"," ").replace("-"," ").toUpperCase().trim();
    }

    private String safe(Object o) {
        return o == null ? "" : o.toString().trim();
    }

    private double parse(Object o) {
        try { return Double.parseDouble(safe(o)); }
        catch (Exception e) { return 0; }
    }
}
