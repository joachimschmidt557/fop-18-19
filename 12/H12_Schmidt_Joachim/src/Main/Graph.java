package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * 
 * @author joachim
 *
 * @param <T> The type of the nodes
 * @param <R> The type of the edge weight
 */
public class Graph <T, R extends Comparable<R>> {

	private R weightOfNonExistingEdges;
	private ArrayList<T> nodes;
	private HashMap<String, ArrayList<ArrayList<R>>> edgeWeightMatrices;
	
	/**
	 * Constructs a new graph
	 * @param weightOfNonExistingEdges The default weight which is assigned to edges that do not exist
	 */
	public Graph(R weightOfNonExistingEdges) {
		
		this.weightOfNonExistingEdges = weightOfNonExistingEdges;
		nodes = new ArrayList<T>();
		edgeWeightMatrices = new HashMap<String, ArrayList<ArrayList<R>>>();
		
	}
	
	/**
	 * Adds a node to the graph
	 * @param newNode
	 */
	public void addNode(T newNode) {
		
		// Add node to list of nodes
		nodes.add(newNode);
		
		// Add rows and columns to matrices
		edgeWeightMatrices.forEach((matrixName, matrix) -> {
			
			// Add one column to each row
			matrix.forEach(x -> x.add(weightOfNonExistingEdges));
			
			// Add last row
			ArrayList<R> newRow = new ArrayList<R>();
			for (int i = 0; i < getNumberOfNodes(); i++)
				newRow.add(weightOfNonExistingEdges);
			matrix.add(newRow);
			
		}); 
		
	}
	
	/**
	 * Sets the weight of this edge
	 * @param whichMatrix The matrix which should contain the edge
	 * @param row The origin node
	 * @param column The destination node
	 * @param newWeight The weight of the edge
	 */
	public void setWeight(String whichMatrix, int row, int column,
						  R newWeight) {
		
		// Check if the wanted matrix exists
		if (edgeWeightMatrices.containsKey(whichMatrix))
			
			edgeWeightMatrices.get(whichMatrix).get(row).set(column, newWeight);
		
		else {
			// Create the matrix
			ArrayList<ArrayList<R>> newMatrix = new ArrayList<ArrayList<R>>();
			
			// Make all fields empty
			for (int i = 0; i < getNumberOfNodes(); i++) {
				
				ArrayList<R> newRow = new ArrayList<R>();
				
				for (int j = 0; j < getNumberOfNodes(); j++)
					newRow.add(weightOfNonExistingEdges);
				
				newMatrix.add(newRow);
				
			}
			
			// Insert the new weight
			newMatrix.get(row).set(column, newWeight);
			
			// Add the matrix to the set
			edgeWeightMatrices.put(whichMatrix, newMatrix);
		}
		
	}
	
	/**
	 * Gets the weight of this edge
	 * @param whichMatrix The matrix
	 * @param row The origin node
	 * @param column The destination node
	 * @return The weight of the edge
	 */
	public R getWeight(String whichMatrix, int row, int column) {
				
		return edgeWeightMatrices.get(whichMatrix).get(row).get(column);
		
	}
	
	/**
	 * Gets the number of nodes currently in the graph
	 * @return The number of nodes
	 */
	public int getNumberOfNodes() {
		
		return nodes.size();
		
	}
	
	/**
	 * Gets all possible non-cyclic paths originating from this node
	 * @param whichMatrix The matrix
	 * @param nodeIndex The origin node
	 * @param printIndex The destination node
	 * @return An array of possible paths in the form "A -> ... -> Z"
	 */
	public String[] getAllPaths(String whichMatrix, int nodeIndex,
								boolean printIndex) {
		
		ArrayList<String> result = new ArrayList<String>();
		
		for (ArrayList<Integer> path : 
				searchPaths(edgeWeightMatrices.get(whichMatrix), nodeIndex, new ArrayList<Integer>()))
			result.add(pathToString(path, printIndex));
		
		//System.out.println(result);		//System.out.println(possibleNeighbors);

		
		return result.toArray(new String[0]);
		
	}
	
	/**
	 * Searches possible paths recursively
	 * @param matrix The matrix
	 * @param nodeIndex The node from which the path originates
	 * @param currentPath The indices visited by now
	 * @return The possible paths in the form "array of indices"
	 */

	private ArrayList<ArrayList<Integer>> searchPaths(ArrayList<ArrayList<R>> matrix,
													  int nodeIndex, ArrayList<Integer> currentPath) {
		
		ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
				
		ArrayList<Integer> neighborsNotVisitedYet = getNeighbors(matrix, nodeIndex).stream()
				.filter(x -> !(currentPath.contains(x)))
				.collect(Collectors.toCollection(ArrayList::new));
		
		//System.out.println("neigh" + nodeIndex + ": " + neighbors);
		//System.out.println("nv" + nodeIndex + ": " + neighborsNotVisitedYet);
		
		//System.out.println("iv" + nodeIndex + ": " + indicesVisited);
		
		currentPath.add(nodeIndex);
		
		//System.out.println("ivv" + nodeIndex + ": " + indicesVisited);
		
		// If there are no neighbors, the path ends here
		if (neighborsNotVisitedYet.isEmpty()) {
			
			paths.add(currentPath);
			return paths;
			
		}
				
		// Each neighbor opens up a possible path
		for (int neighbor : neighborsNotVisitedYet) {
			
			// Append the paths found to the result
			paths.addAll(searchPaths(matrix, neighbor, 
					
					// It is necessary to copy the current path
					new ArrayList<Integer>(currentPath)));
			
		}
		
		//System.out.println("paths" + nodeIndex + ": " + paths);
		
		return paths;
		
	}
	
	/**
	 * Gets the neighbors for a specific node
	 * @param matrix The edge matrix
	 * @param nodeIndex The index of the node
	 * @return A list of neighbor indices
	 */
	private ArrayList<Integer> getNeighbors(ArrayList<ArrayList<R>> matrix, int nodeIndex) {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		// Get row
		ArrayList<R> possibleNeighbors = matrix.get(nodeIndex);
				
		// Which nodes are reachable?
		for (int i = 0; i < possibleNeighbors.size(); i++) {
			
			if (!(possibleNeighbors.get(i).equals(weightOfNonExistingEdges)))
				// Add index (= node index) of existing edge
				result.add(i);
			
		}
		
		return result;
		
	}
	
	/**
	 * Creates a string representation of the path
	 * @param path The indices of the nodes
	 * @param printIndex Whether to print the index or the node
	 * @return The string representation
	 */
	private String pathToString(ArrayList<Integer> path, boolean printIndex) {
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < path.size(); i++) {
			
			if (!printIndex)
				sb.append(nodes.get(path.get(i)).toString());
			else
				sb.append(path.get(i));
			
			if (i < path.size()-1)
				sb.append(" -> ");
			
		}
		return sb.toString();
		
	}
	
	public static void main(String[] args) {
		
		Graph<String, Integer> g = new Graph<String, Integer>(-1);
		
		System.out.println("ha");
		
		g.addNode("A");
		g.addNode("B");
		g.addNode("C");
		g.addNode("D");
		
		System.out.println(g.getNumberOfNodes());
		
		System.out.println("haha");
		
		g.setWeight("example", 0, 1, 3);
		System.out.println("here");
		g.setWeight("example", 1, 0, 2);
		g.setWeight("example", 1, 2, 4);
		g.setWeight("example", 2, 3, 1);
		g.setWeight("example", 3, 2, 2);
		
		System.out.println(g.getWeight("example", 1, 0));
		
		System.out.println("hahaha");
						
		for (String a : g.getAllPaths("example", 1, true))
			System.out.println(a);
		
	}
	
	
}
