package leetcode.array.prefixsum;

/**
 * 560. Subarray Sum Equals K
 *
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 */

import java.util.HashMap;
import java.util.Map;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-video-210507-518318-247-1561-3843887
 * Omkar's Solution
 *
 * Brute force: Exhaustively compute sum of all subarrays
 * Decrease-and-Conquer:
 * Total #of subarrays ending at index n-1 which add up to k
 * Does it help to know how many subarray with sum=k ended at index i+1? If it helps this can be done in constant time.
 * Answer: No it does not help.
 * What could help is how many subarrays with sum = k - nums[i] ended at index i-1
 *
 * How many subarrays of the form nums[j...i] add up to k? where 0<=j<=i
 * We want to get the answer instantly.
 * nums[0...i] - nums[0...j-1] -> differences of two prefixes
 *
 * How many prefixes exists which add up to prefixsum(i) - k ?
 */
/**
 * T(n) = O(n)
 * S(n) = O(n)
 */
public class SubarraySumEqualsK
{
	public static int subarraySum(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int prefixsum = 0;
		int globalCount = 0;
		//empty prefix, there is one number add up to 0
		map.put(0, 1);

		/**
		 * Calculate prefixsum(i)
		 * Check if prefixsum(i) - k exists in the hashtable
		 * Compute my local answer
		 * Update hashtable with a new entry for nums[0...i]
		 */
		for (int i=0; i < nums.length; i++)
		{
			prefixsum = prefixsum + nums[i];

			int x = prefixsum - k;

			if (map.containsKey(x)) //my local answer
			{
				globalCount += map.get(x);
			}
			//Update hashmap
			if (map.containsKey(prefixsum))
			{
				map.put(prefixsum, map.get(prefixsum) + 1);
			}
			else
			{
				map.put(prefixsum, 1);
			}
		}
		return	globalCount;
	}

	public static void main (String[] args)
	{
		int[] nums = {1,1,1};
		int numOfPrefix = subarraySum(nums, 2);
		System.out.println(numOfPrefix); //2

		int[] nums1 = {1,2,3};
		int numOfPrefix1 = subarraySum(nums1, 3);
		System.out.println(numOfPrefix1); //2

		int[] nums2 = {1,2,0,3};
		int numOfPrefix2 = subarraySum(nums2, 6);
		System.out.println(numOfPrefix2); //1

		int[] nums3 = {1,2,0,3};
		int numOfPrefix3 = subarraySum(nums3, 5);
		System.out.println(numOfPrefix3); //1

		int[] nums31 = {1,2,0,3};
		int numOfPrefix31 = subarraySum(nums31, 3);
		System.out.println(numOfPrefix31); //4 -> [[1,2,0], [1,2], [0,3], [3]]

		int[] nums4 = {1, -3, 2, 5, 7, 1};
		int numOfPrefix4 = subarraySum(nums4, 7);
		System.out.println(numOfPrefix4); //2 -> [[2,5], [7]]
	}
}
