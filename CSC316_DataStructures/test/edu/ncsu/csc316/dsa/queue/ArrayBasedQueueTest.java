package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedQueue.
 * Checks the expected outputs of the Queue abstract data type behaviors when using
 * a circular array-based data structure
 *
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class ArrayBasedQueueTest {

	/** queue used for testing */
    private Queue<String> queue;
    
    /**
     * Create a new instance of a circular array-based queue before each test case executes
     */ 
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }

    /**
     * Test the output of the enqueue(e) behavior
     */     
    @Test
    public void testEnqueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("two");
        assertEquals(2, queue.size());
        
        queue.enqueue("three");
        assertEquals(3, queue.size());
        
        queue.enqueue("four");
        assertEquals(4, queue.size());
        
        queue.enqueue("five");
        assertEquals(5, queue.size());
        
        queue.enqueue("six");
        assertEquals(6, queue.size());
    }
    
    /**
     * Test the output of the dequeue(e) behavior, including expected exceptions
     */     
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }        
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("one", queue.front());
        
        queue.enqueue("two");
        assertEquals(2, queue.size());
        assertEquals("one", queue.front());
        
        assertEquals("one", queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals("two", queue.front()); 
        
        queue.enqueue("three");
        assertEquals(2, queue.size());
        assertEquals("two", queue.front());
        
        queue.enqueue("four");
        assertEquals(3, queue.size());
        assertEquals("two", queue.front());
        
        
        assertEquals("two", queue.dequeue());
        assertEquals(2, queue.size());
        assertEquals("three", queue.front());
        
        assertEquals("three", queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals("four", queue.front());
        
        assertEquals("four", queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
         queue.enqueue("two");
        assertEquals(2, queue.size());
        queue.enqueue("three");
        assertEquals(3, queue.size());
        queue.enqueue("four");
        assertEquals(4, queue.size());
        queue.enqueue("five");
        assertEquals(5, queue.size());
        queue.enqueue("six");
        assertEquals(6, queue.size());
        queue.enqueue("seven");
        assertEquals(7, queue.size());
        queue.enqueue("eight");
        assertEquals(8, queue.size());
        
        assertEquals("one", queue.dequeue());
        assertEquals(7, queue.size());
        assertEquals("two", queue.front());
        
        assertEquals("two", queue.dequeue());
        assertEquals(6, queue.size());
        assertEquals("three", queue.front());
        
        assertEquals("three", queue.dequeue());
        assertEquals(5, queue.size());
        assertEquals("four", queue.front());
        
        assertEquals("four", queue.dequeue());
        assertEquals(4, queue.size());
        assertEquals("five", queue.front());
        
        assertEquals("five", queue.dequeue());
        assertEquals(3, queue.size());
        assertEquals("six", queue.front());
        
        assertEquals("six", queue.dequeue());
        assertEquals(2, queue.size());
        assertEquals("seven", queue.front());
        
        assertEquals("seven", queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals("eight", queue.front());
        
        assertEquals("eight", queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());

        

    }
    
    /**
     * Test the output of the front() behavior, including expected exceptions
     */     
    @Test
    public void testFront() {
    	assertEquals(0, queue.size()); 
    	
    	 try {
              queue.front();
              fail("NoSuchElementException should have been thrown.");        
          } catch (Exception e) {
              assertTrue(e instanceof NoSuchElementException);
          }
    	
    	 //other necessary .front() tests are covered in testDequeue()
    }

}