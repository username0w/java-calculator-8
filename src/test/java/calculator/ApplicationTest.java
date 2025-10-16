package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ApplicationTest extends NsTest {

    @Test
    void 빈_문자열_입력() {
        assertSimpleTest(() -> {
            run(" ");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 일반_구분자_사용() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_점_사용() {
        assertSimpleTest(() -> {
            run("//.\\n12,20,23,5");
            assertThat(output()).contains("결과 : 60");
        });
    }

    @Test
    void 두자리_이상_숫자_사용() {
        assertSimpleTest(() -> {
            run("20:2,320");
            assertThat(output()).contains("결과 : 342");
        });
    }

    @Test
    void 실수_숫자_사용() {
        assertSimpleTest(() -> {
            run("2.0:2,320");
            assertThat(output()).contains("결과 : 324");
        });
    }

    @DisplayName("0 입력 시 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"//;\\n1;0;3", "0"})
    void 예외_숫자_0_입력(String input) {
        assertThatThrownBy(() -> run(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양수만 입력 가능합니다");
    }

    @Test
    void 예외_음수_입력_테스트() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("-1,2,3"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("양수만 입력 가능합니다.");
        });
    }

    @Test
    void 예외_2문자_넘는_커스텀_구분자() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> run("//;!\\n1,5:3"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("한 개의 문자로");
        });
    }

    @Test
    void 예외_커스텀_구분자_추가_지정() { // 이건 문자열 입력 오류로 처리
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> run("//;\\n1,3;12//#\\n#2"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("문자는 숫자로 변환할 수 없습니다.");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
