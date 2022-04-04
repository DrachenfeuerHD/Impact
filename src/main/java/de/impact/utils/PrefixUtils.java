package de.impact.utils;

public class PrefixUtils {

    private PrefixUtils() {
        throw new IllegalStateException("Utility Class");
    }

    // TODO: Add your custom prefix, if you want to (only used for login)
    private static String prefix = ">";

    public static String getPrefix() {
        return prefix;
    }

}
