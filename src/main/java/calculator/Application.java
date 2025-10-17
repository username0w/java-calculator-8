package calculator;

import calculator.converter.NumberConverter;
import calculator.model.Numbers;
import calculator.parser.Parser;
import calculator.view.ConsoleInputView;
import calculator.view.ConsoleOutputView;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        double output = 0;

        InputView inputView = new ConsoleInputView();
        OutputView outputView = new ConsoleOutputView();
        String input = inputView.readInput();
        if (input.isEmpty()) {
            outputView.printResult(output);
            return;
        }

        String[] parsedInput = Parser.parse(input);

        List<Double> numbers = NumberConverter.toDoubles(parsedInput);
        Numbers validNumbers = Numbers.create(numbers);
        output = validNumbers.sum();

        outputView.printResult(output);
    }
}
