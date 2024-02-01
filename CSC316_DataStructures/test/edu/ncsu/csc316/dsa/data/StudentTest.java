package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Student
 * 
 * @author Dr. King
 * @author Kal Corwin
 */
public class StudentTest {

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
	
	/** Student with the same information as sOne */
	private Student sOneDupe;
	/** Student with the same information as sFour */
	private Student sFourDupe;
	/** Student with null information */
	private Student nullOne;
	/** Student with null information */
	private Student nullTwo;

	/**
	 * Set up method that initializes Students for testing
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sOneDupe = new Student("OneFirst", "OneLast", 1, 2, 2.0, "twoUnityID" );
		sFourDupe = new Student("FourFirst", "FourLast", 4, 5, 5.0, "fiveUnityID");
		
		nullOne = new Student(null, null, 1, 1, 1.0, null);
		nullTwo = new Student(null, null, 2, 2, 2.0, null);
	}

	/** 
	 * Method to test setFirst
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/** 
	 * Method to test setLast
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Method to test setId
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Method to test setGpa
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Method to test setUnityID
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Method to test compareTo
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertSame(sOne.compareTo(sOne), 0);
		assertSame(sTwo.compareTo(sTwo), 0);
	}
	
	/**
	 * Method to test equals
	 */
	@Test
	public void testEquals() {
		assertTrue(sOne.equals(sOne));
		assertTrue(sOne.equals(sOneDupe));
		assertFalse(sOne.equals(sTwo));
		
		assertTrue(nullOne.equals(nullOne));
		assertFalse(nullOne.equals(nullTwo));
	}
	
	/**
	 * Method to test toString
	 */
	@Test
	public void testToString() {
		String sThreeString = sThree.toString();
		String expected = "ThreeFirst, ThreeLast, 3, 3, 3.0, threeUnityID";
		
		assertTrue(sThreeString.equals(expected));
	}
	
	/**
	 * Method to test hashCode
	 */
	@Test
	public void testHash() {
		int fourHash = sFour.hashCode();
		int fourDupeHash = sFourDupe.hashCode();
		int fiveHash = sFive.hashCode();
		
		assertEquals(fourHash, fourDupeHash);
		assertNotSame(fourHash, fiveHash);
		
	}
}
