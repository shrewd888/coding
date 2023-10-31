package interview.doordash;

import java.util.Arrays;

/**
 *
 * https://leetcode.com/discuss/interview-question/1771653/Door-Dash-Technical-Round
 * https://leetcode.com/discuss/interview-question/2003115/Door-Dash-Onsite-or-E4-or-Software-Engineer-Streaming-and-Processing
 *
 * 826. Most Profit Assigning Work
 *
 * You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:
 *
 * difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
 * worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at most worker[j]).
 * Every worker can be assigned at most one job, but one job can be completed multiple times.
 *
 * For example, if three workers attempt the same job that pays $1, then the total profit will be $3.
 * If a worker cannot complete any job, their profit is $0.
 * Return the maximum profit we can achieve after assigning the workers to the jobs.
 *
 * Example 1:
 * Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * Output: 100
 * Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get a profit of [20,20,30,30] separately.
 *
 * Example 2:
 * Input: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
 * Output: 0
 */
public class MostProfitAssigningDifficultyWork
{
	//sort difficulty level
	//sort worker ability based on difficulty
	public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker)
	{
		//check null or empty inputs!

		int l = difficulty.length;
		Job[] jobs = new Job[l];
		for (int i=0; i<l; i++)
		{
			jobs[i] = new Job(difficulty[i], profit[i]);
		}
		Arrays.sort(jobs, (a,b) -> a.difficulty - b.difficulty);
		Arrays.sort(worker);

		int d = 0;
		int max = 0;
		int total = 0;
		for (int i=0; i < worker.length; i++)
		{
			int workerDifficulty = worker[i];
			while (d < l && workerDifficulty >= jobs[d].difficulty)
			{
				max = Math.max(max, jobs[d].profit);
				d++;
			}
			total = total + max;
		}
		return total;
	}

	public static class Job
	{
		int difficulty;
		int profit;

		public Job() {};

		public Job(int difficulty, int profit)
		{
			this.difficulty = difficulty;
			this.profit = profit;
		}
	}

	public static void main(String ... args)
	{
		int[] difficulty = new int[] {2,4,6,8,10};
		int[] profit = new int[] {10,20,30,40,50};
		int[] worker = new int[] {4,5,6,7};
		int maxprofit = maxProfitAssignment(difficulty, profit, worker);
		System.out.println(maxprofit); //100

		int[] difficulty1 = new int[] {85,47,57};
		int[] profit1 = new int[] {24,66,99};
		int[] worker1 = new int[] {40,25,25};
		int maxprofit1 = maxProfitAssignment(difficulty1, profit1, worker1);
		System.out.println(maxprofit1); //0

	}
}
