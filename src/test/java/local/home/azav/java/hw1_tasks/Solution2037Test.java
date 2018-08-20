package local.home.azav.java.hw1_tasks;

import local.home.azav.java.hw1_tasks.Solution2037;
import org.junit.*;
import static org.junit.Assert.*;

// Тест для задачи 2037
public class Solution2037Test {
    private Solution2037 sol;

    @Before
    public void initTest() {
        sol = new Solution2037();
    }

    @Test
    public void test1() throws Exception {
        sol.strin = new StringBuilder("sdf,er,ertert,");
        sol.intIn = 3;
        assertEquals("sdf,ertert", sol.sol2037());
        sol = null;
    }

    @Test
    public void test2() throws Exception {
        sol.strin = new StringBuilder(",,");
        sol.intIn = 3;
        assertEquals("", sol.sol2037());
        sol = null;
    }
    @Test
    public void test3() throws Exception {
        sol.strin = new StringBuilder("sdf");
        sol.intIn = 3;
        assertEquals("sdf", sol.sol2037());
        sol = null;
    }
    @Test
    public void test4() throws Exception {
        sol.strin = new StringBuilder("we");
        sol.intIn = 3;
        assertEquals("", sol.sol2037());
        sol = null;
    }
    @Test
    public void test5() throws Exception {
        sol.strin = new StringBuilder("sdf,d");
        sol.intIn = 2;
        assertEquals("sdf", sol.sol2037());
        sol = null;
    }

    @Test
    public void testSelectComma() {
        sol.strin = new StringBuilder("we");
        sol.selectComma(0,0);
        assertEquals("we", sol.strin.toString());
    }

    @Test
    public void testSelectComma2() {
        sol.strin = new StringBuilder(",,we");
        sol.selectComma(0,0);
        assertEquals("we", sol.strin.toString());
    }

    @Test
    public void testStrInProc() {
        sol.strin = new StringBuilder("we");
        sol.intIn = 2;
        assertEquals("we", sol.strInProc(0,0));
    }

    @After
    public void afterTest() {
        sol = null;
    }
}