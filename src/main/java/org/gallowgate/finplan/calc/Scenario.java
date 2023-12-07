package org.gallowgate.finplan.calc;

import java.time.LocalDate;

public class Scenario {
    // constructor
    public Scenario() {
    }

    public void run() {
        Aggregator aggregator = new Aggregator();
        aggregator.addEvent(new Event("Pension", 228000, LocalDate.of(2023, 12, 1), Investment.SP500));
        aggregator.addEvent(new Event("ISA", 169000, LocalDate.of(2023, 12, 1), Investment.GROWTH));
        aggregator.addEvent(new Event("Inheritance", 150000, LocalDate.of(2035, 1, 1), Investment.GROWTH));
        aggregator.addEvent(new Event("Cash", 60000, LocalDate.of(2023, 11, 1), Investment.CASH));
        aggregator.aggregate();
    }
}
