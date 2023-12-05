package org.gallowgate.finplan.calc;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class InvestmentTest {

    @Test
    public void growthRate() {
        assertEquals(Investment.GROWTH.rate, 0.04f, 0);
    }

    @Test
    public void sp500Rate() {
        assertEquals(Investment.SP500.rate, 0.08f, 0);
    }

}

