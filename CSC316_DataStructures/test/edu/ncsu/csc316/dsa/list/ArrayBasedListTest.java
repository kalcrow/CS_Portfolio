package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;


/**
 * Test class for ArrayBasedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class ArrayBasedListTest {

	/** list used for tests */
    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new ArrayBasedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        assertFalse(list.isEmpty());
        
        list.add(0, "zero");
        assertEquals(3, list.size());
        assertEquals("zero", list.get(0));
        assertEquals("one", list.get(1));
        assertEquals("two", list.get(2));
        assertFalse(list.isEmpty());
        
        list.add(3, "four");
        assertEquals(4, list.size());
        assertEquals("zero", list.get(0));
        assertEquals("one", list.get(1));
        assertEquals("two", list.get(2));
        assertEquals("four", list.get(3));
        assertFalse(list.isEmpty());
        
        list.add(3, "three");
        assertEquals(5, list.size());
        assertEquals("zero", list.get(0));
        assertEquals("one", list.get(1));
        assertEquals("two", list.get(2));
        assertEquals("three", list.get(3));
        assertEquals("four", list.get(4));
        assertFalse(list.isEmpty());
        
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try{
            list.add(-1,  "negative one");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
    	
    	assertTrue(list.isEmpty());
    	
    	list.addLast("zero");
    	
    	assertEquals(1, list.size());
        assertEquals("zero", list.get(0));
        assertFalse(list.isEmpty());
        
        list.addLast("one");
        
        assertEquals(2, list.size());
        assertEquals("zero", list.get(0));
        assertEquals("one", list.get(1));
        assertFalse(list.isEmpty());
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	
    	assertTrue(list.isEmpty());
    	
    	try {
    		list.last();
    		fail("An IndexOutOfBoundsException should have been thrown");
    	} catch (Exception e) {
    		assertTrue(e instanceof IndexOutOfBoundsException);
    	}
    	
    	list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.last());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.last());
        
        list.add(0, "zero");
        assertEquals(3, list.size());
        assertEquals("two", list.last());
        
        list.add(3, "four");
        assertEquals(4, list.size());
        assertEquals("four", list.last());
        
        list.add(3, "three");
        assertEquals(5, list.size());
        assertEquals("four", list.last());
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {

    	assertTrue(list.isEmpty());
    	
    	list.addFirst("one");
    	
    	assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.addFirst("zero");
        
        assertEquals(2, list.size());
        assertEquals("zero", list.get(0));
        assertEquals("one", list.get(1));
        assertFalse(list.isEmpty());
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
       
    	assertTrue(list.isEmpty());
    	
    	try {
    		list.first();
    		fail("An IndexOutOfBoundsException should have been thrown");
    	} catch (Exception e) {
    		assertTrue(e instanceof IndexOutOfBoundsException);
    	}
    	
    	list.add(0, "two");
        assertEquals(1, list.size());
        assertEquals("two", list.first());
        
        list.add(0, "one");
        assertEquals(2, list.size());
        assertEquals("one", list.first());
        
        list.add(0, "zero");
        assertEquals(3, list.size());
        assertEquals("zero", list.first());
        
        list.add(3, "four");
        assertEquals(4, list.size());
        assertEquals("zero", list.first());
        
        list.add(3, "three");
        assertEquals(5, list.size());
        assertEquals("zero", list.first());
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        
        it.remove();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        
        assertFalse(it.hasNext());
        
        list.addLast("one");
        list.addLast("two");
        list.addLast("three");
        list.addLast("four");
        assertEquals(4, list.size());
        
        Iterator<String> itTwo = list.iterator();
        
        assertEquals("one", itTwo.next());
        assertEquals("two", itTwo.next());
        itTwo.remove();
        assertEquals(3, list.size());
        assertEquals("one", list.get(0));
        assertEquals("three", list.get(1));
        assertEquals("four", list.get(2));
        assertEquals("three", itTwo.next());
        assertEquals("four", itTwo.next());
        
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	
    	list.add(0, "one");
        assertEquals(1, list.size());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        
        list.add(0, "zero");
        assertEquals(3, list.size());
        
        list.add(3, "four");
        assertEquals(4, list.size());
        
        list.add(3, "three");
        assertEquals(5, list.size());
        
        try{
            list.remove(5);
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try{
            list.remove(-1);
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        assertEquals("three", list.remove(3));
        assertEquals(4, list.size());
        assertEquals("zero", list.get(0));
        assertEquals("one", list.get(1));
        assertEquals("two", list.get(2));
        assertEquals("four", list.get(3));
        
        assertEquals("zero", list.remove(0));
        assertEquals(3, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals("four", list.get(2));
        
        assertEquals("four", list.remove(2));
    	assertEquals(2, list.size());
    	assertEquals("one", list.get(0));
    	assertEquals("two", list.get(1));
    	
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
  
    	assertTrue(list.isEmpty());
    	
    	try{
            list.removeFirst();
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    	
    	list.add(0, "one");
        assertEquals(1, list.size());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        
        list.add(0, "zero");
        assertEquals(3, list.size());
        
        list.add(3, "four");
        assertEquals(4, list.size());
        
        list.add(3, "three");
        assertEquals(5, list.size());
        
        assertEquals("zero", list.removeFirst());
        assertEquals(4, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals("three", list.get(2));
        assertEquals("four", list.get(3));
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
    	assertTrue(list.isEmpty());
    	
    	try{
            list.removeLast();
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    	
    	list.add(0, "one");
        assertEquals(1, list.size());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        
        list.add(0, "zero");
        assertEquals(3, list.size());
        
        list.add(3, "four");
        assertEquals(4, list.size());
        
        list.add(3, "three");
        assertEquals(5, list.size());
        
        assertEquals("four", list.removeLast());
        assertEquals(4, list.size());
        assertEquals("zero", list.get(0));
        assertEquals("one", list.get(1));
        assertEquals("two", list.get(2));
        assertEquals("three", list.get(3));
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
    
    	assertTrue(list.isEmpty());
    	
    	list.add(0, "one");
        assertEquals(1, list.size());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        
        list.add(0, "zero");
        assertEquals(3, list.size());
        
        list.add(3, "four");
        assertEquals(4, list.size());
        
        list.add(3, "three");
        assertEquals(5, list.size());
        
        try{
            list.set(5, "five");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try{
            list.set(-1, "negative one");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        list.set(0, "twelve");
        assertEquals(5, list.size());
        assertEquals("twelve", list.get(0));
        assertEquals("one", list.get(1));
        assertEquals("two", list.get(2));
        assertEquals("three", list.get(3));
        assertEquals("four", list.get(4));
        
        list.set(2, "fifty-four");
        assertEquals(5, list.size());
        assertEquals("twelve", list.get(0));
        assertEquals("one", list.get(1));
        assertEquals("fifty-four", list.get(2));
        assertEquals("three", list.get(3));
        assertEquals("four", list.get(4));
        
        list.set(4, "apple");
        assertEquals(5, list.size());
        assertEquals("twelve", list.get(0));
        assertEquals("one", list.get(1));
        assertEquals("fifty-four", list.get(2));
        assertEquals("three", list.get(3));
        assertEquals("apple", list.get(4));
        
    }
}