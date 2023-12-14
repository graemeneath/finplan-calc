package org.gallowgate.finplan.calc;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class EventTest {

    @Test
    public void testSettingAttributes() {
        Event event = new Event();
        event.setAmount(100);
        event.setStartDate(LocalDate.of(1344,5,6));
        event.setEndDate(LocalDate.of(1066,10,12));
        event.setEventType(EventType.INVESTMENT);
        event.setName("Bob");
        event.setInvestment(InvestmentType.GROWTH);

        assertEquals(100, event.getAmount(), 0.01);
        assertEquals(LocalDate.of(1344,5,6), event.getStartDate());
        assertEquals(LocalDate.of(1066,10,12), event.getEndDate());
        assertEquals(EventType.INVESTMENT, event.getEventType());
        assertEquals("Bob", event.getName());
        assertEquals(InvestmentType.GROWTH, event.getInvestment());
    }

    @Test
    public void testWithdrawalNoRemainder() {
        Event event = new Event();
        event.setAmount(75);

        double remainder = event.decreaseCurrentAmount(50);
        assertEquals(25, event.getCurrentAmount(), 0.01);
        assertEquals(0, remainder, 0.01);
    }

    @Test
    public void testWithdrawalWithRemainder() {
        Event event = new Event();
        event.setAmount(75);

        double remainder = event.decreaseCurrentAmount(100);
        assertEquals(0, event.getCurrentAmount(), 0.01);
        assertEquals(25, remainder, 0.01);
    }

    @Test
    public void testApplyInvestment() {
        Event event = new Event();
        event.setAmount(100);
        event.setInvestment(InvestmentType.GROWTH);
        event.applyInvestment();
        assertEquals(104, event.getCurrentAmount(), 0.01);
    }

    @Test
    public void testIsActiveIsTrue() {
        Event event = new Event();
        event.setStartDate(LocalDate.of(1066,10,12));
        assertEquals(true, event.isActive(LocalDate.of(1344,5,6)));

        event.setEndDate(LocalDate.of(1344,5,6));
        assertEquals(true, event.isActive(LocalDate.of(1344,5,6)));
        assertEquals(true, event.isActive(LocalDate.of(1066,10,12)));
        assertEquals(true, event.isActive(LocalDate.of(1200,1,1)));
    }

    @Test
    public void testIsActiveIsFalse() {
        Event event = new Event();
        event.setStartDate(LocalDate.of(1066,10,12));
        assertEquals(false, event.isActive(LocalDate.of(900,1,1)));

        event.setEndDate(LocalDate.of(1344,5,6));
        assertEquals(false, event.isActive(LocalDate.of(1345,5,7)));
        assertEquals(false, event.isActive(LocalDate.of(1065,10,11)));
        assertEquals(false, event.isActive(LocalDate.of(1600,1,1)));
    }
}
