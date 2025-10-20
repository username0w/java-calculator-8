package calculator.exception;

public class ErrorMessage {

    public static final String INPUT_NULL = "입력값이 null 입니다.";
    public static final String INVALID_DELIMITER = "커스텀 구분자는 한 개의 문자로 구성됩니다.";
    public static final String NOT_A_NUMBER = "문자는 숫자로 변환할 수 없습니다.";
    public static final String NON_POSITIVE = "양수만 입력 가능합니다.";

    private ErrorMessage() {
    }
}
