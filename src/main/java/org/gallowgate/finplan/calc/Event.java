package org.gallowgate.finplan.calc;

import java.time.LocalDate;

public class Event {
    private String name;
    private double amount;
    private double currentAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private EventType eventType;
    private InvestmentType investment;

    // constructor
    public Event() {
        this.name = "event";
        this.amount = 0;
        this.startDate = LocalDate.of(1900, 1, 1);
        this.endDate = LocalDate.of(2099,1,1);
        this.eventType = EventType.INVESTMENT;
        this.investment = InvestmentType.CASH;

        reset();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Event setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Event setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public String getName() {
        return name;
    }

    public Event setName(String name) {
        this.name = name;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Event setAmount(double amount) {
        this.amount = amount;
        currentAmount = amount;
        return this;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Event setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public float getInvestmentRate() {
        return investment.rate;
    }

    public InvestmentType getInvestment() {
        return investment;
    }

    public Event setInvestment(InvestmentType investment) {
        this.investment = investment;
        return this;
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

    // this calculation engine works on a yearly basis, so we need to check if the date is within the event's year
    public boolean isActive(LocalDate date) {
        return (
                date.getYear() >= this.startDate.getYear() && date.getYear() <= this.endDate.getYear()
        );
    }
}
