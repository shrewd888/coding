package leetcode.binarysearch.part3;

/**
 * https://leetcode.com/problems/sqrtx/description/
 * 69. Sqrt(x)
 *
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
 * The returned integer should be non-negative as well.
 *
 * You must not use any built-in exponent function or operator.
 *
 * For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 *
 * Example 1:
 * Input: x = 4
 * Output: 2
 * Explanation: The square root of 4 is 2, so we return 2.
 *
 * Example 2:
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
 */
/**
 * Omkar's Solution
 * Binary Search Variants Part 3
 */
public class Sqrt
{
	//Runtime 3ms beats 54 %
	public static int mySqrt(int x) {
		int start = 0;
		int end = x;
		while (start <= end)
		{
			int mid = start + (end-start)/2;
			long multiple = (long) mid * mid;//cast to long because it will overflow for x=2147395599
			if (multiple == x)
			{
				return mid;
			}
			else if (multiple < x)
			{
				start = mid + 1;
			}
			else //mid * mid > x
			{
				end = mid - 1;
			}
		}
		//when exit while loop, and still not finding the mid, the start > end
		//need to return the largest integer that is smaller than the integer of sqrt(x)
		return end;
	}

	public static void main(String ... args)
	{
		int x = 4;
		System.out.println(mySqrt(x));//2

		x = 8;
		System.out.println(mySqrt(x));//2

		x = 2147395599;
		System.out.println(mySqrt(x));//46339
	}
}
