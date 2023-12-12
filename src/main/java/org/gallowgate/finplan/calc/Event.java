package org.gallowgate.finplan.calc;

import java.time.LocalDate;

public class Event {
    private final String name;
    private final double amount;
    private double currentAmount;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final EventType eventType;
    private final InvestmentType investment;

    // constructor
    public Event(String name, double amount, LocalDate startDate, LocalDate endDate, EventType eventType, InvestmentType investment) {
        this.name = name;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventType = eventType;
        this.investment = investment;

        reset();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public EventType getEventType() {
        return eventType;
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
        return (date.getYear() >= this.startDate.getYear());
    }
}
