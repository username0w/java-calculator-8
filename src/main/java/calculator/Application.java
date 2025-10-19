package calculator;

import calculator.controller.CalculatorController;
import calculator.service.CalculatorService;
import calculator.service.CalculatorServiceImpl;
import calculator.view.ConsoleInputView;
import calculator.view.ConsoleOutputView;
import calculator.view.InputView;
import calculator.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new ConsoleInputView();
        OutputView outputView = new ConsoleOutputView();
        CalculatorService calculatorService = new CalculatorServiceImpl();

        CalculatorController calculatorController = new CalculatorController(inputView, outputView, calculatorService);
        calculatorController.run();
    }
}
