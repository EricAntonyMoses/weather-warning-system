package in.nic.meghalaya.weather.util;

public class TextNormalizer {

    public static String normalize(String s) {
        if (s == null) return "";
        return s.toLowerCase()
                .replaceAll("[^a-z]", "");
    }
}
