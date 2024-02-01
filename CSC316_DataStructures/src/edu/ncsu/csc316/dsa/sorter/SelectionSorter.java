package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.data.AbstractComparisonSorter;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * 
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Constructor that takes no input and delegates to the superclass constructor. Since no comparator is specified the 
	 * Natural Order is used.
	 */
	SelectionSorter() {
		super(null);
	}
	
	/**
	 * Constructor that takes a comparator as input and delegates to the superclass constructor
	 * This code was copied from the CSC 316 Workshop 1 guide
	 * 
	 * @param comparator comparator to set
	 */
	public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
    
    /**
     * Method that sorts the data within the given Array into ascending order, based off of the comparator being used.
     * The method increments through the slots in the array, at each one searching the remainder of the Array for the minimum
     * value and moving that value to the slot it is currently on.
     * 
     *  @param data Array of data to sort
     */
	@Override
	public void sort(E[] data) {
		
		int n = data.length;
		
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (compare(data[j], data[min]) < 0) {
					min = j;
				}
			}
			if (i != min) {
				E x = data[i];
				data[i] = data[min];
				data[min] = x;
			}
		}	
	}
}
