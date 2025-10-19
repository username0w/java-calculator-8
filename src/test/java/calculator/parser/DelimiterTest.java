package calculator.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class DelimiterTest {

    @Test
    void 기본구분자_포함된_정규식_생성() {
        Delimiter delimiter = new Delimiter(List.of());
        String regex = delimiter.getRegex();

        assertThat(regex).contains(",").contains(":");
    }

    @Test
    void 커스텀구분자_한글자일때_정규식_생성() {
        Delimiter delimiter = new Delimiter(List.of(";"));
        String regex = delimiter.getRegex();

        assertThat(regex).contains(",").contains(":").contains(";");
    }

    @Test
    void 커스텀구분자가_두글자_이상일때_예외_발생() {
        assertThatThrownBy(() -> new Delimiter(List.of(";;")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자는 한 개의 문자로 구성됩니다.");
    }

    @Test
    void 커스텀구분자_null이면_기본값만_사용() {
        Delimiter delimiter = new Delimiter(null);
        String regex = delimiter.getRegex();

        assertThat(regex).contains(",").contains(":");
    }

}