/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class NearbyWords implements SpellingSuggest {
	// THRESHOLD to determine how many words to look through when looking
	// for spelling suggestions (stops prohibitively long searching)
	// For use in the Optional Optimization in Part 2.
	private static final int THRESHOLD = 1000; 

	Dictionary dict;

	public NearbyWords (Dictionary dict) 
	{
		this.dict = dict;
	}

	/** Return the list of Strings that are one modification away
	 * from the input string.  
	 * @param s The original String
	 * @param wordsOnly controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
	public List<String> distanceOne(String s, boolean wordsOnly )  {
		   List<String> retList = new ArrayList<String>();
//		   System.out.println("going for insertions");
		   insertions(s, retList, wordsOnly);
//		   System.out.println("going for substitutions");
		   substitution(s, retList, wordsOnly);
//		   System.out.println("going for deletions");
		   deletions(s, retList, wordsOnly);
//		   System.out.println("from deletions");
		   return retList;
	}

	
	/** Add to the currentList Strings that are one character mutation away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void substitution(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
		for(int index = 0; index < s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the 
				// letters in the String
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char)charCode);

				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(sb.toString()) && 
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}
	
	/** Add to the currentList Strings that are one character insertion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void insertions(String s, List<String> currentList, boolean wordsOnly ) {
		// TODO: Implement this method  
		StringBuffer temp_string;
		StringBuffer sb;
		
		for(int index=0;index<s.length();index++) {
			for(int j=(int)'a';j<=(int)'z';j++) {
				sb = new StringBuffer(s);
				temp_string = sb.insert(index, (char)j);
				if((!currentList.contains(temp_string.toString())) && (!wordsOnly || dict.isWord(temp_string.toString()))){
					currentList.add(temp_string.toString());
//					System.out.println(temp_string.toString());
				}
			}
		}
		
		String string;
		for(int j=(int)'a';j<=(int)'z';j++) {
			string = s + (char)j ;
			if((!currentList.contains(string.toString())) && (!wordsOnly || dict.isWord(string.toString()))){
				currentList.add(string);
//				System.out.println(string);
			}
		}
		
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly ) {
		// TODO: Implement this method
      
//		System.out.println("given string is : " + s);
		StringBuffer sb = new StringBuffer(s);
		StringBuffer word_after_deletion;
		for(int index=0;index<s.length();index++) {
//			System.out.println("going to delete");
			 sb = new StringBuffer(s);
			word_after_deletion = sb.delete(index, index+1);
			
//			System.out.println("deleted");
			if(!(currentList.contains(word_after_deletion.toString())) && (!wordsOnly || dict.isWord(word_after_deletion.toString()))) {
				currentList.add(word_after_deletion.toString());
//				System.out.println(word_after_deletion.toString());
			}
		}	
      

	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param word The misspelled word
	 * @param numSuggestions is the maximum number of suggestions to return 
	 * @return the list of spelling suggestions
	 * @throws InterruptedException 
	 */
	@Override
	public List<String> suggestions(String word, int numSuggestions) {

		// initial variables
		
		List<String> queue = new LinkedList<String>();     // String to explore
		HashSet<String> visited = new HashSet<String>();   // to avoid exploring the same  
														   // string multiple times
		List<String> retList = new LinkedList<String>();   // words to return
		 
		if(!word.isEmpty()) {
		// insert first node
		queue.add(word);
		visited.add(word);
		String word_to_check;
		int counter = 0;
		// TODO: Implement the remainder of this method, see assignment for algorithm
		while((!queue.isEmpty()) && retList.size()<numSuggestions && counter<10) {
			
			word_to_check = queue.remove(0);
			for(String real_word : distanceOne(word_to_check,true)) {
				if((retList.size() < numSuggestions) && (!visited.contains(real_word)) && (dict.isWord(real_word)) ) {
					visited.add(real_word);
					retList.add(real_word);
					queue.add(real_word);
				}
			}
			counter++;
		}
		}
		
		return retList;

	}	

   public static void main(String[] args) {
	   /* basic testing code to get started
	   String word = "i";
	   // Pass NearbyWords any Dictionary implementation you prefer
	   Dictionary d = new DictionaryHashSet();
	   DictionaryLoader.loadDictionary(d, "data/dict.txt");
	   NearbyWords w = new NearbyWords(d);
	   List<String> l = w.distanceOne(word, true);
	   System.out.println("One away word Strings for for \""+word+"\" are:");
	   System.out.println(l+"\n");

	   word = "tailo";
	   List<String> suggest = w.suggestions(word, 10);
	   System.out.println("Spelling Suggestions for \""+word+"\" are:");
	   System.out.println(suggest);
	   */
   }

}
