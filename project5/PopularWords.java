package project5;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * This class encapsulates the entry method to the program. Read the documentation for the main method
 * 		for a description of the program.
 * @author Ignacio Carrillo-Batalla
 *
 */
public class PopularWords {

	/**
	 * This program reads the file specified in the first argument and prints every distinct word in
	 * 		the file and the number of occurrences of it. The order in which the distinct words are 
	 * 		printed is specified by the second argument. "name" prints the words in alphabetical order,
	 * 		"frequency" prints the words in order from most frequent to least frequent, and "scarcity" 
	 * 		prints the words from least frequent to most frequent. The third argument is optional and 
	 * 		specifies the number of words to be printed; if not specified, every word is printed.
	 * @param args first argument is the file pathname, second argument specifies ordering, third
	 * 		argument specifies number of words to be printed
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = null;
		String order = null;
		int numberOfResults;
		
		
		//	gather first two arguments and create file object
		try {
			file = new File("project5/" + args[0]);
			order = args[1].toLowerCase();
			if (!(order.equals("name") || order.equals("frequency") || order.equals("scarcity"))) {
				System.err.println("Second argument must be 'name', 'frequency' or 'scarcity'");
				System.exit(1);
			}
		} catch (IllegalArgumentException ex) {
			System.err.println("First argument should be a valid filepath and the second argument should be a string.");
			System.exit(1);
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.err.println("First and second arguments must not be blank.");
			System.exit(1);
		} 
		
		//	read file and store data
		WordSet dataset = storeWords(file);
		
		//	extract data from hashtable to array
		@SuppressWarnings("unchecked")
		Entry<String, Integer>[] array = (Entry<String, Integer>[])(dataset.entrySet().toArray
				(new Map.Entry[dataset.size()])); 
		
		//	gather data from third argument if there is one; if not, set numberOfResults to array length 
		try {
			numberOfResults = Integer.parseInt(args[2]);
			if (numberOfResults > array.length)
				numberOfResults = array.length;
		} catch (ArrayIndexOutOfBoundsException ex) {
			numberOfResults = array.length;
		} catch (IllegalArgumentException ex) {
			System.err.println("Third argument must be an integer. The program ignored it.");
			numberOfResults = array.length;
		}
	
		//	order array alphabetically
		if (order.equals("name")) {
			Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
				@Override
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					return o1.getKey().compareTo(o2.getKey());
				}
			};
			MSorter.mergeSort(array, comparator);   
		}
		
		//	order array from least frequent to most frequent
		else if (order.equals("scarcity")) {
			Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
				@Override
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					if (!o1.getValue().equals(o2.getValue()))
						return o1.getValue().compareTo(o2.getValue());
					return o1.getKey().compareTo(o2.getKey());
				}
			};
			MSorter.mergeSort(array, comparator);   
		}
		
		//	order array from most frequent to least frequent
		else {
			Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
				@Override
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					if (!o1.getValue().equals(o2.getValue()))
						return o2.getValue().compareTo(o1.getValue());
					return o1.getKey().compareTo(o2.getKey());
				}
			};
			MSorter.mergeSort(array, comparator);   
		}
		
		//	print data to console
		for (int j = 0; j < numberOfResults; j++) {
			System.out.println(array[j].getKey() + " " + array[j].getValue());
		}
		
	}
	
	/**
	 * Stores every distinct word and their frequencies in a HashMap and returns the HashMap. 
	 * 		Words stored are from the file included in the first argument.
	 * @param file The file from which data will come
	 * @return a HashMap with the data stored in it
	 * @throws FileNotFoundException
	 */
	public static WordSet storeWords(File file) throws FileNotFoundException {
		WordSet dataset = new WordSet();
		Scanner input = new Scanner(file);
		input.useDelimiter("[^a-zA-Z’ʼ'_-]|(\\B[’ʼ'_-])|([’ʼ'_-]\\B)");
		
		//	store words in hashtable
		while (input.hasNext()) {
			String next = input.next().toLowerCase();
			if (next.equals("") || next.equals("_") || next.equals("-")) continue;
			dataset.put(next, 1);
		}
		
		input.close();
		return dataset;
	}
	
}




