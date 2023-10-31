package leetcode.binarysearch.part2;

/**
 * https://leetcode.com/problems/find-peak-element/
 * 162. Find Peak Element
 * A peak element is an element that is strictly greater than its neighbors.
 * Given a 0-indexed integer array nums, find a peak element, and return its index.
 * If the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞. In other words,
 * an element is always considered to be strictly greater than a neighbor that is outside the array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 *
 * Example 2:
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.
 */

/**
 * Omkar's Solution
 * There is no sorting here. How is BS helps?
 * Whenever there is a peak, there must be a valley
 * Zones: asc, P, desc, valley, asc, P, desc
 */
public class FindPeakElement
{
	// no guarantee the array size is >= 3
	public static int findPeakElement(int[] nums) {
		/**
		 * Edge cases
		 */
		//size 1
		if (nums.length == 1) return 0;
		//peak exist at the beginning
		if (nums[0] > nums[1]) return 0; //0 index is the peak
		//peak exist at the end
		if (nums[nums.length-1] > nums[nums.length-2]) return nums.length-1;

		//Handle general case:
		//ascending-P-desc-valley-asc-P-desc-valley-asc-P-desc
		// can start from index 1 and length-2 since we've handled edge cases
		// so now all peaks exist in the interior
		/**
		 * mid can fall in these zones: valley, asc, P, desc region
		 */
		int start = 1;
		int end = nums.length - 2;
		while (start <= end)
		{
			int mid = start + (end-start)/2;
			//nums[mid] is a peak, return mid index
			if (nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1])
			{
				return mid;
			}
			//if nums[mid] is a valley, can go to left or right
			else if (nums[mid-1] > nums[mid] && nums[mid] < nums[mid+1])
			{
				start = mid+1; //or end = mid-1
			}
			//if nums[mid] is the asc zone, find the peak on the right
			//compare to the right toward the interior
			//(could also compare toward the left) but toward interior is more consistent
			else if (nums[mid] < nums[mid+1]) //or && nums[mid-1] < nums[mid]
			{
				start = mid + 1;
			}
			//if nums[mid] is the desc zone, find the peak on the left
			else
			{
				end = mid - 1;
			}
		}
		return -1;
	}
}
