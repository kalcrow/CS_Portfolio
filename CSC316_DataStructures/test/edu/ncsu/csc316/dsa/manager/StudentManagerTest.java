package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.sorter.BubbleSorter;
import edu.ncsu.csc316.dsa.sorter.RadixSorter;

/**
 * Test class for StudentManager
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class StudentManagerTest {

	/** StudentManager used for testing */
	private StudentManager sm;
	/** StudentManager used for custom sorter and comparator testing */
	private StudentManager sm1;
	/** StudentManager used for custom sorter testing */
	private StudentManager sm2;
	
	/**
	 * Set up method to initialize StudentManager object
	 */
	@Before
	public void setUp() {
		sm = new StudentManager("input/student_ascendingID.csv");
		sm1 = new StudentManager("input/student_descendingID.csv", new BubbleSorter<Student>(new StudentGPAComparator()));
		sm2 = new StudentManager("input/student_randomOrder.csv", new RadixSorter<Student>());
	}
	
	/**
	 * Method to test sort
	 */
	@Test
	public void testSort() {
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
	}
	
	/**
	 * Method to test that data is sorted correctly with custom sorters and comparators
	 */
	@Test
	public void testCustomSort() {
		Student[] gpaSorted = sm1.sort();
		
		assertEquals("Nichole", gpaSorted[0].getFirst());
		assertEquals("Alicia", gpaSorted[1].getFirst());
		assertEquals("Charlene", gpaSorted[2].getFirst());
		assertEquals("Cristine", gpaSorted[3].getFirst());
		assertEquals("Dante", gpaSorted[4].getFirst());
		assertEquals("Lacie", gpaSorted[5].getFirst());
		assertEquals("Idalia", gpaSorted[6].getFirst());
		assertEquals("Ara", gpaSorted[7].getFirst());
		assertEquals("Loise", gpaSorted[8].getFirst());
		assertEquals("Tanner", gpaSorted[9].getFirst());
		assertEquals("Amber", gpaSorted[10].getFirst());
		assertEquals("Roxann", gpaSorted[11].getFirst());
		assertEquals("Tyree", gpaSorted[12].getFirst());
		assertEquals("Evelin", gpaSorted[13].getFirst());
		assertEquals("Shanti", gpaSorted[14].getFirst());
		assertEquals("Lewis", gpaSorted[15].getFirst());
		
		
		
		Student[] idSorted = sm2.sort();
		
		assertEquals("Amber", idSorted[0].getFirst());
		assertEquals("Ara", idSorted[1].getFirst());
		assertEquals("Lacie", idSorted[2].getFirst());
		assertEquals("Idalia", idSorted[3].getFirst());
		assertEquals("Evelin", idSorted[4].getFirst());
		assertEquals("Lewis", idSorted[5].getFirst());
		assertEquals("Alicia", idSorted[6].getFirst());
		assertEquals("Tyree", idSorted[7].getFirst());
		assertEquals("Loise", idSorted[8].getFirst());
		assertEquals("Roxann", idSorted[9].getFirst());
		assertEquals("Nichole", idSorted[10].getFirst());
		assertEquals("Charlene", idSorted[11].getFirst());
		assertEquals("Shanti", idSorted[12].getFirst());
		assertEquals("Cristine", idSorted[13].getFirst());
		assertEquals("Tanner", idSorted[14].getFirst());
		assertEquals("Dante", idSorted[15].getFirst());
	}
	

}
