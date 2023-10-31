package leetcode.binarysearch.part2;

/**
 * 153. Find Minimum in Rotated Sorted Array
 *
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array
 * a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 *
 * Example 3:
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 */
/**
 * Omkar's Solution
 * Binary Search Variants Part 2
 * Zones: increase | increase
 * Left zones: 0,1,...,(n-1) length
 * Right zones: 1,2,...n
 */
public class FindMinInARotatedSortedArray
{
	public static int findMin(int[] nums) {
		int start = 0;
		int end = nums.length - 1;
		while (start <= end)
		{
			int mid = start + (end-start)/2;
			//compare mid with the last element, if mid > lastElement then mid is in the left zone,
			//so need to move to the right side, because we are looking for min value
			if (nums[mid] > nums[nums.length-1])
			{
				start = mid + 1;
			}
			else
			{
				end = mid - 1;
			}
		}
		//this is when the boundary is crossed, and endIndex in front of startIndex
		return nums[start];
	}

	public static void main(String ... args)
	{
		int[] nums = {3,4,5,1,2};
		System.out.println(findMin(nums));//1

		int[] nums1 = {4,5,6,7,0,1,2};
		System.out.println(findMin(nums1));//0

		int[] nums2 = {11,13,15,17};
		System.out.println(findMin(nums2));//11
	}
}
