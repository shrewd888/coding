package leetcode.sorting.interval;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/employee-free-time/
 * 759. Employee Free Time
 *
 * We are given a list schedule of employees, which represents the working time for each employee.
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 *
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
 * For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 * Example 1:
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation: There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 *
 * Example 2:
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 */

/**
 * Time Complexity: O(n log k) -> dominant time in the while loop, n can be very large
 */
public class EmployeeFreeTime_Omkar
{
	public static List<Interval> employeeFreeTime(List<List<Interval>> schedule)
	{
		List<Interval> busyIntervals = new ArrayList<>();
		//sort by start time ascending
		Queue<EmployeeSchedule> minHeap = new PriorityQueue<>((i1, i2) -> Integer.compare(i1.interval.start, i2.interval.start));

		//add all FIRST interval in the schedule
		//similar with merge k list, having k pointer
		for (int i=0; i < schedule.size(); i++)
		{
			List<Interval> intervals = schedule.get(i);
			if (intervals.size() > 0)
			{
				Interval first = intervals.get(0);
				//take the interval schedule[i][0] and insert into the PQ with priority = start time
				//need to know the location where it comes from in order to increment the pointer
				EmployeeSchedule employeeSchedule = new EmployeeSchedule(first, i, 0);
				minHeap.add(employeeSchedule);
			}
		}

		while(!minHeap.isEmpty())
		{
			EmployeeSchedule employeeSchedule = minHeap.poll();
			Interval interval = employeeSchedule.interval;
			int outerIndex = employeeSchedule.outerIndex;
			int innerIndex = employeeSchedule.innerIndex;

			if (busyIntervals.size() == 0)
			{
				busyIntervals.add(interval);
			}
			else //result is not empty
			{
				int lastIndex = busyIntervals.size()-1;
				//merge interval into result
				//no overlap: look at the right most interval (the last) in the result and look at the end time
				if (busyIntervals.get(lastIndex).end < interval.start)
				{
					busyIntervals.add(interval);
				}
				else //overlap, hence merge
				{
					Interval last = busyIntervals.get(lastIndex);
					busyIntervals.set(lastIndex, new Interval(last.start, Math.max(last.end, interval.end)));
				}
			}
			//increment the pointer within the same outerIndex (inner schedule)
			innerIndex++;
			if (innerIndex < schedule.get(outerIndex).size()) //make sure the inner index is a valid index
			{
				Interval nextInterval = schedule.get(outerIndex).get(innerIndex);
				EmployeeSchedule empSch = new EmployeeSchedule(nextInterval, outerIndex, innerIndex);
				minHeap.add(empSch);
			}
		}
		System.out.println(busyIntervals);

		//For each interval in the result the gap between each interval is the free time
		List<Interval> freeTimeList = new ArrayList<>();
		int end = busyIntervals.get(0).end;
		for (int i=1; i < busyIntervals.size(); i++)
		{
			Interval interval = busyIntervals.get(i);
			freeTimeList.add(new Interval(end, interval.start));
			end = interval.end;
		}
		return freeTimeList;
	}

	//Runtime: 28 ms, faster than 26.62% of Java online submissions
	public static void main(String ... args)
	{
		//input: [[[1,2],[5,6]],[[1,3]],[[4,10]]]
		List<List<Interval>> schedule = new ArrayList<>();
		List<Interval> inner = new ArrayList<>();
		Interval interval = new Interval(1, 2);
		inner.add(interval);
		interval = new Interval(5, 6);
		inner.add(interval);
		schedule.add(inner);

		inner = new ArrayList<>();
		interval = new Interval(1, 3);
		inner.add(interval);
		schedule.add(inner);

		inner = new ArrayList<>();
		interval = new Interval(4, 10);
		inner.add(interval);
		schedule.add(inner);

		List<Interval> freeTime = employeeFreeTime(schedule);
		System.out.println(freeTime);//[[3,4]]

		//input: [ [[1,3],[6,7]], [[2,4]], [[2,5],[9,12]] ]
		schedule = new ArrayList<>();
		inner = new ArrayList<>();
		interval = new Interval(1, 3);
		inner.add(interval);
		interval = new Interval(6, 7);
		inner.add(interval);
		schedule.add(inner);

		inner = new ArrayList<>();
		interval = new Interval(2, 4);
		inner.add(interval);
		schedule.add(inner);

		inner = new ArrayList<>();
		interval = new Interval(2, 5);
		inner.add(interval);
		interval = new Interval(9, 12);
		inner.add(interval);
		schedule.add(inner);

		freeTime = employeeFreeTime(schedule);
		System.out.println(freeTime); //[[5,6], [7,9]]

	}
}
