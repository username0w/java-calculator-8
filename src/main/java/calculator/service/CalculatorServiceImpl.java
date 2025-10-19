package calculator.service;

import calculator.converter.NumberConverter;
import calculator.model.Numbers;
import calculator.parser.Parser;
import java.util.List;

public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public double calculate(String input) {
        if (input.isEmpty()) {
            return 0;
        }

        String[] parsedInput = Parser.parse(input);
        List<Double> numbers = NumberConverter.toDoubles(parsedInput);
        Numbers validNumbers = Numbers.create(numbers);
        return validNumbers.sum();
    }
}
