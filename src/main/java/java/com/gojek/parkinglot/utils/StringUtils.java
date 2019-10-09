package java.com.gojek.parkinglot.utils;

public final class StringUtils {

    private StringUtils() {
    }

    public static boolean isBlank(final String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(final String str) {
        return !isBlank(str);
    }

    public static void validateNotBlank(final String str) {
        if (isBlank(str)) {
            throw new IllegalArgumentException("string is blank");
        }
    }
}
