package org.gallowgate.finplan.calc;

import java.time.LocalDate;

public class Scenario {
    // constructor
    public Scenario() {
    }

    public void run() {
        Aggregator aggregator = new Aggregator();
        aggregator.addEvent(new Event("ISA", 169000, LocalDate.of(2023, 12, 1), null, EventType.INVESTMENT, InvestmentType.GROWTH));
        aggregator.addEvent(new Event("Inheritance", 150000, LocalDate.of(2035, 1, 1), null, EventType.INVESTMENT, InvestmentType.GROWTH));
        aggregator.addEvent(new Event("Cash", 60000, LocalDate.of(2023, 11, 1), null, EventType.INVESTMENT, InvestmentType.CASH));
        aggregator.addEvent(new Event("Pension", 228000, LocalDate.of(2023, 12, 1), null, EventType.INVESTMENT, InvestmentType.SP500));

        aggregator.addEvent(new Event("Mortgage", 12000, LocalDate.of(2023,11,1), LocalDate.of(2032, 9, 1), EventType.COST, InvestmentType.FLAT));

        aggregator.aggregate();
    }
}
