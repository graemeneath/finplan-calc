package org.gallowgate.finplan.calc;

import org.junit.Assert;
import org.junit.Test;

public class WithdrawalTest {

    @Test
    public void testWithdrawalWithOverrides() {
        Withdrawal withdrawal = new Withdrawal(30000);
        withdrawal.addOverride(75, 25000);
        withdrawal.addOverride(80, 35000);

        Assert.assertEquals(30000f, withdrawal.getAmount(55), 0.01f);
        Assert.assertEquals(25000f, withdrawal.getAmount(76), 0.01f);
        Assert.assertEquals(35000f, withdrawal.getAmount(81), 0.01f);
    }

    @Test
    public void testWithdrawalWithNoOverrides() {
        Withdrawal withdrawal = new Withdrawal(30000);

        Assert.assertEquals(30000f, withdrawal.getAmount(55), 0.01f);
        Assert.assertEquals(30000f, withdrawal.getAmount(76), 0.01f);
        Assert.assertEquals(30000f, withdrawal.getAmount(81), 0.01f);
    }

    @Test
    public void testWithdrawalWithFlowDefinition() {
        Withdrawal withdrawal = new Withdrawal(30000).addOverride(75, 25000);

        Assert.assertEquals(30000f, withdrawal.getAmount(55), 0.01f);
        Assert.assertEquals(25000f, withdrawal.getAmount(85), 0.01f);
    }
}
