package oncall.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class SchedulerMonth {
    private final CalendarMonth calendarMonth;
    private final ArrayDeque<Worker> weekdayWorkers;
    private final ArrayDeque<Worker> holidayWorkers;


    public SchedulerMonth(CalendarMonth calendarMonth, Workers weekdayWorkers, Workers holidayWorkers) {
        this.calendarMonth = calendarMonth;
        this.weekdayWorkers = weekdayWorkers.toQueue();
        this.holidayWorkers = holidayWorkers.toQueue();
    }


    private ArrayDeque<Worker> pickQueue(int date) {
        if (calendarMonth.isHoliday(date)) {
            return holidayWorkers;
        }
        return weekdayWorkers;
    }

    private Worker pickWorker(ArrayDeque<Worker> queue, String yesterday) {
        Worker first = queue.pollFirst();
        if (!first.takeName().equals(yesterday)) {
            queue.addLast(first);
            return first;
        }
        Worker second = queue.pollFirst();
        queue.addFirst(first);
        queue.addLast(second);
        return second;
    }

    public List<String> assign() {
        List<String> callWorkers = new ArrayList<>();
        String yesterday = "";
        for (int date = 1; date <= calendarMonth.takeLastDate(); date++) {
            Worker worker = pickWorker(pickQueue(date), yesterday);
            callWorkers.add(worker.takeName());
            yesterday = worker.takeName();
        }
        return callWorkers;
    }
}
