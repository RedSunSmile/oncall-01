package oncall.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WorkersTest {

    @Test
    void 근무_가능한_최소_인원이_4명이면_예외처리한다() {
        assertThatThrownBy(() -> new Workers(List.of("준팍", "도밥", "고니", "수아")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 근무 가능한 인원은 최소 5명 이상 최대 35명 이하입니다.");
    }

    @Test
    void 근무_가능한_최소_인원이_36명이면_예외처리한다() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < 36; i++) {
            names.add("근무" + i);
        }
        assertThatThrownBy(() -> new Workers(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 근무 가능한 인원은 최소 5명 이상 최대 35명 이하입니다.");
    }

    @Test
    void 근무자의_닉네임이_중복되면_예외처리한다() {
        assertThatThrownBy(() -> new Workers(List.of("준팍", "도밥", "고니", "수아", "수아")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
    }
}
