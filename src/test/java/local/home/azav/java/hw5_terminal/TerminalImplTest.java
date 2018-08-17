package local.home.azav.java.hw5_terminal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TerminalImplTest {
    private TerminalImpl terminal;

    @Before
    public void testApp() {
        TerminalServer terminalServer = new TerminalServer();
        PinValidator pinValidator = new PinValidator();
        terminal = new TerminalImpl(terminalServer, pinValidator);
    }

    @Test
    public void checkAuthenticationTrue() {
        terminal.setCurrentAccount(12345);
        terminal.setCurrentPin(1234);
        assertTrue(terminal.checkAuthentication());
    }

    @Test
    public void checkAuthenticationFalse() {
        terminal.setCurrentAccount(12345);
        terminal.setCurrentPin(9999);
        assertFalse(terminal.checkAuthentication());
    }

    @Test
    public void checkSumAccountBad() {
        terminal.setCurrentAccount(12345);
        terminal.setCurrentPin(1234);
        try {
            assertEquals(120.55, terminal.checkSumAccount().doubleValue(), 0.001D);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void withdrawMoney() {
        terminal.setCurrentAccount(12345);
        terminal.setCurrentPin(1234);
        try {
            assertTrue(terminal.withdrawMoney(100));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void withdrawMoneyNot100() {
        terminal.setCurrentAccount(12345);
        terminal.setCurrentPin(1234);
        try {
            assertFalse(terminal.withdrawMoney(50));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void withdrawMoneyNotMoney() {
        terminal.setCurrentAccount(12345);
        terminal.setCurrentPin(1234);
        try {
            assertFalse(terminal.withdrawMoney(450));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void putMoney() {
        terminal.setCurrentAccount(12345);
        terminal.setCurrentPin(1234);
        try {
            assertTrue(terminal.putMoney(100));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void putMoneyNotAuthentication() {
        terminal.setCurrentAccount(12345);
        terminal.setCurrentPin(111);
        try {
            terminal.putMoney(100);
        } catch (NoSuchFieldException e) {
            System.out.println("Good! Not Authentication!");
        }
    }

    @Test
    public void putMoneyNo100() {
        terminal.setCurrentAccount(12345);
        terminal.setCurrentPin(1234);
        try {
            assertFalse(terminal.putMoney(5));
        } catch (NoSuchFieldException e) {
            System.out.println("Good! Not 100!");
        }
    }

    @Test
    public void getCurrentAccount() {
        terminal.setCurrentAccount(12346);
        assertEquals(12346, terminal.getCurrentAccount());
    }

    @Test
    public void getCurrentPin() {
        terminal.setCurrentPin(1235);
        assertEquals(1235, terminal.getCurrentPin());
    }

    @Test
    public void checkAcc9999() {
        assertFalse(terminal.checkAcc(9999));
    }

    @Test
    public void checkAccTrue() {
        assertTrue(terminal.checkAcc(12345));
    }

    @Test
    public void checkAccFalse() {
        assertFalse(terminal.checkAcc(55555));
    }
}