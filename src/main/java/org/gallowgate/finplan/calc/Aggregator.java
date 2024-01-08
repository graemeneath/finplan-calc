package org.gallowgate.finplan.calc;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

public class Aggregator {
    private final Events events = new Events();
    private final JSONObject result = new JSONObject();
    private final Logger logger = Logger.getLogger(Aggregator.class.getName());

    //constructor
    public Aggregator() {
    }

    public void aggregate() {
        logger.fine("Aggregating...");

        result.clear();
        Results results = new Results(events);
        List<String> headers = events.getHeaders();

        JSONArray jheaders = new JSONArray();
        for (String header : headers) {
            jheaders.put(header);
        }
        result.put("headers", jheaders);

        JSONArray rows = new JSONArray();

        while (!results.isDone()) {
            LocalDate currentDate = results.getCurrentDate();
            List<String> row = results.getNextRow(getWithdrawalAmount(currentDate)).stream().map(d -> String.format("%.2f", d)).toList();

            JSONArray jrow = new JSONArray();
            jrow.put(currentDate.toString());
            for (String r : row) {
                jrow.put(r);
            }
            rows.put(jrow);
        }

        result.put("rows", rows);

        logger.fine(result.toString());
    }

    public void addEvent(Event e) {
        events.addEvent(e);
    }

    public JSONObject getResult() {
        return result;
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
