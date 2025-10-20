package calculator.parser;

import java.util.List;

public class DelimiterResolver {

    public static Delimiter resolve(String input) {
        if (!hasCustomDelimiter(input)) {
            return new Delimiter(List.of());
        }
        String customDelimiter = extractCustomDelimiter(input);
        return new Delimiter(List.of(customDelimiter));
    }

    static boolean hasCustomDelimiter(String input) {
        return input.startsWith(DelimiterFormat.CUSTOM_DELIMITER_PREFIX) && input.contains(
                DelimiterFormat.CUSTOM_DELIMITER_SUFFIX);
    }

    private static String extractCustomDelimiter(String input) {
        int start = DelimiterFormat.CUSTOM_DELIMITER_PREFIX.length();
        int end = input.indexOf(DelimiterFormat.CUSTOM_DELIMITER_SUFFIX);
        return input.substring(start, end);
    }
}
