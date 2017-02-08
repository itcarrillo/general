package project5;

import java.util.HashMap;

/**
 * This class extends the HashMap class. The only difference it makes to the superclass
 * 		is that it overrides the put method to make it more appropriate for the purposes
 * 		of this program.
 * @author Ignacio Carrillo-Batalla
 *
 */
public class WordSet extends HashMap<String, Integer> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Associates the specified key with the specified value unless the specified key 
	 * 		is already in the HashMap; in that case, it increments the value by 1. 
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Integer put(String key, Integer count) {
		if (containsKey(key))
			replace(key, get(key) + 1);
		else
			super.put(key, count);
		return 0;
	}
	
}
