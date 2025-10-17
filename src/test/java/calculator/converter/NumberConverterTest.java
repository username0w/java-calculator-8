package calculator.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class NumberConverterTest {

    @Test
    void shouldSucceed_whenValidPositiveString() {
        List<Double> convertedNumbers = NumberConverter.toDoubles(new String[]{"1", "2.0", "3"});
        assertThat(convertedNumbers).isEqualTo(List.of(1.0, 2.0, 3.0));
    }

    @Test
    void shouldSucceed_whenValidStringWithZero() {
        List<Double> convertedNumbers = NumberConverter.toDoubles(new String[]{"0", "0.0", "2"});
        assertThat(convertedNumbers).isEqualTo(List.of(0.0, 0.0, 2.0));
    }

    @Test
    void shouldSucceed_whenValidNegativeString() {
        List<Double> convertedNumbers = NumberConverter.toDoubles(new String[]{"-1", "0.0", "-2"});
        assertThat(convertedNumbers).isEqualTo(List.of(-1.0, 0.0, -2.0));
    }

    @Test
    void shouldSucceed_whenEmptyString() {
        List<Double> convertedNumbers = NumberConverter.toDoubles(new String[]{"-1", "0.0", ""});
        assertThat(convertedNumbers).isEqualTo(List.of(-1.0, 0.0, 0.0));
    }

    @Test
    void shouldThrow_whenInvalidString() {
        assertThatThrownBy(() -> NumberConverter.toDoubles(new String[]{"-1", "a"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("문자는 숫자로 변환할 수 없습니다.");
    }

    @Test
    void shouldThrow_whenBlankString() {
        assertThatThrownBy(() -> NumberConverter.toDoubles(new String[]{" ", "a"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("문자는 숫자로 변환할 수 없습니다.");
    }
}