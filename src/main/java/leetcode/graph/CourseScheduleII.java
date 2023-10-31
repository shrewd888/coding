package leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Answer taken from Leetcode answer
 * 210. Course Schedule II
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi]
 * indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses.
 * If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 *
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 */
public class CourseScheduleII
{
	static int WHITE = 1;
	static int GRAY = 2;
	static int BLACK = 3;

	boolean isPossible;
	Map<Integer, Integer> color;
	Map<Integer, List<Integer>> adjList;
	List<Integer> topologicalOrder;

	private void init(int numCourses) {
		this.isPossible = true;
		this.color = new HashMap<Integer, Integer>();
		this.adjList = new HashMap<Integer, List<Integer>>();
		this.topologicalOrder = new ArrayList<Integer>();

		// By default all vertices are WHITE
		for (int i = 0; i < numCourses; i++) {
			this.color.put(i, WHITE);
		}
	}

	private void dfs(int node) {

		// Don't recurse further if we found a cycle already
		if (!this.isPossible) {
			return;
		}

		// Start the recursion
		this.color.put(node, GRAY);

		// Traverse on neighboring vertices
		for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<Integer>())) {
			if (this.color.get(neighbor) == WHITE) {
				this.dfs(neighbor);
			} else if (this.color.get(neighbor) == GRAY) {
				// An edge to a GRAY vertex represents a cycle
				this.isPossible = false;
			}
		}

		// Recursion ends. We mark it as black
		this.color.put(node, BLACK);
		this.topologicalOrder.add(node);
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {

		this.init(numCourses);

		// Create the adjacency list representation of the graph
		for (int i = 0; i < prerequisites.length; i++) {
			int dest = prerequisites[i][0];
			int src = prerequisites[i][1];
			List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
			lst.add(dest);
			adjList.put(src, lst);
		}

		// If the node is unprocessed, then call dfs on it.
		for (int i = 0; i < numCourses; i++) {
			if (this.color.get(i) == WHITE) {
				this.dfs(i);
			}
		}

		int[] order;
		if (this.isPossible) {
			order = new int[numCourses];
			for (int i = 0; i < numCourses; i++) {
				order[i] = this.topologicalOrder.get(numCourses - i - 1);//reverse the order
			}
		} else {
			order = new int[0];
		}

		return order;
	}

	static void print(int[] arr)
	{
		System.out.print("[");
		for (int i=0; i < arr.length; i++)
		{
			System.out.print(arr[i]);
			if (i < arr.length-1)
				System.out.print(",");
		}
		System.out.print("] ");
	}


	public static void main(String ... args) {
		CourseScheduleII cs = new CourseScheduleII();

		int numCourses = 2;
		int[][] prerequisites = new int [][]{{1,0}};
		int[] result = cs.findOrder(numCourses, prerequisites);
		print(result);//[0,1]

		int numCourses1 = 4;
		int[][] prerequisites1 = new int [][]{ {1,0},{2,0},{3,1},{3,2} };
		int[] result1 = cs.findOrder(numCourses1, prerequisites1);
		print(result1);//[0,2,1,3] or [0,1,2,3]

		int numCourses2 = 1;
		int[][] prerequisites2 = new int [][]{};
		int[] result2 = cs.findOrder(numCourses2, prerequisites2);
		print(result2);//[0]
	}
}
