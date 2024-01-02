package org.gallowgate.finplan.calc;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AggregatorTest {

    private Aggregator testSetup1() {
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

        return aggregator;
    }

    @Test
    public void expectedResultDimensions() {
        Aggregator aggregator = testSetup1();
        List<ArrayList<String>> rows = aggregator.aggregate();

        assertEquals(31, rows.size());
        assertEquals(5, rows.get(0).size());
    }
}
