package interview.doordash;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
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
 */

/**
 * Time Complexity: # of state * time/state = n jobs * n = O(n^2) -> Dominant
 * Time Complexity Use Binary Search = n jobs * log n = O(n log n)
 * Space Complexity: dimension of the space : 1 dimension because of 1 param which is f(i) -> i=n -> length of the table array
 */
public class MaxProfitInJobSchedulingDP
{
	private int[] table;
	private List<JobScheduling> jobs = new ArrayList<>();

	public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		int n = startTime.length;
		//List<JobScheduling> jobs = new ArrayList<>();
		for (int i=0; i < n; i++)
		{
			jobs.add(new JobScheduling(startTime[i], endTime[i], profit[i]));
		}
		//sort jobs based on start time
		jobs.sort(Comparator.comparingInt(j -> j.startTime));

		table = new int[n+1];

		return helper(jobs, table);
	}

	/**
	 * f(i) = Max of (P[i] + f(j), f(i+1))
	 * j = next job which starttime is <= i's endtime
	 */
	public int helper(List<JobScheduling> jobs, int[] table)
	{
		//size = 3, table[0], table[1], table[2], table[3]=0
		int sz = jobs.size();
		int currentProfit = 0;
		//Base Case
		table[sz] = 0;

		for(int i=sz-1; i >= 0; i--)
		{
			int endTime = jobs.get(i).endTime;
			int validStartIndexForNextJob = findValidStartIndexForNextJob(jobs, endTime);
			if (validStartIndexForNextJob != sz)
			{
				currentProfit = jobs.get(i).profit + table[validStartIndexForNextJob];
			}
			else
			{
				currentProfit = jobs.get(i).profit;
			}
			if (i == sz-1)
			{
				table[i] = currentProfit;
			}
			else
			{
				table[i] = Math.max(currentProfit, table[i+1]);
			}
		}
		return table[0];
	}

	public List<JobScheduling> trackMaxProfit()
	{
		int i=0;
		int sz = jobs.size();
		List<JobScheduling> track = new ArrayList<>();
		while (i < table.length-1)
		{
			int x = jobs.get(i).profit;
			int y = 0;
			int j = findValidStartIndexForNextJob(jobs, jobs.get(i).endTime);
			if (j == sz) //not found
			{
				y = 0;
			}
			else
			{
				y = table[j];
			}
			//max of (exclude i) or (profit[i] + valid job which startTime >= i's endTime)
			if ((x+y) >= table[i+1])
			{
				track.add(jobs.get(i));
				i = j;
			}
			else
			{
				i = i + 1;
			}
		}
		return track;
	}


	public int findValidStartIndexForNextJob(List<JobScheduling> jobs, int endTime)
	{
		int startIndex = 0, endIndex = jobs.size()-1, validStartIndexForNextJob = jobs.size();//if there is no valid index means we are at the end of the job
		while (startIndex <= endIndex)
		{
			int mid = startIndex + (endIndex - startIndex)/2;
			if (jobs.get(mid).startTime >= endTime) //find the next job which startTime is >= endTime of currentJob so that it won't overlap
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

		public JobScheduling()
		{
		}

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
			MaxProfitInJobSchedulingRecursive.JobScheduling that = (MaxProfitInJobSchedulingRecursive.JobScheduling) o;
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
		MaxProfitInJobSchedulingDP max = new MaxProfitInJobSchedulingDP();
		int[] startTime = {2,3,1,3}; //{1,2,3,3}
		int[] endTime = {4,5,3,6}; //{3,4,5,6}
		int[] profit = {10,40,50,70}; //{50,10,40,70}
		int maxProfit = max.jobScheduling(startTime, endTime, profit);
		System.out.println(maxProfit); //120 [ [1,3],[3,6] ] = 50+70 = 120
		System.out.println(max.trackMaxProfit());
		System.out.println();

		max = new MaxProfitInJobSchedulingDP();
		int[] startTime1 = {1,2,3,4,6};
		int[] endTime1 = {3,5,10,6,9};
		int[] profit1 = {20,20,100,70,60};
		int maxProfit1 = max.jobScheduling(startTime1, endTime1, profit1);
		System.out.println(maxProfit1); //150 [ [1,3],[4,6],[6,9] ] = 20+70+60 = 150
		System.out.println(max.trackMaxProfit());
		System.out.println();

		max = new MaxProfitInJobSchedulingDP();
		int[] startTime2 = {2,3,5,7};
		int[] endTime2 = {6,5,10,11};
		int[] profit2 = {5,2,4,1};
		int maxProfit2 = max.jobScheduling(startTime2, endTime2, profit2);
		System.out.println(maxProfit2); //6 [ [2,6],[7,11] ] = 5+1 = 6 or [ [3,5],[5,10] ] = 2+4 = 6
		System.out.println(max.trackMaxProfit());
		System.out.println();
	}
}
