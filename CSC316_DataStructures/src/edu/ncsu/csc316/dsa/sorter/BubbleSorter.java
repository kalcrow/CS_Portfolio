package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.data.AbstractComparisonSorter;

/** 
 * Class which uses the bubble sort algorithm to sort data
 * 
 * @author Kal Corwin
 *
 * @param <E> type of data being sorted (must be Comparable)
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> implements Sorter<E> {

	/**
	 * Constructor which takes no input, and delegates to the superclass's constructor
	 */
	public BubbleSorter() {
		super(null);
	}
	
	/**
	 * Constructor which takes a comparator as input, which it passes through to the superclass's constructor
	 * 
	 * @param comparator comparator to set
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Method which sorts input data by following the bubble sort algorithm
	 * 
	 * @param data data to sort
	 */
	@Override
	public void sort(E[] data) {
		int n = data.length;
		boolean r = true;
		E x;
		
		while (r) {
			r = false;
			for (int i = 1; i < n; i++) {
				if (compare(data[i], data[i - 1]) < 0) {
					x = data[i - 1];
					data[i - 1] = data[i];
					data[i] = x;
					r = true;
				}
			}
		}	
	}

}
