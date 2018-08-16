package local.home.azav.java.hw2_person;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainPersonTest {

    @Test
    public void outer() {
        MainPerson.outer("Test 1!");
    }

    @Test
    public void outerPar() {
        Person person1 = new Person(true, "Вася");
        Person person2 = new Person(false, "Маша");
        MainPerson.outerPar(person1,person2);
    }

    @Test
    public void marryParTrue() {
        Person person1 = new Person(true, "Вася");
        Person person2 = new Person(false, "Маша");
        assertTrue(MainPerson.marryPar(person1,person2));
    }

    @Test
    public void marryParFalse() {
        Person person1 = new Person(true, "Вася");
        Person person2 = new Person(true, "Миша");
        assertFalse(MainPerson.marryPar(person1,person2));
    }

    @Test
    public void main() {
        String[] args = new String[] {};
        MainPerson.main(args);
    }
}