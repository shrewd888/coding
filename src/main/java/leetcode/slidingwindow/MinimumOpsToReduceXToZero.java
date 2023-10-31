package leetcode.slidingwindow;

/**
 * https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
 *
 * 1658. Minimum Operations to Reduce X to Zero
 *
 * You are given an integer array nums and an integer x. In one operation, you can either
 * remove the leftmost or the rightmost element from the array nums and subtract its value from x.
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
 * Explanation: The optimal solution is to remove the last three elements
 * and the first two elements (5 operations in total) to reduce x to zero.
 */
/**
 * Omkar Array Floater 5 - Sliding Windows
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MinimumOpsToReduceXToZero
{
	/**
	 * Find the max / longest subarray of nums such that sum of all elements in
	 * the subarray k = sum(nums) - x
	 */
	public int minOperations(int[] nums, int x) {
		int len = nums.length;
		if (len==0) return 0;

		int k = 0;
		for (int i=0; i < nums.length; i++)
		{
			k = k + nums[i];
		}
		//want to maximize the length of inner subarray that sum up to k, so to minimize left/right end
		k = k - x;
		int left = 0;
		int windowSum = 0;
		int globalMax = -1;
		//work to be done to solve the subproblem ending at index-i
		for (int i=0; i < nums.length; i++)
		{
			windowSum += nums[i];
			while (left <= i && windowSum > k)
			{
				windowSum -= nums[left];
				left++;
			}
			if (windowSum == k)
			{
				//the length of the inside window = i-left+1
				globalMax = Math.max(globalMax, i-left+1);
			}
		}
		if (globalMax == -1) return -1;
		else return (len - globalMax);//the length of the outside left+right or either left/right
	}
}
