package leetcode.slidingwindow_3;

/**
 * https://leetcode.com/problems/maximum-erasure-value/
 * 1695. Maximum Erasure Value
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements.
 * The score you get by erasing the subarray is equal to the sum of its elements.
 *
 * Return the maximum score you can get by erasing exactly one subarray.
 *
 * An array b is called to be a subarray of a if it forms a contiguous subsequence of a,
 * that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
 *
 * Example 1:
 * Input: nums = [4,2,4,5,6]
 * Output: 17
 * Explanation: The optimal subarray here is [2,4,5,6].
 *
 * Example 2:
 * Input: nums = [5,2,1,2,5,2,1,2,5]
 * Output: 8
 * Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Omkar's Solution
 * Find max sub array sum ending at index i with no repeating elements
 * T(n) = O(n)
 * S(n) = O(n)
 */
public class MaximumErasureValue
{
	//Runtime 262 ms - 328 ms beats 5 %
	public static int maximumUniqueSubarray(int[] nums) {
		int left = 0;
		int windowSum = 0;
		int globalMax = 0;
		Map<Integer, Integer> numMap = new HashMap<>();

		for (int i=0; i<nums.length; i++)
		{
			windowSum += nums[i];
			numMap.put(nums[i], numMap.getOrDefault(nums[i],0)+1);

			while (left <= i && numMap.get(nums[i]) > 1)
			{
				windowSum -= nums[left];
				numMap.put(nums[left], numMap.get(nums[left]) - 1);
				if (numMap.get(nums[left])== 0)
				{
					numMap.remove(nums[left]);
				}
				left++;
			}
			globalMax = Math.max(globalMax, windowSum);
		}
		return globalMax;
	}

	public static void main(String ... args)
	{
		int[] nums = {4,2,4,5,6};
		System.out.println(maximumUniqueSubarray(nums));//17

		int[] nums1 = {5,2,1,2,5,2,1,2,5};
		System.out.println(maximumUniqueSubarray(nums1));//8
	}
}
