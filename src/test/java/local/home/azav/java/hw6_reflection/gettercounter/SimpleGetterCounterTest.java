package local.home.azav.java.hw6_reflection.gettercounter;

import local.home.azav.java.hw2_person.Person;
import local.home.azav.java.hw6_reflection.annotationskip.PersonAnnotation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleGetterCounterTest {
    SimpleGetterCounter simpleGetterCounter;

    @Before
    public void testApp() {
        simpleGetterCounter = new SimpleGetterCounter();
    }

    @Test
    public void calcGetterCount() {
        assertEquals(4, simpleGetterCounter.calcGetterCount(PersonAnnotation.class));
    }

    @Test
    public void arrayGetterCount() {
        assertEquals(4, simpleGetterCounter.arrayGetterCount(PersonAnnotation.class).size());
    }

    @Test
    public void main() {
        String[] args = new String[]{};
        SimpleGetterCounter.main(args);
    }
}