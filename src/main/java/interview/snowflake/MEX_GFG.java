package interview.snowflake;

import java.util.HashSet;
import java.util.Set;

/**
 * The MEX is the smallest positive integer that is not present in the array.
 * https://www.geeksforgeeks.org/maximum-mex-from-all-subarrays-of-length-k/
 * Maximum MEX from all subarrays of length K
 *
 * Examples:
 * Input: arr[] = {3, 2, 1, 4}, K = 2
 * Output: 3
 * Explanation:
 * All subarrays having length 2 are {3, 2}, {2, 1}, {1, 4}.
 * In subarray {3, 2}, the smallest positive integer which is not present is 1.
 * In subarray {2, 1}, the smallest positive integer which is not present is 3.
 * In subarray {1, 4}, the smallest positive integer which is not present is 2.
 *
 * Input: arr[] = {6, 1, 3, 2, 4}, K = 3
 * Output: 4
 * Explanation:
 * All subarrays having length 3 are {6, 1, 3}, {1, 3, 2}, {3, 2, 4}
 * In subarray {6, 1, 3}, the smallest positive integer which is not present is 2.
 * In subarray {1, 3, 2}, the smallest positive integer which is not present is 4.
 * In subarray {3, 2, 4}, the smallest positive integer which is not present is 1.
 */
public class MEX_GFG
{
	//N = size of the array, k = size of subset
	static int maxMEX(int arr[], int N, int k)
	{
		Set<Integer> numSet = new HashSet<>();
		int left =0;
		int right=N;

		int max_mex = Integer.MIN_VALUE;
		while (left < right)
		{
			for (int i=left; i<k; i++)
			{
				numSet.add(arr[i]);
			}
			for (int j=1; j <= (k+1); j++)
			{
				if (!numSet.contains(j))
				{
					int mex = j;
					max_mex = Math.max(max_mex, mex);
				}
			}
			//remove the most left
			numSet.remove(arr[left]);
			left++;
		}
		return max_mex;
	}

	public static void main(String ... args)
	{
		int arr[] = {3, 2, 1, 4}, k = 2;
		int maxMex = maxMEX(arr, arr.length, k);
		System.out.println(maxMex); //3

		int arr1[] = {6, 1, 3, 2, 4}, k1 = 3;
		int maxMex1 = maxMEX(arr1, arr1.length, k1);
		System.out.println(maxMex1); //4
	}
}
