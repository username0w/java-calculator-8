package calculator.parser;

public class Parser {

    public static String[] parse(String input) {
        Delimiter delimiter = DelimiterResolver.resolve(input);
        String numberPart = extractNumberPart(input);
        return numberPart.split(delimiter.getRegex());
    }

    private static String extractNumberPart(String input) {
        if (!DelimiterResolver.hasCustomDelimiter(input)) {
            return input;
        }
        int start = input.indexOf(DelimiterFormat.CUSTOM_DELIMITER_SUFFIX);
        return input.substring(start + DelimiterFormat.CUSTOM_DELIMITER_SUFFIX.length());
    }
}
