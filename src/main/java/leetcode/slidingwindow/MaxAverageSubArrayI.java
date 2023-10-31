package leetcode.slidingwindow;

/**
 * 643. Maximum Average Subarray I
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
 * Any answer with a calculation error less than 10^(-5) will be accepted.
 *
 * Example 1:
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 *
 * Example 2:
 * Input: nums = [5], k = 1
 * Output: 5.00000
 */
/**
 * Time Complexity: O(n)
 */
public class MaxAverageSubArrayI
{
	//iterative, move from left to right
	/**
	 * Initialization:
	 * Current Avg = sum(nums[0...k-1])/k -> floating point division
	 * Global Maz = Current Avg
	 */
	public double findMaxAverage(int[] nums, int k) {
		int len = nums.length;
		if(len==0 || k > len) return 0.0;
		int windowSum = 0;
		for (int i=0; i<k; i++)
		{
			windowSum += nums[i];
		}
		double maxWindowSum = windowSum;
		for (int i=k; i<len; i++)
		{
			//nums[0] + nums[1] + nums[2] = x
			//nums[1] + nums[2] + nums[3] = y
			//nums[0] + nums[1] + nums[2] + nums[3] - nums[0] = x + (nums[3] - nums[0])
			windowSum += nums[i] - nums[i-k];
			maxWindowSum = Math.max(maxWindowSum, windowSum);
		}
		return (double) maxWindowSum/k;
	}

	//Runtime: 9 ms, faster than 40.98% of Java online submissions
	public static void main(String ... args)
	{

	}
}
