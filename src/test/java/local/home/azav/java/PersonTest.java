package local.home.azav.java;


import local.home.azav.java.hw2_person.Person;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for class Person.
 */
public class PersonTest {
    private Person person1;
    private Person person2;

    @Before
    public void testApp() {
        person1 = new Person(true, "Вася");
        person2 = new Person(false, "Маша");
        //assertTrue(true);
    }

    @Test
    public void testGetNameSpouseNull() {
        assertEquals("no", person1.getNameSpouse());
        assertEquals("no", person2.getNameSpouse());
    }

    @Test
    public void testGetNameSpouseNotNull() {
        person1.marry(person2);
        assertEquals("Маша", person1.getNameSpouse());
        assertEquals("Вася", person2.getNameSpouse());
        person1.divorce();
    }

    @Test
    public void testDivorce() {
        person1.marry(person2);
        assertEquals("Маша", person1.getNameSpouse());
        assertEquals("Вася", person2.getNameSpouse());
        person1.divorce();
        assertEquals("no", person1.getNameSpouse());
        assertEquals("no", person2.getNameSpouse());
    }

    @Test
    public void testPersonToString() {
        assertEquals("Мужчина( имя= Вася, возраст=18, холост.)", person1.toString());
        assertEquals("Женщина( имя= Маша, возраст=18, не замужем.)", person2.toString());
    }
}
