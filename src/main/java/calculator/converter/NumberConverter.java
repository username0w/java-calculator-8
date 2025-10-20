package calculator.converter;

import calculator.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

public class NumberConverter {

    public static List<Double> toDoubles(String[] input) {
        List<Double> result = new ArrayList<>();
        for (String s : input) {
            if (s.isEmpty()) {
                s = "0";
            }
            try {
                result.add(Double.parseDouble(s));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(ErrorMessage.NOT_A_NUMBER);
            }
        }
        return result;
    }
}
