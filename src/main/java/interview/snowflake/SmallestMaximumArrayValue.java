package interview.snowflake;

import java.util.Arrays;

/**
 * https://leetcode.com/discuss/interview-question/2551033/snowflake-oa-second-question
 * Given an array of n positive integers,the following ops can be done any time
 *
 * 1.choose i st 2<=i<=n
 * 2.choose any x s.t. 1<=x<=arr[i]
 * 3.then set arr[i-1] to arr[i-1]+x
 * 4.set arr[i] to arr[i]-x
 * What is the smallest maximum value in the array after performing these operations from 1-4.
 *
 * T1 input n=4 [1,5,7,6]
 * output 5
 * choose a[3] and x as 4
 * a[2] becomes 9 and a[3] becomes 3
 * choose a[2] and x as 4
 * a[1] becomes 5 and a[2] becomes 5
 * choose a[4] and x as 1
 * a[3] becomes 4 and a[4] becomes 5
 * final array [5,5,3,5] smallest max value is 5
 *
 * T2 n is 3 arr is [5,15,19]
 * output 13
 *
 * T3 n is 4 arr is [10,3,5,7]
 * output is 1
 */

//1.choose i st 2<=i<=n
//2.choose any x s.t. 1<=x<=arr[i]
//3.then set arr[i-1] to arr[i-1]+x
//4.set arr[i] to arr[i]-x
//What is the smallest maximum value in the array after performing these operations from 1-4.
public class SmallestMaximumArrayValue
{
	// Check if mid is possible as a minimum element after any number of operations using
	// this predicate function
	static boolean is_possible_min(int arr[], int mid)
	{
		int n = arr.length;
		//Arrays.sort(arr);

		// Traverse from the end
		for (int i=n-1; i > 0; i--) {

			// mid can't be minimum
			if (arr[i] < mid)
				return false;
			else {
				// Find the x
				int x = mid;

				// Add x to arr[i-1]
				arr[i - 1] += x;

				// arr[i]-x
				arr[i] -= x;
			}
		}

		// Check if the first element is >= mid because if every element
		// is greater than or equal to mid we can conclude
		// mid as a minimum element
		if (arr[0] >= mid)
			return true;

		return false;
	}

// 1.choose i st 2<=i<=n
// 2.choose any x s.t. 1<=x<=arr[i]
// 3.then set arr[i-1] to arr[i-1]+x
// 4.set arr[i] to arr[i]-x
//  What is the smallest maximum value in the array after performing these operations from 1-4.

	static void find_maximum_min(int arr[])
	{
		// Initialize start = 1 and end = maximum element of the array
		int start = 1, end = Arrays.stream(arr).max().getAsInt();

		// Initialize result as INT_MIN
		int result = Integer.MIN_VALUE;

		// Perform binary search while start <= end
		while (start <= end) {

			int mid = end + (start - end) / 2;

			// Check if mid is possible
			// as a minimum element
			if (is_possible_min(arr, mid) == true) {

				// Take the max value of mid
				result = Math.max(result, mid);

				// Try maximizing the min value -- this only work if arrays are in sorted order??
				start = mid + 1;
			}

			// Move left if it is not possible
			else {
				end = mid - 1;
			}
		}

		// Print the result
		System.out.println(result);
	}

	public static void main (String[] args)
	{
		int arr[] = {10,3,5,7};
		find_maximum_min(arr);//1 - if arrays is sorted: 5

		int arr1[] = {1,5,7,6};
		find_maximum_min(arr1);//4 - if arrays is sorted: 5

		int arr2[] = {5,15,19};
		find_maximum_min(arr2);//10 - if arrays is sorted: 15
	}
}
