package leetcode.sorting.interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/meeting-rooms/
 * 252. Meeting Rooms
 *
 * Given an array of meeting time intervals where intervals[i] = [starti, endi],
 * determine if a person could attend all meetings.
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 *
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 */

/**
 * Pre Sorting: O(n log n) -> Transform
 * T(n) = O(1) + T(n-1) -> Conquer (Line sweep/linear scan)
 * = O(n)
 * Time Complexity: O(n log n)
 */
public class MeetingRooms
{
	public static boolean canAttendMeetings(int[][] intervals)
	{
		if (intervals.length == 0) return  true;
		//sort based on start time
		//Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
		Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
		//int start = intervals[0][0];
		int end = intervals[0][1];
		for (int i=1; i<intervals.length; i++)
		{
			if (intervals[i][0] < end) return  false;
			end = intervals[i][1];
		}
		return true;
	}

	public static void sort(int[][] intervals)
	{
		Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
	}

	public static void print(int[][] intervals)
	{
		for (int i=0; i<intervals.length; i++)
		{
			for (int j=0; j<intervals[0].length; j++)
			{
				System.out.println("intervals["+i+"]["+j+"]="+ intervals[i][j]);
			}
		}
	}

	public static void main(String ... args) {

		int[][] intervals = new int[][] {{7,8},{3,5},{3,4},{2,8},{2,5}};

		sort(intervals); //{{2,8},{2,5},{3,5},{3,4},{7,8}}
		System.out.println("intervals.length: "+ intervals.length);//5
		System.out.println("intervals[0].length: "+ intervals[0].length);//2
		System.out.println("intervals[1].length: "+ intervals[1].length);//2

		boolean b = canAttendMeetings(intervals);
		System.out.println(b);//false

		int[][] intervals1 = {{0,30},{5,10},{15,20}};
		b = canAttendMeetings(intervals1);
		System.out.println(b); //false

		int[][] intervals2 = {{7,10},{2,4}};
		b = canAttendMeetings(intervals2);
		System.out.println(b); //true
	}
}
