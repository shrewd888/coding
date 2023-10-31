package leetcode.slidingwindow;

/**
 * 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
 *
 * Given an array of integers arr and two integers k and threshold, return the number of sub-arrays of size k
 * and average greater than or equal to threshold.

 * Example 1:
 * Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
 * Output: 3
 * Explanation: Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively.
 * All other sub-arrays of size 3 have averages less than 4 (the threshold).
 *
 * Example 2:
 * Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
 * Output: 6
 * Explanation: The first 6 sub-arrays of size 3 have averages greater than 5. Note that averages are not integers.
 */

/**
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class NumberOfSubArrays
{
	public static int numOfSubarrays(int[] arr, int k, int threshold) {
		int len = arr.length;
		int count = 0;
		if (len == 0) return count;

		int windowSum = 0;
		for (int i=0; i < k; i++)
		{
			windowSum = windowSum + arr[i];
		}
		//(a+b+c)/k >= t
		int x = threshold * k;
		if (windowSum >= x)
		{
			count++;
		}
		for (int i=k; i<len; i++)
		{
			//remove the left element that is not part of this window
			windowSum += arr[i] - arr[i-k];
			if (windowSum >= x) count++;
		}
		return count;
	}
	//Runtime: 4 ms, faster than 80.28% of Java online submissions f
	public static void main(String ... args)
	{
		int arr[] = {2,2,2,2,5,5,5,8};
		int k = 3;
		int t = 4;

		int sub = numOfSubarrays(arr, k, t);
		System.out.println(sub); //3
	}

}
