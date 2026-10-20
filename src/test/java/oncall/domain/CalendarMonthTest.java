package oncall.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CalendarMonthTest {

    @Test
    void 휴일과_주말과_평일이_아니라면_법정공휴일이다() {
        CalendarMonth monthCalendarMonth = new CalendarMonth(10, Day.수요일);
        boolean date = monthCalendarMonth.isWeekdayHoliday(3);
        assertThat(date).isTrue();
    }

    @Test
    void 휴일은_주말이거나_일요일이다() {
        CalendarMonth monthCalendarMonth = new CalendarMonth(9, Day.토요일);
        boolean date = monthCalendarMonth.isHoliday(8);
        assertThat(date).isTrue();
    }

    @Test
    void 달은_1월부터_12월까지이다() {
        assertThatThrownBy(() -> new CalendarMonth(13, Day.금요일))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 달은 1월부터 12월까지입니다.");
    }

    @Test
    void 달_중에_2월은_28일까지만_존재한다() {
        CalendarMonth monthCalendarMonth = new CalendarMonth(2, Day.화요일);
        assertThat(monthCalendarMonth.takeLastDate()).isEqualTo(28);
    }

    @Test
    void 달_중에_4월_6월_9월_11월은_30일까지만_존재한다() {
        CalendarMonth monthCalendarMonth = new CalendarMonth(6, Day.화요일);
        assertThat(monthCalendarMonth.takeLastDate()).isEqualTo(30);
    }
}
