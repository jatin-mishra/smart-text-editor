package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;



public class DocumentBenchmarking {

	
	public static void main(String [] args) {

	    // Run each test more than once to get bigger numbers and less noise.
	    // You can try playing around with this number.
	    int trials = 100;

	    // The text to test on
	    String textfile = "data/warAndPeace.txt";
		
	    // The amount of characters to increment each step
	    // You can play around with this
		int increment = 20000;

		// The number of steps to run.  
		// You can play around with this.
		int numSteps = 20;
		
		// THe number of characters to start with. 
		// You can play around with this.
		int start = 50000;
		
		// TODO: Fill in the rest of this method so that it runs two loops
		// and prints out timing results as described in the assignment 
		// instructions and following the pseudocode below.
//		Thread.sleep(1000);
//		System.out.println("going into the for loop for time calculation");
		for (int numToCheck = start; numToCheck < numSteps*increment + start; 
				numToCheck += increment)
		{
			// numToCheck holds the number of characters that you should read from the 
			// file to create both a BasicDocument and an EfficientDocument.  
			
			/* Each time through this loop you should:
			 * 1. Print out numToCheck followed by a tab (\t) (NOT a newline)
			 * 2. Read numToCheck characters from the file into a String
			 *     Hint: use the helper method below.
			 * 3. Time a loop that runs trials times (trials is the variable above) that:
			 *     a. Creates a BasicDocument 
			 *     b. Calls fleshScore on this document
			 * 4. Print out the time it took to complete the loop in step 3 
			 *      (on the same line as the first print statement) followed by a tab (\t)
			 * 5. Time a loop that runs trials times (trials is the variable above) that:
			 *     a. Creates an EfficientDocument 
			 *     b. Calls fleshScore on this document
			 * 6. Print out the time it took to complete the loop in step 5 
			 *      (on the same line as the first print statement) followed by a newline (\n) 
			 */  
			
			System.out.print(numToCheck +"\t");
			String text = getStringFromFile(textfile,numToCheck);
			
			long time1 = System.nanoTime();
			for(int i=0;i<trials;i++) {
//				System.out.println("going for basic");
				BasicDocument bd = new BasicDocument(text);
//				System.out.println("going to sleep");
//				Thread.sleep(1000);
			bd.getFleschScore();
			}
			long time2 = System.nanoTime();
			System.out.print((double)((double)(time2-time1)/1000000000)+"\t");
			
//			Thread.sleep(1000);
			
			long time3 = System.nanoTime();
			for(int i=0;i<trials;i++) {
//				System.out.println("going into the efficeint");
				EfficientDocument ed = new EfficientDocument(text);
				ed.getFleschScore();
			}
			long time4 = System.nanoTime();
			
//			Thread.sleep(1000);
			
			System.out.println((double)((double)(time4-time3)/1000000000));
			
                                           		
			
			 
		}
	
	}
	
	/** Get a specified number of characters from a text file
	 * 
	 * @param filename The file to read from
	 * @param numChars The number of characters to read
	 * @return The text string from the file with the appropriate number of characters
	 */
	public static String getStringFromFile(String filename, int numChars) {
//		System.out.println("entered in the reading of the filesystem");
		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile= new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		}
		catch(Exception e)
		{
		  System.out.println("there is a error");
		  System.exit(0);
		}
		
//		System.out.println("returing the string");
		return s.toString();
	}
	
}
