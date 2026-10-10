package oncall.domain;

public class MonthCalendar {
    private final int month;
    private final Day startDay;

    public MonthCalendar(int month, Day startDay) {
        validateMonth(month);
        this.month = month;
        this.startDay = startDay;
    }

    public int takeLastDate() {
        return lastDateOf(month);
    }

    private int lastDateOf(int month) {
        if (month == 2) {
            return 28;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        return 31;
    }

    public Day takeDay(int date) {
        Day day = startDay;
        for (int i = 0; i < date - 1; i++) {
            day = day.next();
        }
        return day;
    }

    public int takeMonth() {
        return month;
    }

    public boolean isHoliday(int date) {
        if (takeDay(date).isWeekend() || Holiday.isHoliday(month, date)) {
            return true;
        }
        return false;
    }

    public boolean isWeekdayHoliday(int date) {
        if (!takeDay(date).isWeekend() && Holiday.isHoliday(month, date)) {
            return true;
        }
        return false;
    }

    private void validateMonth(int month) {
        boolean isInvalidMonth = !(month >= 1 && month <= 12);
        if (isInvalidMonth) {
            throw new IllegalArgumentException("[ERROR] 달은 1월부터 12월까지입니다.");
        }
    }
}