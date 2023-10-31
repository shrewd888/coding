package interview.doordash;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/discuss/interview-question/1320711/doordash-phone-screen
 *
 * You're a dasher, and you want to try planning out your schedule.
 * You can view a list of deliveries along with their associated start time, end time,
 * and dollar amount for completing the order. Assuming dashers can only deliver one order at a time,
 * determine the maximum amount of money you can make from the given deliveries.
 *
 * The inputs are as follows:
 *
 * int start_time: when you plan to start your schedule
 * int end_time: when you plan to end your schedule
 *
 * int d_starts[n]: the start times of each delivery[i]
 * int d_ends[n]: the end times of each delivery[i]
 * int d_pays[n]: the pay for each delivery[i]
 *
 * The output should be an integer representing the maximum amount of money you can make
 * by forming a schedule with the given deliveries.
 *
 * Example #1
 * start_time = 0
 * end_time = 10
 * d_starts = [2, 3, 5, 7]
 * d_ends = [6, 5, 10, 11]
 * d_pays = [5, 2, 4, 1]
 *
 * Expected output: 6
 */

/**
 * Time Complexity: O(n log n)
 * Sort schedule based on start time -> O(n log n)
 * Iterate all deliveries. For each delivery, add to Q -> O(n log n)
 *
 * Space Complexity: O(n) -> input space.
 * No auxiliary space
 */

/***
 * When you discard a chain from the priority queue, it means all future deliveries will also be compatible
 * (non-conflicting) with that chain.
 * This is because we are iterating deliveries sorted by start-time and at each iteration
 * we discard all chains that end before the current delivery starts.
 * In other words, we discard all the chains that are compatible with the current delivery.
 * Since all future deliveries have a greater start-time, all the chains we discarded will be compatible
 * with those future deliveries too.
 * Since we are optimizing, we only care about the maxProfit of any discarded chain
 * (we don't care which chain, just the maxProfit since they all are and always will be compatible).
 *
 * Essentially, all the valuable information from the chains that have been discarded
 * from the priority queue is distilled into the one variable 'maxAmount'.
 */
public class MaxProfitInJobSchedulingDoorDash
{
	public static int maxAmountFromGivenDeliveries(int planStartTime, int planEndTime, int[] startTime, int[] endTime, int[] pays) {

		int n = startTime.length;
		List<DeliveryScheduling> schedules = new ArrayList<>();
		for (int i=0; i < n; i++)
		{
			schedules.add(new DeliveryScheduling(startTime[i], endTime[i], pays[i]));
		}
		//sort schedule based on start time -> O(n log n)
		schedules.sort(Comparator.comparingInt(s -> s.startTime));

		//Min Queue (min heap)
		//when putting delivery schedule in the queue, sort by endTime ASC
		Queue<DeliveryScheduling> minQ = new PriorityQueue<>((i1, i2) -> Integer.compare(i1.endTime, i2.endTime));

		int maxAmount = 0;

		for (DeliveryScheduling schedule : schedules)
		{
			int start = schedule.startTime;
			int end = schedule.endTime;
			//only want to start at a specific startTime, and end at a specific endTime
			if (start < planStartTime || end > planEndTime) continue;

			/**
			 * because job is sorted by startTime and minHeap is sorted by endTime,
			 * next job in the loop, its' startTime is closer to the endTime in minHeap
			 * whatever left in the minHeap, possibly a chain to the job (from loop) or by itself
			 */
			while (!minQ.isEmpty() && start >= minQ.peek().endTime)
			{
				maxAmount = Math.max(maxAmount, minQ.peek().pay);
				minQ.remove();
			}

			//update the job's profit to retain max profit
			schedule.pay = maxAmount + schedule.pay;
			minQ.add(schedule);
		}
		while (!minQ.isEmpty())
		{
			maxAmount = Math.max(maxAmount, minQ.peek().pay);
			minQ.remove();
		}
		return maxAmount;
	}

	public static class DeliveryScheduling
	{
		int startTime;
		int endTime;
		int pay;

		public DeliveryScheduling() {};

		public DeliveryScheduling(int startTime, int endTime, int pay)
		{
			this.startTime = startTime;
			this.endTime = endTime;
			this.pay = pay;
		}
	}

	public static void main(String ... args)
	{
		int planStartTime = 0;
		int planEndTime = 10;
		int[] startTime = { 2, 3, 5, 7 };
		int[] endTime = { 6, 5, 10, 11 };
		int[] pays = { 5, 2, 4, 1 };
		int maxAmount = maxAmountFromGivenDeliveries(planStartTime, planEndTime, startTime, endTime, pays);
		System.out.println(maxAmount); //6
	}

}
