package de.impact.utils;

public class PrefixUtils {

    private PrefixUtils() {
        throw new IllegalStateException("Utility Class");
    }

    private static String prefix = ">";

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        PrefixUtils.prefix = prefix;
    }

}
