package leetcode.binarysearch;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 34. Find First and Last Position of Element in Sorted Array
 *
 * Given an array of integers nums sorted in non-decreasing order,
 * find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 * Example 3:
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 */

/**
 * Omkar's Solution
 * Binary Search Variant Part 1
 * Because we are ask to do in O(log n) so we have to use Binary Search
 * Overall is still O(log n)
 */
public class FindFirstLastPositionOfElementInSortedArray
{
	public static int[] searchRange(int[] nums, int target) {
		int start = 0;
		int end = nums.length-1;

		while (start <= end)
		{
			int mid = start + (end-start)/2;
			if (nums[mid] < target)
			{
				start = mid+1;
			}
			else  //nums[mid] >= target
			{
				end = mid-1;
			}
		}
		/**
		 * Try to find the first index of the element
		 * have to check if start is not outside of nums.length - 1,
		 * or the startIndex is valid, but nums[startIndex] is not the target
		 */
		if (start == nums.length || nums[start] != target)
		{
			return new int[]{-1, -1};
		}
		System.out.println("start: "+start+", end: "+end);

		int startIndex = start;
		int left = start;
		int right = nums.length-1;

		while (left <= right)
		{
			int mid = left + (right-left)/2;
			if (nums[mid] <= target)
			{
				left = mid+1;
			}
			else  //nums[mid] > target
			{
				right = mid-1;
			}
		}
		System.out.println("left: "+left+", right: "+right);
		/**
		 * endIndex has to be valid because right < left and the boundary is: target | > target,
		 * so right will go to the target area
		 * left will point to the area that is > target
		 */
		return new int[] {startIndex, right};
	}

	static void print(int[] arr)
	{
		System.out.print("[");
		for (int i=0; i < arr.length; i++)
		{
			System.out.print(arr[i]);
			if (i < arr.length-1)
				System.out.print(",");
		}
		System.out.print("] ");
	}

	public static void main(String ... args)
	{
		int[] nums = {5,7,7,8,8,10};
		int target = 8;
		print(searchRange(nums, target));//4

//		int[] nums1 = {-1,0,3,5,9,12};
//		int target1 = 2;
//		print(searchRange(nums1, target1));//-1
	}
}
