package calculator.model;

import calculator.exception.ErrorMessage;
import java.util.List;

public class Numbers {

    private final List<Double> numbers;

    private Numbers(List<Double> numbers) {
        this.numbers = numbers;
    }

    public static Numbers create(List<Double> numbers) {
        validateNumbers(numbers);
        return new Numbers(numbers);
    }

    private static void validateNumbers(List<Double> numbers) {
        for (Double number : numbers) {
            if (number <= 0.0) {
                throw new IllegalArgumentException(ErrorMessage.NON_POSITIVE);
            }
        }
    }

    public double sum() {
        return numbers.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    @Override
    public String toString() {
        return "Numbers{" + "values=" + numbers + '}';
    }
}
