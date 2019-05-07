import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * This class is used to implement a stack based on a LIFO singly-linked list, using the type argument String
 * @author Stavropoulos Petros(3150230), Savvidis Konstantinos(3150229) 
 *
 */
public class StringStackImpl implements Stack<String> {
	
	private SinglyLinkedList<String> stack;	//declare a singly-linked list to be used as a stack, using the type argument String

	/**
	 * Create an empty stack named "Stack"
	 */
	public StringStackImpl() {
		this("Stack");
	}
	
	/**
	 * Create an empty stack with its name as an argument
	 * @param name the name of the stack
	 */
	public StringStackImpl(String name){
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
	public void push(String item) {
		stack.insertAtFront(item);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String pop() throws NoSuchElementException {
		return stack.removeFromFront();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String peek() throws NoSuchElementException {
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