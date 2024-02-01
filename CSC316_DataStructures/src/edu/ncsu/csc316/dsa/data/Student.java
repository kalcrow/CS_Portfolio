package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	
	/** Student's first name */
	private String first;
	/** Student's last name */
	private String last;
	/** Student's id number*/
	private int id;
	/** Student's credit hours */
	private int creditHours;
	/** Student's GPA */
	private double gpa;
	/** Student's unityID */
	private String unityID;
	
	/**
	 * Student constructor, which takes in and sets values for all student attributes.
	 * 
	 * @param first first name to set
	 * @param last last name to set
	 * @param id id number to set
	 * @param creditHours creditHours to set
	 * @param gpa gpa to set
	 * @param unityID unityID to set
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		setFirst(first);
		setLast(last);
		setId(id);
		setCreditHours(creditHours);
		setGpa(gpa);
		setUnityID(unityID);
	}
	
	/**
	 * Method that returns the Student's first name
	 * 
	 * @return Student's first name
	 */
	public String getFirst() {
		return first;
	}
	
	/**
	 * Method that sets the Student's first name
	 * 
	 * @param firstIn first name to set
	 */
	public void setFirst(String firstIn) {
		first = firstIn;
	}
	
	/**
	 * Method that returns the Student's last name
	 * 
	 * @return Student's last name
	 */
	public String getLast() {
		return last;
	}
	
	/**
	 * Method that sets the Student's last name
	 * 
	 * @param lastIn last name to set
	 */
	public void setLast(String lastIn) {
		last = lastIn;
	}
	
	/**
	 * Method that returns the Student's ID number
	 * 
	 * @return Student's ID number
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Method that sets the Student's ID number
	 * 
	 * @param idIn id to set
	 */
	public void setId(int idIn) {
		id = idIn;
	}
	
	/**
	 * Method that returns the Student's credit hours
	 * 
	 * @return Student's credit hours
	 */
	public int getCreditHours() {
		return creditHours;
	}
	
	/**
	 * Method that sets the Student's credit hours
	 * 
	 * @param creditHoursIn creditHours to set
	 */
	public void setCreditHours(int creditHoursIn) {
		creditHours = creditHoursIn;
	}
	
	/**
	 * Method that returns the Student's GPA
	 * 
	 * @return Student's GPA
	 */
	public double getGpa() {
		return gpa;
	}
	
	/**
	 * Method that sets the Student's GPA
	 * 
	 * @param gpaIn gpa to set
	 */
	public void setGpa(double gpaIn) {
		gpa = gpaIn;
	}
	
	/**
	 * Method that returns the Student's unityID
	 * 
	 * @return Student's unityID
	 */
	public String getUnityID() {
		return unityID;
	}
	
	/**
	 * Method that sets the Student's unityID
	 * 
	 * @param unityIDIn unityID to set
	 */
	public void setUnityID(String unityIDIn) {
		unityID = unityIDIn;
	}

	/**
	 * Method to compare two student objects, based on their first name, last name, then ID. Returns a positive value
	 *  if this comes before the input Student, a negative value if this comes after that input, and 0 if the two Students
	 *  are equal.
	 *  
	 *  @param o Student to compare to
	 *  @return int value reflecting comparison
	 */
	@Override
	public int compareTo(Student o) {
		int temp = last.compareTo(o.getLast());
		
		if (this.equals(o)) {
			return 0;
		}
		
		if (temp != 0) {
			return 	temp;
		}
		else {
			temp = first.compareTo(o.getFirst());
			
			if (temp != 0) {
				return temp;
			}
			else {
				if (id > o.getId()) {
					return 1;
				}
				else {
					return -1;
				}
			}
		}
	}

	/**
	 * Method that converts a Student into an int hashCode. Two equal Student objects have the same hashCode
	 * 
	 * @return hashed student information
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		return result;
	}

	/**
	 * Method that checks if the current Student is the same as the given Student object. If both have the same 
	 * last name, first name, and ID, then the method returns true. Otherwise it returns false.
	 * 
	 * @param obj object to compare to
	 * @return true if the Students are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (id != other.id)
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}

	/**
	 * Method that converts the Student object into a String of Student information
	 * 
	 * @return String of student information
	 */
	@Override
	public String toString() {
		return first + ", " + last + ", " + id + ", " + creditHours + ", "
				+ gpa + ", " + unityID;
	}
	
	
}
