package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test method for CountingSorter
 * 
 * @author Dr. King
 * @author Kal Corwin
 */
public class CountingSorterTest {
	
	/** First Student for testing */
	private Student sOne;
	/** Second Student for testing */
	private Student sTwo;
	/** Third Student for testing */
	private Student sThree;
	/** Fourth Student for testing */
	private Student sFour;
	/** Fifth Student for testing */
	private Student sFive;
	
	/** First Student with a long ID for testing */
	private Student sLong1;
	/** Second Student with a long ID for testing */
	private Student sLong2;
	/** Third Student with a long ID for testing*/
	private Student sLong3;
	
	
	/** CountingSorter for testing */
	private CountingSorter<Student> sorter;

	/**
	 * Set up method that initializes Student and Sorter Objects for testing
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sLong1 = new Student("Ginko", "Mushi", 43478932, 15, 3.2, "gmushi");
		sLong2 = new Student("Min", "Lemur", 7322, 15, 3.2, "mlemur");
		sLong3 = new Student("Plush", "Preacher", 111111, 15, 3.2, "ppreach");
		
		sorter = new CountingSorter<Student>();
	}
	
	/**
	 * Test method for sort using Students
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
		
		Student[] forewards = {sOne, sTwo, sThree, sFour, sFive};
		Student[] backwards = { sFive, sFour, sThree, sTwo, sOne};
		
		sorter.sort(forewards);
		assertEquals(sOne, forewards[0]);
		assertEquals(sTwo, forewards[1]);
		assertEquals(sThree, forewards[2]);
		assertEquals(sFour, forewards[3]);
		assertEquals(sFive, forewards[4]);
		
		sorter.sort(backwards);
		assertEquals(sOne, backwards[0]);
		assertEquals(sTwo, backwards[1]);
		assertEquals(sThree, backwards[2]);
		assertEquals(sFour, backwards[3]);
		assertEquals(sFive, backwards[4]);
	}
	
	/**
	 * Test method for sort using Students with long ID numbers
	 */
	@Test
	public void testSortLongStudents() {
		Student[] random = {sLong3, sLong1, sLong2};
		
		sorter.sort(random);
		assertEquals(sLong2, random[0]);
		assertEquals(sLong3, random[1]);
		assertEquals(sLong1, random[2]);
	}

}
