package project4;

import static org.junit.Assert.*;

import org.junit.Test;

public class NameTest {

	@Test
	public void testCompareTo() {
		Name name1 = new Name("john", "m", 3);
		Name name2 = new Name("john", "m", 2);
		System.out.println(name1.compareTo(name2));
		Name name3 = new Name("john", "m", 3);
		Name name4 = new Name("mike", "m", 2);
		System.out.println(name3.compareTo(name4));
	}

	@Test
	public void testEqualsObject() {
		Name name1 = new Name("john", "m", 3);
		Name name2 = new Name("john", "m", 3);
		System.out.println(name1.equals(name2));
	}

	@Test
	public void testEqualsIgnoreCount() {
		Name name1 = new Name("john", "m", 3);
		Name name2 = new Name("john", "m", 2);
		System.out.println(name1.equalsIgnoreCount(name2));
		Name name3 = new Name("john", "m", 3);
		Name name4 = new Name("mike", "m", 2);
		System.out.println(name3.equalsIgnoreCount(name4));
	}

}
