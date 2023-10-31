package leetcode.binarysearch.part5;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * 4. Median of Two Sorted Arrays
 *
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 *
 * Example 2:
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 */
/**
 * Omkar's Solution
 * Binary Search Variants Part 5
 * Looking for the kth smallest element
 *
 * Complications:
 * 1. Works well with odd elements but for even element (m+n) we need to find the (k+1)st smallest element as well
 * 2. Some of the indices may not be valid
 * e.g: m = 1, m+n is even, and kth smallest element is m[0] then successor index would be invalid
 *
 */
public class MedianOfTwoSortedArray
{
	//TODO: revisit
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		int start = 0;
		int end = m - 1;
		int k = 0;
		//find kth smallest element in 2 sorted arrays using binary search on the array of length m
		//do Binary Search on 1 array
		if ((m + n) % 2 == 1) // odd element
		{
			k = (m + n)/2 + 1;
		}
		else
		{
			//if combined length (m+n) then kth smallest element: (m+n)/2 & (m+n)/2 + 1
			k = (m + n)/2;
		}

		int kthsmallest = 0;
		int succ = 0;

		while (start <= end)
		{
			int mid = start + (end - start)/2;

			/**
			 * if we find kth smallest element in array m, and the index = mid,
			 * it means there are (k-1-mid) elements in array n that are < k
			 * the index of the last element that is in the array n = k-1-mid-1
			 */
			//if nums1[mid] is the kth smallest element
			/**
			 * if (nums2[k-1-mid-1] <= nums1[mid]
			 * && nums1[mid] <= nums2[k-1-mid]
			 */
			if (get(k-1-mid-1, nums2) <= nums1[mid]
					&& nums1[mid] <= get(k-1-mid, nums2))
			{
				kthsmallest = nums1[mid];
				succ = Math.min(get(mid+1, nums1), get(k-1-mid, nums2));
				break;
			}
			//else if kth smallest element lies in the left zone
			/** else if (nums2[k-1-mid-1] < nums1[mid]) */
			else if (get(k-1-mid-1, nums2) < nums1[mid])
			{
				end = mid - 1; //drop the right zone, keep searching on the left zone
			}
			//else if the kth smallest element in the right zone
			else //nums2[k-1-mid-1] > nums1[mid]
			{
				start = mid + 1;
			}
		}
		//kth smallest element not found in nums1, and nums1 has total "start" elements
		//kth smallest element exist in nums2, with index: k-start-1
		if (start == end+1)
		{
			kthsmallest = get(k-start-1, nums2);
			succ = Math.min(get(start, nums1), get(k-start, nums2));
		}
		if ((m+n) % 2 == 1)
		{
			return kthsmallest;
		}
		else return (kthsmallest + succ)/2;
	}

	private static int get(int i, int[] arr)
	{
		if (i < 0) return Integer.MIN_VALUE;
		if (i >= arr.length) return Integer.MAX_VALUE;
		return arr[i];
	}

	public static void main(String ... args)
	{
		int[] nums1 = {0,1,2,3};
		int[] nums2 = {4,5,6};
		System.out.println(findMedianSortedArrays(nums1, nums2));//3.0

	}
}
