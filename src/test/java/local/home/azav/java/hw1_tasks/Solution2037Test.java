package local.home.azav.java.hw1_tasks;

import local.home.azav.java.hw1_tasks.Solution2037;
import org.junit.*;
import static org.junit.Assert.*;

// Тест для задачи 2037
public class Solution2037Test {
    private Solution2037 sol;

    @Before
    public void initTest() {
        Solution2037 sol = new Solution2037();
    }

    @Test
    public void test1() throws Exception {
        sol = new Solution2037();
        String sb = "sdf,er,ertert,";
        assertEquals("sdf,ertert", sol.sol2037(sb,3));
        sol = null;
    }

    @Test
    public void test2() throws Exception {
        sol = new Solution2037();
        String sb = ",,";
        assertEquals("", sol.sol2037(sb,3));
        sol = null;
    }
    @Test
    public void test3() throws Exception {
        sol = new Solution2037();
        String sb = "sdf";
        assertEquals("sdf", sol.sol2037(sb,3));
        sol = null;
    }
    @Test
    public void test4() throws Exception {
        sol = new Solution2037();
        String sb = "we";
        assertEquals("", sol.sol2037(sb,3));
        sol = null;
    }
    @Test
    public void test5() throws Exception {
        sol = new Solution2037();
        String sb = "sdf,d";
        assertEquals("sdf", sol.sol2037(sb,2));
        sol = null;
    }

    @After
    public void afterTest() {
        sol = null;
    }
}