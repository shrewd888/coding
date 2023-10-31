package interview.doordash;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/discuss/interview-question/1320711/doordash-phone-screen
 **
 * https://leetcode.com/discuss/interview-question/2003115/Door-Dash-Onsite-or-E4-or-Software-Engineer-Streaming-and-Processing
 *
 *
 * 1235. Maximum Profit in Job Scheduling
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/
 *
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take
 * such that there are no two jobs in the subset with overlapping time range.
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 *
 * Example 1:
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 *
 * Example 2:
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 *
 * Example 3:
 * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * Output: 6
 *
 */
public class MaxProfitInJobScheduling
{
	public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		int maxProfit = 0;

		int n = startTime.length;
		List<JobScheduling> jobs = new ArrayList<>();
		for (int i=0; i < n; i++)
		{
			jobs.add(new JobScheduling(startTime[i], endTime[i], profit[i]));
		}
		//sort jobs based on start time
		jobs.sort(Comparator.comparingInt(j -> j.startTime));

		//same result
//		jobs = jobs.stream()
//				.sorted(Comparator.comparing(j -> j.startTime))
//		.collect(Collectors.toList());

		//when putting job in the queue, sort by end time
		Queue<JobScheduling> minHeap = new PriorityQueue<>((i1, i2) -> Integer.compare(i1.endTime, i2.endTime));

		List<JobScheduling> jobSchedulings = new ArrayList<>();
		//List<JobScheduling> possibleChains = new ArrayList<>();
		for (JobScheduling job : jobs)
		{
			int start = job.startTime;
			/**
			 * because job is sorted by startTime and minHeap is sorted by endTime,
			 * next job in the loop, its' startTime is closer to the endTime in minHeap
			 * whatever left in the minHeap, possibly a chain to the job (from loop) or by itself
			 *
			 *
			 *
			 * int[] startTime2 = {2,3,5,7};
			 * 	int[] endTime2 =  {6,5,10,11};
			 * 	int[] profit2 =  {5,2,4,1};
			 * 	int maxProfit2 = jobScheduling(startTime2, endTime2, profit2);
			 * 	System.out.println(maxProfit2); //6 [ [3,5],[5,10] ] = 2+4 = 6
			 */
			while (!minHeap.isEmpty() && start >= minHeap.peek().endTime)
			{
				//maxProfit = Math.max(maxProfit, minHeap.peek().profit);

				int minHeapProfit = minHeap.peek().profit;
				if (minHeapProfit > maxProfit)
				{
					maxProfit = minHeapProfit;
					jobSchedulings.clear();
					jobSchedulings.addAll(minHeap);
				}
				minHeap.remove();
				//JobScheduling jobScheduling = minHeap.remove(); //capture the jobScheduling here
				//jobSchedulings.add(jobScheduling); //this is the start of chain
				//jobSchedulings.add(job);//these must chain together
			}

			//update the job's profit to retain max profit
			job.profit = maxProfit + job.profit;
			minHeap.add(job);
		}

		while (!minHeap.isEmpty())
		{
			maxProfit = Math.max(maxProfit, minHeap.peek().profit);
			minHeap.remove();
		}

		return maxProfit;
	}

	public static class JobScheduling
	{
		int startTime;
		int endTime;
		int profit;

		public JobScheduling() {};

		public JobScheduling(int startTime, int endTime, int profit)
		{
			this.startTime = startTime;
			this.endTime = endTime;
			this.profit = profit;
		}

		@Override
		public String toString()
		{
			return "JobScheduling{" + "startTime=" + startTime + ", endTime=" + endTime
					+ ", profit=" + profit + '}';
		}

		@Override
		public boolean equals(Object o)
		{
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			JobScheduling that = (JobScheduling) o;
			return startTime == that.startTime && endTime == that.endTime && profit == that.profit;
		}

		@Override
		public int hashCode()
		{
			return Objects.hash(startTime, endTime, profit);
		}
	}

	public static void main(String ... args)
	{
//		int[] startTime = {2,3,1,3}; //{1,2,3,3}
//		int[] endTime = {4,5,3,6}; //{3,4,5,6}
//		int[] profit = {10,40,50,70}; //{50,10,40,70}
//		int maxProfit = jobScheduling(startTime, endTime, profit);
//		System.out.println(maxProfit); //120 [ [1,3],[3,6] ] = 50+70 = 120
//
//		int[] startTime1 = {1,2,3,4,6};
//		int[] endTime1 = {3,5,10,6,9};
//		int[] profit1 = {20,20,100,70,60};
//		int maxProfit1 = jobScheduling(startTime1, endTime1, profit1);
//		System.out.println(maxProfit1); //150 [ [1,3],[4,6],[6,9] ] = 20+70+60 = 150

		int[] startTime2 = {2,3,5,7};
		int[] endTime2 = {6,5,10,11};
		int[] profit2 = {5,2,4,1};
		int maxProfit2 = jobScheduling(startTime2, endTime2, profit2);
		System.out.println(maxProfit2); //6 [ [2,6],[5,10] ] = 2+4 = 6

	}
}
