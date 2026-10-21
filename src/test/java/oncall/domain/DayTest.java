package oncall.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DayTest {
    @Test
    void 없는_요일은_예외처리한다() {
        assertThatThrownBy(() -> Day.from("수금"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 존재하지 않는 요일입니다.");
    }

    @Test
    void 일요일_다음은_월요일이다() {
        assertThat(Day.일요일.next()).isEqualTo(Day.월요일);
    }
}
