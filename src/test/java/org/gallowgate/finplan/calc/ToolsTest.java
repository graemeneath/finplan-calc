package org.gallowgate.finplan.calc;

import org.junit.Assert;
import org.junit.Test;

public class ToolsTest {

    @Test
    public void testRoundToTwoDecimals() {
        double input = 1.2345;
        double output = Tools.round(input);
        Assert.assertEquals(1.23, output, 0);
    }

}
