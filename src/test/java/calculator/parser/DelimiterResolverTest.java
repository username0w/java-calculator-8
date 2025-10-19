package calculator.parser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DelimiterResolverTest {

    @Test
    void 커스텀구분자가_없는_경우_기본_Delimiter_반환() {
        String input = "1,2,3";
        Delimiter delimiter = DelimiterResolver.resolve(input);

        assertThat(delimiter.getRegex()).contains(",").contains(":");
    }

    @Test
    void 커스텀구분자가_있는_경우_정확히_반환() {
        String input = "//;\\n1;2;3";
        Delimiter delimiter = DelimiterResolver.resolve(input);

        assertThat(delimiter.getRegex()).contains(";").contains(",").contains(":");
    }

    @Test
    void hasCustomDelimiter_메서드_테스트() {
        assertThat(DelimiterResolver.hasCustomDelimiter("//;\\n1;2;3")).isTrue();
        assertThat(DelimiterResolver.hasCustomDelimiter("1,2,3")).isFalse();
        assertThat(DelimiterResolver.hasCustomDelimiter("//\\n1;2;3")).isTrue();
    }

}