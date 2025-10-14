package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

  public static String readInput() {
    System.out.println("덧셈할 문자열을 입력해 주세요.");
    String input = Console.readLine();
    checkInputIsNull(input);
    if (input.isBlank()) {
      return "0";
    }
    return input;
  }

  private static void checkInputIsNull(String input) {
    if (input == null) {
      throw new IllegalArgumentException("입력값이 null 입니다.");
    }
  }

}
