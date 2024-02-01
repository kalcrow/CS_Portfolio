package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 * Skeleton of this code was copied from CSC316 Workshop 8 guide
 *
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class LinearProbingHashMapTest {
	/** Map object used for testing */
    private Map<Integer, String> map;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
    	map = new LinearProbingHashMap<Integer, String>();
    	map = new LinearProbingHashMap<Integer, String>(true);
    	map = new LinearProbingHashMap<Integer, String>(7);
        map = new LinearProbingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
    	assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));

        // Since our entrySet method returns the entries in the table
        // from left to right, we can use the entrySet to check
        // that our values are in the correct order in the hash table.
        // Alternatively, you could implement a toString() method if you
        // want to check that the exact index/map of each bucket is correct
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4

        assertNull(map.put(4, "string4"));
        assertNull(map.put(11,  "string11"));
        assertEquals("string4", map.put(4,  "newString4"));
        
        assertEquals(3, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        
        assertEquals("string3", map.get(3));
        assertEquals("newString4", map.get(4));
        assertEquals("string11", map.get(11)); 
        
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        assertEquals(11, (int)it.next().getKey());
        assertFalse(it.hasNext());
        
        assertNull(map.put(13, "string13"));
        assertNull(map.put(20, "string20"));
        assertNull(map.put(14, "string14"));
        
        assertNull(map.put(6,  "string6"));
        assertNull(map.put(21,  "string21"));
        assertNull(map.put(7,  "string7"));
        assertEquals(9, map.size());
        
        it = map.entrySet().iterator();
        
        assertEquals("string3", map.get(3));
        assertEquals("newString4", map.get(4));
        assertEquals("string6", map.get(6));
        assertEquals("string7", map.get(7));
        assertEquals("string11", map.get(11));
        assertEquals("string13", map.get(13));
        assertEquals("string14", map.get(14));
        assertEquals("string20", map.get(20));
        assertEquals("string21", map.get(21));
        
        assertEquals(13, (int)it.next().getKey());
        
        assertEquals(20, (int)it.next().getKey());
        assertEquals(14, (int)it.next().getKey());
        assertEquals(6, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertEquals(11, (int)it.next().getKey());
        assertEquals(21, (int)it.next().getKey());
        assertEquals(7, (int)it.next().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
       assertTrue(map.isEmpty());
        assertTrue(map.isEmpty());
        
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(11,  "string11"));
        assertEquals("string4", map.put(4,  "newString4"));
        assertEquals(3, map.size());
        assertFalse(map.isEmpty());
        
        assertEquals("string3", map.get(3));
        assertEquals("newString4", map.get(4));
        assertEquals("string11", map.get(11)); 
        assertNull(map.get(100));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
    	assertTrue(map.isEmpty());
        
        assertNull(map.put(3, "string3"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(11,  "string11"));
        
        assertEquals(3, map.size());
        assertFalse(map.isEmpty());
        
        assertNull(map.remove(5));
        assertEquals("string4", map.remove(4));
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey());
        assertEquals(11, (int)it.next().getKey());
        assertFalse(it.hasNext());
        
        assertNull(map.put(4,  "newString"));
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertEquals(11, (int)it.next().getKey()); 
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
    	
        Iterator<Integer> it = map.iterator();
        assertFalse(it.hasNext());
        
        assertNull(map.put(3, "string3"));

        assertNull(map.put(4, "string4"));
        assertNull(map.put(11,  "string11"));
        assertEquals("string4", map.put(4,  "newString4"));
        
        assertEquals(3, map.size());
        assertFalse(map.isEmpty());
        it = map.iterator(); 
        
        assertEquals(3, (int)it.next());
        assertEquals(4, (int)it.next());
        assertEquals(11, (int)it.next());

    }
    
    /**
     * Test the output of the entrySet() behavior
     */     
    @Test
    public void testEntrySet() {
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator(); 
        assertFalse(it.hasNext());
        
        assertNull(map.put(3, "string3"));

        assertNull(map.put(4, "string4"));
        assertNull(map.put(11,  "string11"));
        assertEquals("string4", map.put(4,  "newString4"));
        
        assertEquals(3, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator(); 
        
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertEquals(11, (int)it.next().getKey());
        
    }
    
    /**
     * Test the output of the values() behavior
     */  
    @Test
    public void testValues() {
        
        Iterator<String> it = map.values().iterator();
        assertFalse(it.hasNext());
        

        assertNull(map.put(3, "string3"));

        assertNull(map.put(4, "string4"));
        assertNull(map.put(11,  "string11"));
        assertEquals("string4", map.put(4,  "newString4"));
        
        assertEquals(3, map.size());
        assertFalse(map.isEmpty());
        it = map.values().iterator(); 
        
        assertEquals("string3", it.next());
        assertEquals("newString4", it.next());
        assertEquals("string11", it.next());
        
    }
}