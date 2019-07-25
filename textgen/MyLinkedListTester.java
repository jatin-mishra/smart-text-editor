package textgen;

import static org.junit.Assert.*;

//import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> emptyList2;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		emptyList2 = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet() 
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
//		System.out.println(shortList.get(0));
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		try {
		list1.add(null);
		fail("check for null addition");
		}catch(NullPointerException n) {
			
		}
		
		try {
			emptyList.add(null);
			fail("when empty then null addition");
		}catch(NullPointerException e) {
			
		}
		
		assertEquals("not adding proper value",true,list1.add(2));
		assertEquals("adding no empty and got error",true,emptyList.add(3));
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		
		assertEquals("not getting proper size",0,emptyList2.size());
		assertEquals("not updating size properly",2,shortList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		try {
			shortList.add(-1,"man");
			fail("create proper index out of bound error");
		}catch(IndexOutOfBoundsException e) {
			
		}
		
		try {
			emptyList2.add(2,null);
			fail("empty list is allowing for index 2");
		}catch(NullPointerException e) {
			
		}
		
		try {
			emptyList2.add(2,3);
			fail("empty list is allowing for index 2");
		}catch(IndexOutOfBoundsException e) {
			
		}

	
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		try {
			emptyList2.set(0, 1);
			fail("should not set 0 index of null list");
		}catch(IndexOutOfBoundsException e) {
			
		}
		
		try {
			shortList.set(1,null);
			fail("should not add null value to the shortlist");
		}catch(NullPointerException e) {
			
		}
//		System.out.println(shortList.get(0));
		assertEquals("set is not returning previous value","A",shortList.set(0,"D"));
		try {
		shortList.set(4,"D");
		fail("out of bounds");
		}catch(IndexOutOfBoundsException e) {
			
		}
			
		
		
		
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
