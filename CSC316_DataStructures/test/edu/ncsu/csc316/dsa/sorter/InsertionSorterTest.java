package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test class for InsertionSorter
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class InsertionSorterTest {

	/** Integer list in ascending order */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** Integer list in descending order */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** Integer list in a random order */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	
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
	
	/** Student list in ascending natural order - correct order*/
	private Student[] studentNOAscending = {s1, s2, s3, s4, s5};
	/** Student list in descending natural order */
	private Student[] studentNODescending = {s5, s4, s3, s2, s1};
	/** Student list in ascending id order - correct order*/
	private Student[] studentIdAscending = {s5, s4, s3, s2, s1};
	/** Student list in descending id order */
	private Student[] studentIdDescending = {s1, s2, s3, s4, s5};
	/** Student list in random order */
	private Student[] studentRandom = {s3, s1, s5, s2, s4};

	/** InsertionSorter to use in Integer tests */
	private InsertionSorter<Integer> integerSorter;
	/** InsertionSorter to use in Student tests */
	private InsertionSorter<Student> studentSorter;
	/** InsertionSorter to use in Student tests with ID comparator */
	private InsertionSorter<Student> studentIdSorter;

	/**
	 * Set up method that initializes integerSorter, studentSorter, and studentIdSorter
	 */
	@Before
	public void setUp() {
		integerSorter = new InsertionSorter<Integer>();
		studentSorter = new InsertionSorter<Student>();
		studentIdSorter = new InsertionSorter<Student>(new StudentIDComparator());
	}

	/**
	 * Test method for sort with integers
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals(Integer.valueOf(1), dataAscending[0]);
		assertEquals(Integer.valueOf(2), dataAscending[1]);
		assertEquals(Integer.valueOf(3), dataAscending[2]);
		assertEquals(Integer.valueOf(4), dataAscending[3]);
		assertEquals(Integer.valueOf(5), dataAscending[4]);

		integerSorter.sort(dataDescending);
		assertEquals(Integer.valueOf(1), dataDescending[0]);
		assertEquals(Integer.valueOf(2), dataDescending[1]);
		assertEquals(Integer.valueOf(3), dataDescending[2]);
		assertEquals(Integer.valueOf(4), dataDescending[3]);
		assertEquals(Integer.valueOf(5), dataDescending[4]);

		integerSorter.sort(dataRandom);
		assertEquals(Integer.valueOf(1), dataRandom[0]);
		assertEquals(Integer.valueOf(2), dataRandom[1]);
		assertEquals(Integer.valueOf(3), dataRandom[2]);
		assertEquals(Integer.valueOf(4), dataRandom[3]);
		assertEquals(Integer.valueOf(5), dataRandom[4]);
	}

	/**
	 * Test method for sort with Students in Natural Order
	 */
	@Test
	public void testSortStudent() {
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
	 * Test method for sort with Students by Id
	 */
	@Test
	public void testSortStudentId() {
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
