package org.gallowgate.finplan.calc;

public enum Investment {
    SINGLE(-1f),
    CASH(-0.02f),
    SAVINGS(0f),
    FLAT(0f),
    GROWTH(0.04f),
    SP500(0.08f);

    public final float rate;

    private Investment(float rate) {
        this.rate = rate;
    }
}

