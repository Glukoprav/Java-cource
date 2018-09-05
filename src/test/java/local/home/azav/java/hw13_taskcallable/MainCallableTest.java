package local.home.azav.java.hw13_taskcallable;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainCallableTest {

    @Test
    public void main() {
        String[] args = new String[] {};
        MainCallable.main(args);
    }

    @Test
    public void callTest() {
        MainCallable mainCallable = new MainCallable();
        try {
            assertNotNull(mainCallable.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}