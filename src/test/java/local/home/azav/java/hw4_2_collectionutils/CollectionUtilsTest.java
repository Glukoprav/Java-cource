package local.home.azav.java.hw4_2_collectionutils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionUtilsTest {

    @Test
    public void addAll() {
        List<Integer> listSource = new ArrayList<>(3);
        listSource.add(1);
        listSource.add(2);
        listSource.add(3);
        List<Integer> listDest = new ArrayList<>(3);
        listDest.add(4);
        listDest.add(5);
        CollectionUtils.addAll(listSource,listDest);
        assertEquals(5,listDest.size());
    }

    @Test
    public void newArrayList() {
        assertNotNull(CollectionUtils.newArrayList());
    }

    @Test
    public void indexOf() {
        List<Integer> listSource = new ArrayList<>(3);
        listSource.add(1);
        listSource.add(2);
        listSource.add(3);
        assertEquals(2,CollectionUtils.indexOf(listSource,3));
    }

    @Test
    public void limit() {
        List<Integer> listSource = new ArrayList<>(3);
        listSource.add(1);
        listSource.add(2);
        listSource.add(3);
        assertEquals(2, CollectionUtils.limit(listSource,2).size());
    }

    @Test
    public void add() {
        List<Integer> listSource = new ArrayList<>(3);
        listSource.add(1);
        listSource.add(2);
        CollectionUtils.add(listSource,3);
        assertEquals(3,listSource.size());
    }

    @Test
    public void removeAll() {
        List<Integer> listSource = new ArrayList<>(3);
        listSource.add(1);
        listSource.add(2);
        listSource.add(3);
        listSource.add(4);
        listSource.add(5);
        List<Integer> listDest = new ArrayList<>(3);
        listDest.add(3);
        listDest.add(4);
        CollectionUtils.removeAll(listSource,listDest);
        assertEquals(3,listSource.size());
    }

    @Test
    public void containsAllTrue() {
        List<Integer> listSource = new ArrayList<>(3);
        listSource.add(1);
        listSource.add(2);
        listSource.add(3);
        listSource.add(4);
        listSource.add(5);
        List<Integer> listDest = new ArrayList<>(3);
        listDest.add(3);
        listDest.add(4);
        assertTrue(CollectionUtils.containsAll(listSource,listDest));
    }

    @Test
    public void containsAllFalse() {
        List<Integer> listSource = new ArrayList<>(3);
        listSource.add(1);
        listSource.add(2);
        listSource.add(3);
        listSource.add(4);
        listSource.add(5);
        List<Integer> listDest = new ArrayList<>(3);
        listDest.add(3);
        listDest.add(6);
        assertFalse(CollectionUtils.containsAll(listSource,listDest));
    }

    @Test
    public void containsAnyTrue() {
        List<Integer> listSource = new ArrayList<>(3);
        listSource.add(1);
        listSource.add(2);
        listSource.add(3);
        listSource.add(4);
        listSource.add(5);
        List<Integer> listDest = new ArrayList<>(3);
        listDest.add(8);
        listDest.add(4);
        assertTrue(CollectionUtils.containsAny(listSource,listDest));
    }

    @Test
    public void containsAnyFalse() {
        List<Integer> listSource = new ArrayList<>(3);
        listSource.add(1);
        listSource.add(2);
        listSource.add(3);
        listSource.add(4);
        listSource.add(5);
        List<Integer> listDest = new ArrayList<>(3);
        listDest.add(9);
        listDest.add(6);
        assertFalse(CollectionUtils.containsAny(listSource,listDest));
    }

    @Test
    public void getTin() {
        CollectionUtils<Long> collectionUtils = new CollectionUtils<>();
        collectionUtils.setTin((long)5);
        assertEquals((long) 5,(long)collectionUtils.getTin());
    }

    @Test
    public void compareTo() {
        CollectionUtils<Integer> collectionUtils = new CollectionUtils<>();
        collectionUtils.setTin(5);
        assertEquals(0, collectionUtils.compareTo(5));
        assertEquals(-1,collectionUtils.compareTo(6));
        assertEquals(1,collectionUtils.compareTo(4));
    }

    @Test
    public void range() {
        List<Integer> listSource = new ArrayList<>(3);
        listSource.add(9);
        listSource.add(2);
        listSource.add(7);
        listSource.add(4);
        listSource.add(5);
        assertEquals("[4, 5, 7]",CollectionUtils.range(listSource,4,7).toString());
    }

    @Test
    public void range1() {
        List<Integer> listSource = new ArrayList<>(3);
        listSource.add(9);
        listSource.add(2);
        listSource.add(7);
        listSource.add(4);
        listSource.add(5);
        Comparator<Integer> comparat = (o1, o2) -> (o1 - o2);
        assertEquals("[4, 5, 7]",CollectionUtils.range(listSource,4,7,comparat).toString());
    }

    @Test
    public void rangeStream() {
        List<Integer> listSource = new ArrayList<>(3);
        listSource.add(9);
        listSource.add(2);
        listSource.add(7);
        listSource.add(4);
        listSource.add(5);
        assertEquals("[4, 5, 7]",CollectionUtils.rangeStream(listSource,4,7).toString());
    }

    @Test
    public void main() {
        String[] args = new String[] {};
        CollectionUtils.main(args);
    }
}