package calculator.converter;

import java.util.ArrayList;
import java.util.List;

public class NumberConverter {

    public static List<Double> toDoubles(String[] input) {
        List<Double> result = new ArrayList<>();
        for (String s : input) {
            try {
                result.add(Double.parseDouble(s));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("문자는 숫자로 변환할 수 없습니다.");
            }
        }
        return result;
    }
}
