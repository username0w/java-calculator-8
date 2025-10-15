package calculator;

import calculator.converter.NumberConverter;
import calculator.parser.Parser;
import calculator.view.InputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        int output = 0;

        String input = InputView.readInput();
        if (input.equals("0")) {
            System.out.println("결과 : " + output);
            return;
        }

        String[] parsedInput = Parser.parse(input);

        List<Integer> numbers = NumberConverter.toIntegers(parsedInput);

        System.out.println("결과 : " + output);
    }
}
