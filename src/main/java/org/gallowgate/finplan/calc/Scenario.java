package org.gallowgate.finplan.calc;

import java.time.LocalDate;

public class Scenario {
    // constructor
    public Scenario() {
        Aggregator aggregator = new Aggregator();
        aggregator.addEvent(new Event("Pension", 228000, LocalDate.of(2023, 12, 1), Investment.SP500));
        aggregator.aggregate();
    }
}
