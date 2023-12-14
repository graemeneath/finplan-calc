package org.gallowgate.finplan.calc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Events {

    private List<Event> events = new ArrayList<>();


    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Event> getEvents(EventType eventType) {
        return events.stream().filter(e -> e.getEventType() == eventType).toList();
    }

    public LocalDate getEarliestDate() {
        LocalDate earliest = events.get(0).getStartDate();

        for (Event e : getEvents(EventType.INVESTMENT)) {
            if (e.getStartDate().isBefore(earliest)) {
                earliest = e.getStartDate();
            }
        }
        return earliest.withDayOfYear(1);
    }

    public LocalDate getLatestDate() {
        LocalDate latest = events.get(0).getEndDate();

        for (Event e : getEvents(EventType.INVESTMENT)) {
            if (e.getEndDate().isAfter(latest)) {
                latest = e.getEndDate();
            }
        }
        return latest.withDayOfYear(1);
    }

    public List<String> getHeaders() {
        List<String> headers = new ArrayList<>();
        headers.add("Date");
        for (Event e : getEvents(EventType.INVESTMENT)) {
            headers.add(e.getName());
        }
        return headers;
    }
}
