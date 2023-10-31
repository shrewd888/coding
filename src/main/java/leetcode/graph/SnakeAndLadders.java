package leetcode.graph;

/**
 * https://leetcode.com/problems/snakes-and-ladders/
 * 909. Snakes and Ladders
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Omkar's Graph Live Class
 */
public class SnakeAndLadders
{
	/**
	 * Directed Graph. Board Game with well-defined neighbors.
	 * No weight on the edges. Therefore, BFS can be used.
	 * For weighted edges we can use extended BFS (Djikstra algorithm).
	 *
	 * Make sure every edge in the graph max to a single dice throw
	 * Shortest path (in terms of the no of hops in the graph) from source:0 to destination:100
	 * This should corresponds to minimum #of dice throws.
	 *
	 * When I throw a dice, the end point has to be the end point of the edge.
	 * 1. Build the graph, n=101
	 * Adj List: a 1D array of size 101
	 * For each vertex we want to populate the adjacency list
	 *
	 * For the edge of i in 0 to 100, the end point of each edge is the value of the hashmap.
	 * if the edge start with a ladder, the end point is the value of where the ladder goes.
	 * if the edge start with a snake's mouth, the end point is the value of where the tail is.
	 * Need to construct a hashmap of where each square ends
	 * hmap[4] =
	 *
	 * 2. BFS
	 *
	 * 3. Outer loop is not needed because we know the source which is 0
	 * We only call BFS on vertex 0, return distance[100]
	 */
	public int snakesAndLadders(int[][] board) {
		int shortestPath = 0;
		if (board.length == 0) return shortestPath; //BFS

		int[] visited = new int[101];
		int[] distance = new int[101];
		//board 100 squares, start outside square 1 -> 101
		//For each vertex we want to populate the adjacency list
		List<Integer>[] adjList = new ArrayList[board.length];
		/* construct a map for every grid */
		Map<Integer, Integer> square = new HashMap<>();

		for (int i=0; i<= 100; i++)
		{
			//add up to 6 edges: (i, i+dice)
			for (int dice=1; dice <= 6; dice++)
			{
				int value = i+dice;
				if (value <= 100)
				{
					int endPoint = square.get(value);
					adjList[i].add(endPoint);
					distance[i] = endPoint;
				}
			}
		}

		for (int i=0; i<= 100; i++)
		{
			//add up to 6 edges
			for (int dice=1; dice <= 6; dice++)
			{
				//
			}
		}

		return shortestPath;
	}
}
