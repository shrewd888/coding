package leetcode.array.prefixsum;

/**
 * https://leetcode.com/problems/contiguous-array/
 * 525. Contiguous Array
 *
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 * Example 1:
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
 *
 * Example 2:
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Omkar's Solution
 * Given: A certain amount of imbalance, how many prefixes have it
 * Track #of 1s minus #of 0s
 * Hash table will take the value of #of 1s - #of 0s:
 * prefixexcess = #of 1s - #of 0s
 *
 * Everytime I see a 0, the value of (#of 1s - #of 0s) decreases by 1
 * Everytime I see a 1, the value of (#of 1s - #of 0s) increases by 1
 * For empty prefix the value of (#of 1s - #of 0s) = 0
 */

/**
 * T(n) = O(n)
 * S(n) = O(1)
 */
//TODO: revisit to understand better the logic
public class ContiguousArray
{
	public static int findMaxLength(int[] nums) {
		//Given an excess, smallest length of previous prefix having it
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0,0);

		int prefixexcess = 0;
		int globalmax = 0;

		for (int i=0; i<nums.length; i++)
		{
			if (nums[i] == 1)
			{
				prefixexcess += 1;
			}
			else
			{
				prefixexcess -= 1;
			}
			//map.get(prefixexcess) is the shortest prev prefix
			if (map.containsKey(prefixexcess))
			{
				int longestLength = (i+1) - map.get(prefixexcess);
				globalmax  = Math.max(globalmax, longestLength);
			}
			else
			{
				map.put(prefixexcess, i+1);
			}
		}
		return globalmax;
	}

	public static void main(String ... args)
	{
		int[] nums = {0,1,1,1,0,0,1};
		System.out.println(findMaxLength(nums));//6

		int[] nums1 = {0,1,0};
		System.out.println(findMaxLength(nums1));//2
	}
}
