package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * 
 * @author Dr. King
 * @author Kal Corwin
 * 
 * @param <E> Sorter type
 */
public interface Sorter<E> {
	
	/**
	 * Method that, when implemented in other classes, will take in and sort an array of integers.
	 * 
	 * @param data data to sort
	 */
	void sort (E[] data);
}
