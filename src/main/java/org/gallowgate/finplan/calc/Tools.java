package org.gallowgate.finplan.calc;

public class Tools {
    public static int factorial(int num) {
        if (num < 0)
            throw new IllegalArgumentException();

        if (num == 0)
            return 1;

        return num * factorial(num -1);
    }
}
