package project4;

/**
 * Creates Name objects to store information for every different name-gender combination in each year.
 * @author Ignacio Carrillo-Batalla
 *
 */
public class Name implements Comparable<Name> {
	private String name, gender;
	private int count;
	
	/**
	 * Constructs Name object with specified name, gender and count.
	 * @param name the name
	 * @param gender the gender
	 * @param count the number of babies with gender and name combination
	 */
	public Name(String name, String gender, int count) {
		//	handling errors in dataset
		if (name.isEmpty()) throw new IllegalArgumentException("Dataset contains an empty name.");
		if (!(gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("m")))
			throw new IllegalArgumentException("Dataset contains an invalid gender.");
		if (count < 0) throw new IllegalArgumentException("Dataset contains a negative name count.");
		
		setName(name);
		setGender(gender);
		setCount(count);
	}
	
	/**
	 * Returns -1, 1 or 0 depending on whether this Name should
	 * 		be ordered before or after the specified Name, or is equal
	 * 		to it.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Name other) {
		//	checks if same object is being compared
		if (this == other) 
			return 0;
		
		//	check if name is equal
		if (name.equalsIgnoreCase(other.name)) {
			//	check if gender is equal
			if (gender.equalsIgnoreCase(other.gender))
				//	return count difference
				return count - other.count;			
			else
				return gender.compareToIgnoreCase(other.gender);
		}
		else
			return name.compareToIgnoreCase(other.name);
	}
	
	/**
	 * Returns negative, positive or 0 depending on whether this Name should
	 * 		be ordered before or after the specified Name, or is equal
	 * 		to it.
	 * @param other Name object to compare to
	 * @return Negative int, positive int or 0 depending on ordering
	 */
	public int compareToIgnoreCount(Name other) {
		//	checks if same object is being compared
		if (this == other) 
			return 0;
		
		//	check if name is equal
		if (name.equalsIgnoreCase(other.name)) {
			//	check if gender is equal
			if (gender.equalsIgnoreCase(other.gender))
				//	return count difference
				return 0;	
			else
				return gender.compareToIgnoreCase(other.gender);
		}
		else
			return name.compareToIgnoreCase(other.name);
	}
	
	/** 
	 * Checks if this Name object equals specified Name object.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		//checks if it is same object
		if (this == obj)
			return true;
		
		//checks if obj is null
		if (obj == null)
			return false;
		
		//checks if obj is of Name class
		if (getClass() != obj.getClass())
			return false;
		
		//casts object to Name type
		Name other = (Name) obj;
		
		//checks if count is equal
		if (count != other.count)
			return false;
		
		//checks if gender is null on this object
		if (gender == null) {
			//checks if gender is not null on other object
			if (other.gender != null)
				return false;
		} 
		//checks if this gender does not equal other gender
		else if (!gender.equalsIgnoreCase(other.gender))
			return false;
		
		//checks if name is null on this object
		if (name == null) {
			//checks if name is not null on other object
			if (other.name != null)
				return false;
		}
		//checks if name is not equal
		else if (!name.equalsIgnoreCase(other.name))
			return false;
		
		return true;
	}
	
	/** 
	 * Checks if this Name object equals specified Name object ignoring count.
	 * @param Object to compare to see if is equal
	 * @return true if is equal, false otherwise
	 */
	public boolean equalsIgnoreCount(Name other) {
		//checks if it is same object
		if (this == other)
			return true;
		
		//checks if obj is null
		if (other == null)
			return false;
		
		//checks if gender is null on this object
		if (gender == null) {
			//checks if gender is not null on other object
			if (other.gender != null)
				return false;
		} 
		//checks if this gender does not equal other gender
		else if (!gender.equalsIgnoreCase(other.gender))
			return false;
		
		//checks if name is null on this object
		if (name == null) {
			//checks if name is not null on other object
			if (other.name != null)
				return false;
		}
		//checks if name is not equal
		else if (!name.equalsIgnoreCase(other.name))
			return false;
		
		return true;
	}


	/**
	 * Returns name of Name object.
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Sets name.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/** 
	 * Returns the gender.
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}


	/** 
	 * Sets the gender
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}


	/**
	 * Returns the count.
	 * @return the count
	 */
	public int getCount() {
		return count;
	}


	/**
	 * Sets the count.
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/** 
	 * Returns a string representation of the object in format 
	 * 		name + ", " + gender + ", " + count.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + ", " + gender + ", " + count;
	}
	
}
