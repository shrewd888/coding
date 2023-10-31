package leetcode.binarysearch.part2;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * 81. Search in Rotated Sorted Array II
 *
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 *
 * Before being passed to your function, nums is rotated at an unknown pivot index k
 * (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]
 * (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 *
 * Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
 * You must decrease the overall operation steps as much as possible.
 *
 * Example 1:
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 *
 * Example 2:
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 */

/**
 * Omkar's Solution
 * Binary Search Variants Part 2
 */
public class SearchInRotatedSortedArrayII
{
	//Runtime 1 ms beats 86 %
	public static boolean search(int[] nums, int target) {
		if (nums.length == 0) return false;
		if (nums.length == 1)
		{
			return (target == nums[0]);
		}
		int start = 0;
		int end = nums.length-1;

		//only apply if firstElement == lastElement, then start from the firstElement that is != lastElement
		if (nums[0] == nums[nums.length-1])
		{
			if (target == nums[0]) return true;
			//need to set start to the left most number that is not equal to the last element
			for (int i = 1; i < nums.length-1; i++)
			{
				if (nums[i] != nums[nums.length - 1])
				{
					start = i;
					break;
				}
				if (i == nums.length-2)
					return (target == nums[0]); // all elements are the same
			}
		}

		String region = "";
		//find where the target zone
		if (target == nums[nums.length-1])
		{
			return true;
		}
		//target in the left zone
		else if (target > nums[nums.length-1])
		{
			region = "left";
		}
		else {
			region = "right";
		}
		while (start <= end)
		{
			int mid = start + (end-start)/2;
			if (nums[mid] == target)
			{
				return true;
			}
			//compare mid with the last element, if mid > lastElement then mid is in the left zone,
			//but if target < lastElement then target is in the right zone.
			//so need to move to the right side, because we are looking for target index
			if (nums[mid] > nums[nums.length-1]) //mid in the left region
			{
				if (target > nums[mid] || region.equals("right"))
				{
					start = mid + 1;
				}
				else //now we are in the target zone
				{
					end = mid-1;
				}
			}
			//move toward target, target is in the left zone
			else if (nums[mid] <= nums[nums.length-1])//mid in the right region
			{
				if (target < nums[mid] || region.equals("left"))
				{
					end = mid - 1;
				}
				else //now we are in the target zone
				{
					start = mid + 1;
				}
			}
		}
		return false;
	}
}
