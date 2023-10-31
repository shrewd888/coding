package leetcode.slidingwindow_3;

/**
 * https://leetcode.com/problems/subarrays-with-k-different-integers/
 * 992. Subarrays with K Different Integers
 *
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 * A good array is an array where the number of different integers in that array is exactly k.
 *
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers:
 * [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 *
 * Example 2:
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Omkar's Solution
 * https://uplevel.interviewkickstart.com/resource/rc-video-431459-755812-247-1564-4091441
 *
 * Tracking 2 separate windows
 * Calculate the #of subarrays ending at index i which are good
 * Global answer: Sum of local answer
 *
 * See 713. Subarray Product Less Than K - how to calculate #of sub array
 * if only 1 window: (i-left+1)
 * if 2 window :  (i-left+1) - (left-leftgreater)
 */
public class SubarraysWithKDifferentIntegers
{
	public static int subarraysWithKDistinct(int[] nums, int k) {
		int globalCount = 0;
		int left = 0;
		//this is the left most pointer
		int leftgreater = 0;

		Map<Integer, Integer> elementMap = new HashMap<>();
		Map<Integer, Integer> elementGreaterMap = new HashMap<>();

		for (int i=0; i < nums.length; i++)
		{
			elementMap.put(nums[i], elementMap.getOrDefault(nums[i],0)+1);

			while (left <=i && elementMap.size() >= k)
			{
				elementMap.put(nums[left], elementMap.get(nums[left]) - 1);
				if (elementMap.get(nums[left])==0)
				{
					elementMap.remove(nums[left]);
				}
				left++;
			}

			elementGreaterMap.put(nums[i], elementGreaterMap.getOrDefault(nums[i],0)+1);

			while (leftgreater <=i && elementGreaterMap.size() > k)// elementGreaterMap.size() = k + 1
			{
				elementGreaterMap.put(nums[leftgreater], elementGreaterMap.get(nums[leftgreater]) - 1);
				if (elementGreaterMap.get(nums[leftgreater])==0)
				{
					elementGreaterMap.remove(nums[leftgreater]);
				}
				leftgreater++;
			}
			globalCount = globalCount + (left - leftgreater);
		}

		return globalCount;
	}

	public static void main(String ... args)
	{
		int[] nums = {1,2,1,2,3};
		int k = 2;
		System.out.println(subarraysWithKDistinct(nums, k));//7

		int[] nums1 = {1,2,1,3,4};
		int k1 = 3;
		System.out.println(subarraysWithKDistinct(nums1, k1));//3
	}
}
