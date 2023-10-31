package leetcode.binarysearch.part3;

/**
 * Omkar's Solution
 * Elements of programming interview
 *
 * sqrt(x) in general will be irrational (can not be expressed in the form of p/q)
 *
 * 2 zones:
 * < sqrt(x) || > sqrt(x), middle is where the narrow range
 * This is called Bisection Search
 *
 */
public class RealSquareRoot
{
	public static double realSqrt(int x)
	{
		double start = 0;
		double end = x;

		while (start <= end)
		{
			double mid = start + (end-start)/2.0;
			double midmid = mid*mid;
			/**
			 * if (mid*mid) is very close to x
			 * define the tolerance
			 */
			if (Math.abs(midmid - x) < 0.00001)
			{
				return mid;
			}
			else if (midmid < x)
			{
				start = mid;
			}
			else //midmid > x
			{
				end = mid;
			}
		}
		return end;
	}
}
