package oncall;

import oncall.ui.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        inputView.inputMonthAndStartDay();
        inputView.inputWeekdayWorkers();
        inputView.inputHolidayWorkers();
    }
}
