package local.home.azav.java.hw5_terminal;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountIsLockedExceptionTest {

    @Test
    public void testExept() {
        try {
            throw new AccountIsLockedException("Test");
        } catch (AccountIsLockedException e) {
            System.out.println("The exception is handled normally!");
        }
    }
}