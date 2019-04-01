package HuntingtonHill;

/**
 * class to model an us-american state
 * @author Florian Kadner
 *
 */
public class State {
	
	private int population;
	private int currentSeats;

	/**
	 * Constructs a new object of type State
	 * @param pop The population of the state
	 */
	public State(int pop) {
		this.population = pop;
		currentSeats = 0;
	}

	/**
	 * Gets the population
	 * @return The population
	 */
	public int getPopulation() {
		return this.population;
	}

	/**
	 * Gets the current number of seats
	 * @return The current number of seats
	 */
	public int getCurrentSeats() {
		return this.currentSeats;
	}

	/**
	 * Adds a seat to the state
	 */
	public void addSeat() {
		this.currentSeats++;
	}

	/**
	 * Calculates the priority according to Huntington Hill
	 * @return The priority
	 */
	public double priority() {
		
		return ( population / Math.sqrt(currentSeats * (currentSeats + 1)) );
		
	}

}
