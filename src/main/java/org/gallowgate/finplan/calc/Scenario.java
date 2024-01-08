package org.gallowgate.finplan.calc;

import org.json.JSONObject;

import java.time.LocalDate;

public class Scenario {
    // constructor
    public Scenario() {
    }

    public void run() {
        Aggregator aggregator = new Aggregator();
        aggregator.addEvent(new Event()
                .setName("ISA")
                .setAmount(171000)
                .setStartDate(LocalDate.of(2023, 12, 1))
                .setEventType(EventType.INVESTMENT)
                .setInvestment(InvestmentType.GROWTH)
        );
        aggregator.addEvent(new Event()
                .setName("Inheritance")
                .setAmount(150000)
                .setStartDate(LocalDate.of(2035, 1, 1))
                .setEventType(EventType.INVESTMENT)
                .setInvestment(InvestmentType.GROWTH)
        );
        aggregator.addEvent(new Event()
                .setName("Cash")
                .setAmount(60000)
                .setStartDate(LocalDate.of(2023, 11, 1))
                .setEventType(EventType.INVESTMENT)
                .setInvestment(InvestmentType.CASH)
        );
        aggregator.addEvent(new Event()
                .setName("Pension")
                .setAmount(232000)
                .setStartDate(LocalDate.of(2023, 12, 1))
                .setEventType(EventType.INVESTMENT)
                .setInvestment(InvestmentType.SP500)
        );

        aggregator.addEvent(new Event()
                .setName("Mortgage")
                .setAmount(10000)
                .setStartDate(LocalDate.of(2023, 1, 1))
                .setEndDate(LocalDate.of(2032,9,1))
                .setEventType(EventType.COST)
                .setInvestment(InvestmentType.FLAT)
        );

        displayResults(aggregator);
    }

    private void displayResults(Aggregator aggregator) {
        aggregator.aggregate();
        JSONObject jresult = aggregator.getResult();
        System.out.println(jresult.get("headers"));
        for(Object row : jresult.getJSONArray("rows")) {
            System.out.println(row);
        }
    }
}
