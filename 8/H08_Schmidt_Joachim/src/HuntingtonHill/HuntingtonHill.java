package HuntingtonHill;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * class to model the Huntington-Hill-Method
 * @author Florian Kadner
 *
 */
public class HuntingtonHill {

	private HashMap<String, State> states;
	private int H;
	
	public static void main(String[] args) {
		
		HuntingtonHill hh = new HuntingtonHill("USPopulation.txt", 100);
		
		try {
			hh.distributeSeats();
		} catch (MoreStatesThanSeatsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(hh.printDistribution());
		
	}
	
	/**
	 * Constructs a new object of a HuntingtonHill class
	 * @param filename The file path containing population data
	 * @param numberOfSeats The number of seats which are available to distribute
	 */
	public HuntingtonHill(String filename, int numberOfSeats) {

		this.H = numberOfSeats;
		states = new HashMap<String, State>();

		try {
			FileReader fr = new FileReader(filename);
			BufferedReader in = new BufferedReader(fr);

			String line;
			while ((line = in.readLine()) != null) {
				// line represents one row of the .txt file
				String[] splittedLine = line.split(";");
				String stateName = splittedLine[0];
				int statePopulation = Integer.parseInt(splittedLine[1]);
				
				states.put(stateName, new State(statePopulation));
			}

			in.close();
			fr.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * distribute the seats with the huntington-hill method
	 * @throws MoreStatesThanSeatsException
	 */
	public void distributeSeats() throws MoreStatesThanSeatsException {
		
		if (states.size() > H)
			throw new MoreStatesThanSeatsException();
		
		int numberOfSeatsDistributed = 0;
		
		// At the beginning, every state gets one seat
		states.forEach((String name, State state) -> {
			state.addSeat();
		});
		
		numberOfSeatsDistributed = states.size();
		
		// Distribute seats
		while (numberOfSeatsDistributed < H) {
			
			// Calculate priority
			Comparator<State> statePriorityComparator = (State s1, State s2) -> Double.compare(s1.priority(), s2.priority());
			
			// Add seat
			states.values().stream().max(statePriorityComparator).get().addSeat();
			
			numberOfSeatsDistributed++;
			
		}
		
			
	}
	
	/**
	 * creates a string containing the existing states and the number of assigned seats
	 * @return string containing the existing states and the number of assigned seats
	 */
	public String printDistribution() {
		
		StringBuilder result = new StringBuilder();
		
		result.append("Distributed ");
		result.append(H);
		result.append(" seats to ");
		result.append(states.size());
		result.append(" states\n\n");
		
		Comparator<Entry<String, State>> stateSeatComparator = (Entry<String, State> e1, Entry<String, State> e2) -> {
			
			int seatsCompared = Integer.compare(e2.getValue().getCurrentSeats(), e1.getValue().getCurrentSeats());
			
			if (seatsCompared != 0)
				return seatsCompared;
			else
				return e1.getKey().compareTo(e2.getKey());
			
		};
		
		states.entrySet().stream().sorted(stateSeatComparator).forEachOrdered((Entry<String, State> e) -> {
			result.append(e.getKey());
			result.append(": ");
			result.append(e.getValue().getCurrentSeats());
			result.append("\n");
		});
		
		return result.toString();

	}
	
	/**
	 * Gets a HashMap of states
	 * @return The states
	 */
	public HashMap<String, State> getStates() {
		return this.states;
	}

}
