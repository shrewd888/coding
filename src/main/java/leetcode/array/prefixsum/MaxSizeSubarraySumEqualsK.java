package leetcode.array.prefixsum;

/**
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/description/
 * 325. Maximum Size Subarray Sum Equals k
 *
 * Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k.
 * If there is not one, return 0 instead.

 * Example 1:
 * Input: nums = [1,-1,5,-2,3], k = 3
 * Output: 4
 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 *
 * Example 2:
 * Input: nums = [-2,-1,2,1], k = 1
 * Output: 2
 * Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Omkar's Solution:
 * Given any value, what is the smallest prefix adding up to that value
 */
public class MaxSizeSubarraySumEqualsK
{
	public static int maxSubArrayLen(int[] nums, int k)
	{
		Map<Integer, Integer> map = new HashMap<>();
		int prefixsum = 0;
		int globalMax = 0;
		//empty prefix, the length is 0
		map.put(0, 0);

		for (int i = 0; i < nums.length; i++)
		{
			prefixsum = prefixsum + nums[i];
			//find the smallest length prefix adding up to prefixsum - k
			int x = prefixsum - k;
			if (map.containsKey(x)) //my local answer
			{
				int length = (i + 1) - map.get(x); //my local answer
				globalMax = Math.max(globalMax, length);
			}
			//if map already contains prefixsum that means there is already exists prefix with the shortest length
			if (!map.containsKey(prefixsum))
			{
				map.put(prefixsum, i + 1);
			}
		}
		return globalMax;
	}

	public static void main(String... args)
	{
		int[] nums = { 1, -1, 5, -2, 3 };
		int k = 3;
		System.out.println(maxSubArrayLen(nums, k)); //4

	}
}