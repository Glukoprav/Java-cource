package local.home.azav.java.hw1_tasks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// Тест для задачи 2009
public class Solution2009Test {
    private Solution2009 sol;

    @Test
    public void testApp3() {
        sol = new Solution2009();
        int count = 3;
        int [] mass = {2,4,6};
        assertEquals(6,sol.sol2009(count,mass));
    }

    @Test
    public void testApp4() {
        sol = new Solution2009();
        int count = 4;
        int [] mass = {2,6,6,9};
        assertEquals(17,sol.sol2009(count,mass));
    }

    @Test
    public void testApp9() {
        sol = new Solution2009();
        int count = 9;
        int [] mass = {1,1,1,1,1,1,1,1,1};
        assertEquals(4,sol.sol2009(count,mass));
    }
}