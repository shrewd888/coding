package leetcode.graph;

/**
 * https://leetcode.com/problems/course-schedule/
 * 207. Course Schedule
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1.
 * So it is impossible.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Detect if there is a cycle, then the answer is false
 * Return true/false and the order of course to take
 */
/**
 * Time Complexity: O(n+e), n is the #of courses/nodes & e is the #of preds/edges
 * Space Complexity: O(n+e) : build the graph and run DFS
 */
//PASS ALL TEST CASES
public class CourseSchedule
{
	public static Pair<Boolean, List<Integer>> canFinish(int numCourses, int[][] prerequisites)
	{
		int len = prerequisites.length;
		if (len == 0 || numCourses == 0) return new Pair<>(Boolean.TRUE, new ArrayList<>());

		List<Integer>[] adjList = new ArrayList[numCourses];

		/**
		 * In-degree of a node in a directed graph is the number of edges that end in that node
		 * (how many arrows point to that node)
		 *
		 * Map of course number and how many preq courses should be taken
		 */
		Map<Integer, Integer> in_degree = new HashMap<>();

		//initialize
		for (int i=0; i < numCourses; i++)
		{
			adjList[i] = new ArrayList<>();
		}
		for (int i=0; i < prerequisites.length; i++)
		{
			//[1,0] in order to take course 1, has to take course 0
			// 0 -> [1]
			adjList[prerequisites[i][1]].add(prerequisites[i][0]);
			//arrow coming from 0 to 1
			in_degree.put(prerequisites[i][0], in_degree.getOrDefault(prerequisites[i][0], 0) + 1);
		}

		/**
		 * any node with zero in-degree can be readily added to the topological ordering.
		 * Same observation in terms of the courses and dependencies:
		 * we can always start taking courses from one that has no dependencies.
		 */
		Queue<Integer> nodesWithZeroDegree = new LinkedList<>();
		for (int i=0; i < numCourses; i++)
		{
			if (in_degree.getOrDefault(i, 0) == 0)
			{
				nodesWithZeroDegree.add(i);
			}
		}

		List<Integer> answer = new ArrayList<>();
		while (!nodesWithZeroDegree.isEmpty())
		{
			/* erase this node from graph */
			int course = nodesWithZeroDegree.poll();
			answer.add(course);

			List<Integer> neighbors = adjList[course];
			for (Integer neighbor : neighbors)
			{
				int x = in_degree.getOrDefault(neighbor, -1) - 1;
				//update the value of in_degree mop
				in_degree.put(neighbor, x);
				if (x == 0)
				{
					nodesWithZeroDegree.add(neighbor);
				}
			}
		}
		if (answer.size() == numCourses) return new Pair<>(Boolean.TRUE, answer);
		else return new Pair<>(Boolean.FALSE, List.of(-1));
	}

	public static class Pair<K,V>
	{
		K key;
		V value;

		Pair(K k, V v)
		{
			this.key = k;
			this.value = v;
		}

		K getKey()
		{
			return key;
		}

		V getValue()
		{
			return value;
		}
	}


	public static void main(String ... args) {

		int numCourses0 = 2;
		int[][] prerequisites0 = {{1,0}};
		Pair<Boolean, List<Integer>> canFinish0 = canFinish(numCourses0, prerequisites0);
		System.out.println(canFinish0.key);//true
		System.out.println(canFinish0.value);//[0, 1]

		int numCourses01 = 3;
		int[][] prerequisites01 = new int[][]{{1,0}, {1,2}, {0,1}};
		Pair<Boolean, List<Integer>> canFinish01 = canFinish(numCourses01, prerequisites01);
		System.out.println(canFinish01.key);//false
		System.out.println(canFinish01.value);//[-1]

		int numCourses02 = 3;
		int[][] prerequisites02 = new int[][]{{1,0}, {1,2}};
		Pair<Boolean, List<Integer>> canFinish02 = canFinish(numCourses02, prerequisites02);
		System.out.println(canFinish02.key);//true
		System.out.println(canFinish02.value);//[0,2,1]

		numCourses0 = 3;
		 prerequisites0 = new int[][] {{1,0}, {0,2}, {2,1}};//circle detected
		 canFinish0 = canFinish(numCourses0, prerequisites0);
		System.out.println(canFinish0.key);//false
		System.out.println(canFinish0.value);//[-1]

		numCourses0 = 4;
		prerequisites0 =  new int[][]{{0,1}, {0,2}, {1,3}, {0,3}};
		canFinish0 = canFinish(numCourses0, prerequisites0);
		System.out.println(canFinish0.key);//true
		System.out.println(canFinish0.value);//[2,3,1,0]

		numCourses0 = 4;
		 prerequisites0 =  new int[][]{{0,1}, {0,2}, {1,3}, {3,0}};
		 canFinish0 = canFinish(numCourses0, prerequisites0);
		System.out.println(canFinish0.key);//false
		System.out.println(canFinish0.value);//[-1]
//
//		int numCourses = 5;
//		int[][] prerequisites = {{1,4}, {2,4}, {3,1}, {3,2}};
//		Pair<Boolean, List<Integer>> canFinish = canFinish(numCourses, prerequisites);
//		System.out.println(canFinish.key);//true
//		System.out.println(canFinish.value);//[0, 4, 1, 2, 3]
//
//		int numCourses1 = 5;
//		int[][] prerequisites1 = {{1,4}, {2,4}, {3,1}, {3,2}, {0,3}};
//		Pair<Boolean, List<Integer>> canFinish1 = canFinish(numCourses1, prerequisites1);
//		System.out.println(canFinish1.key);//true
//		System.out.println(canFinish1.value);//[4, 1, 2, 3, 0]
//
//		int numCourses2 = 2;
//		int[][] prerequisites2 = {{1,0}, {0,1}};
//		Pair<Boolean, List<Integer>> canFinish2 = canFinish(numCourses2, prerequisites2);
//		System.out.println(canFinish2.key);//false
//		System.out.println(canFinish2.value);//[-1]
//
//		int numCourses3 = 2;
//		int[][] prerequisites3 = {{1,0}};
//		Pair<Boolean, List<Integer>> canFinish3 = canFinish(numCourses3, prerequisites3);
//		System.out.println(canFinish3.key);//true
//		System.out.println(canFinish3.value);//[0, 1]
	}
}
