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
public class MaxProfitInJobSchedulingRecursive
{
	public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		int maxProfit = 0;

		int n = startTime.length;
		List<JobScheduling> jobs = new ArrayList<>();
		for (int i=0; i < n; i++)
		{
			jobs.add(new JobScheduling(startTime[i], endTime[i], profit[i]));
		}
		//sort jobs based on start time
		jobs.sort(Comparator.comparingInt(j -> j.startTime));

		//choose first job
		return helper(jobs, 0, new ArrayList<>(), maxProfit);
	}

	/**
	 * A [1,3] = 50
	 * B [2,4] = 10
	 * C [3,5] = 40
	 * D [3,6] = 70
	 * Recursive:
	 *
	 * Include A: add A profit=50, and the rest of the slates is valid jobs with startTime >= A's endTime which
	 * are [C,D]
	 * Exclude A: slates contain [B,C,D], profit=0
	 *
	 * Time Complexity: O(2^n) because for each job we have 2 choices for every n (include or exclude)
	 * Binary Search: O(log n) -> O(n log n)
	 * Dominant: O(2^n)
	 */
	public int helper(List<JobScheduling> jobs, int index, List<JobScheduling> slates, int maxProfit)
	{
		//Base Case
		if (index == jobs.size()) return maxProfit;

		//do binary search instead of going through all the job list
		int validStartIndexForNextJob = findValidStartIndexForNextJob(jobs, jobs.get(index).endTime);

		//include the first job
		slates.add(jobs.get(index));
		int r1 = helper(jobs, validStartIndexForNextJob, slates, maxProfit + jobs.get(index).profit);
		slates.remove(jobs.get(index));

		//exclude the first job
		List<JobScheduling> rest = jobs.subList(index+1, jobs.size());
		slates.addAll(rest);
		int r2 = helper(jobs, index+1, slates, maxProfit);
		slates.removeAll(rest);

		//a = length of job
		//return max of (exclude i) or (profit[i] + valid job which startTime >= i's endTime)
		//return Max( (i+1, a) or profit[i] + R(j,a))
		return Math.max(r1, r2);
	}

	public int findValidStartIndexForNextJob(List<JobScheduling> jobs, int endTime)
	{
		int startIndex = 0, endIndex = jobs.size()-1, validStartIndexForNextJob = jobs.size();//if there is no valid index means we are at the end of the job
		while (startIndex <= endIndex)
		{
			int mid = startIndex + (endIndex - startIndex)/2;
			if (jobs.get(mid).startTime >= endTime)
			{
				validStartIndexForNextJob = mid;
				endIndex = mid - 1;
			}
			else
			{
				startIndex = mid+1;
			}
		}
		return validStartIndexForNextJob;
	}


	public class JobScheduling
	{
		int startTime;
		int endTime;
		int profit;

		public JobScheduling() {}

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
		MaxProfitInJobSchedulingRecursive max = new MaxProfitInJobSchedulingRecursive();
		int[] startTime = {2,3,1,3}; //{1,2,3,3}
		int[] endTime = {4,5,3,6}; //{3,4,5,6}
		int[] profit = {10,40,50,70}; //{50,10,40,70}
		int maxProfit = max.jobScheduling(startTime, endTime, profit);
		System.out.println(maxProfit); //120 [ [1,3],[3,6] ] = 50+70 = 120
		System.out.println();

		max = new MaxProfitInJobSchedulingRecursive();
		int[] startTime1 = {1,2,3,4,6};
		int[] endTime1 = {3,5,10,6,9};
		int[] profit1 = {20,20,100,70,60};
		int maxProfit1 = max.jobScheduling(startTime1, endTime1, profit1);
		System.out.println(maxProfit1); //150 [ [1,3],[4,6],[6,9] ] = 20+70+60 = 150
		System.out.println();

		max = new MaxProfitInJobSchedulingRecursive();
		int[] startTime2 = {2,3,5,7};
		int[] endTime2 = {6,5,10,11};
		int[] profit2 = {5,2,4,1};
		int maxProfit2 = max.jobScheduling(startTime2, endTime2, profit2);
		System.out.println(maxProfit2); //6 [ [2,6],[5,10] ] = 2+4 = 6
		System.out.println();
	}
}
