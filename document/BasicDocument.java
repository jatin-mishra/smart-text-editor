package document;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BasicDocument extends Document 
{
	/** Create a new BasicDocument object
	 * 
	 * @param text The full text of the Document.
	 * @throws InterruptedException 
	 */
	public BasicDocument(String text) 
	{
		super(text);
//		System.out.println("entered into the BASIC DOCUMENT");
//		Thread.sleep(1000);
	}
	
	
	/**
	 * Get the number of words in the document.
	 * A "word" is defined as a contiguous string of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z.  This method completely 
	 * ignores numbers when you count words, and assumes that the document does not have 
	 * any strings that combine numbers and letters. 
	 * 
	 * Check the examples in the main method below for more information.
	 * 
	 * This method should process the entire text string each time it is called.  
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords()
	{
		String text = super.getText();
		String pattern = "[A-Za-z]+";
		
		Matcher p = Pattern.compile(pattern).matcher(text);
		int ttl=0;
		
		while(p.find()) {
			ttl++;
		}	
		//TODO: Implement this method in week 2 according to the comments above.  
		// See the Module 2 support videos if you need help.
	    return ttl;
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * Check the examples in the main method below for more information.  
	 * 
	 * This method should process the entire text string each time it is called.  
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences()
	{
//		System.out.println("start");
		String text = super.getText();
		
		String senpat = "[.?!]+";
		
		Matcher m = Pattern.compile(senpat).matcher(text);
		int count=0;
		
		while(m.find()) {
			count++;
		 
		}
		
		char[] first = text.toCharArray();

//		System.out.println("start");
		if(text.length() > 0 && first[text.length()-1] != '.' && first[text.length()-1] != '!' && first[text.length()-1] != '?' ){

//			System.out.println("start");
			count++;
		}
	

//		System.out.println("last");
        return count;
        //TODO: Implement this method.  See the Module 2 support videos 
        // if you need help.
        
	}
	
	/**
	 * Get the total number of syllables in the document (the stored text). 
	 * To count the number of syllables in a word, it uses the following rules:
	 *       Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 *       
	 * Check the examples in the main method below for more information.  
	 * 
	 * This method should process the entire text string each time it is called.  
	 * 
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables()
	{
		
	
		
	    //TODO: Implement this method in week 2.  See the Module 2 support videos 
        // if you need help.  And note that there is no need to use a regular
		// expression for the syllable counting.  We recommend you implement 
		// the helper function countSyllables in Document.java using a loop, 
		// and then call it here on each word.	
	
		String speech = super.getText();
	
			String second = speech.toLowerCase();
			
			String pattern = "[a-z]+";
			Matcher p = Pattern.compile(pattern).matcher(second);

			String first;
			int total =0;
			while(p.find()) {
				int count=0;
				first = second.substring(p.start(),p.end());
				
				int flag=0;
				int j = 0;
			
				for(char x : first.toCharArray() ) {
			
					if(((x == 'u') || (x == 'o') || (x == 'i')|| (x == 'a') || (x == 'y'))){
						if(flag==0) {
							flag=1;
							count++;
						}
					}
					else if(x == 'e' ) {
						if( flag==0 && j!=first.length()-1) {
							flag=1;
							count++;
						}
						else if( j == first.length()-1 && count == 0) {
							count++;
						}
					}
					else {
						flag=0;
					}
					j++;
			
				}
				total+=count;
		}
			return total;
		}
			
		
		
		
	
  
	
	/* The main method for testing this class. 
	 * You are encouraged to add your own tests.  */
	public static void main(String[] args)
	{
		/* Each of the test cases below uses the method testCase.  The first 
		 * argument to testCase is a Document object, created with the string shown.
		 * The next three arguments are the number of syllables, words and sentences 
		 * in the string, respectively.  You can use these examples to help clarify 
		 * your understanding of how to count syllables, words, and sentences.
		 */
		System.out.println("in main-----------------------------------------------------------------------------------------------------");
		testCase(new BasicDocument("This is a test.  How many???  "
		        + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
				16, 13, 5);
		testCase(new BasicDocument(""), 0, 0, 0);
		testCase(new BasicDocument("sentence, with, lots, of, commas.!  "
		        + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
		testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
		testCase(new BasicDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		testCase(new BasicDocument("Segue"), 2, 1, 1);
		testCase(new BasicDocument("Sentence"), 2, 1, 1);
		testCase(new BasicDocument("Sentences?!"), 3, 1, 1);
		testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
	}
	
}
