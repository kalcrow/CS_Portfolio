package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for RadixSorter
 * 
 * @author Kal Corwin
 */
public class RadixSorterTest {

	/** First Student to use for tests */
	private Student s1 = new Student("Mat", "Cauthon", 5, 12, 2.1, "mcautho");
	/** Fourth Student to use for tests */
	private Student s2 = new Student("Noi", "Doro", 4, 18, 3.4, "ndoro");
	/** Second Student to use for tests */
	private Student s3 = new Student("Shin", "Doro", 3, 16, 3.1, "sdoro");
	/** Third Student to use for tests */
	private Student s4 = new Student("Shin", "Doro", 2, 18, 2.8, "sdoro2");
	/** Fifth Student to use for tests */
	private Student s5 = new Student("Will", "Navidson", 1, 16, 3.4, "wnavids");
	
	/** First Student with a long ID for testing */
	private Student sLong1;
	/** Second Student with a long ID for testing */
	private Student sLong2;
	/** Third Student with a long ID for testing*/
	private Student sLong3;

	/** Student list in ascending id order - correct order*/
	private Student[] studentIdAscending = {s5, s4, s3, s2, s1};
	/** Student list in descending id order */
	private Student[] studentIdDescending = {s1, s2, s3, s4, s5};
	/** Student list in random order */
	private Student[] studentRandom = {s3, s1, s5, s2, s4};
	
	/** RadixSorter object for testing*/
	private RadixSorter<Student> studentSorter;
	
	/**
	 * Set up method that initializes radixSorter Object
	 */
	@Before
	public void setUp() {
		studentSorter = new RadixSorter<Student>();
		
		sLong1 = new Student("Ginko", "Mushi", 43478932, 15, 3.2, "gmushi");
		sLong2 = new Student("Min", "Lemur", 7322, 15, 3.2, "mlemur");
		sLong3 = new Student("Plush", "Preacher", 111111, 15, 3.2, "ppreach");
	}
	
	/**
	 * Test method for sort using Students
	 */
	@Test
	public void testSort() {
		studentSorter.sort(studentIdAscending);
		assertTrue(s5.equals(studentIdAscending[0]));
		assertTrue(s4.equals(studentIdAscending[1]));
		assertTrue(s3.equals(studentIdAscending[2]));
		assertTrue(s2.equals(studentIdAscending[3]));
		assertTrue(s1.equals(studentIdAscending[4]));
		
		studentSorter.sort(studentIdDescending);
		assertTrue(s5.equals(studentIdDescending[0]));
		assertTrue(s4.equals(studentIdDescending[1]));
		assertTrue(s3.equals(studentIdDescending[2]));
		assertTrue(s2.equals(studentIdDescending[3]));
		assertTrue(s1.equals(studentIdDescending[4]));
		
		studentSorter.sort(studentRandom);
		assertTrue(s5.equals(studentRandom[0]));
		assertTrue(s4.equals(studentRandom[1]));
		assertTrue(s3.equals(studentRandom[2]));
		assertTrue(s2.equals(studentRandom[3]));
		assertTrue(s1.equals(studentRandom[4]));
	}

	/**
	 * Test method for sort using Students with long ID numbers
	 */
	@Test
	public void testSortLongStudents() {
		Student[] random = {sLong3, sLong1, sLong2};
		
		studentSorter.sort(random);
		assertEquals(sLong2, random[0]);
		assertEquals(sLong3, random[1]);
		assertEquals(sLong1, random[2]);
	}
}
