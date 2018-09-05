package local.home.azav.java.hw9_serialization.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceImplTest {
    private  ServiceImpl service;

    @Before
    public void setUp() {
        service = new ServiceImpl();
    }

    @Test
    public void doHardWork01() {
        assertEquals(12,service.doHardWork01(12));
        assertEquals(111,service.doHardWork01(0));
    }

    @Test
    public void doHardWork02() {
        assertEquals(14,service.doHardWork02(14));
        assertEquals(222,service.doHardWork02(0));
    }

    @Test
    public void doHardWork03() {
        assertEquals(16,service.doHardWork03(16));
        assertEquals(333,service.doHardWork03(-1));
    }

    @Test
    public void doHardWork04() {
        assertEquals(18,service.doHardWork04(18));
        assertEquals(444,service.doHardWork04(0));
    }
}