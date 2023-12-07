package org.gallowgate.finplan.calc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Results {
    private final List<Event> events;
    private int currentRow = 0;
    private LocalDate currentDate = null;
    private int maxRows = 30;

    // constructor
    public Results(List<Event> events) {
        this.events = events;
        reset();
    }

    public List<Double> getNextRow(double withdrawal) {
        List<Double> result = new ArrayList<>();
        for (Event event : events) {
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

        for (Event event : events) {
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
        for (Event event : events) {
            event.reset();
        }

        currentRow = 0;
        currentDate = getEarliestDate();
    }

    private LocalDate getEarliestDate() {
        LocalDate earliest = events.get(0).getDate();

        for (Event e : events) {
            if (e.getDate().isBefore(earliest)) {
                earliest = e.getDate();
            }
        }
        return earliest.withDayOfYear(1);
    }

    public List<String> getHeaders() {
        List<String> headers = new ArrayList<>();
        headers.add("Date");
        for (Event e : events) {
            headers.add(e.getName());
        }
        return headers;
    }
}
