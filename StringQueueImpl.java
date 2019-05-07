import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * This class is used to implement a queue based on a FIFO singly-linked list, using the type argument String
 * @author Stavropoulos Petros(3150230), Savvidis Konstantinos(3150229) 
 *
 */
public class StringQueueImpl implements Queue<String> {
	
	private SinglyLinkedList<String> queue;	//declare a singly-linked list to be used as a queue, using the type argument String T
	
	/**
	 * Create an empty queue named "Queue"
	 */
	public StringQueueImpl() {
		this("Queue");
	}
	
	/**
	 * Create an empty queue with its name as an argument
	 * @param name the name of the queue
	 */
	public StringQueueImpl(String name){
		queue = new SinglyLinkedList<>(name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void put(String item) {
		queue.insertAtBack(item);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String get() throws NoSuchElementException {
		return queue.removeFromFront();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String peek() throws NoSuchElementException {
		return queue.peekFirstElement();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void printQueue(PrintStream stream) {
		stream.println(queue.printList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return queue.getSize();
	}
}