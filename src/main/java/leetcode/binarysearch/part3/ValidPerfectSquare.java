package leetcode.binarysearch.part3;

/**
 * 367. Valid Perfect Square
 *
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * Follow up: Do not use any built-in library function such as sqrt.
 *
 * Example 1:
 * Input: num = 16
 * Output: true
 *
 * Example 2:
 * Input: num = 14
 * Output: false
 */
public class ValidPerfectSquare
{
	public static boolean isPerfectSquare(int num) {
		int start = 0;
		int end = num;
		while (start <= end)
		{
			int mid = start + (end-start)/2;
			long multiple = (long) mid * mid;//cast to long because it will overflow for x=2147395599
			if (multiple == num) //x is a perfect square
			{
				return true;
			}
			else if (multiple < num)
			{
				start = mid + 1;
			}
			else //mid * mid > x
			{
				end = mid - 1;
			}
		}
		//when exit while loop, and still not finding the mid,
		//then we couldn't find the exact sqrt(x) then return false
		return false;
	}
}
