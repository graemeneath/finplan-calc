package org.gallowgate.finplan.calc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Aggregator {
    private final Events events = new Events();
    private final Logger logger = Logger.getLogger(Aggregator.class.getName());

    //constructor
    public Aggregator() {
    }

    public List<ArrayList<String>> aggregate() {
        List<ArrayList<String>> rows = new ArrayList<>();

        Results results = new Results(events);
        List<String> headers = events.getHeaders();
        rows.add(new ArrayList<>(headers));
        logger.fine(String.join("," ,headers));

        while (!results.isDone()) {
            LocalDate currentDate = results.getCurrentDate();
            List<String> row = results.getNextRow(getWithdrawalAmount(currentDate)).stream().map(d -> String.format("%.2f", d)).toList();
            List<String> mutableRow = new ArrayList<>(row);
            mutableRow.add(0, currentDate.toString());
            rows.add(new ArrayList<>(mutableRow));
            logger.fine(String.join(",", mutableRow));
        }

        return rows;
    }

    public void addEvent(Event e) {
        events.addEvent(e);
    }

    private double getWithdrawalAmount(LocalDate currentDate) {
        // get sum of costs for this period
        double costs = 0;

        for (Event e : events.getEvents()) {
            if (e.getEventType() == EventType.COST && e.isActive(currentDate)) {
                costs += e.getAmount();
            }
        }

        // this value will change dependent on age
        double baseWithdrawal = 30000;

        double totalWithdrawal = baseWithdrawal + costs;
        logger.fine("Total withdrawal: " + totalWithdrawal);
        return totalWithdrawal;
    }

}
