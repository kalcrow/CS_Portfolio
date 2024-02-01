package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * 
 * @author Dr. King
 * @author Kal Corwin
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * Compares students based on id number in ascending order. The method returns 0 if the two
	 * id's are equal, -1 if the first id is less than the second, and 1 otherwise.
	 * 
	 * @param one first student to compare
	 * @param two second student to compare
	 * @return an integer reflecting the comparison
	 */
	@Override
	public int compare(Student one, Student two) {
		int id1 = one.getId();
		int id2 = two.getId();
		
		if (id1 == id2) {
			return 0;
		}
		else if (id1 < id2) {
			return -1;
		}
		else {
			return 1;
		}
	}

}
