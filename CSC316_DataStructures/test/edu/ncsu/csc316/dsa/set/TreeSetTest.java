package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for TreeSet
 * Checks the expected outputs of the Set abstract data type behaviors when using
 * a balanced search tree data structure 
 * Skeleton of code copied from CSC316 Workshop 10 guide
 *
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class TreeSetTest {

	/** Set used for testing */
    private Set<Integer> set;

    /**
     * Create a new instance of a tree-based set before each test case executes
     */      
    @Before
    public void setUp() {
        set = new TreeSet<Integer>();
    }

    /**
     * Test the output of the add behavior
     */     
    @Test
    public void testAdd() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
        set.add(5);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
        
        set.add(3);
        assertEquals(2, set.size());
        
        set.add(5);
        assertEquals(2, set.size());
        
        set.add(2);
        assertEquals(3, set.size());
    }

    /**
     * Test the output of the contains behavior
     */ 
    @Test
    public void testContains() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        assertTrue(set.contains(5));
        assertTrue(set.contains(10));
        assertTrue(set.contains(15));
        assertTrue(set.contains(20));
        assertTrue(set.contains(25));
        assertFalse(set.contains(100));
        assertFalse(set.contains(1));
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        assertEquals(10, (int)set.remove(10));
        assertEquals(4, set.size());
        
        assertNull(set.remove(100));
        assertEquals(4, set.size());
        
        assertNull(set.remove(10));
        assertEquals(4, set.size());
        
        assertEquals(25, (int)set.remove(25));
        assertEquals(3, set.size());
        
        assertEquals(5, (int)set.remove(5));
        assertEquals(2, set.size());
        
        assertEquals(15, (int)set.remove(15));
        assertFalse(set.isEmpty());
        assertEquals(20, (int)set.remove(20));
        
        assertEquals(0, set.size());
        assertTrue(set.isEmpty());
    }
    
    /**
     * Test the output of the retainAll behavior
     */ 
    @Test
    public void testRetainAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        assertEquals(3, other.size());
        
        set.retainAll(other);
        assertEquals(2, set.size());
        assertFalse(set.contains(5));
        assertTrue(set.contains(10));
        assertFalse(set.contains(15));
        assertTrue(set.contains(20));
        assertFalse(set.contains(25));
        assertFalse(set.contains(30));
    }

    /**
     * Test the output of the removeAll behavior
     */     
    @Test
    public void testRemoveAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        assertEquals(3, other.size());
        
        set.removeAll(other);
        assertEquals(3, set.size());
        assertTrue(set.contains(5));
        assertFalse(set.contains(10));
        assertTrue(set.contains(15));
        assertFalse(set.contains(20));
        assertTrue(set.contains(25));
        assertFalse(set.contains(30));
    }

    /**
     * Test the output of the addAll behavior
     */     
    @Test
    public void testAddAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(30);
        other.add(40);
        other.add(50);
        other.add(10);
        
        assertEquals(4, other.size());
        
        set.addAll(other);
        assertEquals(8, set.size());
    }

    /**
     * Test the output of the iterator behavior
     */ 
    @Test
    public void testIterator() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5); 
        set.add(10);
        set.add(15);        
        set.add(20);        
        set.add(25);
        assertEquals(5, set.size());
        
        Iterator<Integer> it = set.iterator();
        assertTrue(it.hasNext());
        assertEquals(5, (int)it.next()); // should be index 0
        assertEquals(10, (int)it.next()); // should be index 2
        assertEquals(15, (int)it.next()); // should be index 3
        assertEquals(20, (int)it.next()); // should be index 4
        assertEquals(25, (int)it.next()); // should be index 5 
        assertFalse(it.hasNext());
    }
}