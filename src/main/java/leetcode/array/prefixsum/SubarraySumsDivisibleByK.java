package leetcode.array.prefixsum;

/**
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * 974. Subarray Sums Divisible by K
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 *
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [4,5,0,-2,-3,1], k = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by k = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 * Example 2:
 * Input: nums = [5], k = 9
 * Output: 0
 */
/* Also look at:
 * 523. Continuous Subarray Sum
*/
import java.util.HashMap;
import java.util.Map;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-video-210507-518318-247-1561-3843887
 * Omkar's Solution
 *
 * A - B = nk
 * A - nk = B
 * remainder of (A/k) should be = remainder of (B/k)
 * Given a number in a mod k system, how many prefixes add up to (p mod k) ?
 *
 * T(n) = O(n)
 * S(n) = O(n)
 */
//TODO: Revisit to understand better
public class SubarraySumsDivisibleByK
{
	public static int subarraysDivByK(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0,1);
		int prefixsum = 0;
		int total = 0;

		for (int i=0; i < nums.length; i++)
		{
			prefixsum = (prefixsum + nums[i]);
			int x = prefixsum % k;
			if (x < 0)
			{
				x = x + k; //Because -1 % 5 = -1, but we need the positive mod 4
			}

			//prefixsum = (prefixsum + nums[i]) % k;
			//How many previous prefixes add up to prefix sum?
			//if prefixsum exist in hmap, then hmap return the count -> local answer
			if (map.containsKey(x))
			{
				total = total + map.get(x); //this is my local answer
				map.put(x, map.get(x) + 1);
			}
			else
			{
				map.put(x, 1);
			}
		}
		return total;
	}

	public static void main (String[] args)
	{
		int[] nums = {4,5,0,-2,-3,1};
		int k = 5;
		System.out.println(subarraysDivByK(nums, k));

		int[] nums1 = {-1,2,9};
		int k1 = 2;
		System.out.println(subarraysDivByK(nums1, k1));
	}
}
