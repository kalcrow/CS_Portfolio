package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 * The skeleton for this code was copied from the CSC316 Workshop 5 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class UnorderedLinkedMapTest {
    /** Integer Map used for UnorderedLinkedMap testing */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertFalse(map.isEmpty());
        assertEquals("string3", map.put(3, "newEntry3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertFalse(map.isEmpty());
        assertNull(map.put(4, "string4"));
        assertEquals("UnorderedLinkedMap[4, 3]", map.toString());
        assertEquals(2, map.size());
        
        assertFalse(map.isEmpty());
        assertNull(map.put(2, "string2"));
        assertEquals("UnorderedLinkedMap[2, 4, 3]", map.toString());
        assertEquals(3, map.size());
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string5", map.get(5));
        assertEquals("UnorderedLinkedMap[5, 1, 4, 2, 3]", map.toString());
        
        assertEquals("string3", map.get(3));
        assertEquals("UnorderedLinkedMap[3, 5, 1, 4, 2]", map.toString());
        
        assertEquals("string5", map.get(5));
        assertEquals("UnorderedLinkedMap[5, 3, 1, 4, 2]", map.toString());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string4", map.remove(4));
        assertEquals("UnorderedLinkedMap[1, 2, 5, 3]", map.toString());
        
        assertEquals("string3", map.remove(3));
        assertEquals("UnorderedLinkedMap[1, 2, 5]", map.toString());
        
        assertNull(map.remove(4));
        
        assertEquals("string1", map.remove(1));
        assertEquals("UnorderedLinkedMap[2, 5]", map.toString());
        //TODO complete this test case
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));

        //TODO complete this test case
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));

        try {
        	it.remove();
        	fail("An UnsupportedOperationException should have been thrown");
        } catch (Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        //TODO complete this test case
    }
}