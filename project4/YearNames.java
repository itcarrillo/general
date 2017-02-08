package project4;

import java.util.*;

/**
 * Creates YearNames objects that are in fact subclasses of MyBST and contain Name objects specifically
 * @author Ignacio Carrillo-Batalla
 *
 */
public class YearNames extends MyBST<Name> {
	private boolean found;
	private int year;
	
	/**
	 * Constructs instance of YearNames with specified year.
	 * @param year the year
	 */
	public YearNames(int year) {
		setYear(year);
	}
	
	/** 
	 * Adds specified object to YearNames tree.
	 * @see project4.MyBST#add(java.lang.Comparable)
	 */
	@Override
	public boolean add(Name name) {
		try {
			if (containsIgnoreCount(name)) throw new 
				IllegalArgumentException();
			found = false;
			root = recAdd(name, root);
			return found;
		} catch (IllegalArgumentException e) {
			System.err.println("There was a name duplicate which system skipped.");
			return false;
		}
	}
	
	/**
	 * Adds specified object to YearNames tree.
	 * @param element Name object to add to tree
	 * @param root root which references tree being operated on
	 * @return root which references new tree
	 */
	private Node<Name> recAdd(Name element, Node<Name> root) {
		if (root == null) {
			//	insertion point found
			root = new Node<Name>(element);
			found = true;
		}
		//	if element to be added is less than or equal to root
		else if (element.compareToIgnoreCount(root.getData()) <= 0)
			root.setLeft(recAdd(element, root.getLeft()));
		//	if element to be added is more than root
		else
			root.setRight(recAdd(element, root.getRight()));
		return root;
	}
	
	/** 
	 * Returns count of babies in year with specified name-gender combination.
	 * @param name name to look for
	 * @param gender gender to look for
	 * @return count of name-gender combination
	 */
	public int getCountByName(String name, String gender) {
		//	check if gender and name are valid
		if (name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
		if (!(gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("m")))
			throw new IllegalArgumentException("Gender must be F, f, m or M.");
		
		Name other = new Name(name, gender, 0);
		Name retrievedName = retrieveIgnoreCount(other);
		
		//	if name is not in year
		if (retrievedName.equals(new Name("none", "f", 0)))
			return 0;
		
		return retrievedName.getCount();
	}
	
	/**
	 * Returns fraction of babies with specified name-gender combination in relation to total count
	 * 		of babies for year.
	 * @param name name to look for
	 * @param gender gender to look for
	 * @return fraction of babies with name-gender combination
	 */
	public double getFractionByName(String name, String gender) {
		return ((double) getCountByName(name, gender)) / getTotalCount(); 
	}
	
	/**
	 * Returns total count of babies in year.
	 * @return total count of babies in year
	 */
	public int getTotalCount() {
		Iterator<Name> itr = iterator();
		int count = 0;
		while (itr.hasNext()) {
			count += itr.next().getCount();
		}
		return count;
	}
	
	/**
	 * Checks if YearNames instance contains specified Name object ignoring count.
	 * @param o Name to look for
	 * @return true if instance does contain Name, false otherwise
	 */
	public boolean containsIgnoreCount(Name o) {
		return recContainsIgnoreCount(o, root);
	}
	
	/**
	 * Checks if YearNames instance contains specified Name object ignoring count.
	 * @param element Name to look for
	 * @param root root which references tree being operated on
	 * @return true if instance does contain Name, false otherwise
	 */
	private boolean recContainsIgnoreCount(Name element, Node<Name> root) {
		if (root == null) return false;
		else if (root.getData().equalsIgnoreCount(element))
			//	does contain
			return true;
		//	if element is less than or equal to root
		else if (element.compareToIgnoreCount(root.getData()) <= 0)
			return recContainsIgnoreCount(element, root.getLeft());
		//	if element is more than root
		else
			return recContainsIgnoreCount(element, root.getRight());
	}
	
	/**
	 * Retrieves, without removing, specified Name object in tree.
	 * @param o Name to retrieve
	 * @return retrieved Name
	 */
	public Name retrieveIgnoreCount(Name o) {
		return recRetrieveIgnoreCount(o, root);
	}
	
	/**
	 * Retrieves, without removing, specified Name object in tree.
	 * @param element Name to retrieve
	 * @param root root which references tree being operated on
	 * @return retrieved Name
	 */
	private Name recRetrieveIgnoreCount(Name element, Node<Name> root) {
		if (root == null) return new Name("none", "f", 0);
		else if (root.getData().equalsIgnoreCount(element))
			return root.getData();
		//	if element is less than or equal to root
		else if (element.compareToIgnoreCount(root.getData()) <= 0)
			return recRetrieveIgnoreCount(element, root.getLeft());
		//	if element is more than root
		else
			return recRetrieveIgnoreCount(element, root.getRight());
	}
	
	/**
	 * Returns the year.
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Returns a string representation of a YearNames object.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "YearNames object for year " + year;
	}
	
	
}

	
