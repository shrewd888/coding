package leetcode.sorting.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. Merge Intervals
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
/**
 * Time Complexity: O(n log n) -> Presorting
 */
public class MergeIntervals
{
	public static int[][] merge(int[][] intervals) {
		List<int[]> result = new ArrayList<>();
		if (intervals.length == 0) return result.toArray(new int[0][0]);

		//sort based on start times
		Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

		//initialize
		result.add(0, new int[]{intervals[0][0], intervals[0][1]});
		for (int i=1; i<intervals.length; i++)
		{
			//if intervals[i] overlaps with result[length(result) - 1]: merge them
			//else: append intervals[i] to the result
			int sz = result.size()-1;
			if (result.get(sz)[1] >= intervals[i][0]) //overlap
			{
				result.set(sz, new int[]{ result.get(sz)[0], Math.max(result.get(sz)[1], intervals[i][1]) });
			}
			else //no overlap
			{
				result.add(intervals[i]);
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

	public static void main(String ... args)
	{
		int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
		int[][] result = merge(intervals);//[1,6][8,10][15,18]
		print(result);

		int[][] intervals1 = {{1,4},{4,5}};
		int[][] result1 = merge(intervals1);
		print(result1);

	}
}
