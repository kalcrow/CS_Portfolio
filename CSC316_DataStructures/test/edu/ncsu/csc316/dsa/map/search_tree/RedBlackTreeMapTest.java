package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 * Skeleton of code copied from CSC316 Workshop 7 guide
 *
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class RedBlackTreeMapTest {

	/** Tree used for testing */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(5, "five");
        assertEquals(1, tree.size());
        assertEquals(5, (int)tree.root().getElement().getKey());

    	tree.put(2,  "two");
    	assertEquals(2, tree.size());
    	assertEquals(5, (int)tree.root().getElement().getKey());
    	assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
    	
    	
        tree.put(5,  "five");
        assertEquals(2, tree.size()); 
    	assertEquals(5, (int)tree.root().getElement().getKey());
    	assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
    	
        tree.put(6, "six");
        assertEquals(3, tree.size());
    	assertEquals(5, (int)tree.root().getElement().getKey());
    	assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
    	assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
    	
        tree.put(1, "one");
        
        assertEquals(4, tree.size());
    	assertEquals(5, (int)tree.root().getElement().getKey());
    	assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
    	assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
    	assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        
    	tree.put(4, "four");
    	assertEquals(5, tree.size());
    	
    	tree.put(3, "three");
    	assertEquals(6, tree.size());
    	
    	tree.put(7,  "seven");
    	assertEquals(7, tree.size());
    	tree.put(8,  "eight");
    	assertEquals(8, tree.size());
    	tree.put(0,  "zero");
    	assertEquals(9, tree.size());
    	tree.put(10,  "ten");
    	assertEquals(10, tree.size());
    	tree.put(9,  "nine");
    	assertEquals(11, tree.size());  
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	assertTrue(tree.isEmpty());
    	assertEquals(0, tree.size());
        
        tree.put(5, "five");
        tree.put(2,  "two");
    	tree.put(3,  "three");
        tree.put(6, "six");
        tree.put(1, "one");
    	tree.put(4, "four");
    	
    	assertEquals("one", tree.get(1));
    	assertEquals("two", tree.get(2));
    	assertEquals("three", tree.get(3));
    	assertEquals("four", tree.get(4));
    	assertEquals("five", tree.get(5));
    	assertEquals("six", tree.get(6));
    	
    	assertNull(tree.get(17));
    	
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        

        tree.put(5, "five");
        tree.put(2,  "two");
    	tree.put(3,  "three");
        tree.put(6, "six");
        tree.put(1, "one");
    	tree.put(4, "four");
    	tree.put(7,  "seven");
    	tree.put(8,  "eight");
    	tree.put(0,  "zero");
    	tree.put(10,  "ten");
    	tree.put(9,  "nine");
    	assertEquals(11, tree.size());
    	
        
        assertEquals("three", tree.remove(3));
        assertEquals("one", tree.remove(1));
        assertEquals("four", tree.remove(4));
        assertEquals("five", tree.remove(5));
        assertEquals("six", tree.remove(6));
                
    }
}