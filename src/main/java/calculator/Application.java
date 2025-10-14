package calculator;

import calculator.view.InputView;

public class Application {

  public static void main(String[] args) {
    int output = 0;

    String input = InputView.readInput();
    if (input.equals("0")) {
      System.out.println("결과 : " + output);
      return;
    }

    System.out.println("결과 : " + output);
  }
}
