package leetcode.binarysearch;

/**
 * 1228. Missing Number In Arithmetic Progression
 *
 * In some array arr, the values were in arithmetic progression: the values arr[i + 1] - arr[i]
 * are all equal for every 0 <= i < arr.length - 1.
 *
 * A value from arr was removed that was not the first or last value in the array.
 *
 * Given arr, return the removed value.
 *
 * Example 1:
 * Input: arr = [5,7,11,13]
 * Output: 9
 * Explanation: The previous array was [5,7,9,11,13].
 *
 * Example 2:
 * Input: arr = [15,13,12]
 * Output: 14
 * Explanation: The previous array was [15,14,13,12].
 */

/**
 * Omkar's Solution
 * Binary Search Variants Part 1
 * Left zone: a, a+d, a+2d, ..., a+id
 *
 * if d==0: all numbers will be the same
 */

public class MissingNumberInArithmeticProgression
{
	public int missingNumber(int[] arr) {
		int a = arr[0];
		//calculate d
		/**
		 * If there is no number removed, d will be:
		 * int d = (arr[arr.length-1] - a)/arr.length - 1;
		 * now since one element is removed, the bottom: arr.length-1 will be = arr.length
		 */
		int d = (arr[arr.length-1] - a)/arr.length;
		int start = 0;
		int end = arr.length - 1;

		while (start <= end)
		{
			int mid = start + (end-start)/2;
			if (arr[mid] == a + mid * d) //left zone
			{
				start = mid+1;
			}
			else
			{
				end = mid-1;
			}
		}
		//if all values are the same, finally start will be = arr.length and end = arr.length-1
		//the below also work in this case
		return arr[end] + d; //arr[start] - d -> won't be a valid number because start = arr.length (invalid index)
	}
}
