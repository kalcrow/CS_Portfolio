package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for StudentIDComparator
 * 
 * @author Dr. King
 * @author Kal Corwin
 */
public class StudentIDComparatorTest {

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

	/** StudentIDComparator for testing */
	private StudentIDComparator comparator;

	/**
	 * Set up method to initialize Student and StudetnIDComparator Objects for testing
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}

	/**
	 * Method to test compare
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);

		assertSame(comparator.compare(sThree, sThree), 0);
		
		assertTrue(comparator.compare(sFive, sFour) > 0);
		assertFalse(comparator.compare(sFour, sFive) > 0);
	}


}
