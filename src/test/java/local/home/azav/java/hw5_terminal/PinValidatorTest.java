package local.home.azav.java.hw5_terminal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PinValidatorTest {
    private PinValidator pinValidator;

    @Test
    public void checkPinTrue() {
        pinValidator = new PinValidator();
        try {
            assertTrue(pinValidator.checkPin(12345, 1234));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkPinFalse() {
        pinValidator = new PinValidator();
        try {
            assertTrue(pinValidator.checkPin(12345, 111));
            fail("Unavailable pin!");
        } catch (IllegalAccessException e) {
            System.out.println("The exception is handled normally!");
        }
    }
}