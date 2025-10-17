package calculator.controller;

import calculator.serivce.CalculatorService;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CalculatorService calculatorService;

    public CalculatorController(InputView inputView, OutputView outputView, CalculatorService calculatorService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.calculatorService = calculatorService;
    }

    public void run() {
        String input = inputView.readInput();
        double result = calculatorService.calculate(input);
        outputView.printResult(result);
    }
}
