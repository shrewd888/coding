package leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/graph-valid-tree/
 * 261. Graph Valid Tree
 * You have a graph of n nodes labeled from 0 to n - 1.
 * You are given an integer n and a list of edges where
 * edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
 *
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 *
 * Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * Output: true
 *
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
 * Output: false
 */
/**
 * Omkar's Graph Live Class
 * A tree is a connected graph with no cycles
 * A tree: unrooted, we can artificially pick one of the vertices as a root
 */
public class GraphValidTree
{
	//if we find a cycle that means the graph is not a valid tree
	public static boolean validTree(int n, int[][] edges) {
		boolean valid = true;
		//initialize visited array with 0
		int[] visited = new int[n];
		int[] parent = new int[n];

		List<Integer>[] adjList = new ArrayList[n];
		for (int i=0; i < n; i++)
		{
			adjList[i] = new ArrayList<>();
		}
		for (int i=0; i < edges.length; i++)
		{
			adjList[edges[i][0]].add(edges[i][1]);
			adjList[edges[i][1]].add(edges[i][0]);
		}
		int component  = 0;
		for (int i=0; i < n; i++)
		{
			if (visited[i] == 0)
			{
				component++;
				if (component > 1) return false;
				//valid = bfs(adjList, visited, parent, i);
				valid = dfs(adjList, visited, parent, i);
			}
		}
		return valid;
	}

	public static boolean bfs(List<Integer>[] adjList, int[] visited, int[] parent, int startNode)
	{
		Queue<Integer> q = new LinkedList<>();
		q.offer(startNode); //add
		visited[startNode] = 1;
		while (!q.isEmpty())
		{
			Integer node = q.poll();
			List<Integer> neighbors = adjList[node];
			for (Integer neighbor : neighbors)
			{
				if (visited[neighbor] == 0)
				{
					visited[neighbor] = 1;
					parent[neighbor] = node;
					q.add(neighbor);
				}
				else //neighbor has been visited
				{
					if (parent[node] != neighbor) //cross edge
						return false;
				}
			}
		}
		return true; //valid tree (no cycle)
	}

	public static boolean dfs(List<Integer>[] adjList, int[] visited, int[] parent, int node)
	{
		visited[node] = 1;
		List<Integer> neighbors = adjList[node];
		for (Integer neighbor : neighbors)
		{
			if (visited[neighbor] == 0)
			{
				parent[neighbor] = node;
				if (!dfs(adjList, visited, parent, neighbor)) return true;
			}
			else //neighbor has been visited
			{
				if (parent[node] != neighbor)
					return false; //backedge / crossedge
			}
		}
		return true; //valid tree (no cycle)
	}

	//Runtime (bfs): 4 ms, faster than 56.20% of Java online submissions
	//Runtime (dfs): 7 ms, faster than 34.70% of Java online submissions
	//Runtime (dfs): 15 ms, faster than 12.25% of Java online submissions for Graph Valid Tree.

	public static void main(String ... args) {
		int[][] edges = new int [][] {{0,1},{0,2},{0,3},{1,4}};
		System.out.println(validTree(5, edges));//true

		int[][] edges1 = new int [][] {{0,1},{1,2},{2,3},{1,3},{1,4}};
		System.out.println(validTree(5, edges1));//false

	}

}
