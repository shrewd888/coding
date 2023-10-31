package leetcode.binarysearch;

/**
 * https://leetcode.com/problems/single-element-in-a-sorted-array/
 * 540. Single Element in a Sorted Array
 *
 * You are given a sorted array consisting of only integers where every element appears exactly twice,
 * except for one element which appears exactly once.
 * Return the single element that appears only once.
 *
 * Your solution must run in O(log n) time and O(1) space.
 *
 * Example 1:
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 */

/**
 * Omkar's Solution
 * Define the zone where we'll hit the unpaired number
 * 3 zones:
 * even-odd | unpaired | odd-even
 * unpaired number will have left side & right side different from itself
 *
 * T(n) = O (log n)
 */
public class SingleElementInASortedArray
{
	public static int singleNonDuplicate(int[] nums) {
		//separate handling so that mid is always well defined (stay inside the array length boundary)
		if (nums.length == 1) return nums[0];
		//if unpaired element is found on index 0
		if (nums[0] != nums[1])
		{
			return nums[0];
		}
		//if unpaired element is found on index nums.length-1
		if (nums[nums.length-1] != nums[nums.length-2])
		{
			return nums[nums.length-1];
		}

		int start = 0;
		int end = nums.length - 1;

		while (start <= end)
		{
			int mid = start + (end-start)/2;
			if (nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1])
			{
				return nums[mid];
			}
			//left zone: if mid is even && nums[mid] = nums[mid+1]
			else if ((mid % 2 == 0 && nums[mid] == nums[mid+1])
				|| (mid % 2 == 1 && nums[mid-1] == nums[mid]))
			{
				start = mid + 1;
			}
			//right zone: if mid is odd && nums[mid-1] = nums[mid]
			else
			{
				end = mid - 1;
			}
		}
		return -1;
	}
}
