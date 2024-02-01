package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class StudentGPAComparator implements Comparator<Student> {

	/**
	 * Compares students based on GPA in descending order. Returns -1 if the first student's GPA is higher than the second, 
	 * and 1 otherwise. If the two GPA's are equal the students follow the natural order.
	 * 
	 * @param one first Student to compare
	 * @param two second Student to compare
	 * @return int reflecting the comparison of the two Students
	 */
	@Override
	public int compare(Student one, Student two) {
		double gpa1 = one.getGpa();
		double gpa2 = two.getGpa();
		
		if (gpa1 == gpa2) {
			return one.compareTo(two);
		}
		else if (gpa1 > gpa2) {
			return -1;
		}
		else {
			return 1;
		}
	}

}
