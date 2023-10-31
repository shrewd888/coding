package interviewkickstart.dynamicprogramming;

import java.util.ArrayList;

/**
 * Count Ways To Reach The Nth Step
 *
 * There is a staircase with n steps. A person standing at the 0-th step wants to reach the n-th one.
 * They are capable of jumping up by certain numbers of steps at a time.
 *
 * Given how the person can jump, count the number of ways they can reach the top.
 *
 * Example One
 * {
 * "steps": [1, 2],
 * "n": 1
 * }
 * Output:
 * 1
 * The person can jump up by either 1 or 2 steps at a time.
 * The only way to reach step 1 from step 0 is to jump up one step once.
 *
 * Example Two
 * {
 * "steps": [1, 2],
 * "n": 2
 * }
 * Output:
 * 2
 * There are two distinct ways to reach step 2: {1, 1}, {2}.
 *
 * Example Three
 * {
 * "steps": [2, 3],
 * "n": 7
 * }
 * Output:
 * 3
 * There are three distinct ways to reach step 7 from step 0: {2, 2, 3}, {2, 3, 2}, {3, 2, 2}.
 */
public class CountWaysToReachNSteps
{
	static Long count_ways_to_climb(ArrayList<Integer> steps, Integer n) {
		//initialize to 0
		long[] table = new long[n+1];
		//Base Case
		table[0] = 1;

		for (int i = 1; i <= n; i++)
		{
			for (int step : steps)
			{
				//if (i==step) table[i] = 1;
				int prevStep = i-step; // n = 2 -> 1-1 = 0, 1-2 = -1
				if (prevStep >= 0)
				{
					table[i] = table[i] + table[prevStep]; //table[1] + table[0] = 0+1 = 1
				}
			}
		}
		return table[n];
	}

	public static void main(String ... args) {
		ArrayList<Integer> steps = new ArrayList<>();
		steps.add(1);
		steps.add(2);
		Long result = count_ways_to_climb(steps, 1);
		System.out.println(result);

		steps = new ArrayList<>();
		steps.add(1);
		steps.add(2);
		result = count_ways_to_climb(steps, 2);
		System.out.println(result);

		steps = new ArrayList<>();
		steps.add(2);
		steps.add(3);
		result = count_ways_to_climb(steps, 7);
		System.out.println(result);
	}
}
