package HuntingtonHill;

/**
 * Exception if there are more states than seats available in the Huntington-Hill-Method
 * @author Florian Kadner
 *
 */
@SuppressWarnings("serial")
public class MoreStatesThanSeatsException extends Exception {
	
	/**
	 * Constructs a new object of this class meaning
	 * that there are more states than seats
	 */
	public MoreStatesThanSeatsException() {
		super("There are more States than Seats available!");
	}
	
}
