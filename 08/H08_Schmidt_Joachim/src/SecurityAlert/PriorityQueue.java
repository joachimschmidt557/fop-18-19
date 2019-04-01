package SecurityAlert;

import java.util.ArrayList;
import java.util.LinkedList;

public class PriorityQueue<T extends Comparable<? super T>> {

	ArrayList<T> internalQueue;

	public static void main(String[] args) {

		PriorityQueue<GovernmentEmployee> test = new PriorityQueue<GovernmentEmployee>();
		GovernmentEmployee asdf = new Secretary("444", Sex.FEMALE);
		test.enqueue(new Other("John Doe", Sex.MALE));
		test.enqueue(new President("Uncle D", Sex.MALE));
		test.enqueue(new Secretary("asdf", Sex.FEMALE));
		test.enqueue(new Secretary("535353", Sex.FEMALE));
		test.enqueue(asdf);
		test.enqueue(new Secretary("3456", Sex.FEMALE));
		System.out.println(test.toString());
		test.addToHeadOfPriorityClass(asdf);
		System.out.println(test.toString());


	}

	/**
	 * Constructs a new, empty Priority queue
	 */
	public PriorityQueue() {

		internalQueue = new ArrayList<T>();

	}

	/**
	 * Adds a new item to the queue
	 * @param e The item to add
	 */
	public void enqueue(T e) {

		// If the current element has a smaller priority than the element
		// we want to insert, we insert the element here
		for (int i = 0; i < internalQueue.size(); i++) {
			// Compareto returns a negative integer when the current item
			// has a smaller priority than e
			if (internalQueue.get(i).compareTo(e) < 0) {
				internalQueue.add(i, e);
				return;
			}
		}
		// If we havent inserted the item yet, we insert it at the end
		internalQueue.add(e);

	}

	/**
	 * Removes the item with the highest priority
	 * @return The item that was removed
	 */
	public T dequeue() {

		// Remove the first element
		if (internalQueue.size() > 0) {
			T element = internalQueue.get(0);
			internalQueue.remove(0);
			return element;
		} else
			return null;

	}

	/**
	 * Gets the size of the queue
	 * @return The size
	 */
	public int getSize() {

		return internalQueue.size();

	}

	/**
	 * Generates a string representation of the queue
	 * @return A string representation
	 */
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < internalQueue.size(); i++) {
			sb.append("#");
			sb.append(i);
			sb.append(": ");
			sb.append(internalQueue.get(i).toString());
			sb.append("\n");
		}

		return sb.toString();

	}

	/**
	 * Prioritizes one element over all other elements with the same
	 * priority as the element
	 * @param e The element
	 */
	public void addToHeadOfPriorityClass(T e) {

		// check if the queue contains e
		if (internalQueue.contains(e)) {

			// Get position of e
			int indexOfE = internalQueue.indexOf(e);
			
			// Get head of priority class
			int indexOfHeadOfPriorityClass = indexOfE;
			for (int i = indexOfE; i > 0 ; i--) {
				
				// If the current element has a different priority,
				// cancel the search
				if (internalQueue.get(i).compareTo(e) != 0) {
					break;
				}
				else {
					indexOfHeadOfPriorityClass = i;
				}
				
			}
			
			// Move e to head of the priority class
			//internalQueue.set(indexOfE, internalQueue.get(indexOfHeadOfPriorityClass));
			//internalQueue.set(indexOfHeadOfPriorityClass, e);
			internalQueue.remove(indexOfE);
			internalQueue.add(indexOfHeadOfPriorityClass, e);
			
		}

	}

}
