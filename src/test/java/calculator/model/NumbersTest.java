package calculator.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.exception.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NumbersTest {

    @Nested
    @DisplayName("create 메서드 테스트")
    class Create {

        @Test
        @DisplayName("유효한 양수 리스트로 Numbers 객체 생성")
        void shouldCreate_whenValidPositiveNumbers() {
            // given
            List<Double> input = List.of(1.0, 2.0, 3.0);

            // when
            Numbers numbers = Numbers.create(input);

            // then
            assertThat(numbers).isNotNull();
        }

        @Test
        @DisplayName("음수 포함 시 IllegalArgumentException 발생")
        void shouldThrow_whenNegativeNumberIncluded() {
            // given
            List<Double> input = List.of(1.0, -2.0, 3.0);

            // when & then
            assertThatThrownBy(() -> Numbers.create(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NON_POSITIVE);
        }

        @Test
        @DisplayName("0 포함 시 IllegalArgumentException 발생")
        void shouldThrow_whenZeroIncluded() {
            // given
            List<Double> input = List.of(0.0, 1.2);

            // when & then
            assertThatThrownBy(() -> Numbers.create(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NON_POSITIVE);
        }
    }

    @Nested
    @DisplayName("sum 메서드 테스트")
    class Sum {

        @Test
        @DisplayName("양수 리스트에 대해 합산 결과 반환")
        void shouldReturnSum_whenValidPositiveNumbers() {
            // given
            Numbers numbers = Numbers.create(List.of(1.0, 2.0, 3.0));

            // when
            double result = numbers.sum();

            // then
            assertThat(result).isEqualTo(6.0);
        }

        @Test
        @DisplayName("빈 리스트일 경우 합은 0 반환")
        void shouldReturnZero_whenNumbersListIsEmpty() {
            // given
            Numbers numbers = Numbers.create(List.of());

            // when
            double result = numbers.sum();

            // then
            assertThat(result).isEqualTo(0.0);
        }
    }

}
