import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * A new implementation of the FIFO queue, using a circular list and without the list node "head", using the type parameter T 
 * @author Stavropoulos Petros(3150230), Savvidis Konstantinos(3150229) 
 *
 * @param <T> the type of object that can be passed as an argument
 */
public class QueueWithOnePointer<T> implements Queue<T>{
	private ListNode<T> tail;
	private String name;
	private int size;
	
	/**
	 * Create an empty queue named "QueueOnePointer"
	 */
	public QueueWithOnePointer() {
		this("QueueOnePointer");
	}
	
	/**
	 * Create an empty queue with its name as an argument
	 * @param name the name of the queue
	 */
	public QueueWithOnePointer(String name){
		this.name = name;
		tail = null;
		size = 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty(){
		return tail==null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getName(){
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void put(T item) {
		ListNode<T> n = new ListNode<>(item);
		if(isEmpty()){	
			tail = n;
			tail.nextNode = tail;
		}else{
			n.nextNode = tail.nextNode;
			tail.nextNode = n;
			tail = n;
		}
		size++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get() throws NoSuchElementException {
		if(isEmpty()) 								//if the list is empty
			throw new NoSuchElementException(name);
		T data = tail.nextNode.data;
		if(tail.nextNode==tail){					//if the list contains 1 node
			tail = tail.nextNode = null;
		}else{										//if the list contains multiple nodes
			tail.nextNode = tail.nextNode.nextNode;
		}
		size--;
		return data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T peek() throws NoSuchElementException {
		if(isEmpty()) throw new NoSuchElementException(name);
		return tail.nextNode.data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void printQueue(PrintStream stream) {
		if(isEmpty()){
			stream.println(name + " is empty!");
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(name + ": [ ");
		ListNode<T> temp = tail.nextNode;
		sb.append(temp.data);
		temp = temp.nextNode;
		while(temp!=tail.nextNode){
			sb.append(" , ");
			sb.append(temp.data);
			temp = temp.nextNode;
		}
		sb.append(" ]");
		stream.println(sb.toString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return size;
	}
}