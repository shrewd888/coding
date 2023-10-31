package leetcode.graph;

/**
 * https://leetcode.com/problems/is-graph-bipartite/
 * 785. Is Graph Bipartite?
 *
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
 * You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
 * More formally, for each v in graph[u], there is an undirected edge between node u and node v.
 * The graph has the following properties:
 *
 * There are no self-edges (graph[u] does not contain u).
 * There are no parallel edges (graph[u] does not contain duplicate values).
 * If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 * A graph is bipartite if the nodes can be partitioned into two independent
 * sets A and B such that every edge in the graph connects a node in set A and a node in set B.
 *
 * Return true if and only if it is bipartite.
 *
 * Example 1:
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 * Explanation: There is no way to partition the nodes into two independent sets
 * such that every edge connects a node in one and a node in the other.
 *
 * Example 2:
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 * Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Omkar's Graph Live Class:
 * Every tree is Bipartite
 * Any cycle of odd length: can't be bipartite
 * Any cycle of even length: is bipartite
 */
public class IsGraphBipartite
{
	public static boolean isBipartite(int[][] graph) {
		boolean isBipartite = true;

		int n = graph.length;
		int[] visited = new int[n]; //a 1D array of size n initialized to 0
		int[] parent = new int[n]; //a 1D array of size n initialized to 0
		int[] distance = new int[n];

		List<Integer>[] adjList = new ArrayList[n];
		//initialize
		for (int i=0; i < n; i++)
		{
			adjList[i] = new ArrayList<>();
		}

		for (int i=0; i < n; i++)
		{
			int[] nodes = graph[i];
			for (int node : nodes)
			{
				adjList[i].add(node);
				adjList[node].add(i);
			}
		}

		for (int i=0; i < n; i++)
		{
			if (visited[i] == 0)
			{
				isBipartite = bfs(adjList, visited, parent, distance, i);
				//if one component is not bipartite, the whole graph is not bipartite
				if (!isBipartite) return isBipartite;
			}
		}
		return isBipartite;
	}

	public static boolean bfs(List<Integer>[] adjList, int[] visited, int[] parent, int[] distance, int startNode)
	{
		Queue<Integer> q = new LinkedList<>();
		q.offer(startNode);//add
		visited[startNode] = 1;
		distance[startNode] = 0;
		while (!q.isEmpty())
		{
			Integer node = q.poll();
			List<Integer> neighbors = adjList[node];
			for (Integer neighbor : neighbors)
			{
				if (visited[neighbor] == 0) //tree edge
				{
					visited[neighbor] = 1;
					parent[neighbor] = node;
					distance[neighbor] = 1 + distance[node];
					q.add(neighbor);
				}
				else //neighbor has been visited
				{
					if (parent[node] != neighbor) //cross edge
					{
						if (distance[neighbor] == distance[node])
							return false; //not Bipartite
					}
				}
			}
		}
		return true; //Bipartite
	}

	//Runtime: 22 ms, faster than 5.30% of Java online submissions
	public static void main(String ... args) {
		int[][] graph = {{1,2,3},{0,2},{0,1,3},{0,2}};
		System.out.println(isBipartite(graph));
	}
}

