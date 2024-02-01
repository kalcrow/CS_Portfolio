package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;


/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 * Skeleton of this code was copied from the CSC316 Workshop 7 guide
 *
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class BinarySearchTreeMapTest {

	/** Tree used for testing */
	private BinarySearchTreeMap<Integer, String> tree;
    
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(2, "two");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(2, (int)tree.root().getElement().getKey());
        
        tree.put(5,  "five");
        assertEquals(2, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.right(tree.root()).getElement().getKey());
        
        tree.put(6,  "six");
        assertEquals(3, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        tree.put(1, "one");
        assertEquals(4, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        
        tree.put(4,  "four");
        assertEquals(5, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        
        tree.put(6,  "newSix");
        assertEquals(5, tree.size());
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals("newSix", tree.right(tree.right(tree.root())).getElement().getValue());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	assertTrue(tree.isEmpty());
    	
    	tree.put(2,  "two");
        tree.put(5,  "five");
        tree.put(6, "six");
        tree.put(1, "one");
        tree.put(4, "four");
        tree.put(3, "three");
        tree.put(7,  "seven");
        assertEquals(7, tree.size());
        
        assertEquals("one", tree.get(1));
        assertEquals("two", tree.get(2));
        assertEquals("three", tree.get(3));
        assertEquals("four", tree.get(4));
        assertEquals("five", tree.get(5));
        assertEquals("six", tree.get(6));
        assertEquals("seven", tree.get(7));
        assertNull(tree.get(12));
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        tree.put(2,  "two");
        tree.put(5,  "five");
        tree.put(6, "six");
        tree.put(1, "one");
        tree.put(4, "four");
        tree.put(3, "three");
        tree.put(7,  "seven");
        assertEquals(7, tree.size());
        
       
        
        assertEquals("four", tree.remove(4));
        assertEquals(6, tree.size());
        assertEquals(3, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        
        assertEquals("six", tree.remove(6));
        assertEquals(5, tree.size());
        assertEquals(7, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        assertEquals("five", tree.remove(5));
        assertEquals(4, tree.size());
        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
        
        assertEquals("two", tree.remove(2));
        assertEquals(3, tree.size());
        assertEquals(3, (int)tree.root().getElement().getKey());
    }
    
    /**
     * Test the output of the toString() behavior
     */
    @Test
    public void testToString() {
    	
    	tree.put(2,  "two");
        tree.put(5,  "five");
        tree.put(6, "six");
        assertEquals(3, tree.size());
    	tree.toString();
    }
    
    /**
     * Test the output of the entrySet() behavior
     */
    @Test
    public void testEntrySet() {
    	tree.put(2,  "two");
        tree.put(5,  "five");
        tree.put(6, "six");
        tree.put(1, "one");
        tree.put(4, "four");
        tree.put(3, "three");
        tree.put(7,  "seven");
        assertEquals(7, tree.size());
        
        Iterable<Entry<Integer, String>> it = tree.entrySet();
        Iterator<Entry<Integer, String>> iterator = it.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("one", iterator.next().getValue());
    }
}