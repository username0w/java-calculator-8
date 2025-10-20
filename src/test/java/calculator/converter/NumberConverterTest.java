package calculator.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.exception.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NumberConverterTest {

    @Nested
    @DisplayName("toDoubles 메서드 테스트")
    class ToDoubles {

        @Test
        @DisplayName("유효한 양수 문자열 배열을 변환한다")
        void shouldSucceed_whenValidPositiveString() {
            // given
            String[] input = {"1", "2.0", "3"};

            // when
            List<Double> convertedNumbers = NumberConverter.toDoubles(input);

            // then
            assertThat(convertedNumbers).isEqualTo(List.of(1.0, 2.0, 3.0));
        }

        @Test
        @DisplayName("0을 포함한 유효한 문자열 배열을 변환한다")
        void shouldSucceed_whenValidStringWithZero() {
            // given
            String[] input = {"0", "0.0", "2"};

            // when
            List<Double> convertedNumbers = NumberConverter.toDoubles(input);

            // then
            assertThat(convertedNumbers).isEqualTo(List.of(0.0, 0.0, 2.0));
        }

        @Test
        @DisplayName("음수를 포함한 유효한 문자열 배열을 변환한다")
        void shouldSucceed_whenValidNegativeString() {
            // given
            String[] input = {"-1", "0.0", "-2"};

            // when
            List<Double> convertedNumbers = NumberConverter.toDoubles(input);

            // then
            assertThat(convertedNumbers).isEqualTo(List.of(-1.0, 0.0, -2.0));
        }

        @Test
        @DisplayName("빈 문자열이 포함된 경우 0.0으로 변환한다")
        void shouldSucceed_whenEmptyString() {
            // given
            String[] input = {"-1", "0.0", ""};

            // when
            List<Double> convertedNumbers = NumberConverter.toDoubles(input);

            // then
            assertThat(convertedNumbers).isEqualTo(List.of(-1.0, 0.0, 0.0));
        }

        @Test
        @DisplayName("숫자로 변환 불가능한 문자가 포함되면 예외를 던진다")
        void shouldThrow_whenInvalidString() {
            // given
            String[] input = {"-1", "a"};

            // when & then
            assertThatThrownBy(() -> NumberConverter.toDoubles(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NOT_A_NUMBER);
        }

        @Test
        @DisplayName("공백 문자열이 포함되면 예외를 던진다")
        void shouldThrow_whenBlankString() {
            // given
            String[] input = {" ", "a"};

            // when & then
            assertThatThrownBy(() -> NumberConverter.toDoubles(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NOT_A_NUMBER);
        }
    }
}
