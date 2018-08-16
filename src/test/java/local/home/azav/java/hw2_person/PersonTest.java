package local.home.azav.java.hw2_person;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void testGetAge() {
        assertEquals(18,person1.getAge());
        assertEquals(18,person2.getAge());
    }

    @Test
    public void testIsMan() {
        assertTrue(person1.isMan());
        assertFalse(person2.isMan());
    }

    @Test
    public void testGetName() {
        assertEquals("Вася",person1.getName());
        assertEquals("Маша",person2.getName());
    }

    @Test
    public void testGetSpouseNull() {
        person1 = new Person(true, "Вася");
        person2 = new Person(false, "Маша");
        assertNull(person1.getSpouse());
        assertNull(person2.getSpouse());
    }

    @Test
    public void testGetSpouseNotNull() {
        person1 = new Person(true, "Вася");
        person2 = new Person(false, "Маша");
        assertTrue(person1.marry(person2));
        assertNotNull(person1.getSpouse());
        assertNotNull(person2.getSpouse());
    }

    @Test
    public void testGetNameSpouseNull() {
        person1 = new Person(true, "Вася");
        person2 = new Person(false, "Маша");
        assertEquals("no", person1.getNameSpouse());
        assertEquals("no", person2.getNameSpouse());
    }

    @Test
    public void testGetNameSpouseNotNull() {
        person1 = new Person(true, "Вася");
        person2 = new Person(false, "Маша");
        assertTrue(person1.marry(person2));
        assertEquals("Маша", person1.getNameSpouse());
        assertEquals("Вася", person2.getNameSpouse());
        assertTrue(person1.divorce());
    }

    @Test
    public void testDivorce() {
        person1 = new Person(true, "Вася");
        person2 = new Person(false, "Маша");
        assertTrue(person1.marry(person2));
        assertEquals("Маша", person1.getNameSpouse());
        assertEquals("Вася", person2.getNameSpouse());
        assertTrue(person1.divorce());
        assertEquals("no", person1.getNameSpouse());
        assertEquals("no", person2.getNameSpouse());
    }

    @Test
    public void testPersonToString() {
        person1 = new Person(true, "Вася");
        person2 = new Person(false, "Маша",17);
        assertEquals("Мужчина( имя= Вася, возраст=18, холост.)", person1.toString());
        assertEquals("Женщина( имя= Маша, возраст=17, не замужем.)", person2.toString());
    }
}