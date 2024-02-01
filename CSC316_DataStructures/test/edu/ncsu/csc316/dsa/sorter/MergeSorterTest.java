package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * Test class for MergeSorter
 * 
 * @author Kal Corwin
 *
 */
public class MergeSorterTest {

	/** String list in ascending order */
	private String[] stringAscending = { "a", "b", "c", "d", "e" };
	/** String list in descending order */
	private String[] stringDescending = { "e", "d", "c", "b", "a" };
	/** String list in a random order */
	private String[] stringRandom = { "d", "a", "e", "c", "b" };
	
	/** First Student to use for tests */
	private Student s1 = new Student("Mat", "Cauthon", 1, 12, 2.1, "mcautho");
	/** Second Student to use for tests */
	private Student s2 = new Student("Noi", "Doro", 4, 18, 3.4, "ndoro");
	/** Third Student to use for tests */
	private Student s3 = new Student("Shin", "Doro", 2, 16, 3.1, "sdoro");
	/** Fourth Student to use for tests */
	private Student s4 = new Student("Shin", "Doro", 3, 18, 2.8, "sdoro2");
	/** Fifth Student to use for tests */
	private Student s5 = new Student("Will", "Navidson", 5, 16, 3.4, "wnavids");
	
	/** Student list in ascending natural order - correct order*/
	private Student[] studentNOAscending = {s1, s2, s3, s4, s5};
	/** Student list in descending natural order */
	private Student[] studentNODescending = {s5, s4, s3, s2, s1};
	/** Student list in ascending GPA order */
	private Student[] studentGpaAscending = {s1, s3, s4, s2, s5};
	/** Student list in descending GPA order - correct order*/
	private Student[] studentGpaDescending = {s2, s5, s3, s4, s1};
	/** Student list in random order */
	private Student[] studentRandom = {s3, s1, s5, s2, s4};
	
	/** SelectionSorter to use in String tests */
	MergeSorter<String> stringSort;
	/** SelectionSorter to use in Student tests */
	MergeSorter<Student> studentSort;
	/** SelectionSorter to use in Student tests with GPA comparator */
	MergeSorter<Student> studentGpaSort;
	
	/**
	 *  Set up method that initializes stringSorter, studentSorter, and studentIdSorter
	 */
	@Before
	public void setUp() {
		stringSort = new MergeSorter<String>();
		studentSort = new MergeSorter<Student>();
		studentGpaSort = new MergeSorter<Student>(new StudentGPAComparator());
	}
	
	/**
	 * Test method for sort with Strings
	 */
	@Test
	public void testSortStrings() {
		
		stringSort.sort(stringAscending);
		assertEquals("a", stringAscending[0]);
		assertEquals("b", stringAscending[1]);
		assertEquals("c", stringAscending[2]);
		assertEquals("d", stringAscending[3]);
		assertEquals("e", stringAscending[4]);
		
		stringSort.sort(stringDescending);
		assertEquals("a", stringDescending[0]);
		assertEquals("b", stringDescending[1]);
		assertEquals("c", stringDescending[2]);
		assertEquals("d", stringDescending[3]);
		assertEquals("e", stringDescending[4]);
		
		
		stringSort.sort(stringRandom);
		assertEquals("a", stringRandom[0]);
		assertEquals("b", stringRandom[1]);
		assertEquals("c", stringRandom[2]);
		assertEquals("d", stringRandom[3]);
		assertEquals("e", stringRandom[4]);
	}
	
	/**
	 * Test method for sort with Students (Natural Order)
	 */
	@Test
	public void testSortStudentsNatural() {
		
		studentRandom[0] = s3;
		studentRandom[1] = s1;
		studentRandom[2] = s5;
		studentRandom[3] = s2;
		studentRandom[4] = s4;
		
		studentSort.sort(studentNOAscending);
		assertTrue(s1.equals(studentNOAscending[0]));
		assertTrue(s2.equals(studentNOAscending[1]));
		assertTrue(s3.equals(studentNOAscending[2]));
		assertTrue(s4.equals(studentNOAscending[3]));
		assertTrue(s5.equals(studentNOAscending[4]));
		
		studentSort.sort(studentNODescending);
		assertTrue(s1.equals(studentNODescending[0]));
		assertTrue(s2.equals(studentNODescending[1]));
		assertTrue(s3.equals(studentNODescending[2]));
		assertTrue(s4.equals(studentNODescending[3]));
		assertTrue(s5.equals(studentNODescending[4]));
		
		studentSort.sort(studentRandom);
		assertTrue(s1.equals(studentRandom[0]));
		assertTrue(s2.equals(studentRandom[1]));
		assertTrue(s3.equals(studentRandom[2]));
		assertTrue(s4.equals(studentRandom[3]));
		assertTrue(s5.equals(studentRandom[4]));
	}
	
	/**
	 * Test method for sort with Students (descending GPA)
	 */
	@Test
	public void testSortStudentsGpa() {
		studentRandom[0] = s3;
		studentRandom[1] = s1;
		studentRandom[2] = s5;
		studentRandom[3] = s2;
		studentRandom[4] = s4;
		
		studentGpaSort.sort(studentGpaDescending);
		assertTrue(s2.equals(studentGpaDescending[0])); //issue here
		assertTrue(s5.equals(studentGpaDescending[1]));
		assertTrue(s3.equals(studentGpaDescending[2]));
		assertTrue(s4.equals(studentGpaDescending[3]));
		assertTrue(s1.equals(studentGpaDescending[4]));
		
		studentGpaSort.sort(studentGpaAscending);
		assertTrue(s2.equals(studentGpaAscending[0]));
		assertTrue(s5.equals(studentGpaAscending[1]));
		assertTrue(s3.equals(studentGpaAscending[2]));
		assertTrue(s4.equals(studentGpaAscending[3]));
		assertTrue(s1.equals(studentGpaAscending[4]));
		
		studentGpaSort.sort(studentRandom);
		assertTrue(s2.equals(studentRandom[0]));
		assertTrue(s5.equals(studentRandom[1]));
		assertTrue(s3.equals(studentRandom[2]));
		assertTrue(s4.equals(studentRandom[3]));
		assertTrue(s1.equals(studentRandom[4]));
	}
}
