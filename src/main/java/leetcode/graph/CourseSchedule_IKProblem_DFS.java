package leetcode.graph;

import java.util.ArrayList;
import java.util.List;

//Wrong answer!
public class CourseSchedule_IKProblem_DFS
{
	public static Pair<Boolean, List<Integer>> canFinish(int numCourses, int[][] prerequisites)
	{
		List<Integer> answer = new ArrayList<>();
		int len = prerequisites.length;
		if (len == 0 || numCourses == 0) return new Pair<>(Boolean.TRUE, answer);

		//initialize visited array with 0
		int[] visited = new int[numCourses];

		//[1,0] -> 0 must be taken before 1
		List<Integer>[] adjList = new ArrayList[numCourses];
		//initialize
		for (int i=0; i < numCourses; i++)
		{
			adjList[i] = new ArrayList<>();
		}
		for (int i=0; i < len; i++)
		{
			adjList[prerequisites[i][1]].add(prerequisites[i][0]);
		}
		// Top level DFS: call DFS on each unvisited node in the graph.
		for (int i=0; i < numCourses; i++)
		{
			//has not been visited
			if (visited[i] == 0)
			{
				if (!dfs(adjList, visited, i))
					return new Pair(Boolean.FALSE, List.of());//empty course
			}
			answer.add(i);

		}
		//Collections.reverse(answer);
		return new Pair(Boolean.TRUE, answer);
	}

	public static boolean dfs(List<Integer>[] adjList, int[] visited, int node)
	{
		visited[node] = 1;
		List<Integer> neighbors = adjList[node];

		for (Integer neighbor : neighbors)
		{
			//cycle detected
			if (visited[neighbor] == 1)
			{
				return false;//not able to complete the course
			}
			else //visited[neighbor] == 0
			{
				if (!dfs(adjList, visited, neighbor)) return false; //cycle detected
			}
		}
		return true;
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

//		numCourses0 = 3;
//		prerequisites0 = new int[][]{{1,0}, {1,2}, {0,1}};
//		canFinish0 = canFinish(numCourses0, prerequisites0);
//		System.out.println(canFinish0.key);//false
//		System.out.println(canFinish0.value);//[-1]
//
//		numCourses0 = 3;
//		prerequisites0 = new int[][] {{1,0}, {0,2}, {2,1}};
//		canFinish0 = canFinish(numCourses0, prerequisites0);
//		System.out.println(canFinish0.key);//false
//		System.out.println(canFinish0.value);//[-1]

		numCourses0 = 4;
		prerequisites0 =  new int[][]{{0,1}, {0,2}, {1,3}, {3,0}};
		canFinish0 = canFinish(numCourses0, prerequisites0);
		System.out.println(canFinish0.key);//false
		System.out.println(canFinish0.value);//[-1]

		numCourses0 = 4;
		prerequisites0 =  new int[][]{{0,1}, {0,2}, {1,3}, {0,3}};
		canFinish0 = canFinish(numCourses0, prerequisites0);
		System.out.println(canFinish0.key);//answer is FALSE, MUST BE true
		System.out.println(canFinish0.value);//[2,3,1,0]

		int numCourses = 5;
		int[][] prerequisites = {{1,4}, {2,4}, {3,1}, {3,2}};
		Pair<Boolean, List<Integer>> canFinish = canFinish(numCourses, prerequisites);
		System.out.println(canFinish.key);//true
		System.out.println(canFinish.value);//[0, 4, 1, 2, 3]

		int numCourses1 = 5;
		int[][] prerequisites1 = {{1,4}, {2,4}, {3,1}, {3,2}, {0,3}};
		Pair<Boolean, List<Integer>> canFinish1 = canFinish(numCourses1, prerequisites1);
		System.out.println(canFinish1.key);//true
		System.out.println(canFinish1.value);//[4, 1, 2, 3, 0]

		int numCourses2 = 2;
		int[][] prerequisites2 = {{1,0}, {0,1}};
		Pair<Boolean, List<Integer>> canFinish2 = canFinish(numCourses2, prerequisites2);
		System.out.println(canFinish2.key);//false
		System.out.println(canFinish2.value);//[-1]

		int numCourses3 = 2;
		int[][] prerequisites3 = {{1,0}};
		Pair<Boolean, List<Integer>> canFinish3 = canFinish(numCourses3, prerequisites3);
		System.out.println(canFinish3.key);//true
		System.out.println(canFinish3.value);//[0, 1]
	}

}
