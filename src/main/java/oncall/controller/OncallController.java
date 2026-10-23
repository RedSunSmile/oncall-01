package oncall.controller;

import oncall.domain.*;
import oncall.ui.InputView;
import oncall.ui.OutputView;
import java.util.List;


public class OncallController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        CalendarMonth calendarMonth = readCalendarMonth();
        SchedulerMonth schedulerMonth = readValidSameWorkers(calendarMonth);
        List<String> result = schedulerMonth.assign();
        outputView.printSchedule(calendarMonth, result);
    }

    private SchedulerMonth readValidSameWorkers(CalendarMonth calendarMonth) {
        while (true) {
            try {
                Workers weekdayWorkers = readWeekdayWorkers();
                Workers holidayWorkers = readHolidayWorkers();
                validateSameWorkers(weekdayWorkers, holidayWorkers);
                return new SchedulerMonth(calendarMonth, weekdayWorkers, holidayWorkers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateSameWorkers(Workers weekdayWorkers, Workers holidayWorkers) {
        boolean isNotSame = !(weekdayWorkers.takeNames().equals(holidayWorkers.takeNames()));
        if (isNotSame) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    private Workers readHolidayWorkers() {
        String inputHolidayWorkers = inputView.inputHolidayWorkers();
        String[] splitHoliday = inputHolidayWorkers.split(",");
        Workers holidayWorkers = new Workers(List.of(splitHoliday));
        return holidayWorkers;
    }

    private Workers readWeekdayWorkers() {
        String inputWeekdayWorkers = inputView.inputWeekdayWorkers();
        String[] splitWeekday = inputWeekdayWorkers.split(",");
        Workers weekdayWorkers = new Workers(List.of(splitWeekday));
        return weekdayWorkers;
    }

    private CalendarMonth readCalendarMonth() {
        while (true) {
            try {
                String monthAndStartDay = inputView.inputMonthAndStartDay();
                String[] twoSplit = monthAndStartDay.split(",");
                validateFormat(twoSplit);
                int number = toMonth(twoSplit[0]);
                Day day = Day.from(twoSplit[1]);
                CalendarMonth calendarMonth = new CalendarMonth(number, day);
                return calendarMonth;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int toMonth(String value) {
        try {
            int number = Integer.parseInt(value);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자입력만 가능합니다.");
        }
    }


    private void validateFormat(String[] twoSplit) {
        boolean isInvalidFormat = !(twoSplit.length == 2);
        if (isInvalidFormat) {
            throw new IllegalArgumentException("[ERROR] 월과 요일은 쉼표로 구분해 입력해야 합니다.");
        }
    }
}
