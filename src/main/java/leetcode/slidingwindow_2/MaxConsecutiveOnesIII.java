package leetcode.slidingwindow_2;

/**
 * 1004. Max Consecutive Ones III
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array
 * if you can flip at most k 0's.
 *
 * Example 1:
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 * Example 2:
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 */

/**
 * Omkar's Solution:
 * T(n) = O(n)
 * S(n) = O(1)
 */
public class MaxConsecutiveOnesIII
{
	public static int longestOnes(int[] nums, int k) {
		int left = 0;
		int windowzeroes = 0;
		int globalmax = 0;
		for (int i=0; i<nums.length; i++)
		{
			//work to be done to solve local problem ending at index i
			//include nums[i] into the window
			if (nums[i] == 0)
				windowzeroes += 1;

			while (left <= i && windowzeroes > k)
			{
				if (nums[left] == 0)
					windowzeroes--;
				left++;
			}
			if (i-left+1 > globalmax)
				globalmax = i-left+1;
		}
		return globalmax;
	}

	public static void main(String ... args)
	{
		int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
		int k = 2;
		System.out.println(longestOnes(nums, k)); //6

		int[] nums1 = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
		int k1 = 3;
		System.out.println(longestOnes(nums1, k1)); //10
	}
}
