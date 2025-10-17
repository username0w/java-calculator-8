package calculator.controller;

import calculator.converter.NumberConverter;
import calculator.model.Numbers;
import calculator.parser.Parser;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.util.List;

public class CalculatorController {

    private final InputView inputView;
    private final OutputView outputView;

    public CalculatorController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        double output = 0;

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
