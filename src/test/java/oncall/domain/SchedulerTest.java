package oncall.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SchedulerTest {

    @Test
    void 평일과_다음날_휴일도_연달아_같은_사람을_다른_사람으로_교체한다() {

        Workers workers = new Workers(List.of("준팍", "도밥", "고니", "수아", "루루", "글로", "솔로스타", "우코", "슬링키", "참새", "도리"));
        Workers holidayWorkers = new Workers(List.of("수아", "루루", "글로", "솔로스타", "우코", "슬링키", "참새", "도리", "준팍", "도밥", "고니"));
        MonthCalendar monthCalendar = new MonthCalendar(5, Day.월요일);
        Scheduler scheduler = new Scheduler(monthCalendar, workers, holidayWorkers);
        List<String> result=scheduler.assign();

        assertThat(result.get(4)).isEqualTo( "루루");
        assertThat(result.get(5)).isEqualTo( "수아");
    }

    @Test
    void 법정공휴일과_다음날_평일_연달아_같은_사람을_다른_사람으로_교체한다() {

        Workers workers = new Workers(List.of("준팍", "도밥","수아", "루루", "글로", "솔로스타", "우코", "슬링키", "참새", "도리","고니"));
        Workers holidayWorkers = new Workers(List.of("수아", "루루", "글로", "솔로스타", "우코", "슬링키", "참새", "도리", "준팍", "도밥", "고니"));
        MonthCalendar monthCalendar = new MonthCalendar(10, Day.월요일);
        Scheduler scheduler = new Scheduler(monthCalendar, workers, holidayWorkers);
        List<String> result=scheduler.assign();

        assertThat(result.get(3)).isEqualTo( "루루");
        assertThat(result.get(4)).isEqualTo( "수아");
    }
}
