package calculator.view;

import calculator.exception.ErrorMessage;
import camp.nextstep.edu.missionutils.Console;

public class ConsoleInputView implements InputView {

    @Override
    public String readInput() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();
        checkInputIsNull(input);
        return input;
    }

    private static void checkInputIsNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_NULL);
        }
    }

}
