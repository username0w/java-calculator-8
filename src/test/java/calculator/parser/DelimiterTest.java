package calculator.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DelimiterTest {

    @Nested
    @DisplayName("getRegex 메서드 테스트")
    class GetRegex {

        @Test
        @DisplayName("기본 구분자가 포함된 정규식 생성")
        void shouldContainDefaultDelimiters_whenCustomDelimitersEmpty() {
            // given
            Delimiter delimiter = new Delimiter(List.of());

            // when
            String regex = delimiter.getRegex();

            // then
            assertThat(regex).contains(",").contains(":");
        }

        @Test
        @DisplayName("커스텀 구분자가 한 글자일 때 정규식 생성")
        void shouldContainCustomAndDefaultDelimiters_whenCustomDelimiterOneChar() {
            // given
            Delimiter delimiter = new Delimiter(List.of(";"));

            // when
            String regex = delimiter.getRegex();

            // then
            assertThat(regex).contains(",").contains(":").contains(";");
        }

        @Test
        @DisplayName("커스텀 구분자가 두 글자 이상일 때 예외 발생")
        void shouldThrowException_whenCustomDelimiterMoreThanOneChar() {
            // when & then
            assertThatThrownBy(() -> new Delimiter(List.of(";;")))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("커스텀 구분자는 한 개의 문자로 구성됩니다.");
        }

        @Test
        @DisplayName("커스텀 구분자가 null이면 기본 구분자만 사용")
        void shouldContainDefaultDelimiters_whenCustomDelimiterIsNull() {
            // given
            Delimiter delimiter = new Delimiter(null);

            // when
            String regex = delimiter.getRegex();

            // then
            assertThat(regex).contains(",").contains(":");
        }
    }
}
