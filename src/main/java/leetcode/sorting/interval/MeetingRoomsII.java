package leetcode.sorting.interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/meeting-rooms-ii/
 * 253. Meeting Rooms II
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of conference rooms required.
 *
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 *
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 */
/**
 * Time Complexity: O(n log n)
 * Space Complexity: Aux space = O(n)
 */
public class MeetingRoomsII
{
	public static int minMeetingRooms(int[][] intervals) {
		int count = 0;
		if (intervals.length == 0) return count;
		//sort based on start times
		Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
		Queue<Integer> q = new PriorityQueue<>(); //min start time

		int nextStart;
		for (int i=0; i < intervals.length; i++)
		{
			if (i==intervals.length-1)
			{
				nextStart = Integer.MAX_VALUE;
			}
			else
			{
				nextStart = intervals[i+1][0];
			}
			//now start meeting: [i][0]
			q.add(intervals[i][1]); //end time of my meeting
			count = Math.max(count, q.size());
			while (!q.isEmpty() && q.peek() <= nextStart)
			{
				//end the meeting, remove from queue
				//at the end, clean up queue by removing all element because all are < Integer.MAX_VALUE
				q.poll();
			}
		}
		return count;
	}

	public static void main(String ... args) {

		int[][] intervals0 = {{5,8}, {6,8}};
		int c = minMeetingRooms(intervals0);
		System.out.println(c);//2

		int[][] intervals = new int[][] {{7,8},{3,5},{3,4},{2,8},{2,5}};
		int b = minMeetingRooms(intervals);
		System.out.println(b);//4

		int[][] intervals1 = {{0,30},{5,10},{15,20}};
		b = minMeetingRooms(intervals1);
		System.out.println(b); //2

		int[][] intervals2 = {{7,10},{2,4}};
		b = minMeetingRooms(intervals2);
		System.out.println(b); //1

		int[][] intervals3 = {{1,5},{8,9},{8,9}};
		b = minMeetingRooms(intervals3);
		System.out.println(b); //2

		//if sort by start time the result = 3 -> wrong : [4,9],[4,17],[9,10] -> all overlap
		//if sort by end time the result = 2 -> correct : [4,9], [9,10], [4,17]
		int[][] intervals4 = {{9,10},{4,9},{4,17}};
		b = minMeetingRooms(intervals4);
		System.out.println(b); //2

		int[][] intervals5 = {{2,11},{6,16},{11,16}};
		b = minMeetingRooms(intervals5);
		System.out.println(b); //2
	}
}
