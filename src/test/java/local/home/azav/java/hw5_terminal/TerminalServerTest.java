package local.home.azav.java.hw5_terminal;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TerminalServerTest {
    private TerminalServer terminalServer;

    @Before
    public void testApp() {
        terminalServer = new TerminalServer();
    }

    @Test
    public void checkAccountTrue() {
        try {
            assertTrue(terminalServer.checkAccount(12342));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkAccountFalse() {
        try {
            assertFalse(terminalServer.checkAccount(233));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sumAccount() {
        try {
            assertEquals(120.55, terminalServer.sumAccount(12345).doubleValue(), 0.001D);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void serverWithdrawMoney() {
        try {
            terminalServer.serverWithdrawMoney(BigDecimal.valueOf(10.0), 12340);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            assertEquals(40, terminalServer.sumAccount(12340).doubleValue(), 0.001D);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void serverWithdrawMoneyExeption() {
        try {
            terminalServer.serverWithdrawMoney(BigDecimal.valueOf(80.0), 12340);
            fail("Trying to withdraw money more available!");
        } catch (NoSuchMethodException e) {
            System.out.println("The exception is handled normally!");
        }
    }

    @Test
    public void serverPutMoney() {
        terminalServer.serverPutMoney(BigDecimal.valueOf(60), 12342);
        try {
            assertEquals(60.44, terminalServer.sumAccount(12342).doubleValue(), 0.001D);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}