package H2;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * 
 * @author Lasse Kisthardt
 *
 */
public class TestsLasseH2 {

	private SelfOrganizedTree<Integer> myTree = new SelfOrganizedTree<Integer>();

	Integer test1= 1;
	Integer test2= 2;
	Integer test3= 3;
	Integer test4= 4;
	Integer test5= 5;
	Integer test6= 6;
	Integer test7= 7;
	Integer test8= 8;
	Integer test9= 9;
	Integer test10= 10;
	Integer test11= 11;
	Integer test12= 12;
	
	
	@Test
	public void zick()
	{
		myTree.insert(3);
		myTree.insert(1);
		myTree.insert(2);
		myTree.insert(5);
		myTree.insert(4);
		myTree.insert(6);
		myTree.insert(8);
		myTree.insert(7);
		
		System.out.println("----------------- TEST ZICK -----------------");
		
		System.out.println("before: ");
		BTreePrinter.printNode(myTree.root);
		
		boolean b1 = myTree.search(1);
		assertEquals(true, b1);
		
		Integer testRootRight = myTree.root.right.value;
		Integer testRootRightLeft = myTree.root.right.left.value;
		Integer testRootRightRight = myTree.root.right.right.value;
		
		assertEquals(null, myTree.root.left);
		assertEquals(test3, testRootRight);
		assertEquals(test2, testRootRightLeft);
		assertEquals(test5, testRootRightRight);
		
		System.out.println("after: ");
		BTreePrinter.printNode(myTree.root);
	}
	
	@Test
	public void zack()
	{
		myTree.insert(3);
		myTree.insert(1);
		myTree.insert(2);
		myTree.insert(5);
		myTree.insert(4);
		myTree.insert(6);
		myTree.insert(8);
		myTree.insert(7);
		
		System.out.println("----------------- TEST ZACK -----------------");
		
		System.out.println("before: ");
		BTreePrinter.printNode(myTree.root);
		
		boolean b1 = myTree.search(5);
		assertEquals(true, b1);
		
		Integer testRootRight = myTree.root.right.value;
		Integer testRootRightRightLeft = myTree.root.right.right.left.value;
		
		assertEquals(test6, testRootRight);
		assertEquals(null, myTree.root.right.left);
		assertEquals(test7, testRootRightRightLeft);
		
		System.out.println("after: ");
		BTreePrinter.printNode(myTree.root);
	}
	
	
	@Test
	public void zickZickEinfach()
	{
		myTree.insert(7);
		myTree.insert(8);
		myTree.insert(4);
		myTree.insert(5);
		myTree.insert(2);
		myTree.insert(6);
		myTree.insert(1);
		myTree.insert(3);
		
		System.out.println("----------------- TEST ZICK-ZICK EINFACH -----------------");
		
		System.out.println("before: ");
		BTreePrinter.printNode(myTree.root);
		
		boolean b1 = myTree.search(2);
		assertEquals(true, b1);
		
		Integer testRootRightRightRight = myTree.root.right.right.right.value;
		Integer testRRLR = myTree.root.right.right.left.right.value;
		
		assertEquals(null, myTree.root.left.left);
		assertEquals(test8, testRootRightRightRight);
		assertEquals(test6, testRRLR);
		
		System.out.println("after: ");
		BTreePrinter.printNode(myTree.root);
	}
	
	@Test
	public void zickZickSchwer()
	{
		myTree.insert(10);
		myTree.insert(7);
		myTree.insert(11);
		myTree.insert(6);
		myTree.insert(8);
		myTree.insert(12);
		myTree.insert(6);
		myTree.insert(5);
		myTree.insert(3);
		myTree.insert(2);
		myTree.insert(4);
		
		System.out.println("----------------- TEST ZICK-ZICK SCHWER -----------------");
		
		System.out.println("before: ");
		BTreePrinter.printNode(myTree.root);

		boolean b1 = myTree.search(5);
		assertEquals(true, b1);
		
		Integer testRootLLL = myTree.root.left.left.left.value;
		Integer testRootL = myTree.root.left.value;
		Integer testLRRR = myTree.root.left.right.right.right.value;
		
		assertEquals(test2, testRootLLL);
		assertEquals(test5, testRootL);
		assertEquals(test8, testLRRR);

		System.out.println("after: ");
		BTreePrinter.printNode(myTree.root);
	}
	
	
	
	@Test
	public void zackZackEinfach()
	{
		myTree.insert(3);
		myTree.insert(2);
		myTree.insert(5);
		myTree.insert(1);
		myTree.insert(4);
		myTree.insert(7);
		myTree.insert(6);
		myTree.insert(8);
		
		System.out.println("----------------- TEST ZACK-ZACK EINFACH -----------------");
		
		System.out.println("before: ");
		BTreePrinter.printNode(myTree.root);
		
		boolean b1 = myTree.search(7);
		assertEquals(true, b1);
		
		Integer testRootRight = myTree.root.right.value;
		Integer testRootLeftLefttLeft = myTree.root.left.left.left.value;
		Integer testRootLeftLefttRight = myTree.root.left.left.right.value;
		
		assertEquals(test8, testRootRight);
		assertEquals(test2, testRootLeftLefttLeft);
		assertEquals(test4, testRootLeftLefttRight);
		
		System.out.println("after: ");
		BTreePrinter.printNode(myTree.root);
	}
	
	
	@Test
	public void zackZackSchwer()
	{
		myTree.insert(4);
		myTree.insert(2);
		myTree.insert(7);
		myTree.insert(1);
		myTree.insert(3);
		myTree.insert(5);
		myTree.insert(8);
		myTree.insert(6);
		myTree.insert(9);
		myTree.insert(10);
		myTree.insert(12);
		myTree.insert(11);
		
		System.out.println("----------------- TEST ZACK-ZACK SCHWER -----------------");
		
		System.out.println("before: ");
		BTreePrinter.printNode(myTree.root);
		
		boolean b1 = myTree.search(10);
		assertEquals(true, b1);
		
		Integer testRootRRLL = myTree.root.right.right.left.left.value;
		Integer testRootRRR = myTree.root.right.right.right.value;
		Integer testRootLeftRight = myTree.root.left.right.value;
		
		assertEquals(test8, testRootRRLL);
		assertEquals(test12, testRootRRR);
		assertEquals(test3, testRootLeftRight);
		
		System.out.println("after: ");
		BTreePrinter.printNode(myTree.root);
	}
	
	@Test
	public void zackZickEinfach()
	{
		myTree.insert(6);
		myTree.insert(2);
		myTree.insert(7);
		myTree.insert(8);
		myTree.insert(1);
		myTree.insert(4);
		myTree.insert(3);
		myTree.insert(5);
		
		System.out.println("----------------- TEST ZACK-ZICK EINFACH -----------------");
		
		System.out.println("before: ");
		BTreePrinter.printNode(myTree.root);
		
		boolean b1 = myTree.search(4);
		assertEquals(true, b1);
		
		Integer testRootRight = myTree.root.right.value;
		Integer testRootRRR = myTree.root.right.right.right.value;
		Integer testRootLeftRight = myTree.root.left.right.value;
		
		assertEquals(test6, testRootRight);
		assertEquals(test8, testRootRRR);
		assertEquals(test3, testRootLeftRight);
		
		System.out.println("after: ");
		BTreePrinter.printNode(myTree.root);
	}
	
	
	@Test
	public void zackZickSchwer()
	{
		myTree.insert(10);
		myTree.insert(8);
		myTree.insert(12);
		myTree.insert(11);
		myTree.insert(2);
		myTree.insert(9);
		myTree.insert(1);
		myTree.insert(5);
		myTree.insert(3);
		myTree.insert(7);
		myTree.insert(4);
		myTree.insert(6);
		
		System.out.println("----------------- TEST ZACK-ZICK SCHWER -----------------");
		
		System.out.println("before: ");
		BTreePrinter.printNode(myTree.root);
		
		boolean b1 = myTree.search(5);
		assertEquals(true, b1);
		
		Integer testRootLeft = myTree.root.left.value;
		Integer testRootLRLL = myTree.root.left.right.left.left.value;
		Integer testRootLLRR = myTree.root.left.left.right.right.value;
		
		assertEquals(test5, testRootLeft);
		assertEquals(test6, testRootLRLL);
		assertEquals(test4, testRootLLRR);
		
		System.out.println("after: ");
		BTreePrinter.printNode(myTree.root);
	}
	
	@Test
	public void zickZackEinfach()
	{
		myTree.insert(2);
		myTree.insert(1);
		myTree.insert(6);
		myTree.insert(4);
		myTree.insert(7);
		myTree.insert(3);
		myTree.insert(5);
		myTree.insert(8);
		
		System.out.println("----------------- TEST ZICK-ZACK EINFACH -----------------");
		
		System.out.println("before: ");
		BTreePrinter.printNode(myTree.root);
		
		boolean b1 = myTree.search(4);
		assertEquals(true, b1);
		
		Integer testRootLeft = myTree.root.left.value;
		Integer testRootRightLeft = myTree.root.right.left.value;
		Integer testRootLeftRight = myTree.root.left.right.value;
		
		assertEquals(test2, testRootLeft);
		assertEquals(test5, testRootRightLeft);
		assertEquals(test3, testRootLeftRight);
		
		System.out.println("after: ");
		BTreePrinter.printNode(myTree.root);
	}
	
	
	@Test
	public void zickZackSchwer()
	{
		myTree.insert(10);
		myTree.insert(2);
		myTree.insert(12);
		myTree.insert(1);
		myTree.insert(9);
		myTree.insert(11);
		myTree.insert(5);
		myTree.insert(2);
		myTree.insert(7);
		myTree.insert(1);
		myTree.insert(4);
		myTree.insert(6);
		myTree.insert(8);
		
		System.out.println("----------------- TEST ZICK-ZACK SCHWER -----------------");
		
		System.out.println("before: ");
		BTreePrinter.printNode(myTree.root);
		
		boolean b1 = myTree.search(5);
		assertEquals(true, b1);
		
		Integer testRootLeft = myTree.root.left.value;
		Integer testRootLRLR = myTree.root.left.right.left.right.value;
		Integer testRootLLL = myTree.root.left.left.left.value;
		
		assertEquals(test5, testRootLeft);
		assertEquals(test8, testRootLRLR);
		assertEquals(test1, testRootLLL);
		
		System.out.println("after: ");
		BTreePrinter.printNode(myTree.root);
	}
	
	@Test
	public void nichtEnthalten()
	{
		myTree.insert(10);
		myTree.insert(2);
		myTree.insert(12);
		myTree.insert(1);
		myTree.insert(9);
		myTree.insert(11);
		myTree.insert(5);
		myTree.insert(2);
		myTree.insert(1);
		myTree.insert(4);
		myTree.insert(6);
		myTree.insert(8);
		
		System.out.println("----------------- TEST NICHT ENTHALTEN -----------------");
		
		BTreePrinter.printNode(myTree.root);
		
		boolean b1 = myTree.search(7);
		assertEquals(false, b1);
	}
}
