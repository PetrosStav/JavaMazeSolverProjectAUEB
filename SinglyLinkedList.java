import java.util.NoSuchElementException;

/**
 * This  class creates a singly linked list using the ListNode class and the type parameter T
 * @author Stavropoulos Petros(3150230), Savvidis Konstantinos(3150229) 
 * 
 * @param <T> the type of object that can be passed as an argument
 */
public class SinglyLinkedList<T>{
	private ListNode<T> firstNode;
	private ListNode<T> lastNode;
	private String name;
	private int size;
	
	/**
	 * Create an empty singly-linked list, using 1 argument
	 * @param name the name of the singly linked list
	 */
	public SinglyLinkedList(String name){
		this.name = name;
		firstNode = lastNode = null;
		size = 0;
	}
	
	/**
	 * Create an empty singly-linked list with no arguments, overloading the previous one
	 */
	public SinglyLinkedList(){
		this("List");
	}
	
	/**
	 * Get whether a list is empty or not
	 * @return false if the list contains one or more nodes, false otherwise
	 */
	public boolean isEmpty(){
		return firstNode==null;
	}
	
	/**
	 * Get the size of a list
	 * @return the number of nodes that the list contains
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * Get the name of a list
	 * @return a string of the name used to initialize the list
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Get the data of the node in the first position
	 * @return the content of the first node in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	public T peekFirstElement() throws NoSuchElementException{
		if(isEmpty()) throw new NoSuchElementException(name);
		return firstNode.data;
	}
	
	/**
	 * Get the data of the node in the last position
	 * @return the content of the last node in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	public T peekLastElement() throws NoSuchElementException{
		if(isEmpty()) throw new NoSuchElementException(name);
		return lastNode.data;
	}
	
	/**
	 * Insert a node in the first position and increase the list size by 1
	 * @param data the content of the node to be inserted
	 */
	public void insertAtFront(T data){
		ListNode<T> n = new ListNode<T>(data);
		if(isEmpty()){
			firstNode = lastNode = n;
		}else{
			n.nextNode = firstNode;
			firstNode = n;
		}
		size++;
	}
	
	/**
	 * Insert a node in the last position and increase the list size by 1
	 * @param data the content of the node to be inserted
	 */
	public void insertAtBack(T data){
		ListNode<T> n = new ListNode<T>(data);
		if(isEmpty()){
			firstNode = lastNode = n;
		}else{
			lastNode = lastNode.nextNode = n;
		}
		size++;
	}
	
	/**
	 * Remove a node from the first position and decrease the list size by 1
	 * @return the content of the node removed
	 * @throws NoSuchElementException if the list is empty
	 */
	public T removeFromFront() throws NoSuchElementException{
		if(isEmpty()) 								//if the list is empty
			throw new NoSuchElementException(name);
		T data = firstNode.data;
		if(firstNode==lastNode){					//if the list contains 1 node
			firstNode = lastNode = null;
		}else{
			firstNode = firstNode.nextNode;			//if the list contains multiple nodes
		}
		size--;
		return data;
	}
	
	/**
	 * Remove a node from the last position and decrease the list size by 1
	 * @return the content of the node removed
	 * @throws NoSuchElementException if the list is empty
	 */
	public T removeFromBack() throws NoSuchElementException{
		if(isEmpty()) 								//if the list is empty
			throw new NoSuchElementException(name);
		T data = lastNode.data;
		if(firstNode==lastNode){					//if the list contains 1 node
			firstNode = lastNode = null;
		}else{										//if the list contains multiple nodes
			ListNode<T> temp = firstNode;
			while(temp.nextNode!=lastNode){
				temp = temp.nextNode;
			}
			lastNode = temp;
			temp.nextNode = null;
		}
		size--;
		return data;
	}
	
	/**
	 * Build a string with the nodes added in the list
	 * @return a string to the console, containing the data of each node
	 * 		   in the list, separated by commas
	 */
	public String printList(){
		if(isEmpty()) return(name + " is empty!");
		StringBuilder sb = new StringBuilder();
		sb.append(name + ": [ ");
		ListNode<T> temp = firstNode;
		sb.append(temp.data);
		temp = temp.nextNode;
		while(temp!=null){
			sb.append(" , ");
			sb.append(temp.data);
			temp = temp.nextNode;
		}
		sb.append(" ]");
		return sb.toString();
	}
}
