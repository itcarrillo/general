package project4;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class YearNamesTest {

	@Test
	public void testAdd() {
		YearNames tree = new YearNames(0);
		tree.add(new Name("i", "f", 0));
		String name = tree.retrieveIgnoreCount(new Name("i", "f", 0)).getName();
		System.out.println(name);
	}

	@Test
	public void testGetCountByName() {
		int count = 0;
		YearNames tree = new YearNames(0);
		tree.add(new Name("i", "f", 5));
		count += tree.getCountByName("i", "f");
		System.out.println(count);
		ArrayList<YearNames> dataset = BabyNames.readFiles(2008, 8);
		System.out.println(dataset.get(2).retrieveIgnoreCount(new Name("john", "m", 0)));
		int count2 = dataset.get(0).getCountByName("john", "m");
		System.out.println("count " + count2);
	}
	
	@Test
	public void testGetFractionByName() {
		ArrayList<YearNames> dataset = BabyNames.readFiles(2008, 8);
		double count2 = dataset.get(0).getFractionByName("john", "m");
		System.out.println("count2 " + count2);
	}
	
	@Test //fixed
	public void testRetrieveIgnoreCount() {
		ArrayList<YearNames> dataset = BabyNames.readFiles(2014, 2);
		System.out.println(dataset.get(0).first());
		Name retrieved = dataset.get(0).retrieveIgnoreCount(new Name("Zyyon", "M", 0));
		System.out.println(retrieved);
	}

	@Test
	public void testGetTotalCount() {
		ArrayList<YearNames> dataset = BabyNames.readFiles(2014, 2);
		int count = dataset.get(1).getTotalCount();
		System.out.println(count);
	}
	
	@Test
	public void testContainsIgnoreCount() {
		YearNames set = new YearNames(0);
		set.add(new Name("john", "m", 2));
		System.out.println(set.containsIgnoreCount(new Name("john", "m", 5)));
	}
	


}
