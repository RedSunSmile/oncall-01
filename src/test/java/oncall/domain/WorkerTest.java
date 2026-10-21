package oncall.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WorkerTest {
    @Test
    void 근무자의_닉네임은_최대5자이다() {
        Worker worker = new Worker("솔로스타다");
        assertThat(worker.takeName().length()).isEqualTo(5);
    }

    @Test
    void 근무자의_닉네임이_6자이상이면_예외처리한다() {
        assertThatThrownBy(() -> new Worker("솔로스타라네"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 근무자 닉네임은 최대 5자입니다.");
    }
}
