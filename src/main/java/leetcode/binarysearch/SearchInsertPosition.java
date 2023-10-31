package leetcode.binarysearch;

/**
 * https://leetcode.com/problems/search-insert-position/
 * 35. Search Insert Position
 *
 * Given a sorted array of distinct integers and a target value,
 * return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 * Example 1:
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 *
 * Example 3:
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 */
public class SearchInsertPosition
{
	public static int searchInsert(int[] nums, int target)
	{
		int startIndex = 0;
		int endIndex = nums.length-1;
		while (startIndex <= endIndex)
		{
			int mid = startIndex + (endIndex - startIndex) / 2;
			if (nums[mid] == target)
			{
				return mid;
			}
			else if (nums[mid] < target)
			{
				startIndex = mid + 1;
			}
			else //nums[mid] > target
			{
				endIndex = mid - 1;
			}
		}
		//at this point startIndex > endIndex, start = end+1 and we exit the loop
		//start points to the where target should be inserted
		/**
		 * If there is no place for target then check if start reached outside the n boundary
		 */
		return startIndex;
	}
}
