package leetcode.binarysearch.part2;

/**
 * 154. Find Minimum in Rotated Sorted Array II
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,4,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,4] if it was rotated 4 times.
 * [0,1,4,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array
 * [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums that may contain duplicates,
 * return the minimum element of this array.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 * Example 1:
 * Input: nums = [1,3,5]
 * Output: 1
 *
 * Example 2:
 * Input: nums = [2,2,2,0,1]
 * Output: 0
 */

/**
 * Omkar's Solution
 * If all numbers are duplicate: T(n) = O(n)
 * If firstNum != lastNum ?? T(n) = O(log n)
 */
public class FindMinInARotatedSortedArrayII
{
	/**
	 * try to eliminate duplicate elements that are at the beginning of the array
	 * if first element == last element, move forward until the element is not the same
	 * and start from here
	 * Purpose: if first element == last element we don't know which zone we should move to
	 */
	public static int findMin(int[] nums) {
		if (nums.length == 1) return nums[0];
		int start = 0;
		int end = nums.length-1;
		//only apply if firstElement == lastElement, then start from the firstElement that is != lastElement
		if (nums[0] == nums[nums.length-1])
		{
			for (int i = 1; i < nums.length-1; i++)
			{
				if (nums[i] != nums[nums.length - 1])
				{
					start = i;
					break;
				}
				if (i == nums.length-2)
					return nums[0]; // all elements are the same
			}
			//this is the first minimum element
			if (nums[start] < nums[0])
				return nums[start];
		}
		while (start <= end)
		{
			int mid = start + (end-start)/2;
			//compare mid with the last element, if mid > lastElement then mid is in the left zone,
			//so need to move to the right side
			if (nums[mid] > nums[nums.length-1])
			{
				start = mid + 1;
			}
			else
			{
				end = mid - 1;
			}
		}
		//this is when the boundary is crossed, and end > start
		return nums[start];
	}

	public static void main(String ... args)
	{
		int[] nums = {3,1};
		System.out.println(findMin(nums));//1

		int[] nums1 = {1,3,5};
		System.out.println(findMin(nums1));//1

		int[] nums2 = {2,2,2,0,1};
		System.out.println(findMin(nums2));//0

		int[] nums3 = {4,5,6,7,1,4,4};
		System.out.println(findMin(nums3));//1
	}
}
