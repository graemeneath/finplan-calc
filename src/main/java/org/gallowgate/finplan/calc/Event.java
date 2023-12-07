package org.gallowgate.finplan.calc;

import java.time.LocalDate;

public class Event {
    private final String name;
    private final double amount;
    private double currentAmount;
    private final LocalDate date;
    private final Investment investment;

    // constructor
    public Event(String name, double amount, LocalDate date, Investment investment) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.investment = investment;

        reset();
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public float getInvestmentRate() {
        return investment.rate;
    }

    public void reset() {
        currentAmount = amount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    // decrease current amount by a withdrawal and return any remaining withdrawal amount should there not be
    // sufficient funds available
    public double decreaseCurrentAmount(double withdrawal) {
        if (withdrawal > currentAmount) {
            double outstandingToWithdraw = withdrawal - currentAmount;
            currentAmount = 0;
            return outstandingToWithdraw;
        } else {
            currentAmount -= withdrawal;
            return 0;
        }
    }

    public void applyInvestment() {
        currentAmount *= (1 + investment.rate);
        currentAmount = Math.round(currentAmount * 100) / 100.0; // round to 2 decimal places
    }

    public boolean isActive(LocalDate date) {
        return (date.getYear() >= this.date.getYear());
    }
}
