package leetcode.sorting.interval;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. Insert Interval
 *
 * You are given an array of non-overlapping intervals where intervals[i] = [starti, endi]
 * represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti
 * and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */

/**
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class InsertInterval
{
	//no presorting needed
	public static int[][] insert(int[][] intervals, int[] newInterval) {

		List<int[]> result = new ArrayList<>();
		int ln = intervals.length;
		if (ln == 0) {
			result.add(newInterval);
			return result.toArray(new int[][]{});
		}

		int bailIndex = ln;
		//as long as the current interval ends before the newInterval starts, we don't change anything
		//otherwise, merge begins
		for (int i=0; i < intervals.length; i++)
		{
			//no overlap
			if (intervals[i][1] < newInterval[0])
			{
				result.add(intervals[i]);
			}
			else //overlap
			{
				//bail out as soon as I see an overlap with new interval
				bailIndex = i;
				break;
			}
		}
		result.add(newInterval);
		//if (result.size() == ln+1 || bailIndex == -1) return result.toArray(new int[][]{}); //already merged & sorted

		//merge interval
		//if we don't bail out at all then the code below won't get executed
		for (int i=bailIndex; i<intervals.length; i++)
		{
			//if intervals[i] overlaps with result[length(result) - 1]: merge them
			//else: append intervals[i] to the result
			int sz = result.size()-1;
			if (result.get(sz)[1] >= intervals[i][0]) //overlap
			{
				result.set(sz, new int[]{ Math.min(result.get(sz)[0], intervals[i][0]), Math.max(result.get(sz)[1], intervals[i][1]) });
			}
			else //no overlap, this new interval resides on the left of intervals[i][0]
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
		int[][] intervals0 = {{1,5}};
		int[] newInterval0 = {6,8};
		int[][] result0 = insert(intervals0, newInterval0);//[1,5][6,8]
		print(result0);

		int[][] intervals = {{1,3},{6,9}};
		int[] newInterval = {2,5};
		int[][] result = insert(intervals, newInterval);//[1,5][6,9]
		print(result);

		int[][] intervals1 = {{1,2},{3,5},{6,7},{8,10},{12,16}};
		int[] newInterval1 = {4,8};
		int[][] result1 = insert(intervals1, newInterval1);//[1,2][3,10][12,16]
		print(result1);

		int[][] intervals2 = {};
		int[] newInterval2 = {5,7};
		int[][] result2 = insert(intervals2, newInterval2);
		print(result2);//[5,7]

		int[][] intervals3 = {{2,6},{7,9}};
		int[] newInterval3 = {15,18};
		int[][] result3 = insert(intervals3, newInterval3);
		print(result3);//[[2,6],[7,9],[15,18]]
	}
}
