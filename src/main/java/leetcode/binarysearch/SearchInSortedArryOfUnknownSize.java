package leetcode.binarysearch;

/**
 * https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/
 * 702. Search in a Sorted Array of Unknown Size
 *
 * This is an interactive problem.
 *
 * You have a sorted array of unique elements and an unknown size.
 * You do not have an access to the array but you can use the ArrayReader interface to access it.
 * You can call ArrayReader.get(i) that:
 *
 * returns the value at the ith index (0-indexed) of the secret array (i.e., secret[i]), or
 * returns 231 - 1 if the i is out of the boundary of the array.
 * You are also given an integer target.
 *
 * Return the index k of the hidden array where secret[k] == target or return -1 otherwise.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: secret = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in secret and its index is 4.
 *
 * Example 2:
 * Input: secret = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in secret so return -1.
 */
public class SearchInSortedArryOfUnknownSize
{
	public int search(ArrayReader reader, int target) {
		int end = 1;
		while (reader.get(end) < target)
		{
			end = end * 2;
		}
		int start = end/2;
		while (start <= end)
		{
			int mid = start + (end-start)/2;
			int midVal = reader.get(mid);
			if (target == midVal)
			{
				return mid;
			}
			else if (target < midVal)
			{
				end = mid - 1;
			}
			else //target > nums[mid]
			{
				start = mid + 1;
			}
		}
		return -1;
	}
}
