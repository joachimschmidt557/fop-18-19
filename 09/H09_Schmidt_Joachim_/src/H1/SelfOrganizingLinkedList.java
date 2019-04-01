package H1;

import java.util.function.Predicate;

/**
 * A linked list which automatically organizes the elements to improve searching
 * speed
 * 
 * @author joachim
 *
 * @param <T> The generic parameter
 */
public class SelfOrganizingLinkedList<T> extends SimpleLinkedList<T> {

	private ReorganizingAlgorithm ra;

	/**
	 * Constructs a new object of type SelfOrganizingLinkedList
	 * 
	 * @param ra The reorganizing algorithm to use for reordering the list so that
	 *           elements can be searched for faster
	 */
	public SelfOrganizingLinkedList(ReorganizingAlgorithm ra) {
		this.ra = ra;
	}

	/*
	public static void main(String[] args) {

		SelfOrganizingLinkedList<String> moveToFront = new SelfOrganizingLinkedList<>(
				ReorganizingAlgorithm.MOVETOFRONT);
		SelfOrganizingLinkedList<String> transpose = new SelfOrganizingLinkedList<>(ReorganizingAlgorithm.TRANSPOSE);
		SelfOrganizingLinkedList<String> counter = new SelfOrganizingLinkedList<>(ReorganizingAlgorithm.COUNT);

		// Randfall: search emtpy list
		System.out.println(moveToFront.search(x -> x.equals("a")));
		System.out.println(transpose.search(x -> x.equals("a")));
		System.out.println(counter.search(x -> x.equals("a")));

		// Randfall: search one item list
		moveToFront.addLast("0");
		transpose.addLast("0");
		counter.addLast("0");

		moveToFront.search(x -> x.equals("0"));
		transpose.search(x -> x.equals("0"));
		counter.search(x -> x.equals("0"));

		moveToFront.print(false);
		transpose.print(false);
		counter.print(true);

		System.out.println("---------------------------------");

		// Randfall: search two item list
		moveToFront.addLast("1");
		transpose.addLast("1");
		counter.addLast("1");

		moveToFront.search(x -> x.equals("1"));
		transpose.search(x -> x.equals("1"));
		counter.search(x -> x.equals("1"));

		moveToFront.print(false);
		transpose.print(false);
		counter.print(true);

		System.out.println("---------------------------------");

		// Add numbers from 0-19
		for (int i = 2; i < 20; i++) {
			moveToFront.addLast(Integer.toString(i));
			transpose.addLast(Integer.toString(i));
			counter.addLast(Integer.toString(i));
		}

		// Search for 5 5 times
		for (int i = 0; i < 5; i++) {
			moveToFront.search(x -> x.equals("5"));
			transpose.search(x -> x.equals("5"));
			counter.search(x -> x.equals("5"));
		}

		// Search for 9 3 times
		for (int i = 0; i < 3; i++) {
			moveToFront.search(x -> x.equals("9"));
			transpose.search(x -> x.equals("9"));
			counter.search(x -> x.equals("9"));
		}

		moveToFront.print(false);
		transpose.print(false);
		counter.print(true);

	}*/

	/**
	 * Searches the list and automatically reorganizes
	 * 
	 * @param predicate The predicate which tests the individual items
	 * @return The first item which matches the predicate, null of no item was found
	 */
	public T search(Predicate<T> predicate) {

		// Search
		ListItem<T> itemBeforeBeforeFoundItem = null;
		ListItem<T> itemBeforeFoundItem = null;
		ListItem<T> foundItem = null;

		// The most-left item which is guaranteed to have the
		// same counter than the itemBefore
		ListItem<T> itemHeadOfCounterClass = head;

		// The exact item before that item
		ListItem<T> itemBeforeItemHeadOfCounterClass = null;

		ListItem<T> itemWeAreCurrentlyLookingAt = head;

		while (itemWeAreCurrentlyLookingAt != null) {

			if (predicate.test(itemWeAreCurrentlyLookingAt.key)) {

				foundItem = itemWeAreCurrentlyLookingAt;
				break;

			} else {

				// Move 1 step towards the right
				itemBeforeBeforeFoundItem = itemBeforeFoundItem;
				itemBeforeFoundItem = itemWeAreCurrentlyLookingAt;
				itemWeAreCurrentlyLookingAt = itemWeAreCurrentlyLookingAt.next;

				// If the item before the current item has a different counter,
				// that item becomes our new itemHeadOfCounterClass
				if (itemBeforeBeforeFoundItem != null)
					if (itemHeadOfCounterClass.counter != itemBeforeFoundItem.counter) {
						itemHeadOfCounterClass = itemBeforeFoundItem;
						itemBeforeItemHeadOfCounterClass = itemBeforeBeforeFoundItem;
					}

				// System.out.println("beforebefore: " + (itemBeforeBeforeFoundItem == null ?
				// "null" : itemBeforeBeforeFoundItem.key));
				/*
				 * System.out.println("beforebefore: " + itemBeforeBeforeFoundItem);
				 * System.out.println("before: " + itemBeforeFoundItem);
				 * System.out.println("currentlylookingat: " + itemWeAreCurrentlyLookingAt);
				 * System.out.println("beforehead: " + itemBeforeItemHeadOfCounterClass);
				 * System.out.println("head: " + itemHeadOfCounterClass); System.out.println();
				 */

			}

		}

		if (foundItem == null)
			return null;

		// Reorganize list
		if (ra.equals(ReorganizingAlgorithm.MOVETOFRONT)) {

			ListItem<T> oldHead = head;
			ListItem<T> oldNext = foundItem.next;
			ListItem<T> oldPrev = itemBeforeFoundItem;

			// If the item is already at the front, cancel
			if (foundItem != head) {

				// Move item to front
				head = foundItem;
				head.next = oldHead;

				// Close the gap
				oldPrev.next = oldNext;

			}

		} else if (ra.equals(ReorganizingAlgorithm.TRANSPOSE)) {

			ListItem<T> oldNext = foundItem.next;
			ListItem<T> oldPrev = itemBeforeFoundItem;
			ListItem<T> oldPrevPrev = itemBeforeBeforeFoundItem;

			// If the item is already at the front, cancel
			if (foundItem != head) {

				// If the item is directly before the head
				if (itemBeforeFoundItem == head) {

					ListItem<T> oldHead = head;
					ListItem<T> oldAfterFoundItem = foundItem.next;

					// Swap item with head
					head = foundItem;
					foundItem.next = oldHead;
					oldHead.next = oldAfterFoundItem;

				} else {

					// Swap item and oldPrev
					oldPrevPrev.next = foundItem;
					foundItem.next = oldPrev;
					oldPrev.next = oldNext;

				}

			}

		} else if (ra.equals(ReorganizingAlgorithm.COUNT)) {

			// Increment count
			foundItem.counter++;

			// Rearrange
			// If the item is already at the front, cancel
			if (foundItem != head) {

				// If the counter of the current item is bigger
				// than the counter of the previous item
				if (foundItem.counter > itemBeforeFoundItem.counter) {

					ListItem<T> oldNext = foundItem.next;
					ListItem<T> oldPrev = itemBeforeFoundItem;

					// If itemHeadOfCounterClass is head,
					// insert at the head
					if (itemHeadOfCounterClass == head) {

						ListItem<T> oldHead = head;

						// Move item to front
						head = foundItem;
						head.next = oldHead;

						// Close the gap
						oldPrev.next = oldNext;
					} else {

						// Put the item before itemHeadOfCounterClass
						itemBeforeItemHeadOfCounterClass.next = foundItem;
						foundItem.next = itemHeadOfCounterClass;

						// Close the gap
						oldPrev.next = oldNext;

					}

				}

			}

		}

		return foundItem.key;
	}

}
