package local.home.azav.java.hw25_spring_hibernate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigAndConsoleTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void makeConsole() {
    }
}

//    @Mock
//    Service service;
//
//    @Mock
//    JdbcTemplate jdbcTemplate;
//
//
//    @Test
//    public void testGetUserNames() {
//
//        List<String> userNames = new ArrayList<String>();
//        userNames.add("bob");
//
//        when(service.getJdbcTemplate()).thenReturn(jdbcTemplate);
//        when(jdbcTemplate.query(anyString(), anyObject()).thenReturn(userNames);
//
//        String retVal = Class.getUserNames("test");
//        assertEquals("bob", retVal);
//    }