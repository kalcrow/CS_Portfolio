package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
//import java.util.NoSuchElementException;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 * Skeleton of this code copied from CSC316 Workshop 2 guide
 *
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class PositionalLinkedListTest {

	/** list to use for testing */
    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
        Position<String> second = list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals(second, list.first());
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
        
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.last());
        
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(first, list.last());
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(second, list.last());
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        
        assertEquals(first, list.first());
        
        Position<String> second = list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals(second, list.first());
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        
        assertEquals(first, list.first());
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(second, list.last());
    }
    
    /**
     * Test the output of the before(position) behavior, including expected exceptions
     */ 
    @Test
    public void testBefore() {
        
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.last());
        
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        
        assertEquals(first, list.before(second));
        assertNull(list.before(first));
    	
    	
    }
    
    /**
     * Test the output of the after(position) behavior, including expected exceptions
     */     
    @Test
    public void testAfter() {

    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.last());
        
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        
        assertEquals(second, list.after(first));
        assertNull(list.after(second));
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddBefore() {
        
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.last());
        
        Position<String> second = list.addLast("two");
        assertEquals(1, list.size());
        
        Position<String> first = list.addBefore(second, "one");
        assertEquals(2, list.size());
        assertEquals(first, list.first());
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddAfter() {
        
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.last());
        
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        
        Position<String> third = list.addAfter(first, "two");
        assertEquals(2, list.size());
        assertEquals(third, list.last());
        
        Position<String> second = list.addAfter(first, "two");
        assertEquals(3, list.size());
        assertEquals(list.after(first), second);
        assertEquals(third, list.last());
    	
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.last());
        
        Position<String> first = list.addLast("zero");
        assertEquals(1, list.size());
        
        Position<String> second = list.addLast("three");
        assertEquals(2, list.size());
        
        list.set(first, "one");
        assertEquals("one", list.first().getElement());
        list.set(second, "two");
        assertEquals("two", list.last().getElement());
    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.last());
        
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Position<String> fourth = list.addLast("four");
        assertEquals(4, list.size());
        
        assertEquals("two", list.remove(second));
        assertEquals(3, list.size());
        assertEquals(first, list.first());
        assertEquals(third, list.after(first));
        assertEquals(fourth, list.last());
        
        assertEquals("one", list.remove(first));
        assertEquals(2, list.size());
        assertEquals(third, list.first());
        assertEquals(fourth, list.last());
        
        assertEquals("four", list.remove(fourth));
        assertEquals(1, list.size());
        assertEquals(third, list.first());
        assertEquals(third, list.last());
        
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
     */     
    @Test
    public void testIterator() {
    	
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.last());
        
        Iterator<String> it = list.iterator();
        
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Position<String> fourth = list.addLast("four");
        assertEquals(4, list.size());
        
        //element iterator
        assertTrue(it.hasNext());
        assertEquals(first.getElement(), it.next());
        assertEquals(second.getElement(), it.next());
        assertEquals(third.getElement(), it.next());
        assertEquals(fourth.getElement(), it.next());
        
        it.remove();
        assertEquals(3, list.size());
        
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());
        
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        
        it.remove();
        assertEquals(2, list.size());
        assertTrue(it.hasNext());
        
        assertEquals(third, it.next());
    }

}