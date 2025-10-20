package calculator.parser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DelimiterResolverTest {

    @Nested
    @DisplayName("resolve 메서드 테스트")
    class Resolve {

        @Test
        @DisplayName("커스텀 구분자가 없으면 기본 구분자 반환")
        void shouldReturnDefaultDelimiter_whenNoCustomDelimiter() {
            // given
            String input = "1,2,3";

            // when
            Delimiter delimiter = DelimiterResolver.resolve(input);

            // then
            assertThat(delimiter.getRegex()).contains(",").contains(":");
        }

        @Test
        @DisplayName("커스텀 구분자가 있으면 정확히 포함된 구분자 반환")
        void shouldReturnCustomDelimiter_whenCustomDelimiterPresent() {
            // given
            String input = "//;\\n1;2;3";

            // when
            Delimiter delimiter = DelimiterResolver.resolve(input);

            // then
            assertThat(delimiter.getRegex()).contains(";").contains(",").contains(":");
        }
    }

    @Nested
    @DisplayName("hasCustomDelimiter 메서드 테스트")
    class HasCustomDelimiter {

        @Test
        @DisplayName("커스텀 구분자가 포함된 경우 true 반환")
        void shouldReturnTrue_whenCustomDelimiterPresent() {
            // when & then
            assertThat(DelimiterResolver.hasCustomDelimiter("//;\\n1;2;3")).isTrue();
            assertThat(DelimiterResolver.hasCustomDelimiter("//\\n1;2;3")).isTrue();
        }

        @Test
        @DisplayName("커스텀 구분자가 없는 경우 false 반환")
        void shouldReturnFalse_whenNoCustomDelimiter() {
            // when & then
            assertThat(DelimiterResolver.hasCustomDelimiter("1,2,3")).isFalse();
        }
    }
}
