package leetcode.binarysearch;

/**
 * 1150. Check If a Number Is Majority Element in a Sorted Array
 * Given an integer array nums sorted in non-decreasing order and an integer target,
 * return true if target is a majority element, or false otherwise.
 *
 * A majority element in an array nums is an element that appears more than nums.length / 2 times in the array.
 *
 * Example 1:
 * Input: nums = [2,4,5,5,5,5,5,6,6], target = 5
 * Output: true
 * Explanation: The value 5 appears 5 times and the length of the array is 9.
 * Thus, 5 is a majority element because 5 > 9/2 is true.
 *
 * Example 2:
 * Input: nums = [10,100,101,101], target = 101
 * Output: false
 * Explanation: The value 101 appears 2 times and the length of the array is 4.
 * Thus, 101 is not a majority element because 2 > 4/2 is false.
 */
public class CheckNumberIsMajorityInSortedArray
{
	public boolean isMajorityElement(int[] nums, int target) {
		int start = 0;
		int end = nums.length-1;
		int halfLength = nums.length/2;

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
			return false;
		}
		int startIndex = start;
		int left = start;
		int right = nums.length-1;
		while (left <= right)
		{
			int mid = left + (right-left)/2;
			if (nums[mid] <= target)
			{
				left = mid+1; //keep moving forward because we want to end to num != target
			}
			else  //nums[mid] > target
			{
				right = mid-1;
			}
		}
		/**
		 * endIndex has to be valid because right < left and the boundary is: target | > target,
		 * so right will go to the target area
		 * left will point to the area that is > target
		 */
		int l = right-startIndex + 1;//this is the frequency of the element
		if (l > halfLength) return true;
		return false;
	}
}
