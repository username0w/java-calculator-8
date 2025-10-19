package calculator.parser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void 기본_구분자_쉼표_콜론으로_분리() {
        String input = "1,2:3";
        String[] result = Parser.parse(input);

        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    void 커스텀_구분자_분리() {
        String input = "//;\\n1;2;3";
        String[] result = Parser.parse(input);

        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    void 커스텀_구분자가_없는_경우_입력_그대로_분리() {
        String input = "4,5,6";
        String[] result = Parser.parse(input);

        assertThat(result).containsExactly("4", "5", "6");
    }

    @Test
    void 빈_문자열_입력_시_빈_문자열_배열_반환() {
        String input = "";
        String[] result = Parser.parse(input);

        assertThat(result).containsExactly("");
    }

}