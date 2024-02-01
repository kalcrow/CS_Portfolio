package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test method for StudentReader
 * 
 * @author Dr. King
 * @author Kal Corwin
 */
public class StudentReaderTest {
	
	/**
	 * Method to test readInputAsArray
	 */
	@Test
	public void testReadFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	}
	
	/**
	 * Method to test that StudentReader.readInputAsArray processes line data in the correct order
	 */
	@Test
	public void testLineOrder() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		
		assertEquals("Amber", contents[0].getFirst());
		
		Student sAmber = contents[0];
		
		assertEquals(sAmber.getFirst(), "Amber");
		assertEquals(sAmber.getLast(), "Michael");
		assertEquals(sAmber.getUnityID(), "michaea");
		assertEquals(sAmber.getId(), 1);
		assertEquals(sAmber.getGpa(), 1.1, 0);
		assertEquals(sAmber.getCreditHours(), 10);
	}
}
