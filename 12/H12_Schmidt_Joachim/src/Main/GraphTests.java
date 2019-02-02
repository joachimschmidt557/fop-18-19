package Main;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTests {

	Graph<String, Integer> exampleGraphFromPDF;
	Graph<Integer, Boolean> booleanGraph;
	Graph<String, Float> bidirectionalGraph;

	public void initializeExampleGraph() {

		// Initialize example graph from pdf
		exampleGraphFromPDF = new Graph<String, Integer>(-1);

		exampleGraphFromPDF.addNode("A");
		exampleGraphFromPDF.addNode("B");
		exampleGraphFromPDF.addNode("C");
		exampleGraphFromPDF.addNode("D");

		exampleGraphFromPDF.setWeight("example", 0, 1, 3);
		exampleGraphFromPDF.setWeight("example", 1, 0, 2);
		exampleGraphFromPDF.setWeight("example", 1, 2, 4);
		exampleGraphFromPDF.setWeight("example", 2, 3, 1);
		exampleGraphFromPDF.setWeight("example", 3, 2, 2);

	}

	public void initializeBooleanGraph() {

		// Initialize Graph
		booleanGraph = new Graph<Integer, Boolean>(false);

		for (int i = 0; i < 10; i++) {
			booleanGraph.addNode(i);
		}

		for (int i = 0; i < 9; i++) {
			booleanGraph.setWeight("nextNumber", i, i + 1, true);
		}

		for (int i = 1; i < 10; i++) {
			booleanGraph.setWeight("prevNumber", i, i - 1, true);
		}

	}

	public void initializeBidirectionalGraph() {

		// Initialize empty Graph
		bidirectionalGraph = new Graph<String, Float>((float) 42.3);

		bidirectionalGraph.addNode("Node");
		bidirectionalGraph.addNode("OtherNode");

		bidirectionalGraph.setWeight("bla", 0, 1, (float) 3);
		bidirectionalGraph.setWeight("bla", 1, 0, (float) 687);

	}

	@Test
	public void testAddNode1() {

		exampleGraphFromPDF = new Graph<String, Integer>(-1);
		assertEquals(exampleGraphFromPDF.getNumberOfNodes(), 0);

		exampleGraphFromPDF.addNode("A");
		assertEquals(exampleGraphFromPDF.getNumberOfNodes(), 1);

		exampleGraphFromPDF.addNode("B");
		assertEquals(exampleGraphFromPDF.getNumberOfNodes(), 2);

		exampleGraphFromPDF.addNode("C");
		assertEquals(exampleGraphFromPDF.getNumberOfNodes(), 3);

		exampleGraphFromPDF.addNode("D");
		assertEquals(exampleGraphFromPDF.getNumberOfNodes(), 4);

	}

	@Test
	public void testAddNode2() {

		booleanGraph = new Graph<Integer, Boolean>(false);
		assertEquals(booleanGraph.getNumberOfNodes(), 0);

		for (int i = 0; i < 10; i++) {

			assertEquals(booleanGraph.getNumberOfNodes(), i);
			booleanGraph.addNode(i);
			assertEquals(booleanGraph.getNumberOfNodes(), i + 1);

		}

	}

	@Test
	public void testAddNode3() {

		bidirectionalGraph = new Graph<String, Float>((float) 42.3);

		bidirectionalGraph.addNode("Node");
		bidirectionalGraph.addNode("OtherNode");

		assertEquals(bidirectionalGraph.getNumberOfNodes(), 2);

	}

	@Test
	public void testSetWeight1() {

		exampleGraphFromPDF = new Graph<String, Integer>(-1);

		exampleGraphFromPDF.addNode("A");
		exampleGraphFromPDF.addNode("B");
		exampleGraphFromPDF.addNode("C");
		exampleGraphFromPDF.addNode("D");

		exampleGraphFromPDF.setWeight("example", 0, 1, 3);
		exampleGraphFromPDF.setWeight("example", 1, 0, 2);
		exampleGraphFromPDF.setWeight("example", 1, 2, 4);
		exampleGraphFromPDF.setWeight("example", 2, 3, 1);
		exampleGraphFromPDF.setWeight("example", 3, 2, 2);

		assertTrue(exampleGraphFromPDF.getWeight("example", 0, 1) == 3);
		assertTrue(exampleGraphFromPDF.getWeight("example", 1, 0) == 2);
		assertTrue(exampleGraphFromPDF.getWeight("example", 1, 2) == 4);
		assertTrue(exampleGraphFromPDF.getWeight("example", 2, 3) == 1);
		assertTrue(exampleGraphFromPDF.getWeight("example", 3, 2) == 2);

	}

	@Test
	public void testSetWeight2() {

		booleanGraph = new Graph<Integer, Boolean>(false);

		for (int i = 0; i < 100; i++) {

			booleanGraph.addNode(i);

		}

		for (int i = 0; i < 99; i++) {

			booleanGraph.setWeight("nextNumber", i, i + 1, true);
			assertTrue(booleanGraph.getWeight("nextNumber", i, i + 1));

			booleanGraph.setWeight("nextNumber", i, i + 1, false);
			assertTrue(!booleanGraph.getWeight("nextNumber", i, i + 1));

		}

	}

	@Test
	public void testSetWeight3() {

		bidirectionalGraph = new Graph<String, Float>((float) 42);
		
		bidirectionalGraph.addNode("Node");
		bidirectionalGraph.addNode("OtherNode");

		bidirectionalGraph.setWeight("foo", 0, 1, (float) 7676);
		bidirectionalGraph.setWeight("bar", 1, 0, (float) 9898);
		
		assertTrue(bidirectionalGraph.getWeight("foo", 0, 1) == 7676);
		assertTrue(bidirectionalGraph.getWeight("foo", 1, 0) == 42);
		
		assertTrue(bidirectionalGraph.getWeight("bar", 0, 1) == 42);
		assertTrue(bidirectionalGraph.getWeight("bar", 1, 0) == 9898);
		
	}
	
	@Test
	public void testGetAllPaths1() {

		initializeExampleGraph();

		String[] actualPathsFromA = new String[] { "A -> B -> C -> D" };
		String[] actualPathsFromB = new String[] { "B -> A", "B -> C -> D" };
		String[] actualPathsFromC = new String[] { "C -> D" };
		String[] actualPathsFromD = new String[] { "D -> C" };

		assertArrayEquals(exampleGraphFromPDF.getAllPaths("example", 0, false), actualPathsFromA);
		assertArrayEquals(exampleGraphFromPDF.getAllPaths("example", 1, false), actualPathsFromB);
		assertArrayEquals(exampleGraphFromPDF.getAllPaths("example", 2, false), actualPathsFromC);
		assertArrayEquals(exampleGraphFromPDF.getAllPaths("example", 3, false), actualPathsFromD);

	}

	@Test
	public void testGetAllPaths2() {

		initializeBooleanGraph();

		String[] actualNextNumbersFrom0 = new String[] { "0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9" };
		String[] actualPrevNumbersFrom5 = new String[] { "5 -> 4 -> 3 -> 2 -> 1 -> 0" };

		assertArrayEquals(booleanGraph.getAllPaths("nextNumber", 0, false), actualNextNumbersFrom0);
		assertArrayEquals(booleanGraph.getAllPaths("prevNumber", 5, false), actualPrevNumbersFrom5);

	}

	@Test
	public void testGetAllPaths3() {

		initializeBidirectionalGraph();

		String[] actualPathsFrom0 = new String[] { "0 -> 1" };
		String[] actualPathsFrom1 = new String[] { "1 -> 0" };

		assertArrayEquals(bidirectionalGraph.getAllPaths("bla", 0, true), actualPathsFrom0);
		assertArrayEquals(bidirectionalGraph.getAllPaths("bla", 1, true), actualPathsFrom1);

	}

}
