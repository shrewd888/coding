package leetcode.array.prefixsum;

/**
 * https://leetcode.com/problems/continuous-subarray-sum/
 * 523. Continuous Subarray Sum
 * Given an integer array nums and an integer k, return true if nums has a continuous subarray
 * of size at least two whose elements sum up to a multiple of k (divisible by k), or false otherwise.
 *
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 *
 * Example 1:
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 *
 * Example 2:
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 *
 * Example 3:
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Omkar's Solution
 * Given a remainder value does there exist a prev prefix with a sum = some value in 0...k-1
 * if k=0:
 * Is there a subarray of size at least 2 which adds up to 0?
 */
//TODO: revisit, not fully understand
public class ContinuousSubarraySum
{
	public boolean checkSubarraySum(int[] nums, int k) {
		if (nums.length < 2) return false;
		//edge case: k=0
		if (k==0)
		{
			for (int i=0; i < nums.length-1; i++)
			{
				//length must be at least 2
				if (nums[i] == nums[i+1] && nums[i] == 0)
				{
					return true;
				}
			}
			return false;
		}
		Map<Integer, Boolean> map = new HashMap<>();
		//Given a value of 0, does there exist a prev prefix with a sum = 0? Yes
		map.put(0, true);

		int prefixSum = nums[0];
		for (int i=1; i<nums.length; i++)
		{
			int p = prefixSum; //want to keep size at least 2
			prefixSum = (prefixSum + nums[i]) % k;
			if (map.containsKey(prefixSum))
			{
				return true;
			}
			else
			{
				map.put(p % k, true);
			}
		}
		return false;
	}

}
