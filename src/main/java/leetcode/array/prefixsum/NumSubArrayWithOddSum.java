package leetcode.array.prefixsum;

/**
 * https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/
 * 1524. Number of Sub-arrays With Odd Sum
 *
 * Given an array of integers arr, return the number of subarrays with an odd sum.
 *
 * Since the answer can be very large, return it modulo 109 + 7.
 *
 * Example 1:
 * Input: arr = [1,3,5]
 * Output: 4
 * Explanation: All subarrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
 * All sub-arrays sum are [1,4,9,3,8,5].
 * Odd sums are [1,9,3,5] so the answer is 4.
 *
 * Example 2:
 * Input: arr = [2,4,6]
 * Output: 0
 * Explanation: All subarrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
 * All sub-arrays sum are [2,6,12,4,10,6].
 * All sub-arrays have even sum and the answer is 0.
 *
 * Example 3:
 * Input: arr = [1,2,3,4,5,6,7]
 * Output: 16
 */

import java.util.HashMap;
import java.util.Map;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-video-334886-623013-247-1561
 * Arrays : Prefix Sum
 *
 * Omkar's Solution
 * prefix = arr[0...j-1] to arr[j...i]
 * if prefix is even: how many prev prefix arr[0...j-1] with an odd sum? -> so that total will be odd
 * if prefix is odd: how many prev prefix arr[0...j-1] with an even sum? -> so that total will be odd
 */
//TODO: Revisit to understand
public class NumSubArrayWithOddSum
{
	//Runtime 92 ms Beats 7.8%
	public static int numOfSubarrays(int[] arr) {
		Map<String, Integer> map = new HashMap<>();
		int prefixsum = 0;
		int total = 0;

		//empty prefix has sum=0, and 0 is even
		map.put("even", 1);
		map.put("odd", 0);

		for (int i=0; i < arr.length; i++)
		{
			prefixsum = prefixsum + arr[i];
			//to have odd sum: if prefix is even, find how many prev prefix that
			//has odd sum
			if (prefixsum % 2 == 0)//even
			{
				total += map.get("odd"); //my local answer
				//map.put("even", map.get("even") + 1);
			}
			else //odd
			{
				total += map.get("even"); //my local answer
				//map.put("odd", map.get("odd") + 1);
			}
			total = total % 1000000007;
			//update hashmap with the latest prefix sum up to index i
			if (prefixsum % 2 == 0)
			{
				map.put("even", map.get("even") + 1);
			}
			else
			{
				map.put("odd", map.get("odd") + 1);
			}
		}
		return total;
	}

	public static void main (String[] args)
	{
		int[] nums = { 1,3,5 };
		System.out.println(numOfSubarrays(nums));//4
	}
}
