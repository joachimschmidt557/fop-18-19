package SecurityAlert;

public class EmergencyQueue<T extends GovernmentEmployee> {

	private PriorityQueue<GovernmentEmployee> queue;
	
	public EmergencyQueue() {
		queue = new PriorityQueue<GovernmentEmployee>();
	}
	
	/**
	 * Rescues a number of government officials according to their priority
	 * @param n The number of people to rescue
	 */
	public void rescue(int n) {
		
		StringBuilder message = new StringBuilder();
		
		if (n > queue.getSize())
			throw new ArrayIndexOutOfBoundsException();
		
		for (int i = 0; i < n; i++) {
			GovernmentEmployee govEmpJustRemoved = queue.dequeue();
			message.append(govEmpJustRemoved.getTitle());
			message.append(" was rescued\n");
		}
		
		System.out.println(message.toString());
		
	}
	
	/**
	 * Make a Secretary a designated survivor
	 * @param ds The Secretary
	 */
	public void chooseDesignatedSurvivor(Secretary ds) {
		
		queue.addToHeadOfPriorityClass(ds);
		
	}
	
	/**
	 * Adds a new GovernmentEmployee to the queue
	 * @param newItem
	 */
	public void enqueue(GovernmentEmployee newItem) {
		queue.enqueue(newItem);
	}
	
	/**
	 * Returns a string representation of the queue of all
	 * government employees
	 * @return
	 */
	public String toString() {
		return queue.toString();
	}
	
}
