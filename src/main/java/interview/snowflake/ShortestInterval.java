package interview.snowflake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/discuss/interview-question/689064/Snowflake-or-Phone-or-Best-Interval
 *
 * 1851. Minimum Interval to Include Each Query
 * https://leetcode.com/problems/minimum-interval-to-include-each-query/
 *
 * Given a list of intervals, and a list of integers, calculate the best interval for each number in that list of intervals.
 *
 * Best intervals in defined as the shortest length interval that covers the number (e.g, [1,10] and [2,5]
 * both cover number 4, but [2,5] is the best interval among these two).
 * Also, the given list of intervals does not partially overlap, they are either disjoint
 * or one fully overlaps another (see the example below)
 *
 * Example:
 * Input:
 * intervals = {{2, 3}, {1, 20}, {15, 16}, {2, 5}, {1, 8}, {9, 12}, {6, 8}}
 * nums = {3, 5, 7, 9, 15, 17, 40}
 * Output:
 * {{2, 3}, {2, 5}, {6, 8}, {9, 12}, {15, 16}, {1, 20}, {}}
 *
 * Explanation:
 * for 3, these intervals cover it: {2,3}, {1,20}, {2,5}, {1,8}, and {2,3} is the one has shortest length (=1),
 * so output {2,3} for 3
 *
 */
public class ShortestInterval
{
	//Brute Force: O (n^2)
	public static List<Interval> shortestInterval(List<Interval> intervals, List<Integer> nums)
	{
		List<Interval> result = new ArrayList<>();
		for (Integer num : nums)
		{
			int minDistance = Integer.MAX_VALUE;
			Interval intl = new Interval();
			for (Interval interval : intervals)
			{
				if (num >= interval.start && num <= interval.end)
				{
					int distance = interval.end - interval.start + 1;
					if (distance < minDistance)
					{
						minDistance = distance;
						intl.start = interval.start;
						intl.end = interval.end;
						intl.distance = minDistance;
					}
				}
			}
			result.add(intl);
		}
		return result;
	}

	//O (n log n) ?
	public static List<Interval> shortestInterval_1(List<Interval> intervals, List<Integer> nums)
	{
		//sort both nums and intervals
		nums.sort(null);

		intervals.sort(Comparator.comparingInt((Interval inter) -> inter.start)
				.thenComparingInt((Interval inter) -> inter.end));

		System.out.println(intervals);//[[1, 8], [1, 20], [2, 1], [2, 3], [2, 5], [6, 8], [9, 12], [15, 16]]

		List<Interval> result = new ArrayList<>();
		for (Integer num : nums)
		{
			int minDistance = Integer.MAX_VALUE;
			Interval intl = new Interval(-1, -1);
			for (Interval interval : intervals)
			{
				if (num >= interval.start && num <= interval.end)
				{
					int distance = interval.end - interval.start + 1;
					if (distance < minDistance)
					{
						minDistance = distance;
						intl.start = interval.start;
						intl.end = interval.end;
						intl.distance = minDistance;
					}
				}
			}
			result.add(intl);
		}
		return result;
	}

	public static class Interval
	{
		public int start;
		public int end;
		public int distance;

		public Interval() {}

		public Interval(int start, int end)
		{
			this.start = start;
			this.end = end;
		}

		public Interval(int start, int end, int distance) {
			this.start = start;
			this.end = end;
			this.distance = distance;
		}

		public String toString()
		{
			return "[" + this.start + ", " + this.end + "]";
		}
	}

	public static void main(String ... args)
	{
		List<Interval> intervals = Arrays.asList(new Interval(2,3), new Interval(1,20), new Interval(15,16), new Interval(2,5),
				new Interval(1,8), new Interval(9, 12), new Interval(6,8));
		List<Integer> nums = Arrays.asList(3, 5, 7, 9, 15, 17, 40);
		List<Interval> shortestInterval = shortestInterval(intervals, nums);
		System.out.println(shortestInterval); //[[2, 3], [2, 5], [6, 8], [9, 12], [15, 16], [1, 20], [0, 0]]

		List<Interval> intervals1 = Arrays.asList(new Interval(2,5), new Interval(1,20), new Interval(2,1), new Interval(15,16), new Interval(2,3),
				new Interval(1,8), new Interval(9, 12), new Interval(6,8));
		List<Integer> nums1 = Arrays.asList(3, 5, 7, 9, 15, 17, 40);
		List<Interval> shortestInterval1 = shortestInterval_1(intervals1, nums1);
		System.out.println(shortestInterval1); //[[2, 3], [2, 5], [6, 8], [9, 12], [15, 16], [1, 20], [-1, -1]]

	}
}
