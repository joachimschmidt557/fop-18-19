package H2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * @author Aria Jamili
 *
 */
public class SelfOrganizedTreeTest {

	private SelfOrganizedTree<Integer> myTree = new SelfOrganizedTree<Integer>();

	@Test
	public void searchTest() {
		myTree.insert(3);
		myTree.insert(1);
		myTree.insert(2);
		myTree.insert(5);
		myTree.insert(4);
		myTree.insert(6);
		myTree.insert(8);
		myTree.insert(7);
		
		Integer test1= 1;
		Integer test2= 2;
		Integer test3= 3;
		Integer test4= 4;
		Integer test5= 5;
		Integer test6= 6;
		Integer test8= 8;
		
		//zick test
		boolean b1 = myTree.search(1);
		assertEquals(true, b1);
		
		Integer testRootLeft;
		Integer testRootRight = myTree.root.right.value;
		Integer testRootRightLeft = myTree.root.right.left.value;
		Integer testRootRightRight = myTree.root.right.right.value;
		
		assertEquals(null, myTree.root.left);
		assertEquals(test3, testRootRight);
		assertEquals(test2, testRootRightLeft);
		assertEquals(test5, testRootRightRight);
		
		//zickzack test
		boolean b3 = myTree.search(3);
		assertEquals(true, b3);


		testRootLeft = myTree.root.left.value;
		Integer testRootLeftLeft;
		Integer testRootLeftRight = myTree.root.left.right.value;
		testRootRight = myTree.root.right.value;
		testRootRightLeft = myTree.root.right.left.value;
		testRootRightRight = myTree.root.right.right.value;
		
		assertEquals(test1, testRootLeft);
		assertEquals(null, myTree.root.left.left);
		assertEquals(test2, testRootLeftRight);
		assertEquals(test5, testRootRight);
		assertEquals(test4, testRootRightLeft);
		assertEquals(test6, testRootRightRight);
		
		//zackzick test
		boolean b2 = myTree.search(2);
		assertEquals(true, b2);
		
		testRootLeft = myTree.root.left.value;
		testRootRight = myTree.root.right.value;
		testRootRightRight = myTree.root.right.right.value;
		
		assertEquals(test1, testRootLeft);
		assertEquals(null, myTree.root.left.left);
		assertEquals(null, myTree.root.left.right);
		assertEquals(test3, testRootRight);
		assertEquals(null, myTree.root.right.left);
		assertEquals(test5, testRootRightRight);
			
		//zackzack test
		boolean b4 = myTree.search(5);
		assertEquals(true, b4);
		
		testRootLeft = myTree.root.left.value;
		testRootLeftLeft = myTree.root.left.left.value;
		testRootLeftRight = myTree.root.left.right.value;
		testRootRight = myTree.root.right.value;
		testRootRightRight = myTree.root.right.right.value;
		
		assertEquals(test3, testRootLeft);
		assertEquals(test2, testRootLeftLeft);
		assertEquals(test4, testRootLeftRight);
		assertEquals(test6, testRootRight);
		assertEquals(null, myTree.root.right.left);
		assertEquals(test8, testRootRightRight);
		
		//zickzick test
		boolean b5 = myTree.search(2);
		assertEquals(true, b5);
		
		testRootLeft = myTree.root.left.value;
		testRootRight = myTree.root.right.value;
		testRootRightRight = myTree.root.right.right.value;
		
		assertEquals(test1, testRootLeft);
		assertEquals(null, myTree.root.left.left);
		assertEquals(null, myTree.root.left.right);
		assertEquals(test3, testRootRight);
		assertEquals(null, myTree.root.right.left);
		assertEquals(test5, testRootRightRight);
		
		//zack test
		boolean b6 = myTree.search(3);
		assertEquals(true, b6);
		
		testRootLeft = myTree.root.left.value;
		testRootLeftLeft = myTree.root.left.left.value;
		testRootRight = myTree.root.right.value;
		testRootRightLeft = myTree.root.right.left.value;
		testRootRightRight = myTree.root.right.right.value;
		
		assertEquals(test2, testRootLeft);
		assertEquals(test1, testRootLeftLeft);
		assertEquals(null, myTree.root.left.right);
		assertEquals(test5, testRootRight);
		assertEquals(test4, testRootRightLeft);
		assertEquals(test6, testRootRightRight);
		
		//wenn das Element nicht vorhanden ist
		boolean b7 = myTree.search(9);
		assertEquals(false, b7);
	}
}
