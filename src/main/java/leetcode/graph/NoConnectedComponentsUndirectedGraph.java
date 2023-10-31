package leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 *
 * 323. Number of Connected Components in an Undirected Graph
 * You have a graph of n nodes.
 * You are given an integer n and an array edges where edges[i] = [ai, bi]
 * indicates that there is an edge between ai and bi in the graph.
 *
 * Return the number of connected components in the graph.
 *
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 *
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 * Output: 1
 */
/**
 * Omkar's Graph Live Class
 * 1. Build the graph: #of vertices in the graph, we are given ids that are convenient: 0 to n-1:
 * use AdjList: a 1D array of Lists of integers of size n, initialize to an empty list
 *
 * 2. Write a function : BFS (start from source) : use Queue -> represent the Shortest Path
 * DFS: mark a source node as visited
 *
 * 3. Outer Loop: which will call BFS/DFS
 *
 * Time Complexity: O(n) push/pop, vertices, n = degree of the node + O(m) looking at adjList of each vertex = O(n+m)
 * Aux Space = O(n): for DFS: the max call stack is the height of the tree which at most = n
 *
 * BFS/DFS: Height of tree vs Depth of call stack
 */
public class NoConnectedComponentsUndirectedGraph
{
	public static int countComponents(int n, int[][] edges) {
		//initialize visited array with 0
		int[] visited = new int[n];

		//create adj list
		List<Integer>[] adjList = new ArrayList[n];
		for (int i=0; i < n; i++)
		{
			adjList[i] = new ArrayList<>();
		}
		//add src,dst to adjList
		//[0] -> [1]
		//[1] -> [0, 2]
		//[2] -> [1, 3]
		//[3] -> [4]
		for (int i=0; i < edges.length; i++)
		{
			adjList[edges[i][0]].add(edges[i][1]);
			adjList[edges[i][1]].add(edges[i][0]);
		}
		int components = 0;
		//loop through every vertex, can use BFS or DFS
		for (int i=0; i < n; i++)
		{
			System.out.println("** visited["+i+"]=" + visited[i]);
			if (visited[i] == 0)
			{
				components++;
				//bfs(adjList, visited, i);
				dfs(adjList, visited, i);
			}
		}
		return components;
	}

	//startNode is a source, could be 0...(n-1)
	public static void bfs(List<Integer>[] adjList, int[] visited, int startNode)
	{
		Queue<Integer> q = new LinkedList<>();
		q.add(startNode);
		visited[startNode] = 1;
		while (!q.isEmpty())
		{
			Integer node = q.poll();
			List<Integer> neighbours = adjList[node];
			for (Integer neighbour : neighbours)
			{
				if (visited[neighbour]==0)
				{
					visited[neighbour] = 1;
					q.add(neighbour);
				}
			}
		}
	}

	//recursive
	public static void dfs(List<Integer>[] adjList, int[] visited, int startNode)
	{
		visited[startNode] = 1;
		List<Integer> neighbours = adjList[startNode];
		for (Integer neighbour : neighbours)
		{
			if (visited[neighbour]==0)
			{
				dfs(adjList, visited, neighbour);
			}
		}
	}

	public static void main(String ... args) {
		int[][] edges = new int [][] {{0,1},{1,2},{3,4}};
		System.out.println(countComponents(5, edges));//2

		edges = new int [][] {{0,1},{1,2},{2,3},{3,4}};
		System.out.println(countComponents(5, edges));//1

		edges = new int [][] {{0,1},{1,2},{2,3},{1,3},{1,4}};
		System.out.println(countComponents(5, edges));//1
	}
}
