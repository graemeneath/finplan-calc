package org.gallowgate.finplan.calc;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class EventsTest {

    private Events setUpTestEvents() {
        Events events = new Events();

        events.addEvent(new Event().setName("One").setEventType(EventType.INVESTMENT).setStartDate(LocalDate.of(2018,1,1)).setEndDate(LocalDate.of(2021,1,2)));
        events.addEvent(new Event().setName("Two").setEventType(EventType.INVESTMENT).setStartDate(LocalDate.of(2019,1,1)).setEndDate(LocalDate.of(2022,1,2)));
        events.addEvent(new Event().setName("Three").setEventType(EventType.INVESTMENT).setStartDate(LocalDate.of(2020,1,1)).setEndDate(LocalDate.of(2023,1,2)));
        events.addEvent(new Event().setName("Four").setEventType(EventType.COST).setStartDate(LocalDate.of(2020,1,1)).setEndDate(LocalDate.of(2031,1,2)));
        events.addEvent(new Event().setName("Five").setEventType(EventType.COST).setStartDate(LocalDate.of(2010,1,1)).setEndDate(LocalDate.of(2031,1,2)));

        return events;
    }

    @Test
    public void testGetEvents() {
        final Events events = setUpTestEvents();
        assertEquals(5, events.getEvents().size());
        assertEquals(3, events.getEvents(EventType.INVESTMENT).size());
        assertEquals(2, events.getEvents(EventType.COST).size());
    }

    @Test
    public void testGetHeaders() {
        final Events events = setUpTestEvents();
        assertEquals(4, events.getHeaders().size());
        assertEquals("Date,One,Two,Three", String.join(",", events.getHeaders()));
    }

    @Test
    public void testGetEarliestDate() {
        final Events events = setUpTestEvents();
        assertEquals(LocalDate.of(2018,1,1), events.getEarliestDate());
    }

    @Test
    public void testGetLatestDate() {
        final Events events = setUpTestEvents();
        assertEquals(LocalDate.of(2023,1,1), events.getLatestDate());
    }
}
