package calculator.model;

import java.util.List;

public class Numbers {

    private final List<Integer> numbers;

    private Numbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Numbers create(List<Integer> numbers) {
        validateNumbers(numbers);
        return new Numbers(numbers);
    }

    private static void validateNumbers(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number <= 0) {
                throw new IllegalArgumentException("양수만 입력 가능합니다.");
            }
        }
    }

    @Override
    public String toString() {
        return "Numbers{" + "values=" + numbers + '}';
    }
}
