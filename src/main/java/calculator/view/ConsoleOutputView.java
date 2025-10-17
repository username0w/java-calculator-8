package calculator.view;

public class ConsoleOutputView implements OutputView {

    @Override
    public void printResult(double result) {
        if (result % 1 == 0 && result <= Long.MAX_VALUE && result >= Long.MIN_VALUE) {
            System.out.println("결과 : " + (long) result);
        } else {
            System.out.println("결과 : " + result);
        }
    }
}
