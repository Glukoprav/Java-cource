package local.home.azav.java.hw1_tasks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Solution2019Test {
    private Solution2019 sol;

    @Before
    public void initTest() {
        sol = new Solution2019();
    }

    @Test
    public void sol2019a() {
        assertEquals(7,sol.sol2019(24));
    }

    @Test
    public void sol2019b() {
        assertEquals(11,sol.sol2019(65));
    }

    @Test
    public void sol2019c() {
        assertEquals(2,sol.sol2019(2));
    }
}