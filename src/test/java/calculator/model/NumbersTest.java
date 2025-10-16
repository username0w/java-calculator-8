package calculator.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NumbersTest {

    @Nested
    @DisplayName("create")
    class Create {

        @Test
        @DisplayName("유효한 숫자로 객체 생성")
        void shouldCreate_whenValidPositiveNumbers() {
            Numbers numbers = Numbers.create(List.of(1.0, 2.0, 3.0));
            assertThat(numbers).isNotNull();
        }

        @Test
        @DisplayName("음수 포함 시 예외 발생")
        void shouldThrow_whenNegativeNumberIncluded() {
            assertThatThrownBy(() -> Numbers.create(List.of(1.0, -2.0, 3.0)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("양수만 입력 가능합니다.");
        }

        @Test
        @DisplayName("0 포함 시 예외 발생")
        void shouldThrow_whenZeroIncluded() {
            assertThatThrownBy(() -> Numbers.create(List.of(0.0, 1.2)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("양수만 입력 가능합니다.");
        }
    }

    @Nested
    @DisplayName("sum")
    class Sum {

        @Test
        @DisplayName("유효한 숫자로 결과 생성")
        void shouldSucceed_whenValidPositiveNumbers() {
            Numbers numbers = Numbers.create(List.of(1.0, 2.0, 3.0));
            assertThat(numbers.sum()).isEqualTo(6.0);
        }

        @Test
        @DisplayName("빈 리스트로 객체 생성")
        void shouldSucceed_whenEmptyNumbers() {
            Numbers numbers = Numbers.create(List.of());
            assertThat(numbers.sum()).isEqualTo(0.0);
        }
    }

    @Test
    void toString_shouldReturnExpectedFormat() {
        Numbers numbers = Numbers.create(List.of(1.0, 2.0));
        assertThat(numbers.toString()).isEqualTo("Numbers{values=[1.0, 2.0]}");
    }

}