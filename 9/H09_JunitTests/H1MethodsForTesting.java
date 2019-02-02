// SelfOrganizingLinkedList
// Methods for H1 Tests
	/**
	 * 
	 * @param e Generic element
	 * @return the element if found else return null.
	 */
	public ListItem<T> getElement(T e) {
		for (ListItem<T> p = head; p != null; p = p.next) {
			if (p.key.equals(e))
				return p;
		}
		return null;
	}
	
	/**
	 * Set the counter of an element in the list.
	 * 
	 * @param index   Position of the element
	 * @param counter Number of views of the element
	 */
	public void setCounter(int index, int counter) {
		for (ListItem<T> p = head; p != null; p = p.next) {
			if (index == 0) {
				p.counter = counter;
				break;
			} else
				index--;
		}
	}