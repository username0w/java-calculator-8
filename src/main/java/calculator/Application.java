package calculator;

import calculator.parser.Parser;
import calculator.view.InputView;
import java.util.Arrays;

public class Application {

  public static void main(String[] args) {
    int output = 0;

    String input = InputView.readInput();
    if (input.equals("0")) {
      System.out.println("결과 : " + output);
      return;
    }

    String[] parsedInput = Parser.parse(input);
    System.out.println("parsedInput : " + Arrays.toString(parsedInput));

    System.out.println("결과 : " + output);
  }
}
