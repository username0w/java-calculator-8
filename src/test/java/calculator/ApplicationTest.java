package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ApplicationTest extends NsTest {

    @Nested
    @DisplayName("정상 입력 테스트")
    class ValidInputTests {

        @Test
        @DisplayName("입력이 빈 문자열인 경우 0을 반환한다")
        void shouldReturnZero_whenInputIsEmptyString() {
            // given
            String input = ",,";

            // when & then
            assertSimpleTest(() -> {
                run(input);
                assertThat(output()).contains("결과 : 0");
            });
        }

        @Test
        @DisplayName("일반 구분자(, :)를 사용한 경우 합을 반환한다")
        void shouldReturnSum_whenUsingDefaultDelimiters() {
            // given
            String input = "1,2:3";

            // when & then
            assertSimpleTest(() -> {
                run(input);
                assertThat(output()).contains("결과 : 6");
            });
        }

        @Test
        @DisplayName("커스텀 구분자(;)를 사용한 경우 합을 반환한다")
        void shouldReturnSum_whenUsingCustomDelimiter() {
            // given
            String input = "//;\\n1;2;3";

            // when & then
            assertSimpleTest(() -> {
                run(input);
                assertThat(output()).contains("결과 : 6");
            });
        }

        @Test
        @DisplayName("커스텀 구분자(.)를 사용한 경우 합을 반환한다")
        void shouldReturnSum_whenUsingDotAsCustomDelimiter() {
            // given
            String input = "//.\\n12,20,23,5";

            // when & then
            assertSimpleTest(() -> {
                run(input);
                assertThat(output()).contains("결과 : 60");
            });
        }

        @Test
        @DisplayName("두 자리 이상 숫자들을 합산한다")
        void shouldReturnSum_whenNumbersHaveMultipleDigits() {
            // given
            String input = "20:2,320";

            // when & then
            assertSimpleTest(() -> {
                run(input);
                assertThat(output()).contains("결과 : 342");
            });
        }

        @Test
        @DisplayName("실수 숫자들을 합산한다")
        void shouldReturnSum_whenUsingDecimalNumbers() {
            // given
            String input = "2.0:2,320";

            // when & then
            assertSimpleTest(() -> {
                run(input);
                assertThat(output()).contains("결과 : 324");
            });
        }
    }

    @Nested
    @DisplayName("예외 상황 테스트")
    class ExceptionTests {

        @DisplayName("0 입력 시 예외가 발생한다")
        @ParameterizedTest
        @ValueSource(strings = {"//;\\n1;0;3", "0"})
        void shouldThrowException_whenZeroIsInput(String input) {
            assertThatThrownBy(() -> run(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("양수만 입력 가능합니다");
        }

        @Test
        @DisplayName("음수 입력 시 예외가 발생한다")
        void shouldThrowException_whenNegativeNumberInput() {
            assertSimpleTest(() -> {
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("양수만 입력 가능합니다.");
            });
        }

        @Test
        @DisplayName("두 문자 이상 커스텀 구분자 지정 시 예외가 발생한다")
        void shouldThrowException_whenCustomDelimiterMoreThanOneChar() {
            assertSimpleTest(() -> {
                assertThatThrownBy(() -> run("//;!\\n1,5:3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("한 개의 문자로");
            });
        }

        @Test
        @DisplayName("커스텀 구분자를 추가 지정하면 예외가 발생한다")
        void shouldThrowException_whenAdditionalCustomDelimiterSpecified() {
            assertSimpleTest(() -> {
                assertThatThrownBy(() -> run("//;\\n1,3;12//#\\n#2"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("문자는 숫자로 변환할 수 없습니다.");
            });
        }

        @Test
        @DisplayName("공백 문자가 입력되면 예외가 발생한다")
        void shouldThrowException_whenInputContainsWhitespace() {
            assertSimpleTest(() -> {
                assertThatThrownBy(() -> run("3, ,12"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("문자는 숫자로 변환할 수 없습니다.");
            });
        }
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
