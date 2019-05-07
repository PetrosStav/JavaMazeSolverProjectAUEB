/**
 * This class creates a single node, which is the base to construct lists, using the type parameter T
 * @author Stavropoulos Petros(3150230), Savvidis Konstantinos(3150229)
 *  
 * @param <T> the type of object that can be passed as an argument
 */
public class ListNode<T> {
	T data;
	ListNode<T> nextNode;
	
	/**
	 * Constructor of a ListNode, using 2 arguments
	 * @param data the Object contained by the ListNode
	 * @param node the next ListNode, where the current one is pointing to
	 */
	public ListNode(T data,ListNode<T> node) {
		this.data = data;
		nextNode = node;
	}
	
	/**
	 * Constructor of a ListNode, using 1 argument, overloading the previous one
	 * @param data the next ListNode, where the current one is pointing to
	 */
	public ListNode(T data){
		this(data,null);
	}
	
	/**
	 * Method that returns the data of a ListNode
	 * @return the content of the ListNode
	 */
	T getObject(){
		return data;
	}
	
	/**
	 * Method that returns the next ListNode
	 * @return the next ListNode
	 */
	ListNode<T> getNext(){
		return nextNode;
	}
	
}
