package leetcode.binarysearch.part2;

/**
 * 941. Valid Mountain Array
 *
 * Given an array of integers arr, return true if and only if it is a valid mountain array.
 *
 * Recall that arr is a mountain array if and only if:
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 *
 * Example 1:
 * Input: arr = [2,1]
 * Output: false
 *
 * Example 2:
 * Input: arr = [3,5,5]
 * Output: false
 *
 * Example 3:
 * Input: arr = [0,3,2,1]
 * Output: true
 */
/**
 * Omkar's Solution
 * Binary Search Variants Part 2
 *
 * Do we need to look at all elements ? YES
 * Using 2 pointers approach, both pointers will arrive at the same location which is the peak
 * T(n) = O(n)
 */
public class ValidMountainArray
{
	//Runtime 9 ms beatas 26%
	public static boolean validMountainArray(int[] arr) {
		if (arr.length <= 2) return false;
		int i;
		int j;
		//figure out how far we can walk along the ascending slope
		for (i=0; i < arr.length - 2; i++)
		{
			//Compare arr[i] with arr[i+1]
			if (arr[i] >= arr[i+1])
				break;
		}
		for (j=arr.length-1; j > 0; j--)
		{
			//Compare arr[j] with arr[j-1]
			if (arr[j] >= arr[j-1])
				break;
		}
		return i==j && (i > 0 && i<arr.length-1);
	}

	public static void main(String ... args)
	{
		int[] nums = {0,2,3,4,5,2,1,0};
		System.out.println(validMountainArray(nums));//true

		int[] nums1 = {2,1};
		System.out.println(validMountainArray(nums1));//false

		int[] nums2 = {3,5,5};
		System.out.println(validMountainArray(nums2));//false
	}
}
