package leetcode.binarysearch;

/**
 * https://leetcode.com/problems/missing-element-in-sorted-array/
 * 1060. Missing Element in Sorted Array
 *
 * Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an integer k,
 * return the kth missing number starting from the leftmost number of the array.
 *
 * Example 1:
 * Input: nums = [4,7,9,10], k = 1
 * Output: 5
 * Explanation: The first missing number is 5.
 *
 * Example 2:
 * Input: nums = [4,7,9,10], k = 3
 * Output: 8
 * Explanation: The missing numbers are [5,6,8,...], hence the third missing number is 8.
 *
 * Example 3:
 * Input: nums = [1,2,4], k = 3
 * Output: 6
 * Explanation: The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 */

/**
 * Omkar's Solution
 * Binary Search Variants Part 1
 *
 * Left zone: missing numbers < k
 * Mid zone: missing number = k
 * Right zone: missing number > k
 *
 * Or:
 * Left zone: missing number < k
 * Right zone: missing number >= k
 */
public class MissingElementInASortedArray
{
	//Runtime 0 ms beats 100 %
	public int missingElement(int[] nums, int k) {
		int start = 0;
		int end = nums.length - 1;
		int totalMissingNumbersOnLeft;
		while (start <= end)
		{
			int mid = start + (end-start)/2;
			//Calculate how many numbers are missing up to this point
			//nums[mid] - nums[0] are all the numbers beyond nums[0]
			//mid = how many of those numbers there actually are in the array
			//missing numbers = (nums[mid] - nums[0]) - mid
			totalMissingNumbersOnLeft = nums[mid] - (nums[0] + mid); //nums[0] + mid -> predictive number
			if (totalMissingNumbersOnLeft < k)
			{
				start = mid + 1;
			}
			else
			{
				end = mid - 1;
			}
		}
		totalMissingNumbersOnLeft = nums[end] - (nums[0] + end);
		return nums[end] + (k - totalMissingNumbersOnLeft);
	}
}
