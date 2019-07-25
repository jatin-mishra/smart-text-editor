package spelling;


/** A class for timing the Dictionary Implementations
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 */

public class DictionaryBenchmarking {

	
	public static void main(String [] args) {

	    // Run each test more than once to get bigger numbers and less noise.
	    // You can try playing around with this number.
	    int trials = 500;

	    // The text to test on
	    String dictFile = "data/dict.txt";
		
	    // The amount of words to increment each step
	    // You can play around with this
		int increment = 2000;

		// The number of steps to run.  
		// You can play around with this.
		int numSteps = 20;
		
		// The number of words to start with. 
		// You can play around with this.
		int start = 50000;
		
		String notInDictionary = "notaword";
		
		// TODO: Play around with the numbers above and graph the output to see trends in the data
		for (int numToCheck = start; numToCheck < numSteps*increment + start; 
				numToCheck += increment)
		{
			// Time the creation of finding a word that is not in the dictionary.
			DictionaryLL llDict = new DictionaryLL();
			DictionaryBST bstDict = new DictionaryBST();
			
			DictionaryLoader.loadDictionary(llDict, dictFile, numToCheck);
			DictionaryLoader.loadDictionary(bstDict, dictFile, numToCheck);
			
			long startTime = System.nanoTime();
			for (int i = 0; i < trials; i++) {
				llDict.isWord(notInDictionary);
			}
			long endTime = System.nanoTime();
			long timeLL = (endTime - startTime);  
			
			startTime = System.nanoTime();
			for (int i = 0; i < trials; i++) {
				bstDict.isWord(notInDictionary);
			}
			endTime = System.nanoTime();
			long timeBST = (endTime - startTime);
			
			System.out.println(numToCheck + "\t" + timeLL + "\t" + timeBST);
			
		}
	
	}
	
}

//50000	255837336	166029
//52000	271448706	102029
//54000	258274438	69565
//56000	270900067	72348
//58000	292094744	67710
//60000	312262638	90899
//62000	350197032	67247
//64000	658274624	95536
//66000	372762144	78377
//68000	626279711	93681
//70000	390067196	81160
//72000	437733305	109913
//74000	428601706	96000
//76000	409259321	75130
//78000	429394750	81623
//80000	480235353	77449
//82000	523592127	72812
//84000	461659576	78377
//86000	421110920	75130
//88000	431220142	75594
