package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class encapsulates the main method and the static methods it calls.
 * The program stores a dataset of baby names since a specified year to another specified year in memory
 * and then runs in a loop asking the user to enter a name and gender and subsequently showing the percentage
 * of babies named as specified over the total number of babies for every year between specified years inclusively.
 */
public class BabyNames {

	/**
	 * Main method
	 * @param args first argument is start year, second argument is end year. If left empty, 
	 * 		program uses 1880 as start year and 2015 as end year
	 */
	public static void main(String[] args) {
		//	set baseYear and numberOfYears
		int baseYear = 0;
		int numberOfYears = 0;
		
		try {
			baseYear = Integer.parseInt(args[0]);
			numberOfYears = Integer.parseInt(args[1]) - baseYear + 1;
		} catch (IndexOutOfBoundsException ex) {
			baseYear = 1880;
			numberOfYears = 136;
		} catch (IllegalArgumentException ex) {
			System.err.println("Base year and end year have to be integers");
			System.exit(1);
		}
		
		if (numberOfYears < 0) {
			System.err.println("Base year must be smaller than end year");
			System.exit(1);
		}
		
		//	read data files and store in dataset
		ArrayList<YearNames> dataset = readFiles(baseYear, numberOfYears);
				
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
				
		while (true) {
			System.out.print("Enter a name to see how much its popularity has changed"
				+ " over the years or Q to terminate the program: ");
			//set name to console input
			String name = input.nextLine();
			//if console input is "Q" or "q", terminate program
			if (name.equalsIgnoreCase("Q")) 
				System.exit(0);
			
			System.out.print("Enter the gender (F or M): ");
			
			String gender = input.nextLine();
			
			//check if dataset contains name at least once
			if (!(contains(dataset, name, gender))) {
				System.out.println("No such name in the dataset.\n");
				continue;
			}
					
			//print year, percentage and histogram for each year
			for (int elapsedYears = 0; elapsedYears < numberOfYears; elapsedYears++) {
				int currentYear = elapsedYears + baseYear;
				double percentage = dataset.get(elapsedYears).getFractionByName(name, gender) * 100;
				System.out.printf("%d  (%.4f) :  %s%n", currentYear, percentage, generateHistogram(percentage));
			}
					
			System.out.println();
		}

	}
	
	/** Reads data files and stores data in memory.
	 * @return dataset organized in list of YearNames objects
	 */
	@SuppressWarnings("resource")
	public static ArrayList<YearNames> readFiles(int baseYear, int numberOfYears) { 
		//create ArrayList to store data set
		ArrayList<YearNames> dataset = new ArrayList<YearNames>();
		
		//iterate over files for each year
		for (int elapsedYears = 0; elapsedYears < numberOfYears; elapsedYears++) {
			int currentYear = elapsedYears + baseYear;
			//test if file reads
			String fileName = "project4/data/yob" + currentYear + ".txt";
			File f = new File(fileName);
			if (!f.canRead()) {
				System.err.printf("Error: cannot read " + "data from file %s", fileName);
				System.exit(1);
			}
			Scanner input = null;
			try {
				input = new Scanner(f).useDelimiter(",");
			} catch (FileNotFoundException e) {
				System.err.printf("Error: cannot read " + "data from file %s", fileName);
				System.exit(1);
			}
			
			//instantiate YearNames object
			YearNames yearNames = new YearNames(elapsedYears);
			
			//instantiate Name objects for each name, gender and count combination
			while (input.hasNextLine()) {
				String readName = input.next();
				String readGender = input.next();
				input.skip(",");
				int readCount = Integer.parseInt(input.nextLine());
				Name name = new Name(readName, readGender, readCount);
				//add Name object to YearNames object
				yearNames.add(name);
			}
			
			//add YearNames object to dataset
			dataset.add(yearNames);
			
			input.close();
		}
		return dataset;
	}
	
	/** Checks if dataset contains name-gender combination specified in parameter.
	 * @param dataset dataset list
	 * @param name name being searched for in dataset
	 * @param gender gender being searched for in dataset
	 * @return true if name-gender combination is in dataset. false, otherwise
	 */
	public static boolean contains(ArrayList<YearNames> dataset, String name, String gender) {
		//iterate over each list of Names in each list of yearNames
		for (YearNames candidateYear : dataset) {
			if (candidateYear.containsIgnoreCount(new Name(name, gender, 0)))
				return true;			
		}
		//if in whole dataset no name specified by parameter is found return false
		return false;
	} 
	
	/** Generates histogram for specified percentage.
	 * @param percentage the percentage of babies given a particular name in
	 * 			a given year
	 * @return histogram in a string
	 */
	public static String generateHistogram(double percentage) {
		//calculate number of bars
		int numberOfBars = (int) Math.ceil(percentage * 100);
		String histogram = "";
		//generate string
		for (int bars = 0; bars < numberOfBars; bars++)
			histogram += "|";
		return histogram;
	}
	
}


