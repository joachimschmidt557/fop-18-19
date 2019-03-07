package SecurityAlert;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmergencyTest {

	private EmergencyQueue<GovernmentEmployee> emergency;
	private PriorityQueue<Integer> intQueue;
	private PriorityQueue<String> stringQueue;
	private PriorityQueue<GovernmentEmployee> govEmpQueue;

	// TEST DATA: SINGAPORE
	// President
	President president = new President("Halimah Yacob", Sex.FEMALE);

	// Secretaries
	Secretary primeMinister = new Secretary("Lee Hsien Loong", Sex.MALE);
	Secretary defenceMinister = new Secretary("Ng Eng Hen", Sex.MALE);
	Secretary foreignMinister = new Secretary("Vivian Balakrishnan", Sex.MALE);
	Secretary homeMinister = new Secretary("K. Shanmugam", Sex.MALE);

	// Others
	Other speakerOfParliament = new Other("Tan Chuan-Jin", Sex.MALE);
	Other leaderOfTheHouse = new Other("Grace Fu", Sex.FEMALE);

	/**
	 * Initialize the tests
	 */
	@BeforeEach
	public void init() {
		emergency = new EmergencyQueue<GovernmentEmployee>();
		intQueue = new PriorityQueue<Integer>();
		stringQueue = new PriorityQueue<String>();
		govEmpQueue = new PriorityQueue<GovernmentEmployee>();

		emergency.enqueue(defenceMinister);
		emergency.enqueue(foreignMinister);
		emergency.enqueue(homeMinister);
		emergency.enqueue(leaderOfTheHouse);
		emergency.enqueue(president);
		emergency.enqueue(primeMinister);
		emergency.enqueue(speakerOfParliament);

	}

	// Test PriorityQueue

	/**
	 * Test enqueueing one object
	 */
	@Test
	public void testEnqueue1() {

		intQueue.enqueue(123);
		assertEquals(intQueue.getSize(), 1);

	}

	/**
	 * Test enqueueing multiple objects
	 */
	@Test
	public void testEnqueue2() {

		stringQueue.enqueue("Apple");
		stringQueue.enqueue("Banana");
		stringQueue.enqueue("Chocolate");
		assertEquals(stringQueue.getSize(), 3);
		assertEquals(stringQueue.dequeue(), "Chocolate");
		assertEquals(stringQueue.dequeue(), "Banana");
		assertEquals(stringQueue.dequeue(), "Apple");

	}

	/**
	 * Test dequeuing one object
	 */
	@Test
	public void testDequeue1() {

		stringQueue.enqueue("Apple");
		assertEquals(stringQueue.dequeue(), "Apple");

	}

	/**
	 * Test dequeuing one object
	 */
	@Test
	public void testDequeue2() {

		intQueue.enqueue(123);
		intQueue.enqueue(123);
		intQueue.enqueue(234);
		intQueue.enqueue(345);
		intQueue.enqueue(123);
		assertEquals(intQueue.dequeue(), new Integer(345));
		assertEquals(intQueue.dequeue(), new Integer(234));
		assertEquals(intQueue.dequeue(), new Integer(123));
		assertEquals(intQueue.dequeue(), new Integer(123));

	}

	/**
	 * Get size of empty queue
	 */
	@Test
	public void testGetSize1() {

		assertEquals(intQueue.getSize(), 0);
		assertEquals(stringQueue.getSize(), 0);
		assertEquals(govEmpQueue.getSize(), 0);

	}

	/**
	 * Get size of queue with one element
	 */
	@Test
	public void testGetSize2() {

		govEmpQueue.enqueue(defenceMinister);
		assertEquals(govEmpQueue.getSize(), 1);

	}

	/**
	 * Get size of queue with multiple elements
	 */
	@Test
	public void testGetSize3() {

		for (int i = 0; i < 100; i++)
			intQueue.enqueue(i);
		assertEquals(intQueue.getSize(), 100);

	}

	/**
	 * Test empty queue
	 */
	@Test
	public void testToString1() {

		assertEquals(intQueue.toString(), "");
		assertEquals(stringQueue.toString(), "");
		assertEquals(govEmpQueue.toString(), "");

	}

	/**
	 * Test queue with one element
	 */
	@Test
	public void testToString2() {

		stringQueue.enqueue("I like trains");
		assertEquals(stringQueue.toString(), "#0: I like trains\n");

	}

	/**
	 * Test queue with multiple elements
	 */
	@Test
	public void testToString3() {

		intQueue.enqueue(2);
		intQueue.enqueue(3);
		intQueue.enqueue(1);
		intQueue.enqueue(1);
		assertEquals(intQueue.toString(), "#0: 3\n#1: 2\n#2: 1\n#3: 1\n");

	}

	/**
	 * Test with empty queue
	 */
	@Test
	public void testHeadPriorityClass1() {

		intQueue.addToHeadOfPriorityClass(2);
		stringQueue.addToHeadOfPriorityClass("blabla");
		govEmpQueue.addToHeadOfPriorityClass(primeMinister);
		assertEquals(intQueue.dequeue(), null);
		assertEquals(stringQueue.dequeue(), null);
		assertEquals(govEmpQueue.dequeue(), null);

	}

	/**
	 * Test with only one element
	 */
	@Test
	public void testHeadPriorityClass2() {

		stringQueue.enqueue("blabla");
		stringQueue.addToHeadOfPriorityClass("blabla");
		assertEquals(stringQueue.dequeue(), "blabla");

	}

	/**
	 * Test with multiple elements
	 */
	@Test
	public void testHeadPriorityClass3() {

		govEmpQueue.enqueue(president);
		govEmpQueue.enqueue(defenceMinister);
		govEmpQueue.enqueue(foreignMinister);
		govEmpQueue.enqueue(homeMinister);
		govEmpQueue.enqueue(leaderOfTheHouse);
		govEmpQueue.addToHeadOfPriorityClass(foreignMinister);
		govEmpQueue.dequeue();
		assertEquals(govEmpQueue.dequeue(), foreignMinister);

	}

	// Test EmergencyQueue

	/**
	 * Test rescuing no one
	 */
	@Test
	public void testRescue1() {

		emergency.rescue(0);
		assertEquals(emergency.toString(), "#0: Madame President Halimah Yacob\n" + "#1: Mister Secretary Ng Eng Hen\n"
				+ "#2: Mister Secretary Vivian Balakrishnan\n" + "#3: Mister Secretary K. Shanmugam\n"
				+ "#4: Mister Secretary Lee Hsien Loong\n" + "#5: Madame Grace Fu\n" + "#6: Mister Tan Chuan-Jin\n");

	}

	/**
	 * Test rescuing people
	 */
	@Test
	public void testRescue2() {

		emergency.rescue(3);
		assertEquals(emergency.toString(), "#0: Mister Secretary K. Shanmugam\n"
				+ "#1: Mister Secretary Lee Hsien Loong\n" + "#2: Madame Grace Fu\n" + "#3: Mister Tan Chuan-Jin\n");

	}

	/**
	 * Test choosing a designated survivor
	 */
	@Test
	public void testChooseDesignatedSurvivor1() {

		emergency.chooseDesignatedSurvivor(homeMinister);
		assertEquals(emergency.toString(),
				"#0: Madame President Halimah Yacob\n" + "#1: Mister Secretary K. Shanmugam\n"
						+ "#2: Mister Secretary Ng Eng Hen\n" + "#3: Mister Secretary Vivian Balakrishnan\n"
						+ "#4: Mister Secretary Lee Hsien Loong\n" + "#5: Madame Grace Fu\n"
						+ "#6: Mister Tan Chuan-Jin\n");

	}

	/**
	 * Test choosing another designated survivor
	 */
	@Test
	public void testChooseDesignatedSurvivor2() {

		emergency.chooseDesignatedSurvivor(primeMinister);
		assertEquals(emergency.toString(),
				"#0: Madame President Halimah Yacob\n" + "#1: Mister Secretary Lee Hsien Loong\n"
						+ "#2: Mister Secretary Ng Eng Hen\n" + "#3: Mister Secretary Vivian Balakrishnan\n"
						+ "#4: Mister Secretary K. Shanmugam\n" + "#5: Madame Grace Fu\n"
						+ "#6: Mister Tan Chuan-Jin\n");

	}

	/**
	 * Enqueue one person
	 */
	@Test
	public void testEnqueueEmergencyQueue1() {

		emergency.rescue(7);
		emergency.enqueue(president);

		assertEquals(emergency.toString(), "#0: Madame President Halimah Yacob\n");

	}

	/**
	 * Enqueue multiple people
	 */
	@Test
	public void testEnqueueEmergencyQueue2() {

		emergency.rescue(7);
		emergency.enqueue(president);
		emergency.enqueue(speakerOfParliament);

		assertEquals(emergency.toString(), "#0: Madame President Halimah Yacob\n" + "#1: Mister Tan Chuan-Jin\n");

	}

	/**
	 * Test converting the whole queue to string
	 */
	@Test
	public void testToStringEmergencyQueue1() {

		assertEquals(emergency.toString(), "#0: Madame President Halimah Yacob\n" + "#1: Mister Secretary Ng Eng Hen\n"
				+ "#2: Mister Secretary Vivian Balakrishnan\n" + "#3: Mister Secretary K. Shanmugam\n"
				+ "#4: Mister Secretary Lee Hsien Loong\n" + "#5: Madame Grace Fu\n" + "#6: Mister Tan Chuan-Jin\n");

	}
	
	/**
	 * Test converting an empty emergency queue
	 */
	@Test
	public void testToStringEmergencyQueue2() {

		emergency.rescue(7);
		assertEquals(emergency.toString(), "");

	}

}
