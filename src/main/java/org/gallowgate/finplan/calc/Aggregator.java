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
        List<String> headers = results.getHeaders();
        System.out.println(String.join("," ,headers));
        while (!results.isDone()) {
            LocalDate currentDate = results.getCurrentDate();
            List<String> row = results.getNextRow(getWithdrawalAmount(currentDate)).stream().map(d -> String.format("%.2f", d)).toList();
            List<String> mutableRow = new ArrayList<>(row);
            mutableRow.add(0, currentDate.toString());
            System.out.println(String.join(",", mutableRow));
        }
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public List<Event> getEvents() {
        return events;
    }

    private double getWithdrawalAmount(LocalDate currentDate) {
        // get sum of costs for this period
        double costs = 0;

        for (Event e : events) {
            if (e.getEventType() == EventType.COST
            && e.getStartDate().getYear() <= currentDate.getYear()
            && e.getEndDate().getYear() >= currentDate.getYear()) {
                costs += e.getAmount();
            }
        }

        // this value will change dependent on age
        double baseWithdrawal = 30000;

        double totalWithdrawal = baseWithdrawal + costs;
        System.out.println("Total withdrawal: " + totalWithdrawal);
        return totalWithdrawal;
    }

}
