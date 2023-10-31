package leetcode.sorting.interval;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/interval-list-intersections/
 *
 * 986. Interval List Intersections
 * You are given two lists of closed intervals, firstList and secondList,
 * where firstList[i] = [starti, endi] and secondList[j] = [startj, endj].
 * Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 *
 * The intersection of two closed intervals is a set of real numbers that are
 * either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 * Example 1:
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *
 * Example 2:
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 */
/**
 * Time Complexity: O(m+n)
 * Space Complexity: O(m+n)
 */
public class IntervalListIntersections
{
	public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

		if (firstList.length == 0 || secondList.length == 0) return new int[][]{};
		List<int[]> result = new ArrayList<>();

		int i=0;
		int j=0;
		while (i < firstList.length && j < secondList.length)
		{
			//1st list element is on the left of the 2nd list element
			if (firstList[i][1] < secondList[j][0]) i++;
			//2nd list element is on the left of the 1st list element
			else if (secondList[j][1] < firstList[i][0]) j++;
			else //overlap
			{
				//intersection: the max of the startPoint & the min of the endPoint
				int[] overlap = new int[]{ Math.max(firstList[i][0], secondList[j][0]), Math.min(firstList[i][1], secondList[j][1]) };
				result.add(overlap);
				if (firstList[i][1] <= secondList[j][1])
				{
					i++;
				}
				else
				{
					j++;
				}
			}
		}
		return result.toArray(new int[][]{});
	}

	public static void print(int[][] result)
	{
		for (int i=0; i < result.length; i++)
		{
			System.out.print("[");
			for (int j=0; j < result[0].length; j++)
			{
				System.out.print(result[i][j]);
				if (j < result[0].length-1)
					System.out.print(",");
			}
			System.out.print("]");
		}
		System.out.println();
	}

	//Runtime: 2 ms, faster than 99.94% of Java online submissions for Interval List
	public static void main(String ... args)
	{
		int[][] firstList = {{0,2},{5,10},{13,23},{24,25}};
		int[][] secondList = {{1,5},{8,12},{15,24},{25,26}};
		int[][] result = intervalIntersection(firstList, secondList);
		print(result);//[1,2][5,5][8,10][15,23][24,24][25,25]

		int[][] firstList1 = {{1,3},{5,9}};
		int[][] secondList1 = {};
		int[][] result1 = intervalIntersection(firstList1, secondList1);
		print(result1);//none
	}
}
