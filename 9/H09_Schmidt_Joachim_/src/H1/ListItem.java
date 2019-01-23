package H1;

public class ListItem<T> {
	public T key;
	public ListItem<T> next;
	public int counter;
	
	public ListItem(T key) {
		this.key = key;
	}
	
	public ListItem(T key, ListItem<T> next) {
		this.key = key;
		this.next = next;
	}
	
}
