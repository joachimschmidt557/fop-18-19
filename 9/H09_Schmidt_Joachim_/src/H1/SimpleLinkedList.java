package H1;

import java.util.function.Predicate;

public class SimpleLinkedList<T> {
	
	public ListItem<T> head;
	
	/**
	 * Adds a given key to the end of the list
	 * @param key
	 */
	public void addLast(T key) {
		ListItem<T> li = new ListItem<T>(key);
		if(head == null) {
			head = li;
		} else {
			ListItem<T> last = getLast();
			last.next = li;
		}
	}
	
	/**
	 * @return the last ListeItem of the list
	 */
	private ListItem<T> getLast() {
		if(head == null) {
			return null;
		} else {
			ListItem<T> p = head;
			while(p.next != null) {
				p = p.next;
			}
			return p;
		}
	}
	
	/**
	 * @param predicate
	 * @return the first matching key to the given predicate in the list
	 */
	public T search(Predicate<T> predicate) {
		if(head == null) {
			return null;
		} else {
			ListItem<T> p = head;
			while(p != null) {
				if(predicate.test(p.key)) {
					return p.key;
				}
				p = p.next;
			}
			return null;
		}
	}
	
	/**
	 * Prints the list
	 * @param counter
	 */
	public void print(boolean counter) {
		if(head == null) {
			return;
		} else {
			ListItem<T> p = head;
			while(p != null) {
				System.out.print(p.key);
				if(p.next != null) {
					System.out.print(",\t");
				}
				p = p.next;
			}
			System.out.println();
			if(counter) {
			p = head;
			while(p != null) {
				System.out.print(p.counter);
				if(p.next != null) {
					System.out.print(",\t");
				}
				p = p.next;
			}
			System.out.println();
			}
		}
	}

}
