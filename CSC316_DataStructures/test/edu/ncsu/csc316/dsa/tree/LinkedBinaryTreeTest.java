package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 * The skeleton for this code was copied from the CSC316 Workshop 6 guide
 *
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class LinkedBinaryTreeTest {

	/** Tree used for testing */
    private LinkedBinaryTree<String> tree;
    /** First Position used for testing */
    private Position<String> one;
    /** Second Position used for testing */
    private Position<String> two;
    /** Third Position used for testing */
    private Position<String> three;
    /** Fourth Position used for testing */
    private Position<String> four;
    /** Fifth Position used for testing */
    private Position<String> five;
    /** Sixth Position used for testing */
    private Position<String> six;
    /** Seventh Position used for testing */
    private Position<String> seven;
    /** Eighth Position used for testing */
    private Position<String> eight;
    /** Ninth Position used for testing */
    private Position<String> nine;
    /** Tenth Position used for testing */
    private Position<String> ten;

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     *            
     *            
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        
        assertEquals("five", tree.set(five, "newString"));
        assertEquals("LinkedBinaryTree[\none\n two\n  six\n  ten\n   seven\n   newString\n three\n  four\n   eight\n   nine\n]", tree.toString());
        
        assertEquals("one", tree.set(one, "newRoot"));
        assertEquals("LinkedBinaryTree[\nnewRoot\n two\n  six\n  ten\n   seven\n   newString\n three\n  four\n   eight\n   nine\n]", tree.toString());
        
        assertEquals("nine", tree.set(nine,  "newLeaf"));
        assertEquals("LinkedBinaryTree[\nnewRoot\n two\n  six\n  ten\n   seven\n   newString\n three\n  four\n   eight\n   newLeaf\n]", tree.toString());
        
        try {
        	tree.set(null, "invalid");
        	fail("An IllegalArgumentException should have been thrown");
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        	assertEquals("Position is not a valid linked binary tree node", e.getMessage());
        	assertEquals("LinkedBinaryTree[\nnewRoot\n two\n  six\n  ten\n   seven\n   newString\n three\n  four\n   eight\n   newLeaf\n]", tree.toString());
        }
        
        tree.setRoot(nine);
        
        assertEquals(nine, tree.root());
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        createTree();
        assertEquals(10, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        
        try {
        	tree.numChildren(null);
        	fail("An IllegalArgumentException should have been thrown");
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        	assertEquals("Position is not a valid linked binary tree node", e.getMessage());
        }
        
        assertEquals(2, tree.numChildren(one));
        assertEquals(2, tree.numChildren(two));
        assertEquals(0, tree.numChildren(six));
        assertEquals(2, tree.numChildren(ten));
        assertEquals(0, tree.numChildren(seven));
        assertEquals(0, tree.numChildren(five));
        assertEquals(1, tree.numChildren(three));
        assertEquals(2, tree.numChildren(four));
        assertEquals(0, tree.numChildren(eight));
        assertEquals(0, tree.numChildren(nine));
       
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        
        assertEquals(one, tree.parent(two));
        assertEquals(one, tree.parent(three));
        
        assertEquals(two, tree.parent(six));
        assertEquals(two, tree.parent(ten));
        
        assertEquals(ten, tree.parent(seven));
        assertEquals(ten, tree.parent(five));
        
        assertEquals(three, tree.parent(four));
        
        assertEquals(four, tree.parent(eight));
        assertEquals(four, tree.parent(nine));
        
        try {
        	tree.parent(null);
        	fail("An IllegalArgumentException should have been thrown");
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        	assertEquals("Position is not a valid linked binary tree node", e.getMessage());
        }
        
        assertNull(tree.parent(one));
        
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        
        assertEquals(seven, tree.sibling(five));
        assertEquals(five, tree.sibling(seven));
        
        assertEquals(six, tree.sibling(ten));
        assertEquals(ten, tree.sibling(six));
        
        assertEquals(three, tree.sibling(two));
        assertEquals(two, tree.sibling(three));
        
        assertEquals(eight, tree.sibling(nine));
        assertEquals(nine, tree.sibling(eight));
        
        assertNull(tree.sibling(one));
        assertNull(tree.sibling(four));
        
        try {
        	tree.sibling(null);
        	fail("An IllegalArgumentException should have been thrown");
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        	assertEquals("Position is not a valid linked binary tree node", e.getMessage());
        }
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(three));
        assertTrue(tree.isInternal(four));
        assertFalse(tree.isInternal(five));
        assertFalse(tree.isInternal(six));
        assertFalse(tree.isInternal(seven));
        assertFalse(tree.isInternal(eight));
        assertFalse(tree.isInternal(nine));
        assertTrue(tree.isInternal(ten));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(two));
        assertFalse(tree.isLeaf(three));
        assertFalse(tree.isLeaf(four));
        assertTrue(tree.isLeaf(five));
        assertTrue(tree.isLeaf(six));
        assertTrue(tree.isLeaf(seven));
        assertTrue(tree.isLeaf(eight));
        assertTrue(tree.isLeaf(nine));
        assertFalse(tree.isLeaf(ten));
        
        try {
        	tree.isLeaf(null);
        	fail("Should have thrown an IllegalArgumentException");
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        	assertEquals("Position is not a valid linked binary tree node", e.getMessage());
        }
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
        assertFalse(tree.isRoot(three));
        assertFalse(tree.isRoot(four));
        assertFalse(tree.isRoot(five));
        assertFalse(tree.isRoot(six));
        assertFalse(tree.isRoot(seven));
        assertFalse(tree.isRoot(eight));
        assertFalse(tree.isRoot(nine));
        assertFalse(tree.isRoot(ten));
        assertFalse(tree.isRoot(null));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        
        Iterable<Position<String>> preOrderList = tree.preOrder();
        
        Iterator<Position<String>> it = preOrderList.iterator(); 
        assertTrue(it.hasNext());
        assertEquals(it.next(), one);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), two);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), six);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), ten);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), seven);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), five);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), three);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), four);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), eight);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), nine);
  
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();

        Iterable<Position<String>> postOrderList = tree.postOrder();
        
        Iterator<Position<String>> it = postOrderList.iterator(); 
        assertTrue(it.hasNext());
        assertEquals(it.next(), six);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), seven);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), five);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), ten);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), two);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), eight);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), nine);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), four);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), three);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), one);
        
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        
        Iterable<Position<String>> inOrderList = tree.inOrder();
        
        Iterator<Position<String>> it = inOrderList.iterator(); 
        assertTrue(it.hasNext());
        assertEquals(it.next(), six);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), two);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), seven);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), ten);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), five);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), one);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), eight);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), four);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), nine);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), three);
        
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
        //TODO complete this test case
    	
    	assertEquals(0, tree.size());
    	assertNull(tree.root());
    	
    	Iterable<Position<String>> inOrderList = tree.inOrder();
        Iterator<Position<String>> it = inOrderList.iterator(); 
        assertFalse(it.hasNext());
    	
    	
    	//try: root, size, one of the traversals?, remove
    }
    
    /**
     * Test method for levelOrder() traversal method
     */
    @Test
    public void testLevelOrder() {
        createTree();
        
        Iterable<Position<String>> levelOrderList = tree.levelOrder(); //issue is potentially within queue methods? 
        //I don't think it could be an issue within AbstractTree.java
        
        Iterator<Position<String>> it = levelOrderList.iterator(); 
        assertTrue(it.hasNext());
        assertEquals(it.next(), one);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), two);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), three);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), six);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), ten);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), four);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), seven);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), five);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), eight);
        
        assertTrue(it.hasNext());
        assertEquals(it.next(), nine);
        
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
    	assertEquals(0, tree.size());
    
    	
    	one = tree.addRoot("one");
    	assertEquals(1, tree.size());
    	two = tree.addLeft(one, "two");
    	assertEquals(2, tree.size());
    	three = tree.addLeft(two, "three");
    	
    	try {
    		tree.addRoot("four");
    		fail("An IllegalArgumentException should have been thrown");
    	} catch (Exception e) {
    		assertTrue(e instanceof IllegalArgumentException);
    		assertEquals("The tree already has a root.", e.getMessage());
    	}
    	
    	try {
    		tree.addLeft(null, null);
    		fail("An IllegalArgumentException should have been thrown");
    	} catch (Exception e) {
    		assertTrue(e instanceof IllegalArgumentException);
        	assertEquals("Position is not a valid linked binary tree node", e.getMessage());
    	}
    	
    	try {
    		tree.addLeft(two, "four");
    		fail("An IllegalArgumentException should have been thrown");
    	} catch (Exception e) {
    		assertTrue(e instanceof IllegalArgumentException);
    		assertEquals("Node already has a left child.", e.getMessage());
    	}
    	
    	 Iterable<Position<String>> inOrderList = tree.inOrder();
         
         Iterator<Position<String>> it = inOrderList.iterator(); 
         assertTrue(it.hasNext());
         assertEquals(it.next(), three);
         
         try {
        	 it.remove();
        	 fail("An UnsupportedOperationException should have been thrown");
         } catch (Exception e) {
        	 assertTrue(e instanceof UnsupportedOperationException);
        	 assertEquals("The remove operation is not supported yet.", e.getMessage());
         }
         
         assertTrue(it.hasNext());
         assertEquals(it.next(), two);
         assertTrue(it.hasNext());
         assertEquals(it.next(), one);
         assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	assertEquals(0, tree.size());
    	
    	one = tree.addRoot("one");
    	assertEquals(1, tree.size());
    	two = tree.addRight(one, "two");
    	assertEquals(2, tree.size());
    	three = tree.addRight(two, "three");
    	try {
    		tree.addRight(null, "four");
    		fail("An IllegalArgumentException should have been thrown");
    	} catch (Exception e) {
    		assertTrue(e instanceof IllegalArgumentException);
        	assertEquals("Position is not a valid linked binary tree node", e.getMessage());
    	}
    	
    	try {
    		tree.addRight(two, "four");
    		fail("An IllegalArgumentException should have been thrown");
    	} catch (Exception e) {
    		assertTrue(e instanceof IllegalArgumentException);
    		assertEquals("Node already has a right child.", e.getMessage());
    	}
    	
    	 Iterable<Position<String>> inOrderList = tree.inOrder();
         
         Iterator<Position<String>> it = inOrderList.iterator(); 
         assertTrue(it.hasNext());
         assertEquals(it.next(), one);
         
         try {
        	 it.remove();
        	 fail("An UnsupportedOperationException should have been thrown");
         } catch (Exception e) {
        	 assertTrue(e instanceof UnsupportedOperationException);
        	 assertEquals("The remove operation is not supported yet.", e.getMessage());
         }
         
         assertTrue(it.hasNext());
         assertEquals(it.next(), two);
         assertTrue(it.hasNext());
         assertEquals(it.next(), three);
         assertFalse(it.hasNext());
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        
        assertEquals("three", tree.remove(three));
        assertEquals(one.getElement(), tree.parent(four).getElement());
        assertEquals(four, tree.right(one));
        assertEquals(9, tree.size());
        
        try {
        	tree.remove(ten);
        	fail("An IllegalArgumentException should have been thrown");
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        	assertEquals("The node has two children", e.getMessage());
        }
        assertEquals(9, tree.size());
        
        assertEquals("seven", tree.remove(seven));
        assertEquals(1, tree.numChildren(ten));
        assertNull(tree.left(ten));
        assertEquals(five, tree.right(ten));
        assertEquals(8, tree.size());
        
        assertEquals("ten", tree.remove(ten));
        assertEquals(five, tree.right(two));
        assertEquals(two, tree.parent(five));
        assertEquals(7, tree.size());
        
        assertEquals("six", tree.remove(six));
        assertNull(tree.left(two));
        assertEquals(6, tree.size());
        
        assertEquals("five", tree.remove(five));
        assertEquals(5, tree.size());
        assertEquals("two", tree.remove(two));
        assertEquals(4, tree.size());
        
        assertEquals("one", tree.remove(one));
        assertEquals(3, tree.size());
        assertEquals(four, tree.root());
        
        
    }
}