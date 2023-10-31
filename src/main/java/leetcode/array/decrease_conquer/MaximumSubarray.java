package leetcode.array.decrease_conquer;

/**
 * https://leetcode.com/problems/maximum-subarray/
 * 53. Maximum Subarray
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 *
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 */

/**
 * https://uplevel.interviewkickstart.com/resource/rc-video-210507-518318-247-1560-3843887
 * Omkar's solution
 * f(i) = Max subarray on a prefix nums[0..i] ending at index i
 * max (f(i-1) + nums[i], nums[i])
 *
 * T(n) = O(n)
 * S(n) = O(1)
 */
public class MaximumSubarray
{
	public static int maxSubArray(int[] nums) {

		int prefix = nums[0];
		int globalMax = nums[0];

		for (int i=1; i < nums.length; i++)
		{
			prefix = Math.max(prefix + nums[i], nums[i]);
			globalMax = Math.max(globalMax, prefix);
		}
		return globalMax;
	}

	public static void main (String[] args)
	{
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		int result = maxSubArray(nums);
		System.out.println(result); //6

		nums = new int[]{5,4,-1,7,8};
		result = maxSubArray(nums);
		System.out.println(result); //23

		nums = new int[]{1};
		result = maxSubArray(nums);
		System.out.println(result); //1
	}
}
