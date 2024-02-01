package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/** 
 * Test class for BubbleSorter
 * 
 * @author Kal Corwin
 */
public class BubbleSorterTest {
	
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
	
	/** Student list in ascending natural order - correct order */
	private Student[] studentNOAscending = {s1, s2, s3, s4, s5};
	/** Student list in descending natural order */
	private Student[] studentNODescending = {s5, s4, s3, s2, s1};
	/** Student list in ascending id order - correct order */
	private Student[] studentIdAscending = {s5, s4, s3, s2, s1};
	/** Student list in descending id order */
	private Student[] studentIdDescending = {s1, s2, s3, s4, s5};
	/** Student list in random order */
	private Student[] studentRandom = {s3, s1, s5, s2, s4};
	
	/** BubbleSorter to use in Student tests */
	BubbleSorter<Student> studentSorter;
	/** BubbleSorter to use in Student tests with ID comparator */
	BubbleSorter<Student> studentIdSorter;
	
	/**
	 * Set up method that initializes studentSorter and studentIdSorter
	 */
	@Before
	public void setUp() {
		studentSorter = new BubbleSorter<Student>();
		studentIdSorter = new BubbleSorter<Student>(new StudentIDComparator());
	}
	
	/**
	 * Test method for sort with Students in Natural Order
	 */
	@Test
	public void testSort() {
		studentRandom[0] = s3;
		studentRandom[1] = s1;
		studentRandom[2] = s5;
		studentRandom[3] = s2;
		studentRandom[4] = s4;
		
		studentSorter.sort(studentNOAscending);
		assertTrue(s1.equals(studentNOAscending[0]));
		assertTrue(s2.equals(studentNOAscending[1]));
		assertTrue(s4.equals(studentNOAscending[2]));
		assertTrue(s3.equals(studentNOAscending[3]));
		assertTrue(s5.equals(studentNOAscending[4]));
		
		studentSorter.sort(studentNODescending);
		assertTrue(s1.equals(studentNODescending[0]));
		assertTrue(s2.equals(studentNODescending[1]));
		assertTrue(s4.equals(studentNODescending[2]));
		assertTrue(s3.equals(studentNODescending[3]));
		assertTrue(s5.equals(studentNODescending[4]));
		
		studentSorter.sort(studentRandom);
		assertTrue(s1.equals(studentRandom[0]));
		assertTrue(s2.equals(studentRandom[1]));
		assertTrue(s4.equals(studentRandom[2]));
		assertTrue(s3.equals(studentRandom[3]));
		assertTrue(s5.equals(studentRandom[4]));
	}
	
	/**
	 * Test Method for sort with Students in Id order
	 */
	@Test
	public void testSortId() {
		studentRandom[0] = s3;
		studentRandom[1] = s1;
		studentRandom[2] = s5;
		studentRandom[3] = s2;
		studentRandom[4] = s4;
		
		studentIdSorter.sort(studentIdAscending);
		assertTrue(s5.equals(studentIdAscending[0]));
		assertTrue(s4.equals(studentIdAscending[1]));
		assertTrue(s3.equals(studentIdAscending[2]));
		assertTrue(s2.equals(studentIdAscending[3]));
		assertTrue(s1.equals(studentIdAscending[4]));
		
		studentIdSorter.sort(studentIdDescending);
		assertTrue(s5.equals(studentIdDescending[0]));
		assertTrue(s4.equals(studentIdDescending[1]));
		assertTrue(s3.equals(studentIdDescending[2]));
		assertTrue(s2.equals(studentIdDescending[3]));
		assertTrue(s1.equals(studentIdDescending[4]));
		
		studentIdSorter.sort(studentRandom);
		assertTrue(s5.equals(studentRandom[0]));
		assertTrue(s4.equals(studentRandom[1]));
		assertTrue(s3.equals(studentRandom[2]));
		assertTrue(s2.equals(studentRandom[3]));
		assertTrue(s1.equals(studentRandom[4]));
	}

}
