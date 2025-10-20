package calculator.parser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Nested
    @DisplayName("parse 메서드 테스트")
    class Parse {

        @Test
        @DisplayName("기본 구분자 쉼표, 콜론으로 분리")
        void shouldSplitByCommaAndColon_whenNoCustomDelimiter() {
            // given
            String input = "1,2:3";

            // when
            String[] result = Parser.parse(input);

            // then
            assertThat(result).containsExactly("1", "2", "3");
        }

        @Test
        @DisplayName("커스텀 구분자로 분리")
        void shouldSplitByCustomDelimiter_whenCustomDelimiterPresent() {
            // given
            String input = "//;\\n1;2;3";

            // when
            String[] result = Parser.parse(input);

            // then
            assertThat(result).containsExactly("1", "2", "3");
        }

        @Test
        @DisplayName("빈 문자열 입력 시 빈 문자열 배열 반환")
        void shouldReturnEmptyStringArray_whenInputIsEmpty() {
            // given
            String input = "";

            // when
            String[] result = Parser.parse(input);

            // then
            assertThat(result).containsExactly("");
        }
    }
}
