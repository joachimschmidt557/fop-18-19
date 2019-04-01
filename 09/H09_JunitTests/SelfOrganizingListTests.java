package H1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for the self organizing linked list.
 * 
 * @author Nhan Huynh
 */
@DisplayName("Tests for the self organizing list.")
public class SelfOrganizingListTests {

	@Nested
	@DisplayName("Method: search")
	public class TestSearch {
		
		private SelfOrganizingLinkedList<Integer> createListFrom1To6(ReorganizingAlgorithm ra) {
			SelfOrganizingLinkedList<Integer> list = new SelfOrganizingLinkedList<>(ra);
			IntStream.rangeClosed(1, 6).forEach(list::addLast);
			return list;
		}

		@SafeVarargs
		private final <T> void assertListIs(SimpleLinkedList<? extends T> list, T... content) {
			ArrayList<T> al = new ArrayList<>();

			for (ListItem<? extends T> current = list.head; current != null; current = current.next) {
				al.add(current.key);
			}
			Assertions.assertArrayEquals(content, al.toArray());
		}

		@Nested
		@DisplayName("Strategy: Move to Front")
		public class StrategyMoveToFront {
			
			@Test
			@DisplayName("An empty list should return null.")
			public void testHeadIsNull() {
				SelfOrganizingLinkedList<Integer> list = new SelfOrganizingLinkedList<Integer>(
						ReorganizingAlgorithm.MOVETOFRONT);
				Assertions.assertNull(list.search(s -> s == 1));
			}
			
			@Test
			@DisplayName("Element is not found.")
			public void testMoveToFrontElementNotFound() {
				SelfOrganizingLinkedList<Integer> list = createListFrom1To6(ReorganizingAlgorithm.MOVETOFRONT);
				Assertions.assertNull(list.search(s -> s == 8));
			}

			@Test
			@DisplayName("Current element fullfills the predicate.")
			public void testMoveToFrontCurrentElementFullfillsPredicate() {
				SelfOrganizingLinkedList<Integer> list = createListFrom1To6(ReorganizingAlgorithm.MOVETOFRONT);
				int actual = list.search(s -> s == 1);
				Assertions.assertEquals(Integer.valueOf(1), list.head.key);
				Assertions.assertEquals(1, actual);
			}

			@DisplayName("Strategy should move the found element to the front.")
			@ParameterizedTest(name = "Element {0} should be moved to the front when it is searched.")
			@ValueSource(ints = { 1, 2, 3, 4, 5, 6 })
			public void testMoveToFrontWhenSearched(int target) {
				SelfOrganizingLinkedList<Integer> list = createListFrom1To6(ReorganizingAlgorithm.MOVETOFRONT);
				int actual = list.search(s -> s == target);
				// Searched element should be moved to the front.
				Integer[] correctOrder = new Integer[6];
				correctOrder[0] = target;
				// After the first position the order remains without the searched element.
				for (int i = 1, currentNumber = 1; i < correctOrder.length; i++, currentNumber++) {
					if (currentNumber == target)
						currentNumber++;
					correctOrder[i] = currentNumber;
				}
				Assertions.assertEquals(target, actual);
				assertListIs(list, correctOrder);
			}
		}

		@Nested
		@DisplayName("Strategy: Transpose")
		public class StrategyTranspose {
			
			@Test
			@DisplayName("An empty list should return null.")
			public void testHeadIsNull() {
				SelfOrganizingLinkedList<Integer> list = new SelfOrganizingLinkedList<Integer>(
						ReorganizingAlgorithm.TRANSPOSE);
				Assertions.assertNull(list.search(s -> s == 1));
			}
			@Test
			@DisplayName("Element is not found. One element list.")
			public void testTransponseElementNotFoundOneElementList() {
				SelfOrganizingLinkedList<Integer> list = new SelfOrganizingLinkedList<Integer>(
						ReorganizingAlgorithm.TRANSPOSE);
				list.addLast(1);
				Assertions.assertNull(list.search(s -> s == 8));
			}

			@Test
			@DisplayName("Element is not found. More than one element list.")
			public void testTransponseElementNotFoundMoreElementList() {
				SelfOrganizingLinkedList<Integer> list = createListFrom1To6(ReorganizingAlgorithm.TRANSPOSE);
				Assertions.assertNull(list.search(s -> s == 8));
			}

			@Test
			@DisplayName("Current element fulfills predicate.")
			public void testTransponseurrentElementFullfillsPredicate() {
				SelfOrganizingLinkedList<Integer> list = createListFrom1To6(ReorganizingAlgorithm.TRANSPOSE);
				int actual = list.search(s -> s == 1);
				Assertions.assertEquals(Integer.valueOf(1), list.head.key);
				Assertions.assertEquals(1, actual);
			}

			@DisplayName("Strategy should transpose the found element.")
			@ParameterizedTest(name = "Element {0} should be transposed when it is searched.")
			@ValueSource(ints = { 1, 2, 3, 4, 5, 6 })
			public void testTransposeWhenSearched(int target) {
				SelfOrganizingLinkedList<Integer> list = createListFrom1To6(ReorganizingAlgorithm.TRANSPOSE);
				int actual = list.search(i -> i == target);
				Integer[] result = IntStream.rangeClosed(1, 6).mapToObj(i -> i).toArray(Integer[]::new);
				int pos = Arrays.binarySearch(result, target);
				if (pos != 0) {
					result[pos] = result[pos - 1];
					result[pos - 1] = target;
				}
				assertListIs(list, result);
				Assertions.assertEquals(target, actual);
			}

			@DisplayName("Strategy should find the found element when searched.")
			@ParameterizedTest(name = "Element {0} should be found.")
			@ValueSource(ints = { 1, 2, 3, 4, 5, 6 })
			public void testTransposeFindElement(int target) {
				SelfOrganizingLinkedList<Integer> list = createListFrom1To6(ReorganizingAlgorithm.TRANSPOSE);
				Assertions.assertEquals(target, (int) list.search(s -> s == target));
			}

			@Test
			@DisplayName("Strategy should transpose the found element even when searched multiple times.")
			public void testTransposeWhenSearchedMultiplieTimes() {
				SelfOrganizingLinkedList<Integer> list = createListFrom1To6(ReorganizingAlgorithm.TRANSPOSE);
				int actual01 = list.search(s -> s == 6);
				int actual02 = list.search(s -> s == 6);
				int actual03 = list.search(s -> s == 6);
				int actual04 = list.search(s -> s == 6);
				int actual05 = list.search(s -> s == 6);
				int actual06 = list.search(s -> s == 1);
				assertListIs(list, 1, 6, 2, 3, 4, 5);
				Assertions.assertEquals(6, actual01);
				Assertions.assertEquals(6, actual02);
				Assertions.assertEquals(6, actual03);
				Assertions.assertEquals(6, actual04);
				Assertions.assertEquals(6, actual05);
				Assertions.assertEquals(1, actual06);
			}
		}

		@Nested
		@DisplayName("Strategy: Count")
		public class StrategyCount {
			
			@Test
			@DisplayName("An empty list should return null.")
			public void testHeadIsNull() {
				SelfOrganizingLinkedList<Integer> list = new SelfOrganizingLinkedList<Integer>(
						ReorganizingAlgorithm.COUNT);
				Assertions.assertNull(list.search(s -> s == 1));
			}
			
			@Test
			@DisplayName("Element is not found. One element list.")
			public void testCountElementNotFoundOneElementList() {
				SelfOrganizingLinkedList<Integer> list = new SelfOrganizingLinkedList<Integer>(
						ReorganizingAlgorithm.COUNT);
				list.addLast(1);
				Assertions.assertNull(list.search(s -> s == 8));
			}

			@Test
			@DisplayName("Element is not found. More than one element list.")
			public void testCountElementNotFoundMoreElementList() {
				SelfOrganizingLinkedList<Integer> list = createListFrom1To6(ReorganizingAlgorithm.COUNT);
				Assertions.assertNull(list.search(s -> s == 8));
			}

			@DisplayName("Strategy should find the found element when searched.")
			@ParameterizedTest(name = "Element {0} should be found.")
			@ValueSource(ints = { 1, 2, 3, 4, 5, 6 })
			public void testCountFindElements(int target) {
				SelfOrganizingLinkedList<Integer> list = createListFrom1To6(ReorganizingAlgorithm.COUNT);
				Assertions.assertEquals(target, (int) list.search(s -> s == target));
			}

			@DisplayName("Strategy should order items based on how often they have been searched for.")
			@ParameterizedTest(name = "Element {0} counter should be increased after search.")
			@ValueSource(ints = { 1, 2, 3, 4, 5, 6, 1, 1, 2, 3, 4, 4, 4, 5, 6, 6, 4, 3, 2, 4, 3, 3 })
			public void testCountIncreasedCounter(int target) {
				SelfOrganizingLinkedList<Integer> list = createListFrom1To6(ReorganizingAlgorithm.COUNT);
				int expected = list.getElement(target).counter + 1;
				list.search(s -> s == target);
				Assertions.assertEquals(expected, list.getElement(target).counter);
			}

			@DisplayName("Strategy: Count. Strategy should move the found element when searched if the counter is greater than the element before it.")
			public void testCountMoveIfGreaterCounter(int target) {
				SelfOrganizingLinkedList<Integer> list = new SelfOrganizingLinkedList<Integer>(
						ReorganizingAlgorithm.COUNT);
				IntStream.rangeClosed(1, 5).forEach(list::addLast);
				list.setCounter(0, 99);
				list.setCounter(1, 76);
				list.setCounter(2, 45);
				list.setCounter(3, 44);
				list.setCounter(4, 19);
				list.search(s -> s == 4);
				list.search(s -> s == 4);
				Assertions.assertEquals(46, list.getElement(4).counter);
				assertListIs(list, 1, 2, 4, 3, 5);
			}
		}
	}
}
