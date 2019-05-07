import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * A new implementation of the FIFO queue, using a circular list and without the list node "head", using the type argument String
 * @author Stavropoulos Petros(3150230), Savvidis Konstantinos(3150229) 
 *
 */
public class StringQueueWithOnePointer implements Queue<String> {
	private ListNode<String> tail;
	private String name;
	private int size;
	
	/**
	 * Create an empty queue named "QueueOnePointer"
	 */
	public StringQueueWithOnePointer() {
		this("QueueOnePointer");
	}
	
	/**
	 * Create an empty queue with its name as an argument
	 * @param name the name of the queue
	 */
	public StringQueueWithOnePointer(String name){
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
	public void put(String item) {
		ListNode<String> n = new ListNode<>(item);
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
	public String get() throws NoSuchElementException {
		if(isEmpty()) 								//if the list is empty
			throw new NoSuchElementException(name);
		String data = tail.nextNode.data;
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
	public String peek() throws NoSuchElementException {
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
		ListNode<String> temp = tail.nextNode;
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