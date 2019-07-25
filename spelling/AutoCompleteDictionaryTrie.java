package spelling;

import java.util.List;


import java.util.Queue;
import java.util.Set;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.HashSet;
import java.util.LinkedList;


public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    
    private List<String> list_to_return = new LinkedList<String>();
	private Queue<TrieNode> queue = new LinkedList<TrieNode>();
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
    public TrieNode getRoot() {
    	return this.root;
    }
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		if(word != null) {
		String lower_word = word.toLowerCase();
		TrieNode tr = new TrieNode(); 
				tr = this.root;
		for(char character : lower_word.toCharArray()) {
			
			
			if(tr.is_present(character)) {
			tr = tr.getChild(character);
			}else {
				tr = tr.insert(character);
			}
		}
		
		if(!tr.endsWord()) {
			tr.setEndsWord(true);
			size++;
			return true;
		}
		}
		return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		TrieNode new_tr = new TrieNode(); 
				new_tr = this.root;
		for(Character character : s.toLowerCase().toCharArray()) {			
			if(new_tr.is_present(character)) {
				new_tr = new_tr.getChild(character);
			}else {
				return false;
			}	
		}
		
		if(new_tr.endsWord()) {
			return true;
		}
		return false;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
   
 
    	 list_to_return.clear();
    	 queue.clear();
    	 
    	 
    	 if(numCompletions == 0)
    		 return list_to_return;
    	 
    	 
    	 TrieNode first = new TrieNode();
//    	 TrieNode transfer = new TrieNode();
//    	 transfer = this.getRoot();
    	 
    	 first= this.getRoot();

    	 if(!prefix.isEmpty()) {
    		 
    		 for(Character character : prefix.toLowerCase().toCharArray()) {
    			 	if(!first.is_present(character)){
//    			 		System.out.println(character + " " + prefix);
    			 			return list_to_return;
    			 	}
    			 	first = first.getChild(character);
    		 }
    	 if(first.endsWord())
    		 list_to_return.add(prefix);
    	 }
    	 
    	 	Set<Character> hs = first.getValidNextCharacters();
    		TrieNode iterator_tn = new TrieNode();
    		
    		for(Character character : hs) {
    				queue.add(first.getChild(character));
//    				System.out.println(character + " in queue ");
    		}
    	
    		while(!queue.isEmpty() && list_to_return.size()< numCompletions) {
//    			System.out.println("---------------------------------------------------------------size is : " + list_to_return.size());
//    			printTree();.
//    			printTree();
//    			hs.clear();
 
    			iterator_tn = queue.remove();
    			if(iterator_tn.endsWord()) {
    					list_to_return.add(iterator_tn.getText());
    			}
    			hs = iterator_tn.getValidNextCharacters();
    			
//    			if(!hs.isEmpty()) {
    			for(Character character : hs) {
    					queue.add(iterator_tn.getChild(character));
    			}
//    			}
//    			iterator_tn = null;
			
    		}
//    		printTree();
//    		System.out.println("they want " + numCompletions + "we have " + list_to_return.size());
//    		System.out.println(root == transfer);
//    		this.root = transfer;
    		
    		return list_to_return;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(this.root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{	
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText() + " " + curr.endsWord());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}
