import java.util.ArrayList;

/**	Generic stack class 
 * @author Sungmin Kim
 * @date 2021/10/03
 * @param <T>
 */
public class NotationStack<T> implements StackInterface<T> {
	
	private T[] stack;
	private int topIndex;
	private boolean initialized;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	/** Default constructor 
	 	Set 50 as default capacity of array. */
	public NotationStack() {
		this(DEFAULT_CAPACITY);
	}//end default constructor
	
	/**	Constructor
	 * 	@param initialCapacity to be set as size of array. */
	public NotationStack(int initialCapacity) {
		@SuppressWarnings("unchecked")
		T[] tempStack = (T[]) new Object[initialCapacity];
		stack = tempStack;
		topIndex = -1;
		initialized = true;
	}//end constructor
	
	
	/**	Checks whether this stack is empty.
	 *	@return True if this stack is empty, or false if not. */
	public boolean isEmpty() {
		return topIndex < 0;
	}

	/**	Checks whether this stack is full.
	 * 	@return True if this stack is full, or false if not. */
	public boolean isFull() {
		return topIndex + 1 ==  stack.length;
	}//end isFull

	/**	Pop an entry from this stack.
	 * 	@return the top entry of the stack.
	 * 	@throws StackUnderflowException if the stack is empty. */
	public T pop() throws StackUnderflowException {
		T top = top();
		stack[topIndex--] = null;
		return top;
	}//end pop

	/**	See what is on the top of this stack.
	 * 	@return the top entry of the stack.
	 *	@throws StackUnderflowException if the stack is empty. */
	public T top() throws StackUnderflowException {
		if(isEmpty())
			throw new StackUnderflowException();
		else
			return stack[topIndex];
	}//end top

	/**	Gets the number of the elements in this stack.
	 *	@return the number the elements in this stack. */
	public int size() {
		return topIndex + 1;
	}//end size

	/**	Push a new entry into this stack.
	 * 	@return True if the push was successful
	 *	@throws StackOverflowException if this stack is full */
	public boolean push(T newEntry) throws StackOverflowException {
		if(isFull()) {
			throw new StackOverflowException();
		}else {
			stack[++topIndex] = newEntry;
			return true;
		}
	}//end push

	/**	Creates a string with delimiter between every single elements of this stack.
	 *	@param delimiter to be insert between elements. 
	 *	@return result of the string. */
	public String toString(String delimiter) {
		String result = "";
		for(int index = 0; index < topIndex; index++) {
			result += stack[index] + delimiter;
		}
		return result + stack[topIndex];
	}//end toString

	/**	Default toString 
	 * 	@return	toString without any delimiter. */
	public String toString() {
		return toString("");
	}//end default toString
	
	/**	Takes an array list and fill the elements into this stack.
	 *	@param list an array list type of T. */
	public void fill(ArrayList<T> list) {
		for (int index = 0; index < list.size() ; index++) {
			stack[++topIndex] = list.get(index);
		}
	}//end fill

}
