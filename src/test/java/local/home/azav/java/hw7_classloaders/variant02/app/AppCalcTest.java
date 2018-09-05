package local.home.azav.java.hw7_classloaders.variant02.app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppCalcTest {
    private AppCalc appCalc;

    @Before
    public void setUp() {
        appCalc = new AppCalc();
    }

    @Test
    public void main() {
        String[] args = new String[]{};
        try {
            AppCalc.main(args);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSetP1() {
        appCalc.setP1(22);
        assertEquals(22, appCalc.getP1());
    }

    @Test
    public void getSetP2() {
        appCalc.setP2(24);
        assertEquals(24, appCalc.getP2());
    }

    @Test
    public void addInt() {
        appCalc.setP1(12);
        appCalc.setP2(15);
        assertEquals(27, appCalc.addInt());
    }

    @Test
    public void addInt1() {
        assertEquals(34, appCalc.addInt(20, 14));
    }

    @Test
    public void mulInt() {
        appCalc.setP1(8);
        appCalc.setP2(9);
        assertEquals(72, appCalc.mulInt());
    }

    @Test
    public void mulInt1() {
        assertEquals(42, appCalc.mulInt(7,6));
    }
}