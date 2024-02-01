package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapPriorityQueue
 * Checks the expected outputs of the Priorty Queue abstract data type behaviors when using
 * a min-heap data structure 
 * Skeleton of code copied from CSC316 Workshop 9 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class HeapPriorityQueueTest {

	/** Heap used for testing */
    private PriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */     
    @Before
    public void setUp() {
        heap = new HeapPriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the insert(k,v) behavior
     */     
    @Test
    public void testInsert() {
        assertTrue(heap.isEmpty());
        assertSame(heap.size(), 0);
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());
        
        heap.insert(9, "nine");
        assertEquals(8, (int)heap.min().getKey());
        assertEquals(2, heap.size());
        heap.deleteMin();
        assertEquals(9, (int)heap.min().getKey());
        assertEquals(1, heap.size());
        
        heap.insert(3, "three");
        assertEquals(2, heap.size());
        assertEquals(3, (int)heap.min().getKey());
    }
    
    /**
     * Test the output of the min behavior
     */ 
    @Test
    public void testMin() {
        assertTrue(heap.isEmpty());
        assertSame(heap.size(), 0);
        
        assertNull(heap.min());
        
        heap.insert(7,  "seven");
        assertEquals(7, (int)heap.min().getKey());
        
        heap.insert(6,  "six");
        assertEquals(6, (int)heap.min().getKey());
        
        heap.insert(9,  "nine");
        assertEquals(6, (int)heap.min().getKey());
        
        heap.insert(4,  "four");
        assertEquals(4, (int)heap.min().getKey());
    }
    
    /**
     * Test the output of the deleteMin behavior
     */     
    @Test 
    public void deleteMin() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        assertNull(heap.deleteMin());
        
        heap.insert(7,  "seven");
        heap.insert(6,  "six");
        heap.insert(9,  "nine");
        heap.insert(4,  "four");
        assertEquals(4, heap.size());
        
        assertEquals(4, (int)heap.min().getKey());
        Entry<Integer, String> testMin = heap.deleteMin();
        assertEquals(4, (int)testMin.getKey());
        assertEquals(6, (int)heap.min().getKey());
        assertEquals(3, heap.size());
        
        testMin = heap.deleteMin();
        assertEquals(7, (int)heap.min().getKey());
        assertEquals(6, (int)testMin.getKey());
        assertEquals(2, heap.size());
        
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */ 
    @Test
    public void testStudentHeap() {
        PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
       sHeap.insert(s3, "three");
       assertEquals(s3, (Student)sHeap.min().getKey());
       assertEquals(1, sHeap.size());
       
       sHeap.insert(s5, "five");
       assertEquals(s3, (Student)sHeap.min().getKey());
       assertEquals(2, sHeap.size());
       
       sHeap.insert(s2,  "two");
       assertEquals(s2, (Student)sHeap.min().getKey());
       assertEquals(3, sHeap.size());
       
       sHeap.insert(s4,  "four");
       assertEquals(s2, (Student)sHeap.min().getKey());
       assertEquals(4, sHeap.size());
       
       sHeap.insert(s1,  "one");
       assertEquals(s1, (Student)sHeap.min().getKey());
       assertEquals(5, sHeap.size());
       
       
       assertEquals(s1, sHeap.deleteMin().getKey());
       assertEquals(4, sHeap.size());
       assertEquals(s2, sHeap.deleteMin().getKey());
       assertEquals(3, sHeap.size());
       assertEquals(s3, sHeap.deleteMin().getKey());
       assertEquals(2, sHeap.size());
       assertEquals(s4, sHeap.deleteMin().getKey());
       assertEquals(1, sHeap.size());
       assertFalse(sHeap.isEmpty());
       assertEquals(s5, sHeap.deleteMin().getKey());
       assertEquals(0, sHeap.size());
       assertTrue(sHeap.isEmpty());
       
       assertNull(sHeap.deleteMin());
    }
}