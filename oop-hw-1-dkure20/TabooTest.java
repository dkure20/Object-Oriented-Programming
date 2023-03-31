// TabooTest.java
// Taboo class tests -- nothing provided.

import java.util.*;

import junit.framework.TestCase;

public class TabooTest extends TestCase {
    public void testTaboo1(){
        List<Integer> rules = Arrays.asList(1, 2, 1,3,5,1, 7,7,8,9);
        Taboo<Integer> taboo = new Taboo<>(rules);
        Set<Integer> setFor1 = taboo.noFollow(1);
        assertTrue(setFor1.contains(2));
        assertTrue(setFor1.contains(3));
        assertTrue(setFor1.contains(7));
        assertFalse(setFor1.contains(9));
        Set<Integer> setFor2 = taboo.noFollow(5);
        assertTrue(setFor2.contains(1));
        assertFalse(setFor2.contains(3));
        Set<Integer> setFor3 = taboo.noFollow(9);
        assertFalse(setFor3.contains(1));
        assertFalse(setFor3.contains(2));
    }
    public void testNoFollow2() {
        List<Integer> rules = Arrays.asList(1, 2, 3, 1, 2, 1, 3, 1, 2, 1, 3, 3, 2, 1, 1, 2, 2);
        Taboo<Integer> taboo = new Taboo<>(rules);

        Set<Integer> setFor1 = taboo.noFollow(1);
        assertTrue(setFor1.contains(1) && setFor1.contains(3));
        assertTrue( setFor1.contains(2));
        Set<Integer> setFor2 = taboo.noFollow(2);
        assertTrue(setFor2.contains(1) && setFor2.contains(2));
        assertTrue(setFor2.contains(3));

    }
    public void testNoFollow3() {
        List<String> rules = Arrays.asList("a","b",null , "c", "d", null, "e");
        Taboo<String> taboo = new Taboo<>(rules);

        Set<String> setFor1 = taboo.noFollow("b");
        assertFalse(setFor1.contains("c"));
        Set<String> setFor2 = taboo.noFollow("c");
        assertTrue(setFor2.contains("d"));
        assertFalse(setFor2.contains("e"));
    }
    public void testreduceBasicTest() {
        List<String> list = Arrays.asList("a", "c", "a", "b");
        Taboo<String> taboo = new Taboo<String>(list);
        List<String> reducedList = new ArrayList<String>(Arrays.asList("a", "c", "b", "x", "c", "a"));
        taboo.reduce(reducedList);
        List<String> resultList = new ArrayList<String>(Arrays.asList("a", "x", "c"));
        assertEquals(resultList, reducedList);
    }
    public void testReduceOther() {
        List<String> list = Arrays.asList("a","b", "a","d","o","k","o", "d");
        Taboo<String> taboo = new Taboo<String>(list);
        List<String> reducedList = new ArrayList<String>(Arrays.asList("a", "d", "b", "x", "c", "o","k","d"));
        taboo.reduce(reducedList);
        List<String> resultList = new ArrayList<String>(Arrays.asList("a", "x", "c","o"));
        assertEquals(resultList, reducedList);
    }
    public void testReduceOther2() {
        List<String> list = Arrays.asList("a","b", null, "c", null, "d", "e","d","b","k");
        Taboo<String> taboo = new Taboo<String>(list);
        List<String> reducedList = new ArrayList<String>(Arrays.asList("a", "d", "b","c", "o", "d", "e","b","k"));
        taboo.reduce(reducedList);
        List<String> resultList = new ArrayList<String>(Arrays.asList("a", "d","c","o","d","k"));
        assertEquals(resultList, reducedList);
    }
    public void testReduceOther3() {
        List<String> list = Arrays.asList("a","a");
        Taboo<String> taboo = new Taboo<String>(list);
        List<String> reducedList = new ArrayList<String>(Arrays.asList("a", "a", "a","a", "a", "d", "e","b","k"));
        taboo.reduce(reducedList);
        List<String> resultList = new ArrayList<String>(Arrays.asList("a", "d", "e","b","k"));
        assertEquals(resultList, reducedList);
    }

}
