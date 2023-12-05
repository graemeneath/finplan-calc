package org.gallowgate.finplan.calc;

import java.time.LocalDate;

public class Event {
    final String name;
    final double amount;
    final LocalDate date;
    final Investment investment;

    // constructor
    public Event(String name, double amount, LocalDate date, Investment investment) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.investment = investment;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public float getInvestmentRate() {
        return investment.rate;
    }
}
