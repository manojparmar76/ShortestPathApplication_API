/**
 * 
 */
package com.spa.util;

import java.util.List;

/**
 * @author Manoj Parmar
 *
 */
public class ShortestPath {

	private static final int NO_PARENT = -1;

	public static int[] shortestPath = null;

	/**
	 * Method to get the value of the shortest path.
	 * 
	 * @return
	 */
	public static int[] getShortestPath() {
		return shortestPath;
	}

	/**
	 * Find the shortest path between source and destination
	 * 
	 * @param graphMatrix
	 * @param source
	 * @param destination
	 * @return
	 */
	public static double findMinimumDistance(double[][] graphMatrix, int source, int destination) {
		int vertices = graphMatrix[0].length;
		double[] shortestDistances = new double[vertices]; // Array to hold the shortest distance of the all the nodes from the source
		boolean[] added = new boolean[vertices]; // To handle the vertex value.
		int[] parents = new int[vertices]; // Array that will hold the parent which is involved in shortest path

		// Set the distance ad INFINITE and added as false
		for (int vertexIndex = 0; vertexIndex < vertices; vertexIndex++) {
			shortestDistances[vertexIndex] = Integer.MAX_VALUE;
			added[vertexIndex] = false;
		}
		shortestDistances[source] = 0; // Set the distance of starting node to itself with 0;
		parents[source] = NO_PARENT; // Set the parent of starting node with NO_PARENT;

		// Find shortest path for all nodes
		for (int i = 1; i < vertices; i++) {

			// Pick the minimum distance vertex from the set of vertices not yet
			// processed. nearestVertex is always equal to startNode in first
			// iteration.
			int nearestVertex = -1;
			double shortestDistance = Integer.MAX_VALUE;
			for (int vertexIndex = 0; vertexIndex < vertices; vertexIndex++) {
				if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
					nearestVertex = vertexIndex;
					shortestDistance = shortestDistances[vertexIndex];
				}
			}

			// Mark the picked vertex as processed
			if(nearestVertex != -1) { 
				added[nearestVertex] = true;

				// Update distance value of the adjacent vertices of the picked
				// vertex.
				for (int vertexIndex = 0; vertexIndex < vertices; vertexIndex++) {
					double edgeDistance = graphMatrix[nearestVertex][vertexIndex];
	
					if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
						parents[vertexIndex] = nearestVertex;
						shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
					}
				} 
			}
		}

		//Shortest distance in of the destination
		double shortestDistance = -1;
		for (int i = 0; i < shortestDistances.length; i++) {
			if (i == destination) {
				shortestPath = parents;
				shortestDistance = shortestDistances[i];
				System.out.println("\n Shortest Path : ");
			}
		}

		return shortestDistance;
	}

	/**
	 * Recursive method to find the all the planets visited in the shotest path
	 * 
	 * @param currentVertex
	 * @param parents
	 * @param nodeList
	 * @return
	 */
	public static List<Integer> findVisitedNodeIndex(int currentVertex, int[] parents, List<Integer> nodeList) {
		if (currentVertex == NO_PARENT) {
			return nodeList;
		}
		findVisitedNodeIndex(parents[currentVertex], parents, nodeList);
		nodeList.add(currentVertex);
		System.out.print(currentVertex + " ");
		return nodeList;
	}
	
}
