package in.nic.meghalaya.weather.util;

import java.util.Map;

public class ImdCodeMapper {

    // NOWCAST categories
    public static final Map<Integer, String> NOWCAST_MAP = Map.ofEntries(
        Map.entry(1, "No Weather"),
        Map.entry(2, "Light Rain"),
        Map.entry(7, "Moderate Rain"),
        Map.entry(12, "Heavy Rain"),
        Map.entry(14, "Severe Thunderstorm"),
        Map.entry(15, "Very Severe Thunderstorm"),
        Map.entry(17, "Thunderstorm with Hail"),
        Map.entry(19, "High Lightning Probability")
    );

    // RAINFALL categories
    public static final Map<String, String> RAIN_MAP = Map.of(
        "LE", "Large Excess",
        "E", "Excess",
        "N", "Normal",
        "D", "Deficient",
        "LD", "Large Deficient",
        "NR", "No Rain",
        "ND", "No Data"
    );

    // WARNINGS
    public static final Map<Integer, String> WARNING_MAP = Map.ofEntries(
        Map.entry(1, "No Warning"),
        Map.entry(2, "Heavy Rain"),
        Map.entry(4, "Thunderstorm & Lightning"),
        Map.entry(5, "Hailstorm"),
        Map.entry(15, "Very Heavy Rain"),
        Map.entry(17, "Extremely Heavy Rain")
    );
}
