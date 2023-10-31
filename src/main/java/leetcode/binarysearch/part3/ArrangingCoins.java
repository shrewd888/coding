package leetcode.binarysearch.part3;

/**
 * 441. Arranging Coins
 * You have n coins and you want to build a staircase with these coins.
 * The staircase consists of k rows where the ith row has exactly i coins. The last row of the staircase may be incomplete.
 *
 * Given the integer n, return the number of complete rows of the staircase you will build.
 *
 * Input: n = 5
 * Output: 2
 * Explanation: Because the 3rd row is incomplete, we return 2.
 *
 * Input: n = 8
 * Output: 3
 * Explanation: Because the 4th row is incomplete, we return 3.
 */
/**
 * Omkar's Solution
 * Binary Search Variants Part 3
 * Find the largest triangular number <= n
 * e.g: 9 (below can be drawn as a triangle)
 * X
 * X X
 * X X X
 * X X X
 *
 * 1 + 2 + 3 + .. = i(i+1)/2 = n
 *
 */
public class ArrangingCoins
{
	//Runtime 4 ms beats 56 %
	public static int arrangeCoins(int n) {
		int start = 1;
		int end = n;
		while (start <= end)
		{
			int mid = start + (end-start)/2;
			long x = (long) mid* (mid+1)/2;//need to cast to long to avoid overflow
			if (x == n) return mid;
			else if (x < n)
			{
				start = mid + 1;
			}
			else
			{
				end = mid - 1;
			}
		}
		//could find the exact number of staircase rows, ended up with 2 zones:
		// < i | > i -> end | start
		return end; //the #of full rows we can construct
	}
}
