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
        terminal = new TerminalImpl(terminalServer,pinValidator);
    }

    @Test
    public void checkSumAccount() {
        terminal.setCurrentAccount(12345);
        terminal.setCurrentPin(1234);
        try {
            assertEquals(120.55,terminal.checkSumAccount().doubleValue(),0.001D);
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
    public void getCurrentAccount() {
        terminal.setCurrentAccount(12346);
        assertEquals(12346,terminal.getCurrentAccount());
    }

//    @Test
//    public void setCurrentAccount(int currentAccount) {
//
//        this.currentAccountT = currentAccount;
//    }

    @Test
    public void getCurrentPin() {
        terminal.setCurrentPin(1235);
        assertEquals(1235,terminal.getCurrentPin());
    }

//    @Test
//    public void setCurrentPin(int currentPin) {
//        this.currentPinT = currentPin;
//    }
}