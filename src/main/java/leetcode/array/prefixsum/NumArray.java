package leetcode.array.prefixsum;

/**
 * https://leetcode.com/problems/range-sum-query-immutable/description/
 * 303. Range Sum Query - Immutable
 *
 * Given an integer array nums, handle multiple queries of the following type:
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 *
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * int sumRange(int left, int right) Returns the sum of the elements of nums
 * between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 *
 * Example 1:
 * 	Input
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * 		[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 	Output
 * [null, 1, -1, -3]
 *
 * Explanation
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
 * numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
 * numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
 */

/**
 * Omkar's Solution
 * return prefixsum[j] - prefixsum[i-1]
 *
 * T(n) = O(1)
 * S(n) = O(n)
 */
public class NumArray
{
	int nums[];
	public NumArray(int[] nums) {
		this.nums = nums;
		//precompute the sum
		for (int i=1; i<nums.length; i++)
		{
			nums[i] = nums[i] + nums[i-1];
		}
	}

	public int sumRangeEfficient(int left, int right) {
		if (left == 0)
		{
			return nums[right];
		}
		return nums[right] - nums[left-1];//minus previous element from left, because this element is already sum of its previous elements
	}

	public int sumRange(int left, int right) {
		int sum = 0;
		while (left <= right)
		{
			sum = sum + nums[left];
			left++;
		}
		return sum;
	}
}
