package leetcode.slidingwindow_2;

/**
 * 1658. Minimum Operations to Reduce X to Zero
 *
 * You are given an integer array nums and an integer x.
 * In one operation, you can either remove the leftmost or the rightmost element
 * from the array nums and subtract its value from x.
 * Note that this modifies the array for future operations.
 *
 * Return the minimum number of operations to reduce x to exactly 0 if it is possible,
 * otherwise, return -1.
 *
 * Example 1:
 * Input: nums = [1,1,4,2,3], x = 5
 * Output: 2
 * Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
 *
 * Example 2:
 * Input: nums = [5,6,7,8,9], x = 4
 * Output: -1
 *
 * Example 3:
 * Input: nums = [3,2,20,1,1,3], x = 10
 * Output: 5
 * Explanation: The optimal solution is to remove the last three elements and the
 * first two elements (5 operations in total) to reduce x to zero.
 */

import java.util.Arrays;

/**
 * Omkar's Solution:
 * Find the longest subarray of nums such that sum of all elements in
 * the subarray = (sum(nums) - x)/k
 */
public class MinOpsToReduceXToZero
{
	static int left = 0;
	static int windowsum = 0;
	static int globalmax = -1;//it is possible all nums sum up to x
	//k = sum(nums) - x
	//find maximum length of array that sum-up to k, so that we can remove min #of left or right elements
	public static int minOperations(int[] nums, int x) {

		int sum=0;
		for (int i=0; i < nums.length; i++)
		{
			sum = sum + nums[i];
		}
		int k = sum - x;
		for (int i=0; i < nums.length; i++)
		{
			//work to be done to solve the subproblem ending at index i
			windowsum += nums[i];
			while (left <= i && windowsum > k)
			{
				windowsum -= nums[left];
				left++;
			}
			if (windowsum == k)
			{
				globalmax = Math.max(globalmax, i-left+1);//maximum length of subarray
			}
		}
		if (globalmax == -1)
			return -1;
		else
			return nums.length - globalmax;
	}
}
