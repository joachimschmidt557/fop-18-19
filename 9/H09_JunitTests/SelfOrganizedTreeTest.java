package H2;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Test for the self organized tree.
 * 
 * @author Nhan Huynh
 *
 */
@DisplayName("Tests for the self organnized tree.")
public class SelfOrganizedTreeTest {

	@Nested
	@DisplayName("Method: search")
	public class TestSearch {

		private final int[] getBinaryTreeValues() {
			int[] btnValues = { 1000, 500, 250, 125, 62, 31, 93, 187, 156, 218, 375, 281, 265, 328, 437, 468, 406, 750,
					625, 562, 531, 593, 687, 656, 718, 875, 812, 781, 843, 937, 906, 968, 1500, 1250, 1125, 1062, 1031,
					1093, 1187, 1156, 1218, 1375, 1281, 1265, 1328, 1437, 1406, 1468, 1750, 1625, 1562, 1531, 1593,
					1687, 1656, 1718, 1875, 1812, 1781, 1843, 1937, 1906, 1968 };
			return btnValues;
		}

		private final SelfOrganizedTree<Integer> getBinaryTree() {
			SelfOrganizedTree<Integer> btn = new SelfOrganizedTree<Integer>();
			int[] btnValues = getBinaryTreeValues();
			for (int i = 0; i < btnValues.length; i++)
				btn.insert(btnValues[i]);
			return btn;
		}

		/**
		 * Comparison of whether the actual tree is equal to the expected tree.
		 * 
		 * @param expected Expected binary tree
		 * @param actual Actual binary tree
		 */
		private final <T> void assertTree(SelfOrganizedTree<? extends Comparable<T>> expected,
				SelfOrganizedTree<? extends Comparable<T>> actual) {
			String expectedTree = expected.toString();
			String actualTree = actual.toString();
			Assertions.assertEquals(expectedTree, actualTree);
		}

		@Test
		@DisplayName("Empty binary tree")
		public void testRootIsNull() {
			SelfOrganizedTree<Integer> btn = new SelfOrganizedTree<Integer>();
			Assertions.assertNull(btn.root);
		}

		@DisplayName("Element not found")
		@ParameterizedTest(name = "Element {0} cannot be found.")
		@ValueSource(ints = { 110, 232, 532, 645, 3432, 1, 0, 011, 54353, 30483, 213, 59674 })
		public void testElementNotFound(int target) {
			SelfOrganizedTree<Integer> actual = getBinaryTree();
			Assertions.assertFalse(actual.search(target));
		}

		@Test
		@DisplayName("Root fulfills search.")
		public void testRootIsSearched() {
			SelfOrganizedTree<Integer> actual = getBinaryTree();
			SelfOrganizedTree<Integer> expected = getBinaryTree();
			Assertions.assertTrue(actual.search(1000));
			assertTree(expected, actual);
		}

		@Nested
		@DisplayName("Zick- & Zack-Rotation")
		public class ZickAndZackRotation {

			@Test
			@DisplayName("Zick-Rotation")
			public void testZickRotation() {
				SelfOrganizedTree<Integer> actual = getBinaryTree();
				SelfOrganizedTree<Integer> expected = new SelfOrganizedTree<Integer>();
				int[] values = getBinaryTreeValues();
				expected.insert(500);
				for (int i = 0; i < values.length; i++)
					expected.insert(values[i]);
				Assertions.assertTrue(actual.search(500));
				assertTree(expected, actual);
			}

			@Test
			@DisplayName("Zack-Rotation")
			public void testZackRotation() {
				SelfOrganizedTree<Integer> actual = getBinaryTree();
				SelfOrganizedTree<Integer> expected = new SelfOrganizedTree<Integer>();
				int[] values = getBinaryTreeValues();
				expected.insert(1500);
				for (int i = 0; i < values.length; i++)
					expected.insert(values[i]);
				Assertions.assertTrue(actual.search(1500));
				assertTree(expected, actual);
			}
		}

		@Nested
		@DisplayName("Zick-Zick- & Zack-Zack-Rotation")
		public class ZickZickAndZackZackRotation {

			@DisplayName("Zick-Zick Rotation:")
			@ParameterizedTest(name = "Search for element {0}.")
			@ValueSource(ints = { 250, 125, 62, 31, 265, 562, 531, 781, 1125, 1062, 1031, 1265, 1562, 1531, 1781 })
			public void testZickZickRotation(int target) {
				SelfOrganizedTree<Integer> actual = getBinaryTree();
				SelfOrganizedTree<Integer> expected = new SelfOrganizedTree<Integer>();
				int[] values = getBinaryTreeValues();
				switch (target) {
				// Left Left
				case 250:
					expected.insert(250);
					expected.insert(500);
					break;
				// Left Left Left
				case 125:
					expected.insert(1000);
					expected.insert(125);
					expected.insert(250);
					break;
				// Left Left Left Left
				case 62:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(62);
					expected.insert(125);
					break;
				// Left Left Left Left Left
				case 31:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(250);
					expected.insert(31);
					expected.insert(62);
					break;
				// Left Left Right Left Left
				case 265:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(250);
					expected.insert(265);
					expected.insert(281);
					break;
				// Left Right Left Left
				case 562:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(562);
					expected.insert(625);
					break;
				// Left Right Left Left Left
				case 531:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(750);
					expected.insert(531);
					expected.insert(562);
					break;
				// Left Right Right Left Left
				case 781:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(750);
					expected.insert(781);
					expected.insert(812);
					break;
				// Right Left Left
				case 1125:
					expected.insert(1000);
					expected.insert(1125);
					expected.insert(1250);
					break;
				// Right Left Left Left
				case 1062:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1062);
					expected.insert(1125);
					break;
				// Right Left Left Left Left
				case 1031:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1250);
					expected.insert(1031);
					expected.insert(1062);
					break;
				// Right Left Right Left Left
				case 1265:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1250);
					expected.insert(1265);
					expected.insert(1281);
					break;
				// Right Right Left Left
				case 1562:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1562);
					expected.insert(1625);
					break;
				// Right Right Left Left Left
				case 1531:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1750);
					expected.insert(1531);
					expected.insert(1562);
					break;
				// Right Right Right Left Left
				case 1781:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1750);
					expected.insert(1781);
					expected.insert(1812);
					break;
				}
				for (int i = 0; i < values.length; i++)
					expected.insert(values[i]);
				Assertions.assertTrue(actual.search(target));
				assertTree(expected, actual);
			}

			@DisplayName("Zack-Zack Rotation:")
			@ParameterizedTest(name = "Search for element {0}.")
			@ValueSource(ints = { 1750, 1875, 1937, 1968, 1718, 1437, 1468, 1218, 875, 937, 968, 718, 437, 468, 218 })
			public void testZackZackRotation(int target) {
				SelfOrganizedTree<Integer> actual = getBinaryTree();
				SelfOrganizedTree<Integer> expected = new SelfOrganizedTree<Integer>();
				int[] values = getBinaryTreeValues();
				switch (target) {
				// Right Right
				case 1750:
					expected.insert(1750);
					expected.insert(1500);
					break;
				// Right Right Right
				case 1875:
					expected.insert(1000);
					expected.insert(1875);
					expected.insert(1750);
					break;
				// Right Right Right Right
				case 1937:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1937);
					expected.insert(1875);
					break;
				// Right Right Right Right
				case 1968:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1750);
					expected.insert(1968);
					expected.insert(1937);
					break;
				// Right Right Left Right Right
				case 1718:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1750);
					expected.insert(1718);
					expected.insert(1687);
					break;
				// Right Left Right Right
				case 1437:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1437);
					expected.insert(1375);
					break;
				// Right Left Right Right Right
				case 1468:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1250);
					expected.insert(1468);
					expected.insert(1437);
					break;
				// Right Left Left Right Right
				case 1218:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1250);
					expected.insert(1218);
					expected.insert(1187);
					break;
				// Left Right Right
				case 875:
					expected.insert(1000);
					expected.insert(875);
					expected.insert(750);
					break;
				// Left Right Right Right
				case 937:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(937);
					expected.insert(875);
					break;
				// Left Right Right Right Right
				case 968:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(750);
					expected.insert(968);
					expected.insert(937);
					break;
				// Left Right Left Right Right
				case 718:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(750);
					expected.insert(718);
					expected.insert(687);
					break;
				// Left Left Right Right
				case 437:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(437);
					expected.insert(375);
					break;
				// Left Left Right Right Right
				case 468:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(250);
					expected.insert(468);
					expected.insert(437);
					break;
				// Left Left Left Right Right Right
				case 218:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(250);
					expected.insert(218);
					expected.insert(187);
					break;
				}
				for (int i = 0; i < values.length; i++)
					expected.insert(values[i]);
				Assertions.assertTrue(actual.search(target));
				assertTree(expected, actual);
			}
		}

		@Nested
		@DisplayName("Zack-Zick- & Zick-Zack-Rotation")
		public class ZackZickAndZickZackRotation {

			@DisplayName("Zack-Zick Rotation:")
			@ParameterizedTest(name = "Search for element {0}.")
			@ValueSource(ints = { 93, 187, 375, 328, 750, 687, 593, 843, 1093, 1187, 1375, 1328, 1593, 1687, 1843 })
			public void testZackZickRotation(int target) {
				SelfOrganizedTree<Integer> actual = getBinaryTree();
				SelfOrganizedTree<Integer> expected = new SelfOrganizedTree<Integer>();
				int[] values = getBinaryTreeValues();
				switch (target) {
				// Left Left Left Left Right
				case 93:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(250);
					expected.insert(93);
					break;
				// Left Left Left Right
				case 187:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(187);
					break;
				// Left Left Right
				case 375:
					expected.insert(1000);
					expected.insert(375);
					break;
				// Left Left Right Left Right
				case 328:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(250);
					expected.insert(328);
					break;
				// Left Right
				case 750:
					expected.insert(750);
					break;
				case 687:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(687);
					break;
				// Left Right Left Left Right
				case 593:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(750);
					expected.insert(593);
					break;
				// Left Right Right Left Right
				case 843:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(750);
					expected.insert(843);
					break;
				// Right Left Left Left Right
				case 1093:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1250);
					expected.insert(1093);
					break;
				// Right Left Left Right
				case 1187:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1187);
					break;
				// Right Left Right
				case 1375:
					expected.insert(1000);
					expected.insert(1375);
					break;
				// Right Left Right Left Right
				case 1328:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1250);
					expected.insert(1328);
					break;
				// Right Right Left Left Right
				case 1593:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1750);
					expected.insert(1593);
					break;
				// Right Right Left Right
				case 1687:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1687);
					break;
				// Right Right Right Left Right
				case 1843:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1750);
					expected.insert(1843);
					break;
				}
				for (int i = 0; i < values.length; i++)
					expected.insert(values[i]);
				Assertions.assertTrue(actual.search(target));
				assertTree(expected, actual);
			}

			@DisplayName("Zick-Zack Rotation:")
			@ParameterizedTest(name = "Search for element {0}.")
			@ValueSource(ints = { 1906, 1812, 1625, 1656, 1250, 1281, 1406, 1156, 625, 906, 812, 656, 406, 281, 156 })
			public void testZickZackRotation(int target) {
				SelfOrganizedTree<Integer> actual = getBinaryTree();
				SelfOrganizedTree<Integer> expected = new SelfOrganizedTree<Integer>();
				int[] values = getBinaryTreeValues();
				switch (target) {
				// Right Right Right Right Left
				case 1906:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1750);
					expected.insert(1906);
					break;
				// Right Right Right Left
				case 1812:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1812);
					break;
				// Right Right Left
				case 1625:
					expected.insert(1000);
					expected.insert(1625);
					break;
				// Right Right Left Right Left
				case 1656:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1750);
					expected.insert(1656);
					break;
				// Right Left
				case 1250:
					expected.insert(1250);
					break;
				// Right Left Right Left
				case 1281:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1281);
					break;
				// Right Left Right Right Left
				case 1406:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1250);
					expected.insert(1406);
					break;
				// Right Left Left Right Left
				case 1156:
					expected.insert(1000);
					expected.insert(1500);
					expected.insert(1250);
					expected.insert(1156);
					break;
				// Left Right Right Right Left
				case 906:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(750);
					expected.insert(906);
					break;
				// Left Right Right Left
				case 812:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(812);
					break;
				// Left Right Left Right Left
				case 656:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(750);
					expected.insert(656);
					break;
				// Left Right Left
				case 625:
					expected.insert(1000);
					expected.insert(625);
					break;
				// Left Left Right Right Left
				case 406:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(250);
					expected.insert(406);
					break;
				// Left Left Right Left
				case 281:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(281);
					break;
				// Left Left Left Right Left
				case 156:
					expected.insert(1000);
					expected.insert(500);
					expected.insert(250);
					expected.insert(156);
					break;
				}
				for (int i = 0; i < values.length; i++)
					expected.insert(values[i]);
				Assertions.assertTrue(actual.search(target));
				assertTree(expected, actual);
			}
		}
	}
}
