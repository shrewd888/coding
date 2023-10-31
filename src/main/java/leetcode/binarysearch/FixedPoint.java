package leetcode.binarysearch;

/**
 * https://leetcode.com/problems/fixed-point/
 * 1064. Fixed Point
 *
 * Given an array of distinct integers arr, where arr is sorted in ascending order,
 * return the smallest index i that satisfies arr[i] == i. If there is no such index, return -1.
 *
 * Example 1:
 * Input: arr = [-10,-5,0,3,7]
 * Output: 3
 * Explanation: For the given array, arr[0] = -10, arr[1] = -5, arr[2] = 0, arr[3] = 3, thus the output is 3.
 *
 * Example 2:
 * Input: arr = [0,2,5,8,17]
 * Output: 0
 * Explanation: arr[0] = 0, thus the output is 0.
 *
 * Example 3:
 * Input: arr = [-10,-5,3,4,7,9]
 * Output: -1
 * Explanation: There is no such i that arr[i] == i, thus the output is -1.
 *
 */

/**
 * Omkar's Solution
 * Binary Search Variants Part 1
 *
 * 2 zones:
 * A[i] < i and A[i] >= i
 */
public class FixedPoint
{
	public static int fixedPoint(int[] arr) {
		int start = 0;
		int end = arr.length - 1;

		while (start <= end)
		{
			int mid = start + (end-start)/2;
			if (arr[mid] < mid)
			{
				start = mid+1;
			}
			else //if (arr[mid] >= mid)
			{
				end = mid-1;
			}
		}

		if (start < arr.length && arr[start] == start)
			return start;

		return -1;
	}

	public static void main(String ... args)
	{
		int[] nums = {-10,-5,2,3,7};
		System.out.println(fixedPoint(nums));//2

		int[] nums0 = {-10,-5,0,3,7};
		System.out.println(fixedPoint(nums0));//3

		int[] nums1 = {0,2,5,8,17};
		System.out.println(fixedPoint(nums1));//0

		int[] nums2 = {-10,-5,3,4,7,9};
		System.out.println(fixedPoint(nums2));//-1
	}
}
