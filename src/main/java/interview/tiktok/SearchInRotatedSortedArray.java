package interview.tiktok;
/**
 * 12.20.2022
 * Round 1: Coding, 2nd problem with Zhebo (seems nice)
 *
 * 33. Search in Rotated Sorted Array
 *
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k
 * (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ...,
 * nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target,
 * return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Example 3:
 * Input: nums = [1], target = 0
 * Output: -1
 */

/**
 * Omkar's Solution
 * Can be done with 2 BS, find the boundary first (just like FindMinInARotatedSortedArray)
 * then run BS to the zone where the target is.
 * Solution below is using one pass.
 */
public class SearchInRotatedSortedArray
{

	//I wrote below, honestly I need to have a deep understanding why are we using region left & right
	//and in line 77 and 89 why didn't I use 'end' as an index?
	//The interviewer seems confused about my code, but this code passes all of his test cases!
	public int search(int[] nums, int target) {
		if (nums.length == 0) return -1;
		if (nums.length == 1)
		{
			if (target == nums[0]) return 0;
			else return -1;
		}

		String region = "";
		//find where the target zone
		if (target == nums[nums.length-1])
		{
			return nums.length-1;
		}
		//target in the left zone
		else if (target > nums[nums.length-1])
		{
			region = "left";
		}
		else {
			region = "right";
		}

		int start = 0;
		int end = nums.length - 1;
		while (start <= end)
		{
			int mid = start + (end-start)/2;
			if (nums[mid] == target)
			{
				return mid;
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
		return -1;
	}
}
