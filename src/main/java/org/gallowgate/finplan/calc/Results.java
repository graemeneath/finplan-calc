package org.gallowgate.finplan.calc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Results {
    private final Events events;
    private int currentRow = 0;
    private LocalDate currentDate = null;
    private int maxRows = 30;

    // constructor
    public Results(Events events) {
        this.events = events;
        reset();
    }

    public List<Double> getNextRow(double withdrawal) {
        List<Double> result = new ArrayList<>();
        for (Event event : events.getEvents(EventType.INVESTMENT)) {
            if (event.isActive(currentDate)) {
                withdrawal = event.decreaseCurrentAmount(withdrawal);
                result.add(event.getCurrentAmount());
                event.applyInvestment();
            } else {
                result.add(0.0);
            }
        }

        currentRow += 1;
        currentDate  = currentDate.plusYears(1);
        return result;
    }

    public boolean isDone() {
        if (currentRow >= maxRows) {
            return true;
        }

        for (Event event : events.getEvents()) {
            if (event.getCurrentAmount() > 0) {
                return false;
            }
        }
        return true;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
        reset();
    }

    private void reset() {
        for (Event event : events.getEvents()) {
            event.reset();
        }

        currentRow = 0;
        currentDate = events.getEarliestDate();
    }
}
