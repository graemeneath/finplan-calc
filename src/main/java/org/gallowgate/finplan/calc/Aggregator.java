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
        for (Event e : events) {
            LocalDate date = e.getDate();
            Double amount = e.getAmount();
            Float rate = e.getInvestmentRate();
            for (int year = 0; year < 30; year++) {
                System.out.println("Date %s, amount £%.2f".formatted(date, amount));
                date = date.plusYears(1);
                amount = amount * (1 + rate);
            }
        }
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public List<Event> getEvents() {
        return events;
    }
}
