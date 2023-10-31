package interview.snowflake;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/maximise-minimum-element-possible-in-array-after-performing-given-operations/
 *
 * Maximise minimum element possible in Array after performing given operations
 *
 * Given an array arr[] of size N. The task is to maximize the minimum value of the array after performing given operations.
 * In an operation, value x can be chosen and
 * A value 3 * x can be subtracted from the arr[i] element.
 * A value x is added to arr[i-1]. and
 * A value of 2 * x can be added to arr[i-2].
 *
 * Find the maximum possible minimum element of the array after any such operations.
 *
 * Examples:
 * Input: arr[] = {1, 2, 3, 4, 5, 6}
 * Output: 3
 * Explanation: The last element is chosen and  x =1 can be chosen.
 * So 3*x gets subtracted from arr[i] and x gets added to arr[i-1] and 2*x to arr[i-2] so the array becomes {1, 2, 3, 6, 6, 3}
 * In the 4th index x =1 can be chosen and now the array becomes {1, 2, 5, 7, 3, 3}.
 * In the 3rd index x = 1 can be chosen and now the array becomes {1, 4, 6, 4, 3, 3}.
 * In the 2nd index again x =1 can be chosen and now the array becomes {3, 4, 3, 4, 3, 3, 3}.
 * Hence the maximum possible minimum value is 3.
 *
 * Input: arr[] = {9, 13, 167}
 * Output: 51
 */
public class SmallestMaximumValue_GFG
{

	// Check if mid is possible as
	// a minimum element after
	// any number of operations using
	// this predicate function
	// Guess the answer Binary Search approach
	static Boolean is_possible_min(int arr[],
			int mid)
	{
		int N = arr.length;

		// Traverse from the end
		for (int i = N - 1; i >= 2; i--) {

			// mid can't be minimum
			if (arr[i] < mid)
				return false;
			else {
				// Find the 3x -- guessing mid is the minimum, so when substracting 'number' from min, you gain the max.
				int extra = arr[i] - mid;

				// Find the x
				extra /= 3;

				// Add x to a[i-1]
				arr[i - 1] += extra;

				// Add 2x to a[i-2]
				arr[i - 2] += 2 * extra;
			}
		}

		// Check if the first two elements are >= mid because if every element
		// is greater than or equal to mid we can conclude
		// mid as a minimum element
		if (arr[0] >= mid && arr[1] >= mid)
			return true;

		return false;
	}

	// Function to find the
	// maximum possible minimum value
	static void find_maximum_min(int arr[])
	{
		// Initialize f = 1 and l as the
		// maximum element of the array
		int f = 1, l =  Arrays.stream(arr).max().getAsInt();

		// Initialize res as INT_MIN
		int res = Integer.MIN_VALUE;

		// Perform binary search while f<=l
		while (f <= l) {

			int mid = l + (f - l) / 2;

			// Check if mid is possible
			// as a minimum element
			if (is_possible_min(arr, mid) == true) {

				// Take the max value of mid
				res = Math.max(res, mid);

				// Try maximizing the min value
				f = mid + 1;
			}

			// Move left if it is not possible
			else {
				l = mid - 1;
			}
		}

		// Print the result
		System.out.println(res);
	}

	// Driver Code
	public static void main (String[] args)
	{
		// Initialize the array
		int arr[] = { 1, 2, 3, 4, 5, 6 };
		// Function call
		find_maximum_min(arr); //3

		int arr1[] = {5,4,1,3,2,6};
		find_maximum_min(arr1); //3

		int arr2[] = {9, 13, 167};
		find_maximum_min(arr2); //83
	}
}

