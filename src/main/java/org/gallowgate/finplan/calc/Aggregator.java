package org.gallowgate.finplan.calc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aggregator {
    List<Event> events;

    //constructor
    public Aggregator() {
        events = new ArrayList<Event>();
    }

    public void aggregate() {
        Results results = new Results(events);
        while (!results.isDone()) {
            for (Double d : results.getNextRow(35000)) {
                System.out.print(String.format("%.2f ", d));
            }
            System.out.println();
        }
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public List<Event> getEvents() {
        return events;
    }

    private LocalDate getEarliestDate() {
        LocalDate earliest = events.get(0).getDate();

        for (Event e : events) {
            if (e.getDate().isBefore(earliest)) {
                earliest = e.getDate();
            }
        }
        return earliest;
    }
}
