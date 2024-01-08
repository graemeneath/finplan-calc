package org.gallowgate.finplan.calc;

import java.time.LocalDate;

public class Person {

    private String name;
    private LocalDate dob;

    public Person(String name, LocalDate dob) {
        this.name = name;
        this.dob = dob;
    }

    public int getAge(LocalDate currentDate) {
        return dob.until(currentDate).getYears();
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", date of birth=" + dob +
                '}';
    }

    public static Person create(String name, LocalDate dob) {
        return new Person(name, dob);
    }

    public static Person create(String name) {
        return new Person(name, LocalDate.of(1900,1,1));
    }

    public static Person create(LocalDate dob) {
        return new Person("Unknown", dob);
    }

    public static Person create() {
        return new Person("Unknown", LocalDate.of(1900, 1, 1));
    }
}
