import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * This class is used to implement a queue based on a FIFO singly-linked list, using the type parameter T
 * @author Stavropoulos Petros(3150230), Savvidis Konstantinos(3150229) 
 * 
 * @param <T> the type of object that can be passed as an argument
 */
public class QueueImpl<T> implements Queue<T>{
	
	private SinglyLinkedList<T> queue;	//declare a singly-linked list to be used as a queue, using the type parameter T
	
	/**
	 * Create an empty queue named "Queue"
	 */
	public QueueImpl() {
		this("Queue");
	}
	
	/**
	 * Create an empty queue with its name as an argument
	 * @param name the name of the queue
	 */
	public QueueImpl(String name){
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
	public void put(T item) {
		queue.insertAtBack(item);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get() throws NoSuchElementException {
		return queue.removeFromFront();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T peek() throws NoSuchElementException {
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