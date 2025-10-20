package calculator.view;

public class ConsoleOutputView implements OutputView {

    @Override
    public void printResult(double result) {
        System.out.println(Messages.RESULT_PREFIX + formatResult(result));
    }

    private String formatResult(double result) {
        if (result % 1 == 0 && result <= Long.MAX_VALUE && result >= Long.MIN_VALUE) {
            return String.valueOf((long) result);
        }
        return String.valueOf(result);
    }
}
