package leetcode.slidingwindow;

/**
 * 1176. Diet Plan Performance
 * A dieter consumes calories[i] calories on the i-th day.
 *
 * Given an integer k, for every consecutive sequence of k days (calories[i], calories[i+1], ..., calories[i+k-1]
 * for all 0 <= i <= n-k), they look at T, the total calories consumed during that sequence of k days
 * (calories[i] + calories[i+1] + ... + calories[i+k-1]):
 *
 * If T < lower, they performed poorly on their diet and lose 1 point;
 * If T > upper, they performed well on their diet and gain 1 point;
 * Otherwise, they performed normally and there is no change in points.
 * Initially, the dieter has zero points. Return the total number of points the dieter has after
 * dieting for calories.length days.
 *
 * Note that the total points can be negative.
 *
 * Example 1:
 * Input: calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
 * Output: 0
 * Explanation: Since k = 1, we consider each element of the array separately and compare it to lower and upper.
 * calories[0] and calories[1] are less than lower so 2 points are lost.
 * calories[3] and calories[4] are greater than upper so 2 points are gained.
 *
 * Example 2:
 * Input: calories = [3,2], k = 2, lower = 0, upper = 1
 * Output: 1
 * Explanation: Since k = 2, we consider subarrays of length 2.
 * calories[0] + calories[1] > upper so 1 point is gained.
 *
 * Example 3:
 * Input: calories = [6,5,0,0], k = 2, lower = 1, upper = 5
 * Output: 0
 * Explanation:
 * calories[0] + calories[1] > upper so 1 point is gained.
 * lower <= calories[1] + calories[2] <= upper so no change in points.
 * calories[2] + calories[3] < lower so 1 point is lost.
 */
public class DietPlanPerformance
{
	//this is wrong because in each 1 increment span element to k elements
	public static int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
		int totalPoint = 0;
		int index = 0;
		int length = calories.length;
		while (index < length)
		{
			int total = 0;
			int limit = index + k;
			while(index < limit && limit <= length)
			{
				total += calories[index];
				index++;
			}
			if (total < lower)
			{
				totalPoint -= 1;
			}
			else if (total > upper)
			{
				totalPoint += 1;
			}
		}
		return totalPoint;
	}

	//Omkar's Solution
	public static int dietPlanPerformance_efficient(int[] calories, int k, int lower, int upper) {
		int windowSum = 0;
		int point = 0;
		for (int i=0; i < k; i++)
		{
			windowSum += calories[i];
		}
		if (windowSum > upper)
		{
			point = 1;
		}
		else if (windowSum < lower)
		{
			point = -1;
		}

		for (int i=k; i<calories.length; i++)
		{
			windowSum += calories[i] - calories[i-k];
			if (windowSum > upper)
			{
				point++;
			}
			else if (windowSum < lower)
			{
				point--;
			}
		}
		return point;
	}

	public static void main(String ... args)
	{
		int[] calories = {1,2,3,4,5};
		int k = 1, lower = 3, upper = 3;
		System.out.println(dietPlanPerformance_efficient(calories, k, lower, upper));//0

		int[] calories1 = {3,2};
		int k1 = 2, lower1 = 0, upper1 = 1;
		System.out.println(dietPlanPerformance_efficient(calories1, k1, lower1, upper1));//1

		int[] calories2 = {6,5,0,0};
		int k2 = 2, lower2 = 1, upper2 = 5;
		System.out.println(dietPlanPerformance_efficient(calories2, k2, lower2, upper2));//0
	}
}
