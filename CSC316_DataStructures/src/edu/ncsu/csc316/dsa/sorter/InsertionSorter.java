package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.data.AbstractComparisonSorter;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Kal Corwin
 * @param <E> type of objects to be sorted
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> implements Sorter<E> {

	
	/**
	 * Constructor for an InsertionSorter object which takes in a comparator that will be used to determine the sorted order of inputs.
	 * This code was copied from the CSC 316 Workshop 1 guide
	 * 
	 * @param comparator comparator to set
	 */
	 public InsertionSorter(Comparator<E> comparator) {
	        super(comparator);
	    }
	 /**
	 * InsertionSorter constructor that takes no input
	 * This code was copied from the CSC 316 Workshop 1 guide
	 * */
	public InsertionSorter() {
		super(null);
	}
	
	/**
	 * This method takes the input array of data and moves through it sequentially, shifting each element forward until 
	 * the element before it is less than it, ending with the array sorted in ascending order.
	 * 
	 * @param data data to sort
	 */
	@Override
	public void sort(E[] data) {
		int n = data.length;
		E x;
		int j;
		
		for (int i = 1; i < n; i++) {
			x = data[i];
			j = i - 1;
			
			while ( j >= 0 && compare(data[j], x) > 0) {
				data[j + 1] = data[j];
				j--;
			}
			
			data[j + 1] = x;
		}	
	}
}
