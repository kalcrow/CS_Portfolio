package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * Method which sorts input data by following the counting sort algorithm
	 */
	@Override
	public void sort(E[] data) {
		int n = data.length;
		
		int min = data[0].getId();
		int max = data[0].getId();
		for (int i = 0; i < n; i++) {
			min = Math.min(data[i].getId(), min);
			max = Math.max(data[i].getId(), max);
		}
		
		int k = max - min + 1;
		
		int[] bArray = new int[k];
		for (int i = 0; i < n; i++) {
			bArray[data[i].getId() - min] = bArray[data[i].getId() - min] + 1;
		}
		
		for (int i = 1; i < k; i++) {
			bArray[i] = bArray[i - 1] + bArray[i];
		}
		
		@SuppressWarnings("unchecked")
		E[] fArray = (E[])(new Identifiable[n]);
		for (int i = n - 1; i >= 0; i--) {
			fArray[bArray[data[i].getId() - min] - 1] = data[i];
			bArray[data[i].getId() - min] = bArray[data[i].getId() - min] - 1;
		}
		
		for (int i = 0; i < n; i++) {
			data[i] = fArray[i];
		}
	}
}
