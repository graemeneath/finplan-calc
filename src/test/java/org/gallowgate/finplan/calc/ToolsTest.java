package org.gallowgate.finplan.calc;

import org.junit.Test;

import static org.gallowgate.finplan.calc.Tools.factorial;
import static org.junit.Assert.assertEquals;

public class ToolsTest {

    @Test
    public void testFactorialPositive() {
        assertEquals(factorial(1), 1);
        assertEquals(factorial(2), 2);
        assertEquals(factorial(5), 120);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegative() {
        factorial(-1);
    }

    @Test
    public void testFactorialZero() {
        assertEquals(factorial(0), 1);
    }

}