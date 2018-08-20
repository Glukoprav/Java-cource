package local.home.azav.java.hw1_tasks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Solution2034Test {
    private Solution2034 sol;

    @Before
    public void initTest() {
        sol = new Solution2034();
    }

    @Test
    public void test1sol2034() {
        sol.indMass = 7;
        sol.mass = new int[]{5, 3, 2, 3, 2, 4, 7};
        int[] ints = {1, 1};
        assertArrayEquals(ints, sol.sol2034());
    }

    @Test
    public void curElemWhile() {
        sol.indMass = 7;
        sol.mass = new int[]{5, 3, 2, 3, 2, 4, 7};
        assertEquals(3, sol.curElemWhile(3,3,5,4));
    }
}