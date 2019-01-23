package H2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * 
 * @author Aria Jamili
 * @author Felix Graner
 */

/*
 * "Improvement" of Arias Tests:
 * now before every test these different trees are built --> for visual representation check the image
 * 
 * better readability
 * 
 * testing single functions
 *
 * Bonus meme somewhere
 */
	// if you want to add own tests just use the trees already given or stick to my way of doing things for minimum bugs

	//My Way of doing things:
	/*
	 * first make a private SelfOrganizedTree<T> but be aware that using other than Integer may lead to false negative because of compareTrees()
	 * Make an [] int and fill it in the order that you want to insert your values into the binary tree and insert it in the initialize() part through a for-loop
	 * 
	 * shouldBeTree is wiped during every initialize() so you can just enter your expected values into that.
	 * Use the handsome compareTrees method to compare the two trees and test it with assertTrue()
	 * 
	 * compareTrees takes the roots of two trees and compares each node of both trees with a != b, returns true if no differences were detected
	 */
public class SelfOrganizedTreeTest {
	
	private SelfOrganizedTree<Integer> myFirstTree = new SelfOrganizedTree<Integer>();
	private SelfOrganizedTree<Integer> mySmallZickTree = new SelfOrganizedTree<Integer>();
	private SelfOrganizedTree<Integer> mySmallZackTree= new SelfOrganizedTree<Integer>();
	private SelfOrganizedTree<Integer> shouldBeTree = new SelfOrganizedTree<Integer>();
	private SelfOrganizedTree<Integer> firstBigTree = new SelfOrganizedTree<Integer>();

	
	@SuppressWarnings("all")
	// The Workaround for not being able to compare Trees directly
	// Should actually use .compareTo but Java is .trash ~~_(Ô_Ô)_~~
	public  Boolean compareTrees(BinaryTreeNode firstTreeRoot, BinaryTreeNode secondTreeRoot) {
		if(firstTreeRoot.value != secondTreeRoot.value) // if any value is not equal the trees are unequal
			return false;
		
		if(firstTreeRoot.left != null) // go to the most left node
			compareTrees(firstTreeRoot.left, secondTreeRoot.left);
		
		if(firstTreeRoot.right != null) // take all right possibilities
			compareTrees(firstTreeRoot.right, secondTreeRoot.right);
		return true;
		
			
		
	}

	
	public void initialize() {
										      //         3
		myFirstTree = new SelfOrganizedTree<Integer>();//  \
		myFirstTree.insert(3);				 //		  /     \
		myFirstTree.insert(1);				//      1        5
		myFirstTree.insert(2); 			   //         2    4   6
		myFirstTree.insert(5);             //                     8
		myFirstTree.insert(4);            //                     7
		myFirstTree.insert(6);
		myFirstTree.insert(8);
		myFirstTree.insert(7);
		
		//[]int shouldBe = {root, left, right}; when done correctly
		shouldBeTree = new SelfOrganizedTree<Integer>();
		
		
		mySmallZickTree = new SelfOrganizedTree<Integer>();
		int[] myElements = {10,5,2,8,15};
		for(int i:myElements) 
			mySmallZickTree.insert(i);
		
		mySmallZackTree = new SelfOrganizedTree<Integer>();
		int[] myZacks = {10,15,12,18,5}; 
		for(int i:myZacks)
			mySmallZackTree.insert(i);
		
		firstBigTree = new SelfOrganizedTree<Integer>();
		int[] bigElementArray = {50, 25, 12, 6, 3, 15, 13, 18, 75, 60, 55, 53, 58, 65, 63, 68, 90, 85, 100, 82};
		for(int i:bigElementArray)
			firstBigTree.insert(i);
	}
	
	@Test
	public void searchTest() { // nothing changed here, only took the initializing of the tree out of the function
		initialize();
		Integer test1= 1;
		Integer test2= 2;
		Integer test3= 3;
		Integer test4= 4;
		Integer test5= 5;
		Integer test6= 6;
		Integer test8= 8;
		//zick test
		boolean b1 = myFirstTree.search(1);
		
		
		assertEquals(true, b1);
		
		Integer testRootLeft;
		Integer testRootRight = myFirstTree.root.right.value;
		Integer testRootRightLeft = myFirstTree.root.right.left.value;
		Integer testRootRightRight = myFirstTree.root.right.right.value;
		
		assertEquals(null, myFirstTree.root.left);
		assertEquals(test3, testRootRight);
		assertEquals(test2, testRootRightLeft);
		assertEquals(test5, testRootRightRight);
		
		//zickzack test
		boolean b3 = myFirstTree.search(3);
		assertEquals(true, b3);


		testRootLeft = myFirstTree.root.left.value;
		Integer testRootLeftLeft;
		Integer testRootLeftRight = myFirstTree.root.left.right.value;
		testRootRight = myFirstTree.root.right.value;
		testRootRightLeft = myFirstTree.root.right.left.value;
		testRootRightRight = myFirstTree.root.right.right.value;
		
		assertEquals(test1, testRootLeft);
		assertEquals(null, myFirstTree.root.left.left);
		assertEquals(test2, testRootLeftRight);
		assertEquals(test5, testRootRight);
		assertEquals(test4, testRootRightLeft);
		assertEquals(test6, testRootRightRight);
		
		//zackzick test
		boolean b2 = myFirstTree.search(2);
		assertEquals(true, b2);
		
		testRootLeft = myFirstTree.root.left.value;
		testRootRight = myFirstTree.root.right.value;
		testRootRightRight = myFirstTree.root.right.right.value;
		
		assertEquals(test1, testRootLeft);
		assertEquals(null, myFirstTree.root.left.left);
		assertEquals(null, myFirstTree.root.left.right);
		assertEquals(test3, testRootRight);
		assertEquals(null, myFirstTree.root.right.left);
		assertEquals(test5, testRootRightRight);
			
		BTreePrinter.printNode(myFirstTree.root);
		
		//zackzack test
		boolean b4 = myFirstTree.search(5);
		assertEquals(true, b4);
		
		BTreePrinter.printNode(myFirstTree.root);
		
		testRootLeft = myFirstTree.root.left.value;
		testRootLeftLeft = myFirstTree.root.left.left.value;
		testRootLeftRight = myFirstTree.root.left.right.value;
		testRootRight = myFirstTree.root.right.value;
		testRootRightRight = myFirstTree.root.right.right.value;
		
		assertEquals(test3, testRootLeft);
		assertEquals(test2, testRootLeftLeft);
		assertEquals(test4, testRootLeftRight);
		assertEquals(test6, testRootRight);
		assertEquals(null, myFirstTree.root.right.left);
		assertEquals(test8, testRootRightRight);
		
		//zickzick test
		boolean b5 = myFirstTree.search(2);
		assertEquals(true, b5);
		
		testRootLeft = myFirstTree.root.left.value;
		testRootRight = myFirstTree.root.right.value;
		testRootRightRight = myFirstTree.root.right.right.value;
		
		assertEquals(test1, testRootLeft);
		assertEquals(null, myFirstTree.root.left.left);
		assertEquals(null, myFirstTree.root.left.right);
		assertEquals(test3, testRootRight);
		assertEquals(null, myFirstTree.root.right.left);
		assertEquals(test5, testRootRightRight);
		
		//zack test
		boolean b6 = myFirstTree.search(3);
		assertEquals(true, b6);
		
		testRootLeft = myFirstTree.root.left.value;
		testRootLeftLeft = myFirstTree.root.left.left.value;
		testRootRight = myFirstTree.root.right.value;
		testRootRightLeft = myFirstTree.root.right.left.value;
		testRootRightRight = myFirstTree.root.right.right.value;
		
		assertEquals(test2, testRootLeft);
		assertEquals(test1, testRootLeftLeft);
		assertEquals(null, myFirstTree.root.left.right);
		assertEquals(test5, testRootRight);
		assertEquals(test4, testRootRightLeft);
		assertEquals(test6, testRootRightRight);
		
		//wenn das Element nicht vorhanden ist
		boolean b7 = myFirstTree.search(9);
		assertEquals(false, b7);
		
	}
	
	@Test
	public void equalsTest() {
		initialize();
		
		SelfOrganizedTree<Integer> someTree = new SelfOrganizedTree<Integer>();
		someTree.insert(5);
		SelfOrganizedTree<Integer> anotherTree = new SelfOrganizedTree<Integer>();
		anotherTree.insert(5);
		assertTrue(compareTrees(someTree.root, anotherTree.root));
	}

	@Test
	public void firstZickTest() { 
		initialize();
		myFirstTree.search(1);
		
		shouldBeTree.insert(1);
		shouldBeTree.insert(3);
		shouldBeTree.insert(2);
		shouldBeTree.insert(5);
		shouldBeTree.insert(4);
		shouldBeTree.insert(6);
		shouldBeTree.insert(8);
		shouldBeTree.insert(7);
		assertEquals(compareTrees(myFirstTree.root, shouldBeTree.root), true);
	}
	
	@Test
	public void secondZickTest() {
		initialize();
		mySmallZickTree.search(5);
		int[] shouldBeValues = {5,2,10,8,15};
		for(int i:shouldBeValues)
			shouldBeTree.insert(i);
		//shouldBeTree.print();
		assertEquals(compareTrees(shouldBeTree.root, mySmallZickTree.root), true);
	}
	
	@Test
	public void zackTest() {
		initialize();
		mySmallZackTree.search(15);
		int[] shouldBes = {15,18,10,12,5};
		for(int i:shouldBes)
			shouldBeTree.insert(i);
		assertTrue(compareTrees(shouldBeTree.root, mySmallZackTree.root));
		// When some guy uses dead memes in his presentation
		// Normal people reaction: https://tinyurl.com/yanho977
		// My reaction: https://tinyurl.com/yda5kdaq
	}
	
	@Test
	public void firstZackZackTest() {
		initialize();
		mySmallZackTree.search(18);
		int[] shouldBe = {18,15,10,5,12};
		for(int i:shouldBe)
			shouldBeTree.insert(i);
		assertTrue(compareTrees(shouldBeTree.root, mySmallZackTree.root));
	}
	
	@Test
	public void firstZickZickTest() {
		initialize();
		mySmallZickTree.search(2);
		int[] shouldBeValues = {2,5,10,8,15};
		for(int i:shouldBeValues)
			shouldBeTree.insert(i);
		assertTrue(compareTrees(shouldBeTree.root, mySmallZickTree.root));
	}
	
	@Test
	public void firstZickZackTest() {
		initialize();
		mySmallZackTree.search(12);
		int[] shouldBe = {12,10,5,15,18};
		for(int i:shouldBe)
			shouldBeTree.insert(i);
		assertTrue(compareTrees(shouldBeTree.root, mySmallZackTree.root));
	}

	@Test
	public void firstDepthThreeZackZackTest() {
		initialize();
		firstBigTree.search(100);
		int[] shouldBe = {50,  25,12,6,3,15,13,18,  100,90,75,85,82,60,55,53,58,60,63,68};
		for(int i:shouldBe)
			shouldBeTree.insert(i);
		assertTrue(compareTrees(shouldBeTree.root, firstBigTree.root));
	}
		
	@Test
	public void firstDepthFourZackZackTest() {
		initialize();
		firstBigTree.search(68);
		int[] shouldBe = {50,  25,12,6,3,15,13,18,  75,90,100,85,82,68,65,60,63,55,53,58};
		for(int i:shouldBe)
			shouldBeTree.insert(i);
		assertTrue(compareTrees(shouldBeTree.root, firstBigTree.root));
	}
	
	@Test
	public void secondDepthFourZackZackTest() {
		initialize();
		firstBigTree.search(18);
		int[] shouldBe = {50,  25,18,15,12,13,6,3,    75,90,60,100,85,82,55,53,58,65,63,68};
		for(int i:shouldBe)
			shouldBeTree.insert(i);
		assertTrue(compareTrees(shouldBeTree.root, firstBigTree.root));
	}
}