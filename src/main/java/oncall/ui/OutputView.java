package oncall.ui;

import oncall.domain.CalendarMonth;
import java.util.List;

public class OutputView {

    public void printSchedule(CalendarMonth calendarMonth, List<String> result) {
        for (int date = 1; date <= calendarMonth.takeLastDate(); date++) {
            int month = calendarMonth.takeMonth();
            String dayName = calendarMonth.takeDay(date).takeName();
            String worker = result.get(date - 1);
            String holidayMark = "";
            if (calendarMonth.isWeekdayHoliday(date)) {
                holidayMark = "(휴일)";
            }
            System.out.println(month + "월 " + date + "일 " + dayName + holidayMark + " " + worker);
        }
    }
}
