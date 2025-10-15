package calculator.parser;

public class Parser {

    private static final String DEFAULT_DELIMITER = ",|:";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";

    public static String[] parse(String input) {
        // 커스텀 구분자 없는 경우
        if (!hasCustomDelimiter(input)) {
            return input.split(DEFAULT_DELIMITER); // 기본 구분자
        }
        // 커스텀 문자 추출하기
        String customDelimiter = extractCustomDelimiter(input);
        // 검증하기
        validateCustomDelimiter(customDelimiter);
        // 숫자 부분 받기
        String numberPart = extractNumberPart(input);
        // 분리하기
        String delimiters = DEFAULT_DELIMITER + "|" + customDelimiter;
        return numberPart.split(delimiters);
    }

    private static boolean hasCustomDelimiter(String input) {
        // 시작이 틀린 경우
        if (!input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            return false;
        }
        // 종료가 없는 경우
        int delimiterEndIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
        if (delimiterEndIndex == -1) {
            return false;
        }
        return true;
    }

    private static String extractCustomDelimiter(String input) {
        int start = CUSTOM_DELIMITER_PREFIX.length();
        int end = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
        return input.substring(start, end);
    }

    private static void validateCustomDelimiter(String delimiter) {
        // 커스텀 문자 크기가 1보다 큰경우
        if (delimiter.length() > 1) {
            throw new IllegalArgumentException("커스텀 구분자는 한 개의 문자로 이뤄져야합니다.");
        }
    }

    private static String extractNumberPart(String input) {
        int start = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
        return input.substring(start + CUSTOM_DELIMITER_SUFFIX.length());
    }


}
