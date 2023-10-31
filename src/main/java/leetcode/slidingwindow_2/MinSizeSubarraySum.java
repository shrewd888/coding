package leetcode.slidingwindow_2;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * 209. Minimum Size Subarray Sum
 *
 * Given an array of POSITIVE integers nums and a positive integer target, return the minimal length of a contiguous subarray
 * [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 *
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 *
 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 */
public class MinSizeSubarraySum
{
	//TODO: revisit, not pass all test cases
	public static int minSubArrayLen(int target, int[] nums) {
		int sum = 0;
		int left = 0;
		//find prefix sum
		for (int i=0; i < nums.length; i++)
		{
			sum += nums[i];
			if (sum >= target)
			{
				left = i;
				break;
			}
		}
		int globalmin = left > 0 ? left + 1 : 0; //length of subarray
		int leftIndexToMove = 0;
		for (int i=left+1; i < nums.length; i++)
		{
			sum = sum + nums[i]; //must be > target
			//sum = sum - nums[leftIndexToMove];
			while (sum >= target)
			{
				sum = sum - nums[leftIndexToMove];
				int length = i - leftIndexToMove + 1;
				globalmin = Math.min(globalmin, length); //because local start with 1
				leftIndexToMove++;
				//sum = sum - nums[leftIndexToMove];
			}
			//leftIndexToMove = 0;
		}
		return globalmin;
	}

	/**
	 * T(n) = O(n)
	 * S(n) = O(1)
	 */
	public static int minSubArrayLen_Omkar(int target, int[] nums) {
		int windowSum = 0;
		int globalMin = Integer.MAX_VALUE;
		int left = 0;

		for (int i=0; i < nums.length; i++)
		{
			//Work to be done for subarray ending at index i
			windowSum += nums[i];
			while (left <= i && windowSum >= target)
			{
				windowSum -= nums[left];
				globalMin = Math.min(globalMin, i-left + 1);
				left++;
			}
		}
		if (globalMin == Integer.MAX_VALUE) return 0;
		return globalMin;
	}

	public static void main(String ... args)
	{
		int[] nums = {2,3,1,2,4,3};
		int k = 7;
		System.out.println(minSubArrayLen_Omkar(k, nums));//2

		int[] nums1 = {1,4,4};
		int k1 = 4;
		System.out.println(minSubArrayLen_Omkar(k1, nums1));//1

		int[] nums2 = {1,1,1,1,1,1,1,1};
		int k2 = 11;
		System.out.println(minSubArrayLen_Omkar(k2, nums2));//0

		int[] nums3 = {1,2,3,4,5};
		int k3 = 11;
		System.out.println(minSubArrayLen_Omkar(k3, nums3));//3

		int[] nums4 = {2,3,1,2,4,3,7};
		int k4 = 7;
		System.out.println(minSubArrayLen_Omkar(k4, nums4));//1
	}
}
