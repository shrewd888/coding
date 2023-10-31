package leetcode.sorting.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-interval-to-include-each-query/
 * 1851. Minimum Interval to Include Each Query
 *
 * You are given a 2D integer array intervals, where intervals[i] = [lefti, righti]
 * describes the ith interval starting at lefti and ending at righti (inclusive).
 * The size of an interval is defined as the number of integers it contains,
 * or more formally righti - lefti + 1.
 *
 * You are also given an integer array queries.
 * The answer to the jth query is the size of the smallest interval i such that lefti <= queries[j] <= righti.
 * If no such interval exists, the answer is -1.
 *
 * Return an array containing the answers to the queries.
 *
 * Example 1:
 * Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * Output: [3,3,1,4]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,4] is the smallest interval containing 2. The answer is 4 - 2 + 1 = 3.
 * - Query = 3: The interval [2,4] is the smallest interval containing 3. The answer is 4 - 2 + 1 = 3.
 * - Query = 4: The interval [4,4] is the smallest interval containing 4. The answer is 4 - 4 + 1 = 1.
 * - Query = 5: The interval [3,6] is the smallest interval containing 5. The answer is 6 - 3 + 1 = 4.
 *
 * Example 2:
 * Input: intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * Output: [2,-1,4,6]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,3] is the smallest interval containing 2. The answer is 3 - 2 + 1 = 2.
 * - Query = 19: None of the intervals contain 19. The answer is -1.
 * - Query = 5: The interval [2,5] is the smallest interval containing 5. The answer is 5 - 2 + 1 = 4.
 * - Query = 22: The interval [20,25] is the smallest interval containing 22. The answer is 25 - 20 + 1 = 6.
 */
public class MinIntervalToIncludeEachQuery
{
	public static int[] minInterval(int[][] intervals, int[] queries) {
		int[] result = new int[queries.length];
		if (intervals.length == 0 || queries.length==0)
		{
			result[0] = -1;
			return result;
		}

		int[] copyQueries = new int[queries.length];
		for (int i=0; i < queries.length; i++)
		{
			copyQueries[i] = queries[i];
		}

		//sort intervals based on start
		Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
		//sort queries in asc order
		Arrays.sort(queries);

		//queue is sorted by distance, end location
		Queue<IntervalDistance> q = new PriorityQueue<>((c1, c2) -> {
			if (c1.distance == c2.distance) {
				return c1.interval.end - c2.interval.end;
			}
			return c1.distance - c2.distance;
		});

		//for printing purpose
		List<IntervalDistance> intervalDistanceList = new ArrayList<>();

		//because queries are sorted, when return, need to maintain original order
		Map<Integer, Integer> queryDistanceMap = new HashMap<>();

		//add into PQ with endPoint, distance as priority
		//Time Complexity: O(n log n)
		int index = 0;
		for (int i=0; i < queries.length; i++)
		{
			int query = queries[i];
			//clean up, remove all invalid intervals from the queue
			//queries & intervals need to be sorted
			while (!q.isEmpty() && q.peek().interval.end < query) //because q is sorted by endPoint
			{
				q.poll();
			}
			while (index < intervals.length)
			{
				int start = intervals[index][0];
				int end = intervals[index][1];
				int distance = (end - start) + 1;
				if (start <= query && query <= end) //query is between the interval -> valid
				{
					q.add(new IntervalDistance(new Interval(start, end), distance));
					index++;//go to the next interval
				}
				else if (start <= query)
				{
					index++;
				}
				else // (start > query) //because interval is sorted by start, we know immediately if this interval & beyond is AFTER the query
				{
					break;
				}
			}
			result[i] = -1;
			//initialize
			IntervalDistance intervalDistance = new IntervalDistance(new Interval(-1, -1), -1);
			if (!q.isEmpty())
			{
				result[i] = q.peek().distance;
				intervalDistance = new IntervalDistance(q.peek().interval, result[i]);
			}
			queryDistanceMap.put(query, result[i]); ///need map if we want to return the answer based on the original order of input query
			//can also use LinkedHashMap and no need to use copyQueries
			intervalDistanceList.add(intervalDistance);//this is for print purpose only
		}
		System.out.println(intervalDistanceList);

		int[] returnResult = new int[result.length];
		for (int i=0; i < copyQueries.length;i++)
		{
			returnResult[i] = queryDistanceMap.get(copyQueries[i]);
		}
		//print(returnResult);
		//return result;
		return returnResult;
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

	//Runtime: 297 ms, faster than 18.08% of Java online submissions
	public static void main(String ... args)
	{
		int[][] intervals = {{1,4},{2,4},{3,6},{4,4}};
		int[] queries = {2,3,4,5};
		int[] result = minInterval(intervals, queries);
		print(result); //[[2,4],3, [2,4],3, [4,4],1, [3,6],4] -> [3,3,1,4]

		int[][] intervals1 = {{2,3},{2,5},{1,8},{20,25}};
		int[] queries1 = {2,19,5,22};
		int[] result1 = minInterval(intervals1, queries1);//[[2,3],2, [2,5],4, [-1,-1],-1, [20,25],6]
		print(result1);//[2,4,-1,6]

		int[][] intervals2 = {{4,5},{5,8},{1,9},{8,10},{1,6}};
		int[] queries2 = {7,9,3,9,3};
		int[] result2 = minInterval(intervals2, queries2);//[[1,6],6, [1,6],6, [5,8],4, [8,10],3, [8,10],3]
		print(result2);//[6,6,4,3,3] -> unsorted queries: [4,3,6,3,6]
	}
}
