package H1;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import java.util.function.Predicate;

/**
 * 
 * @author Aria Jamili
 *
 */

public class SelfOrganizingAlgorithmTest {

	// Predicates
	
	private class isSix implements Predicate<Integer> {

		@Override
		public boolean test(Integer value) {
			if(value == 6) {
				return true;
			}
			return false;
		}
	}
	
	private class isFive implements Predicate<Integer> {

		@Override
		public boolean test(Integer value) {
			if(value == 5) {
				return true;
			}
			return false;
		}
	}
	
	private class isFour implements Predicate<Integer> {

		@Override
		public boolean test(Integer value) {
			if(value == 4) {
				return true;
			}
			return false;
		}
	}

	private class isThree implements Predicate<Integer> {

		@Override
		public boolean test(Integer value) {
			if(value == 3) {
				return true;
			}
			return false;
		}
	}
	
	private class isTwo implements Predicate<Integer> {

		@Override
		public boolean test(Integer value) {
			if(value == 2) {
				return true;
			}
			return false;
		}
	}

	private class isOne implements Predicate<Integer> {

		@Override
		public boolean test(Integer value) {
			if(value == 1) {
				return true;
			}
			return false;
		}
	}

	private Predicate<Integer> isSix = new isSix();
	private Predicate<Integer> isFive = new isFive();
	private Predicate<Integer> isFour = new isFour();
	private Predicate<Integer> isThree = new isThree();
	private Predicate<Integer> isTwo = new isTwo();
	private Predicate<Integer> isOne = new isOne();
	
	
	@Test
	void searchMoveToFrontTest() {
		System.out.println("----------------------------------------------\n");
		System.out.println("searchMoveToFrontTest :\n");
		
		SelfOrganizingLinkedList<Integer> list = new SelfOrganizingLinkedList<Integer>(ReorganizingAlgorithm.MOVETOFRONT);
		list.addLast(5);
		list.addLast(2);
		list.addLast(1);
		list.addLast(4);
		list.addLast(3);
		
		System.out.println("list vor dem Aufruf search(isOne) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		
		
		Integer r1 = list.search(isOne);
		assertEquals(list.head.key, r1);
		
		System.out.println("list nach dem Aufruf search(isOne) :");
		System.out.println("");
		list.print(false);
		System.out.println("");

		System.out.println("list vor dem Aufruf search(isTwo) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		
		Integer r2 = list.search(isTwo);
		assertEquals(list.head.key, r2);
		
		System.out.println("list nach dem Aufruf search(isTwo) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		
		System.out.println("list vor dem Aufruf search(isTwo) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		
		Integer r22 = list.search(isTwo);
		assertEquals(list.head.key, r22);
		
		System.out.println("list nach dem Aufruf search(isTwo) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		
		System.out.println("list vor dem Aufruf search(isThree) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		
		Integer r3 = list.search(isThree);;
		assertEquals(list.head.key, r3);
		
		System.out.println("list nach dem Aufruf search(isThree) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		System.out.println("----------------------------------------------\n");
	}
	
	@Test
	void searchTransposeTest() {
		System.out.println("----------------------------------------------\n");
		System.out.println("searchTransposeTest :\n");
		
		SelfOrganizingLinkedList<Integer> list = new SelfOrganizingLinkedList<Integer>(ReorganizingAlgorithm.TRANSPOSE);
		list.addLast(5);
		list.addLast(2);
		list.addLast(1);
		list.addLast(4);
		list.addLast(3);
		
		System.out.println("list vor dem Aufruf search(isOne) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		
		Integer r1 = list.search(isOne);
		Integer test1 =  1;
		assertEquals( test1, r1);
		
		System.out.println("list nach dem Aufruf search(isOne) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		
		System.out.println("list vor dem Aufruf search(isTwo) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		
		Integer r2 = list.search(isTwo);
		Integer test2 =  2;
		assertEquals( test2, r2);
		
		System.out.println("list nach dem Aufruf search(isTwo) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		
		System.out.println("list vor dem Aufruf search(isThree) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		
		System.out.println("----------------------------------------------");
		Integer r3 = list.search(isThree);
		Integer test3 =  3;
		assertEquals( test3, r3);
		
		System.out.println("list nach dem Aufruf search(isThree) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		
		System.out.println("list vor dem Aufruf search(isFive) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		
		System.out.println("----------------------------------------------");
		Integer r4 = list.search(isFive);
		Integer test4 =  5;
		assertEquals( test4, r4);
		
		System.out.println("list nach dem Aufruf search(isFive) :");
		System.out.println("");
		list.print(false);
		System.out.println("");
		System.out.println("----------------------------------------------\n");
	}

	@Test
	void searchCountTest() {
		System.out.println("----------------------------------------------\n");
		System.out.println("searchCountTest :\n");
		SelfOrganizingLinkedList<Integer> list = new SelfOrganizingLinkedList<Integer>(ReorganizingAlgorithm.COUNT);
		list.addLast(5);
		list.addLast(2);
		list.addLast(1);
		list.addLast(4);
		list.addLast(3);
		
		System.out.println("list vor dem Aufruf search(isTwo) :");
		System.out.println("");
		list.print(true);
		System.out.println("");
		
		list.search(isTwo);
		
		System.out.println("list nach dem Aufruf search(isTwo) :");
		System.out.println("");
		list.print(true);
		System.out.println("");
			
		list.search(isThree);
		
		System.out.println("list nach dem Aufruf search(isThree) :");
		System.out.println("");
		list.print(true);
		System.out.println("");
		
		list.search(isOne);
		
		System.out.println("list nach dem Aufruf search(isOne) :");
		System.out.println("");
		list.print(true);
		System.out.println("");
		
		
		list.search(isOne);

		System.out.println("list nach dem Aufruf search(isOne) :");
		System.out.println("");
		list.print(true);
		System.out.println("");
		
		list.search(isOne);
		
		System.out.println("list nach dem Aufruf search(isOne) :");
		System.out.println("");
		list.print(true);
		System.out.println("");
		
		list.search(isTwo);
		
		System.out.println("list nach dem Aufruf search(isTwo) :");
		System.out.println("");
		list.print(true);
		System.out.println("");

		list.search(isTwo);

		System.out.println("list nach dem Aufruf search(isTwo) :");
		System.out.println("");
		list.print(true);
		System.out.println("");
		
		list.search(isFour);

		System.out.println("list nach dem Aufruf search(isFour) :");
		System.out.println("");
		list.print(true);
		System.out.println("");
		
		list.search(isFour);

		System.out.println("list nach dem Aufruf search(isFour) :");
		System.out.println("");
		list.print(true);
		System.out.println("");
		
		list.search(isFour);

		System.out.println("list nach dem Aufruf search(isFour) :");
		System.out.println("");
		list.print(true);
		System.out.println("");
		
		list.search(isFour);
		
		System.out.println("list nach dem Aufruf search(isFour) :");
		System.out.println("");
		list.print(true);
		System.out.println("");
		
		list.search(isFour);
		
		System.out.println("list nach dem Aufruf search(isFour) :");
		System.out.println("");
		list.print(true);
		System.out.println("");
		
		assertEquals(list.head.counter, 5);
		
		Integer TestNull = list.search(isSix); 
		
		assertEquals(null, TestNull);
		
	}
	
}
