package oncall;

import oncall.ui.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        String monthAndStartDay=inputView.inputMonthAndStartDay();
        String inputWeekdayWorkers=inputView.inputWeekdayWorkers();
        String inputHolidayWorkers=inputView.inputHolidayWorkers();
    }
}
