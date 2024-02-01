package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * Method which sorts input data by following the radix sort algorithm
	 * 
	 * @param data data to sort
	 */
	@Override
	public void sort(E[] data) {
		int n = data.length;
		int k = 0;
		
		for (int i = 0; i < n; i++) {
			k = Math.max(k, data[i].getId());
		}
		int x = (int)Math.ceil( Math.log(k + 1) / Math.log(10) );
		
		int p = 1;
		for (int j = 1; j <= x; j++) {
			int[] bArray = new int[10];
			for (int i = 0; i < n; i++) {
				bArray[(data[i].getId() / p) % 10 ] = bArray[ (data[i].getId() / p) % 10] + 1;
			}
			
			for (int i = 1; i < 10; i++) {
				bArray[i] = bArray[i - 1] + bArray[i];
			}
			
			@SuppressWarnings("unchecked")
			E[] fArray = (E[])(new Identifiable[n]);
			for (int i = n - 1; i >= 0; i--) {
				fArray[ bArray[ (data[i].getId() / p) % 10 ] - 1 ] = data[i];
				bArray[ (data[i].getId() / p) % 10 ] = bArray[ (data[i].getId() / p) % 10 ] - 1;
			}
			
			for (int i = 0; i < n; i++) {
				data[i] = fArray[i];
			}
			
			p = p * 10;
		}
		
	}
}
