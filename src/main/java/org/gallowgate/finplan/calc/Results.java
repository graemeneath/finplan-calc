package org.gallowgate.finplan.calc;

import java.util.ArrayList;
import java.util.List;

public class Results {
    List<Event> events;
    int currentRow = 0;
    int maxRows = 30;

    // constructor
    public Results(List<Event> events) {
        this.events = events;
    }

    public List<Double> getNextRow(double withdrawal) {
        List<Double> result = new ArrayList<>();
        for (Event event : events) {
            withdrawal = event.decreaseCurrentAmount(withdrawal);
            result.add(event.getCurrentAmount());
            event.applyInvestment();
        }

        currentRow += 1;
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
        return true;  // all events have been exhausted and all funds have been used up
    }

    public void reset() {
        for (Event event : events) {
            event.reset();
        }

        currentRow = 0;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
        reset();
    }
}
