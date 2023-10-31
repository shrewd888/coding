package leetcode.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
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

/**
 * Stefan Andonian did the code (IK Coding Coach)
 * Time Complexity
 * Space Complexity
 */
public class CourseScheduleII_IK
{

	public static int[] findOrder(int numCourses, int[][] prerequisites)
	{
		int m = prerequisites.length; //#of row
		if (m == 0)
			return new int[] {};

		int n = prerequisites[0].length; //#of col

		List<Course> nodes = new ArrayList<>(4);

		for (int i = 0; i < numCourses; i++)
		{
			Course course = new Course();
			course.id = i;
			nodes.add(course);
		}
		/**
		 * #of col = 2
		 * #of row = x
		 * [0,0], [0,1]
		 * [1,0], [1,1]
		 * [2,0], [2,1]
		 * ..
		 * ..
		 */
		for (int row = 0; row < m; row++)
		{
			//should take pre-course before post-course
			int post = prerequisites[row][0];
			int pre = prerequisites[row][1];

			nodes.get(pre).postIds.add(post);
			nodes.get(post).preIds.add(pre);
		}
		//add courses that don't have prerequisite to the queue
		//FIFO
		Queue<Course> queue = new ArrayDeque<>();
		for (Course course : nodes)
		{
			if (course.preIds.isEmpty())
			{
				queue.add(course);
			}
		}

		int[] courseOrder = new int[numCourses];
		int index = 0;
		while(!queue.isEmpty())
		{
			Course course = queue.poll();
			if (course.preIds.isEmpty() && course.hasBeenTaken == false)
			{
				courseOrder[index] = course.id;
				course.hasBeenTaken = true;
				index++;
				course.postIds.forEach(post ->
				{
					Course postCourse = nodes.get(post);
					postCourse.preIds.remove(course.id);
					queue.add(postCourse);
				});
			}
		}
		return courseOrder;
	}

	public static class Course
	{
		Integer id;
		boolean hasBeenTaken;
		Set<Integer> preIds = new HashSet<>();
		Set<Integer> postIds = new HashSet<>();
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
		int[][] prerequisites = {{1,0}};
		int numCourses = 2;
		int[] courseOrder = findOrder(numCourses, prerequisites);
		print(courseOrder); //[0,1]

		int[][] prerequisites1 = {{1,0},{2,0},{3,1},{3,2}};
		numCourses = 4;
		courseOrder = findOrder(numCourses, prerequisites1);
		print(courseOrder);//[0,1,2,3]

		int[][] prerequisites2 = {};
		numCourses = 1;
		courseOrder = findOrder(numCourses, prerequisites2);
		print(courseOrder);//[]

		int[][] prerequisites3 = {{1,4}, {2,4}, {3,1}, {3,2}};
		numCourses = 5;
		courseOrder = findOrder(numCourses, prerequisites3);
		print(courseOrder);//[0,4,1,2,3]

		int[][] prerequisites4 = {{1,0}, {0,1}};
		numCourses = 2;
		courseOrder = findOrder(numCourses, prerequisites4);
		System.out.println(courseOrder);//I@7c75222b
	}
}
