package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test method for SkipListMap
 * Skeleton of code copied from CSC316 Workshop 5 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 */
public class SkipListMapTest {
    /** Integer Map used for SkipListMap testing */
	private Map<Integer, String> map;
	/** Student Map used for SkipListMap testing*/
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SkipListMap<Integer, String>();
        studentMap = new SkipListMap<Student, Integer>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SkipListMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertFalse(map.isEmpty());
        assertNull(map.put(2, "string2"));
        assertEquals("SkipListMap[2, 3]", map.toString());
        assertEquals(2, map.size());
        
        assertFalse(map.isEmpty());
        assertNull(map.put(4, "string4"));
        assertEquals("SkipListMap[2, 3, 4]", map.toString());
        assertEquals(3, map.size());
        
        assertFalse(map.isEmpty());
        assertEquals("string3", map.put(3, "newString3"));
        assertEquals("SkipListMap[2, 3, 4]", map.toString());
        assertEquals(3, map.size());
        
        assertFalse(map.isEmpty());
        assertEquals("newString3", map.put(3, "string3"));
        assertEquals("SkipListMap[2, 3, 4]", map.toString());
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
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

        assertNull(map.get(10));
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string5", map.get(5));
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
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string4", map.remove(4));
        assertEquals("SkipListMap[1, 2, 3, 5]", map.toString());
        
        assertEquals("string5", map.remove(5));
        assertEquals("SkipListMap[1, 2, 3]", map.toString());
        
        assertNull(map.remove(5));
        
        assertEquals("string1", map.remove(1));
        assertEquals("SkipListMap[2, 3]", map.toString());
    }
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        
        assertTrue(studentMap.isEmpty());
        assertNull(studentMap.put(s4, 1));
        assertNull(studentMap.put(s3, 2));
        assertNull(studentMap.put(s2, 3));
        assertNull(studentMap.put(s5, 4));
        assertNull(studentMap.put(s1, 5));
        
        assertEquals((Integer)5, studentMap.get(s1));
        assertEquals((Integer)3, studentMap.get(s2));
        assertEquals((Integer)2, studentMap.get(s3));
        assertEquals((Integer)1, studentMap.get(s4));
        assertEquals((Integer)4, studentMap.get(s5));
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
        
        Iterator<Integer> it = map.iterator();
        
        assertTrue(it.hasNext());
        assertEquals((Integer)1, it.next());
        
        assertTrue(it.hasNext());
        assertEquals((Integer)2, it.next());
        
        assertTrue(it.hasNext());
        assertEquals((Integer)3, it.next());
        
        try {
        	it.remove();
        	fail("An UnsupportedOperationException should have been thrown");
        } catch (Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
        
        assertTrue(it.hasNext());
        assertEquals((Integer)4, it.next());
        
        assertTrue(it.hasNext());
        assertEquals((Integer)5, it.next());
        
        assertFalse(it.hasNext());
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
        
        Iterator<String> it = map.values().iterator();
        assertTrue(it.hasNext());
        
        assertEquals("string1", it.next());
        
        try {
        	it.remove();
        	fail("An UnsupportedOperationException should have been thrown");
        } catch (Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
    }
}
