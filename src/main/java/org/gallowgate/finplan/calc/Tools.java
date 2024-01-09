package org.gallowgate.finplan.calc;

public class Tools {

    public static double round(double value) {
        return Math.round(value * 100d) / 100d; // round to two decimal places
    }
}
