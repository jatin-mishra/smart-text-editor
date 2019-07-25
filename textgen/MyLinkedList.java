package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) throws NullPointerException
	{
		// TODO: Implement this method
		if(element != null) {
		LLNode<E> new_element = new LLNode<E>(element);	
		new_element.next = tail;
		new_element.prev = tail.prev;
		new_element.prev.next = new_element;
		new_element.next.prev = new_element;
		size++;
		return true;
		}else {
			throw new NullPointerException("cannot add null element");
			
		}
		
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method.
		int i=0,flag=0;
		LLNode<E> iterating;
		iterating = head.next;
		
		
			while(i < this.size && i<= index) {
				flag=1;
				iterating = iterating.next;
				i++;
			}
			if(i==index+1 && flag==1) {
			  	return iterating.prev.data;
			}else {
				throw new IndexOutOfBoundsException("index is out of bound");
			}

	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) throws  IndexOutOfBoundsException , NullPointerException 
	{
		// TODO: Implement this method
		if(element == null) {
			throw new NullPointerException("cannot add null element");
		}
		
		LLNode<E> instance_to_add = new LLNode<E>(element);
		
		int i=0;
		if(index>=0 && index<=size) {

		    LLNode<E> iterator ;
		    iterator = head.next;
		    
		    while(i<index) {
		    	iterator = iterator.next;
		    	i++;
		    }
		    instance_to_add.next=iterator;
		    instance_to_add.prev = iterator.prev;
		    iterator.prev.next=instance_to_add;
		    iterator.prev=instance_to_add;
		    size++;

		}else {
			throw new IndexOutOfBoundsException();
		}
		

	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method
		int i=0;
		if(index>=0 && index<this.size) {
		LLNode<E> iterating_to_delete;
		iterating_to_delete = head.next;
		while(i<index) {
			iterating_to_delete = iterating_to_delete.next;
			i++;
		}
		
		iterating_to_delete.prev.next = iterating_to_delete.next;
		iterating_to_delete.next.prev = iterating_to_delete.prev;
		size--;
		return iterating_to_delete.data;
		}else {
			throw new IndexOutOfBoundsException("index is out of bounds");
		}

	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element)  throws IndexOutOfBoundsException,NullPointerException
	{
		// TODO: Implement this method
		if(element == null) {
			throw new NullPointerException("cannot assign a null pointer");
		}
		
		int i = 0;
		E previous_element;
		if(index>=0 && index<size) {
			LLNode<E> iterator_to_set;
			iterator_to_set = head.next;
			while(i<index) {
				iterator_to_set = iterator_to_set.next;
				i++;
			}
		previous_element = iterator_to_set.data;
		iterator_to_set.data = element;
		}else {
			throw new IndexOutOfBoundsException("index is out of bound");
		}
			
		return previous_element;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
