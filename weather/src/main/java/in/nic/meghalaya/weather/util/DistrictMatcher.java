package in.nic.meghalaya.weather.util;

import java.util.*;

public class DistrictMatcher {

    public static final List<String> MEGHALAYA_DISTRICTS = List.of(
        "East Khasi Hills",
        "West Khasi Hills",
        "South West Khasi Hills",
        "Ri Bhoi",
        "East Jaintia Hills",
        "West Jaintia Hills",
        "East Garo Hills",
        "West Garo Hills",
        "South Garo Hills",
        "North Garo Hills",
        "South West Garo Hills",
        "Eastern West Khasi Hills"
    );

    private static final Map<String, String> NORMALIZED = new HashMap<>();

    static {
        for (String d : MEGHALAYA_DISTRICTS) {
            NORMALIZED.put(TextNormalizer.normalize(d), d);
        }
    }

    public static String match(String input) {
        String norm = TextNormalizer.normalize(input);
        for (String key : NORMALIZED.keySet()) {
            if (norm.contains(key) || key.contains(norm)) {
                return NORMALIZED.get(key);
            }
        }
        return null;
    }
}
