package textgen;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.*;

public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	private byte is_trained = 0;
	private byte is_generable = 0;
	
	
	// it will stop the train function to train further,as required quality is that if
	// we want to train again then we must use retrain method (train should not be working now).
	
	// The random number generator
	private Random rnGenerator;
	
	private List<String> list = new LinkedList<String>();
	
	
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	public boolean is_trained() {
		if(is_trained == 1)
			return true;
		return false;
	}
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		if(is_trained==0) {
			wordList.clear();
			list.clear();
			int to_add=0;
//			Matcher m = Pattern.compile("[a-zA-Z']+").matcher(sourceText);
			String[] list_of_data = sourceText.split("\\s");
//			while(m.find()) {
			for(String word : list_of_data) {
//				String word = sourceText.substring(m.start(),m.end());
				ListNode ln = new ListNode(word);
				if(!list.contains(word)) {
					
					/*
					 * add to the wordList then add to the lastone's nextwordlist.
					 * 
					 */
					list.add(word); // check for if wordList is null or not
					if(!wordList.isEmpty())
					wordList.get(to_add).addNextWord(word);
					wordList.add(ln);
					to_add = wordList.size()-1;
					
				is_generable = 1;
					
				}else {	
					/*
					 add to the previous one's nextword list and then change the to_add equal to the index where should i add now,
					 */
					wordList.get(to_add).addNextWord(word);
					to_add = list.indexOf(word);

				}
				
			}
			
			is_trained = 1;
			
			if(is_generable ==1)
			wordList.get(to_add).addNextWord(list.get(0));
			
		  }
		
	
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
	if(is_generable==1) {
//		String text = wordList.get(0).getWord();
		int select_a_list_node = 0;
		String word="",text="";
		ListNode ll = wordList.get(select_a_list_node);
//		text = ll.getWord() + " ";
//		String prev_word;
		for(int i=0;i<numWords;i++) {
//			System.out.println("this is : " + rnGenerator.nextInt());
	
//			do {
//			prev_word = word;
		    word = ll.getRandomNextWord(rnGenerator);
//			}while(prev_word.equalsIgnoreCase(word));
		    
		    
			text =text + word + " ";
			ll = wordList.get(list.indexOf(word));
		}
		
		return text;
	}
	else return null;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		
	if(is_trained == 1 ) {

			is_generable = 0;
			wordList.clear();
			list.clear();
		
		if(!sourceText.isEmpty()) {
			
			
			
			int to_add=0;
//			Matcher m = Pattern.compile("[a-zA-Z']+").matcher(sourceText);
			String[] list_of_data = sourceText.split("\\s");
//			while(m.find()) {
				for(String word : list_of_data) {
//				String word = sourceText.substring(m.start(),m.end());
				ListNode ln = new ListNode(word);
				if(!list.contains(word)) {
				
				/*
				 * add to the wordList then add to the lastone's nextwordlist.
				 * 
				 */
					list.add(word); // check for if wordList is null or not
					if(!wordList.isEmpty())
							wordList.get(to_add).addNextWord(word);
					wordList.add(ln);
					to_add = wordList.size()-1;
					is_generable = 1;
				
			   }else {	
				/*
				 add to the previous one's nextword list and then change the to_add equal to the index where should i add now,
				 */
				wordList.get(to_add).addNextWord(word);
				to_add = list.indexOf(word);

			}
		}
			
		if(is_generable == 1)
		wordList.get(to_add).addNextWord(list.get(0));
		}
	}
		
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		
//		String textString = "";
		
		System.out.println(textString);
		gen.train(textString);
		System.out.println("trained are : ");
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		
		
//		MarkovTextGeneratorLoL first = new MarkovTextGeneratorLoL(new Random(42));
//		String for_training = "hello yes i am hello am i yes i i am you how yes you am hello";
//		gen.retrain(for_training);
//		System.out.println(for_training);
//		System.out.println("it is being trained by empty file");
//		System.out.println("generator is : " + gen.generateText(20));
//		
		
	}

}

/** Links a word to the next words in the list 
* You should use this class in your implementation. */
class ListNode
{
  // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		Random generating = new Random();
		int n;
		n = generating.nextInt(nextWords.size());
	    return nextWords.get(n);
//		return null;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}




