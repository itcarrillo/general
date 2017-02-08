package project5;

import java.util.Comparator;
import java.util.Map;

/**
 * This class specifies methods that Sort arrays of Map.Entry<String, Integer> objects according
 * 		to a comparator object.
 * @author Ignacio Carrillo-Batalla
 *
 */
public class MSorter {
	
	/**
	 * Wrapper method for the sort method. Uses a Merge Sort algorithm.The first parameter is the 
	 * 		array being sorted and the second parameter is the comparator object that specifies ordering.
	 * @param array array being sorted
	 * @param comparator comparator object that specifies ordering
	 */
	public static void mergeSort(Map.Entry<String, Integer>[] array, Comparator<Map.Entry<String, Integer>> comparator) {
		Object[] tempArray = new Object[array.length];
		mergeSort(0, array.length - 1, array, tempArray, comparator);
	}
	
	/** 
	 * Merge sort method. 
	 * @param first first index of the part of the array being sorted
	 * @param last last index of the part of the array being sorted
	 * @param array the array being sorted
	 * @param tempArray the temporary array used in the sorting algorithm
	 * @param comparator comparator object that specifies ordering
	 */
	public static void mergeSort(int first, int last, Map.Entry<String, Integer>[] array, 
			Object[] tempArray, Comparator<Map.Entry<String, Integer>> comparator) {
		
		if (first < last) {
			int middle = (first + last) / 2;
			mergeSort(first, middle, array, tempArray, comparator);
			mergeSort(middle + 1, last, array, tempArray, comparator);
			merge(first, middle, middle + 1, last, array, tempArray, comparator);
		}
	}
	
	/**
	 * The merge part of the Merge Sort algorithm. 
	 * @param leftFirst first index of the left half of the array
	 * @param leftLast last index of the left half of the array
	 * @param rightFirst first index of the right half of the array
	 * @param rightLast last index of the right half of the array
	 * @param array the array being sorted
	 * @param tempArray the temporary array used in the sorting algorithm
	 * @param comparator comparator object that specifies ordering
	 */
	@SuppressWarnings("unchecked")
	public static void merge(int leftFirst, int leftLast, int rightFirst, int rightLast, 
			Map.Entry<String, Integer>[] array, Object[] tempArray, Comparator<Map.Entry<String, Integer>> comparator) {
		
		int index = leftFirst;
		int saveFirst = leftFirst;
		
		while ((leftFirst <= leftLast) && (rightFirst <= rightLast)) {
			if (comparator.compare(array[leftFirst], array[rightFirst]) < 0) {
				tempArray[index] = array[leftFirst];
				leftFirst++;
			}
			else {
				tempArray[index] = array[rightFirst];
				rightFirst++;
			}
			index++;
		}
		
		while (leftFirst <= leftLast) {
			tempArray[index] = array[leftFirst];
			leftFirst++;
			index++;
		}
		
		while (rightFirst <= rightLast) {
			tempArray[index] = array[rightFirst];
			rightFirst++;
			index++;
		}
		
		for (index = saveFirst; index <= rightLast; index++)
			array[index] = (Map.Entry<String, Integer>) tempArray[index];
	}
}
