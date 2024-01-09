package org.gallowgate.finplan.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaxCalculations {
    private final ArrayList<List<Double>> taxTable;

    // constructor
    public TaxCalculations() {
        taxTable = new ArrayList<List<Double>>();
        taxTable.add(Arrays.asList(12570d, 0d));
        taxTable.add(Arrays.asList(50270d, 0.2d));
        taxTable.add(Arrays.asList(125140d, 0.4d));
        taxTable.add(Arrays.asList(999999999d, 0.45d));
    }

    private double getTaxCalc(double amount, double offset) {
        double tax = 0d;
        if (amount <= 0d) {
            return tax; // no tax for negative income!
        }

        double lastBreakpoint = 0d;
        for (List<Double> row : taxTable) {
            double adjustedBreakpoint = Math.max(0d, row.get(0) - offset);
            double band = adjustedBreakpoint - lastBreakpoint;
            double taxRate =  row.get(1);

            lastBreakpoint = adjustedBreakpoint;
            double taxable = Math.min(amount, band);
            tax += taxable * taxRate;
            amount -= taxable;
        }

        return Tools.round(tax);
    }

    // otherEarnings is the amount of income that has already been taxed, and so some of the allowances have already been used
    public double getTax(double amount, double otherTaxableEarnings) {
        return getTaxCalc(amount, otherTaxableEarnings);
    }

    public double getTax(double amount) {
        return getTax(amount, 0d);
    }

    public double calculateGrossForNetRequired(double netAmount, double otherTaxableEarnings) {
        // use iterative method to find the gross amount required
        double low = 0d;
        double high = 999999999d;

        while (high - low > 0.01d) {
            double mid = (high + low) / 2d;
            double tax = getTax(mid, otherTaxableEarnings);
            if (mid - tax > netAmount) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return Tools.round(low);
    }

    public double calculateGrossForNetRequired(double netAmount) {
        return calculateGrossForNetRequired(netAmount, 0d); // no other taxable earnings!
    }

    public ArrayList<List<Double>> getTaxTable() {
        return taxTable;
    }
}
