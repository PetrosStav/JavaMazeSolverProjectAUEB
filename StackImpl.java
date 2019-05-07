import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * This class is used to implement a stack based on a LIFO singly-linked list, using the type parameter T
 * @author Stavropoulos Petros(3150230), Savvidis Konstantinos(3150229) 
 * 
 * @param <T> the type of object that can be passed as an argument
 */
public class StackImpl<T> implements Stack<T>{
	
	private SinglyLinkedList<T> stack;	//declare a singly-linked list to be used as a stack, using the type parameter T

	/**
	 * Create an empty stack named "Stack"
	 */
	public StackImpl() {
		this("Stack");
	}
	
	/**
	 * Create an empty stack with its name as an argument
	 * @param name the name of the stack
	 */
	public StackImpl(String name){
		stack = new SinglyLinkedList<>(name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void push(T item) {
		stack.insertAtFront(item);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T pop() throws NoSuchElementException {
		return stack.removeFromFront();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T peek() throws NoSuchElementException {
		return stack.peekFirstElement();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void printStack(PrintStream stream) {
		stream.println(stack.printList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return stack.getSize();
	}
}