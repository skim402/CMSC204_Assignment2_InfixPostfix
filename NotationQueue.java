import java.util.ArrayList;

/** Generic queue class
 * @author Sungmin Kim
 * @date 2021/10/03
 * @param <T>
 */
public class NotationQueue<T> implements QueueInterface<T> {

	private T[] queue;
	private int lastIndex;
	private boolean initialized;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	/**	default constructor */
	public NotationQueue() {
		this(DEFAULT_CAPACITY);
	}//end default constructor
	
	/**	Constructor
	 * @param initialCapacity to set the size of array. */
	public NotationQueue(int initialCapacity) {
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initialCapacity];
		queue = tempQueue;
		lastIndex = -1;
		initialized = true;
	}//end constructor
	
	/**	Sees if this queue is empty.
	 *	@return true if the queue is empty, or false if not. */
	public boolean isEmpty() {
		return lastIndex < 0;
	}//end isEmpty

	/**	Sees if this queue is full.
	 * 	@return true if the queue is full, or false if not. */
	public boolean isFull() {
		return lastIndex + 1 == queue.length;
	}//end isFull

	/**	Dequeue the first element of this queue.
	 * 	@return dequeue the object dequeued. 
	 * 	@throws QueueUnderflowException if the queue is empty. */
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) {
			throw new QueueUnderflowException();
		}else {
			T dequeue = queue[0];
			for(int index = 0 ; index < lastIndex; index++) {
				queue[index] = queue[index + 1];
			}//end for
			queue[lastIndex--] = null;
			return dequeue;
		}//end if
	}//end dequeue

	/**	Checks how many elements are in this queue.
	 * 	@return size the number of last index plus one. */
	public int size() {
		return lastIndex + 1;
	}//end size

	/**	Enqueue an entry into this queue.
	 * 	@param entry the object to be enqueued.
	 * 	@return true if enqueue is successful.
	 * 	@throws QueueOverflowException if the queue is full. */
	public boolean enqueue(T entry) throws QueueOverflowException {
		if(isFull()) {
			throw new QueueOverflowException();
		}else {
			queue[++lastIndex] = entry;
			return true;
		}//end if
	}//end enqueue

	/**	Creates a string contains every entries in this queue with delimiter in between entries. 
	 * 	@param delimiter to be added between elements.
	 *	@return result which is created containing entries of queue and delimiters. */
	public String toString(String delimiter) {
		String result = "";
		for(int index = 0; index < lastIndex; index++) {
			result += queue[index] + delimiter;
		}//end for
		return result + queue[lastIndex];
	}//end toString

	/**	Default toString
	 * 	@return toString without delimiters. */
	public String toString() {
		return toString("");
	}
	
	/**	Fills every elements of a list into this queue.
	 * 	@param list to be added to the queue. */
	public void fill(ArrayList<T> list) {
		for (int index = 0; index < list.size() ; index++) {
			queue[++lastIndex] = list.get(index);
		}//end for
	}//end fill

}
