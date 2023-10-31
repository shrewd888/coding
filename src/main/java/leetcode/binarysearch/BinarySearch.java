package leetcode.binarysearch;

/**
 * https://leetcode.com/problems/binary-search/
 * 704. Binary Search
 *
 * Given an array of integers nums which is sorted in ascending order, and an integer target,
 * write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 *
 * Example 2:
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 */
/**
 * T(n) = O(log n)
 * S(n) = O(log n) recursive
 * S(n) = O(1) iterative
 */
public class BinarySearch
{
	public static int search(int[] nums, int target) {
		if (nums.length == 0) return -1;
		return helper(nums, target, 0, nums.length-1);
	}

	//Runtime 0 ms beats 100 %
	private static int helper(int[] nums, int target, int start, int end)
	{
		int mid = start + (end-start)/2;
		while (start <= end)
		{
			if (target == nums[mid])
				return mid;
			else if (target < nums[mid])
			{
				return helper(nums, target, start, mid - 1);
			}
			else //target > nums[mid]
			{
				return helper(nums, target, mid + 1, end);
			}
		}
		return -1;
	}

	//Runtime 0 ms beats 100 %
	private static int helper_iterative(int[] nums, int target, int start, int end)
	{
		while (start <= end)
		{
			int mid = start + (end-start)/2;
			if (target == nums[mid])
			{
				return mid;
			}
			else if (target < nums[mid])
			{
				end = mid - 1;
			}
			else //target > nums[mid]
			{
				start = mid + 1;
			}
		}
		return -1;
	}

	public static void main(String ... args)
	{
		int[] nums = {-1,0,3,5,9,12};
		int target = 9;
		System.out.println(search(nums, target));//4

		int[] nums1 = {-1,0,3,5,9,12};
		int target1 = 2;
		System.out.println(search(nums1, target1));//-1
	}
}
