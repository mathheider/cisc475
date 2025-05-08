package edu.udel.cisc475.debug;
/**
 * Dijkstra performs Dijkstra's algorithm to find the length of a shortest path
 * from a source vertex to all other vertices in a weighted directed graph.
 */
public class Dijkstra {

	/**
	 * Adjacency Matrix. If adjMat[i][j]==0 there is no edge from vertex i to
	 * vertex j. Otherwise, adjMat[i][j] should be positive and there is an edge
	 * from i to j with weight adjMat[i][j].
	 */
	private int[][] adjMat;

	/**
	 * The start vertex. The algorithm will find the length of a shortest path
	 * from source to every vertex in the graph.
	 */
	private int source;

	/**
	 * From Dijkstra's algorithm, the current upper bound on the distance from
	 * source to each vertex. This is an array of length the number of vertices.
	 */
	private int[] distance;

	/**
	 * From Dijkstra's algorithm, for each vertex this tells you whether the
	 * vertex has been visited by the algorithm. Array of length number of
	 * vertices.
	 */
	private boolean[] visited;

	/**
	 * Constructs new instance of Dijkstra but does not perform the computation.
	 * 
	 * @param adjMat
	 *                   the adjacency matrix; the length of this array is the
	 *                   number n of vertices; each element of this array should
	 *                   also be an array of length n
	 * @param source
	 *                   the source vertex, an integer in 0 .. n-1.
	 */
	public Dijkstra(int[][] adjMat, int source) {
		this.adjMat = adjMat;
		this.source = source;
	}

	/**
	 * Computes the shortest distance from source to all other vertices.
	 * 
	 * @return array of length number of vertices; entry at index i is the
	 *         shortest distance from source to vertex i
	 */
	public int[] search() {
		distance = new int[adjMat.length];
		visited = new boolean[adjMat.length];
		distance[source] = 0;
		for (int i = 0; i < adjMat.length; i++) {
			if (i == source)
				continue;
			distance[i] = Integer.MAX_VALUE; // "infinity"
		}
		for (int i = 0; i < adjMat.length; i++) {
			int minDistVertex = findMinDistVertex();
			visited[minDistVertex] = true;
			for (int j = i; j < adjMat.length; j++) {
				if (adjMat[minDistVertex][j] != 0 && visited[j] == false
						&& distance[minDistVertex] != Integer.MAX_VALUE) {
					int newDist = distance[minDistVertex]
							+ adjMat[minDistVertex][j];
					if (newDist < distance[j]) {
						distance[j] = newDist;
					}
				}
			}
		}
		for (int i = 0; i < adjMat.length; i++) {
			System.out.println("A shortest path from vertex " + source
					+ " to vertex " + i + " has length " + distance[i]);
		}
		return distance;
	}

	/**
	 * Finds an unvisited vertex i for which distance[i] is finite and minimal
	 * among all unvisited vertices. Note: this would be better performed using
	 * a PriorityQueue.
	 * 
	 * @return such an i
	 */
	private int findMinDistVertex() {
		int minVertex = -1;
		for (int i = 0; i < distance.length; i++) {
			if (visited[i] == false
					&& (minVertex == -1 || distance[i] < distance[minVertex])) {
				minVertex = i;
			}
		}
		return minVertex;
	}

	/**
	 * Main function just performs some simple tests (without checking the
	 * results).
	 * 
	 * @param args
	 *                 not used
	 */
	public static void main(String[] args) {
		System.out.println("Test 1...");
		int[][] adjMat1 = {{0, 2, 0, 4, 0, 0}, {0, 0, 3, 2, 0, 0},
				{2, 0, 0, 0, 0, 4}, {0, 0, 0, 0, 2, 0}, {0, 0, 0, 0, 0, 1},
				{0, 0, 0, 0, 0, 0}};
		Dijkstra d1 = new Dijkstra(adjMat1, 0);
		d1.search();
		System.out.println();

		System.out.println("Test 2...");
		int[][] adjMat2 = {{0, 0, 0, 13}, {2, 0, 0, 0}, {10, 3, 0, 0},
				{0, 14, 1, 0}};
		Dijkstra d2 = new Dijkstra(adjMat2, 2);
		d2.search();
	}
}
