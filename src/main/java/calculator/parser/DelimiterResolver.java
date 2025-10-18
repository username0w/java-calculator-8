package calculator.parser;

import java.util.List;

public class DelimiterResolver {

    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";

    public static Delimiter resolve(String input) {

        if (!hasCustomDelimiter(input)) {
            return new Delimiter(null); // 기본 구분자
        }

        // 커스텀 문자 추출하기
        String customDelimiter = extractCustomDelimiter(input);
        return new Delimiter(List.of(customDelimiter));
    }

    public static String extractNumberPart(String input) {
        if (!hasCustomDelimiter(input)) {
            return input;
        }
        int start = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
        return input.substring(start + CUSTOM_DELIMITER_SUFFIX.length());
    }

    private static boolean hasCustomDelimiter(String input) {
        return input.startsWith(CUSTOM_DELIMITER_PREFIX) && input.contains(CUSTOM_DELIMITER_SUFFIX);
    }

    private static String extractCustomDelimiter(String input) {
        int start = CUSTOM_DELIMITER_PREFIX.length();
        int end = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
        // start, end 같을 경우 Delimiter 객체 생성 시 커스텀 글자 수 확인에서 예외 던진다.
        return input.substring(start, end);
    }
}
