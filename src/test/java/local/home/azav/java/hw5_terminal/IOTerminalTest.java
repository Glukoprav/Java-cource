package local.home.azav.java.hw5_terminal;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Scanner;

import static org.junit.Assert.*;

public class IOTerminalTest {
    private IOTerminal ioTer;
    private Scanner scanner;

    @Before
    public void setUp() {
        ioTer = new IOTerminal();
    }

//    @Test
//    public void inTerAcc() {
//        ioTer.inTerAcc();
//    }

//    @Test
//    public void inTerPin() {
//    }
//
//    @Test
//    public void inTerOper() {
//    }
//
//    @Test
//    public void inTerSum() {
//    }

    @Test
    public void outBadSum() {
        ioTer.outBadSum(10);
    }

    @Test
    public void outSumAkk() {
        ioTer.outSumAkk("10");
    }

    @Test
    public void outOk() {
        ioTer.outOk();
    }

    @Test
    public void outMoney() {
        ioTer.outMoney(BigDecimal.valueOf(20.5), BigDecimal.valueOf(30.6));
    }

    @Test
    public void outRep() {
        ioTer.outRep();
    }

    @Test
    public void outRepAcc() {
        ioTer.outRepAcc();
    }

    @Test
    public void outRepPin() {
        ioTer.outRepPin();
    }

    @Test
    public void outRepSumm() {
        ioTer.outRepSumm();
    }

//    @Test
//    public void blocked() {
//
//    }

    @Test
    public void outBlock() {
        ioTer.outBlock(20);
    }

//    @Test
//    public void clearLine() {
//    }

    @Test
    public void outErrIncom() {
        ioTer.outErrIncom("Test");
    }
}