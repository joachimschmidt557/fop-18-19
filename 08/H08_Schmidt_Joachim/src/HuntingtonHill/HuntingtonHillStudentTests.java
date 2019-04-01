package HuntingtonHill;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HuntingtonHillStudentTests {

	HuntingtonHill hh;

	@Test
	public void readFileTest1() {
		hh = new HuntingtonHill("USPopulation.txt", 100);
		assertEquals(50, hh.getStates().size(), "Wrong number of elements in hashtable.");
		assertEquals(4802982, hh.getStates().get("Alabama").getPopulation(), "Population of first state is wrong.");
		assertEquals(568300, hh.getStates().get("Wyoming").getPopulation(), "Population of last state is wrong.");
	}

	@Test
	public void readFileTest2() {
		hh = new HuntingtonHill("USPopulation.txt", 100);
		assertEquals(0, hh.getStates().get("New_Mexico").getCurrentSeats(),
				"At initialization the number of assigned seats should be zero.");
		assertEquals("Utah", hh.getStates().keySet().toArray()[2]);
	}

	@Test
	public void calculatePriorityTest1() {
		State narnia = new State(777);
		narnia.addSeat();
		narnia.addSeat();
		assertEquals(317.208, narnia.priority(), 0.001);
	}

	@Test
	public void calculatePriorityTest2() {
		State pandora = new State(4);
		pandora.addSeat();
		pandora.addSeat();
		pandora.addSeat();
		pandora.addSeat();
		assertEquals(0.894, pandora.priority(), 0.001);
	}

	@Test
	public void distributeSeatsTest() throws MoreStatesThanSeatsException {
		hh = new HuntingtonHill("USPopulation.txt", 100);
		hh.distributeSeats();
		assertEquals(2, hh.getStates().get("Washington").getCurrentSeats());
		assertEquals(1, hh.getStates().get("Montana").getCurrentSeats());
	}

	@Test
	public void moreStatesThanSeatsExceptionTest() {
		assertThrows(MoreStatesThanSeatsException.class, () -> {
			hh = new HuntingtonHill("USPopulation.txt", 25);
			hh.distributeSeats();
		});
	}

}
