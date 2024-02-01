package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 * Skeleton of this code was copied from the CSC316 Workshop 7 guide
 *
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class AVLTreeMapTest {

	/** Tree used for testing */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(5,  "five");
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
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(4, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        
        tree.put(3, "three");
        assertEquals(6, tree.size());
        assertEquals(4, (int)tree.root().getElement().getKey());
        
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        tree.put(0, "zero"); 
        
        tree.put(7,  "seven");
        assertEquals(8, tree.size());
        
        
        tree.put(15, "fifteen");
        tree.put(22, "twenty two");
        tree.put(3, "three");
        tree.put(8, "eight");
        tree.put(11,  "eleven");
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
        assertTrue(tree.isEmpty());
        

        
        tree.put(2,  "two");
        tree.put(5,  "five");
     tree.put(6, "six");
      tree.put(1, "one");
      tree.put(4, "four");
     tree.put(3, "three");
    tree.put(7,  "seven");
    assertEquals(7, tree.size());
        
      assertEquals("one", tree.remove(1));
      assertEquals("six", tree.remove(6));
     assertEquals("three", tree.remove(3));
        
     assertNull(tree.remove(13));   
    }
}