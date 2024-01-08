package org.gallowgate.finplan.calc;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class PersonTest {

    @Test
    public void ageTest() {
        Person p = Person.create("John", LocalDate.of(2000, 1, 1));
        Assert.assertEquals(20, p.getAge(LocalDate.of(2020, 3, 1)));
    }
}
