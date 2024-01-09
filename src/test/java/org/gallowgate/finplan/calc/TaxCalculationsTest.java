package org.gallowgate.finplan.calc;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TaxCalculationsTest {

    @Test
    // check that the values are correct and that the results are returned in a deterministic order
    public void verifyTaxTable() {
        TaxCalculations taxCalculations = new TaxCalculations();
        ArrayList<List<Double>> taxTable = taxCalculations.getTaxTable();

        Assert.assertEquals(4, taxTable.size());
        Assert.assertEquals(12570d, taxTable.get(0).get(0), 0.1);
        Assert.assertEquals(0.0d, taxTable.get(0).get(1), 0.001);
        Assert.assertEquals(50270d, taxTable.get(1).get(0), 0.1);
        Assert.assertEquals(0.2d, taxTable.get(1).get(1), 0.001);
        Assert.assertEquals(125140d, taxTable.get(2).get(0), 0.1);
        Assert.assertEquals(0.4d, taxTable.get(2).get(1), 0.1);
        Assert.assertEquals(999999999d, taxTable.get(3).get(0), 0.1);
        Assert.assertEquals(0.45d, taxTable.get(3).get(1), 0.001);
    }

    @Test
    public void checkTaxAmount() {
        TaxCalculations taxCalculations = new TaxCalculations();

        Assert.assertEquals(0d, taxCalculations.getTax(-100), 0.1);
        Assert.assertEquals(0d, taxCalculations.getTax(10000), 0.1);
        Assert.assertEquals(7486d, taxCalculations.getTax(50000), 0.1);
        Assert.assertEquals(27432d, taxCalculations.getTax(100000), 0.1);
    }

    @Test
    public void checkTaxAmountWithOffset() {
        TaxCalculations taxCalculations = new TaxCalculations();

        Assert.assertEquals(0d, taxCalculations.getTax(-100, 0), 0.1);
        Assert.assertEquals(0d, taxCalculations.getTax(10000, 0), 0.1);
        Assert.assertEquals(7486d, taxCalculations.getTax(50000, 0), 0.1);
        Assert.assertEquals(27432d, taxCalculations.getTax(100000, 0), 0.1);

        Assert.assertEquals(0d, taxCalculations.getTax(-100, 5000), 0.1);
        Assert.assertEquals(486d, taxCalculations.getTax(10000, 5000), 0.1);
        Assert.assertEquals(4000d, taxCalculations.getTax(20000, 15000), 0.1);

        Assert.assertEquals(0d, taxCalculations.getTax(-100, 10000), 0.1);
    }

    @Test
    public void grossForNetCalculation() {
        TaxCalculations taxCalculations = new TaxCalculations();

        Assert.assertEquals(10000d, taxCalculations.calculateGrossForNetRequired(10000), 0.1);
        Assert.assertEquals(50000d, taxCalculations.calculateGrossForNetRequired(42514), 0.1);
        Assert.assertEquals(100000d, taxCalculations.calculateGrossForNetRequired(72568), 0.1);

        Assert.assertEquals(10000d, taxCalculations.calculateGrossForNetRequired(9514, 5000), 0.1);
        Assert.assertEquals(20000d, taxCalculations.calculateGrossForNetRequired(16000, 15000), 0.1);
    }
}
