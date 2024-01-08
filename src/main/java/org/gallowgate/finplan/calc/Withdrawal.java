package org.gallowgate.finplan.calc;

import java.util.HashMap;
import java.util.Map;

public class Withdrawal {

    private double base = 0.0;

    private final Map<Integer, Double> overrides =  new HashMap<>();

    public Withdrawal(double base) {
        this.base = base;
    }

    public Withdrawal addOverride(int age, double amount) {
        overrides.put(age, amount);
        return this;
    }

    public double getAmount(int age) {
        double amount = base;

        int overrideAge = overrides.keySet().stream().filter(a -> a <= age).max(Integer::compareTo).orElse(0);
        if (overrides.containsKey(overrideAge)) {
            amount = overrides.get(overrideAge);
        }

        return amount;
    }
}
