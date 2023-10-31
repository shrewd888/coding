package interview.doordash;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import leetcode.sorting.interval.Interval;

/**
 * https://leetcode.com/discuss/interview-question/1281915/doordash-e5-phone-screen-onsite-offer
 *
 * 759. Employee Free Time
 * We are given a list schedule of employees, which represents the working time for each employee.
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 * Return the list of finite intervals representing common, positive-length free time for all employees,
 * also in sorted order.
 *
 * Even though we are representing Intervals in the form [x, y], the objects inside are Intervals,
 * not lists or arrays.
 * For example,
 * schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).
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
public class EmployeeFreeTime
{
	public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

		//int sz = schedule.stream().mapToInt(internal -> internal.size()).sum();
		//could also put size as schedule.size() since we only put one element at a time into the PQ

		List<Interval> mergedInterval = new ArrayList<>();
		List<Interval> result = new ArrayList<>();
		Queue<Interval> minHeap = new PriorityQueue<>((i1, i2) -> Integer.compare(i1.start, i2.start));

		int numEmployee = schedule.size();
		//add all first element to the heap
		for (int i=0; i < numEmployee; i++)
		{
			List<Interval> employee = schedule.get(i);
			minHeap.add(employee.get(0));
		}
		int innerIndex = 1, outerIndex = 0;

		while (!minHeap.isEmpty())
		{
			if (outerIndex == numEmployee)
			{
				innerIndex++;
				outerIndex = 0;//reset
				Interval out = minHeap.poll(); // remove
				merge(out, mergedInterval);
			}
			List<Interval> employee = schedule.get(outerIndex);
			if (innerIndex < employee.size())
			{
				Interval interval = employee.get(innerIndex);
				minHeap.add(interval);
			}
			outerIndex++;
		}

		for (int i=1; i < mergedInterval.size(); i++)
		{
			Interval prevInterval = mergedInterval.get(i-1);
			Interval curInterval = mergedInterval.get(i);

			Interval freeInterval = new Interval(prevInterval.end, curInterval.start);
			result.add(freeInterval);
		}
		return result;
	}

	public static void merge(Interval current, List<Interval> merged)
	{
		if (merged.size()==0)
		{
			merged.add(current);
		}
		else {
			Interval lastMerged = merged.get(merged.size()-1);
			if (lastMerged.end >= current.start)
			{
				lastMerged.end = Math.max(lastMerged.end,current.end);
				merged.set(merged.size()-1, lastMerged);
			}
			else
			{
				merged.add(current);
			}
		}
	}

	//Runtime: 49 ms, faster than 7.36% of Java online submissions
	public static void main(String ... args)
	{
		//[ [[43,76],[86,91]], [[17,20],[29,30]] ]

		List<List<Interval>> schedule = new ArrayList<>();
		List<Interval> inner = new ArrayList<>();
		Interval interval = new Interval(43, 76);
		inner.add(interval);
		interval = new Interval(86, 91);
		inner.add(interval);
		schedule.add(inner);

		inner = new ArrayList<>();
		interval = new Interval(17, 20);
		inner.add(interval);
		interval = new Interval(29, 30);
		inner.add(interval);
		schedule.add(inner);


		//[20, 29]
		//[30, 43]
		//[76, 86]
		List<Interval> result = employeeFreeTime(schedule);
		result.stream().forEach(interval1 -> {
			System.out.println("[" + interval1.start +", " +interval1.end + "]");
		});

		System.out.println();

		//[[ [1,2],[5,6]], [[1,3]], [[4,10]]]
		schedule = new ArrayList<>();
		inner = new ArrayList<>();
		interval = new Interval(1, 2);
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

		//[3, 4]
		result = employeeFreeTime(schedule);
		result.stream().forEach(interval1 -> {
			System.out.println("[" + interval1.start +", " +interval1.end + "]");
		});

		System.out.println();

		//[ [[1,3],[6,7]], [[2,4]], [[2,5],[9,12]] ]
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

		interval = new Interval(2, 5);
		inner.add(interval);
		interval = new Interval(9, 12);
		inner.add(interval);
		schedule.add(inner);

		//[5, 6]
		//[7, 9]
		result = employeeFreeTime(schedule);
		result.stream().forEach(interval1 -> {
			System.out.println("[" + interval1.start +", " +interval1.end + "]");
		});
	}
}
