package local.home.azav.java;

import org.junit.*;
import static org.junit.Assert.*;

public class SolutionTest {
    private Solution sol;



// Тест для задачи 2037
    @Before
    public void initTest() {
        Solution sol = new Solution();
    }

    @Test
    public void test1() throws Exception {
        sol = new Solution();
        StringBuilder sb = new StringBuilder("sdf,er,ertert,");
        assertEquals("sdf,ertert", sol.sol2037(sb,3));
        sol = null;
    }

    @Test
    public void test2() throws Exception {
        sol = new Solution();
        StringBuilder sb = new StringBuilder(",,");
        assertEquals("", sol.sol2037(sb,3));
        sol = null;
    }
    @Test
    public void test3() throws Exception {
        sol = new Solution();
        StringBuilder sb = new StringBuilder("sdf");
        assertEquals("sdf", sol.sol2037(sb,3));
        sol = null;
    }
    @Test
    public void test4() throws Exception {
        sol = new Solution();
        StringBuilder sb = new StringBuilder("we");
        assertEquals("", sol.sol2037(sb,3));
        sol = null;
    }
    @Test
    public void test5() throws Exception {
        sol = new Solution();
        StringBuilder sb = new StringBuilder("sdf,d");
        assertEquals("sdf", sol.sol2037(sb,2));
        sol = null;
    }


    @After
    public void afterTest() {
        sol = null;
    }
}