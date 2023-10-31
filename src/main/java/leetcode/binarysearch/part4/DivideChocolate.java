package leetcode.binarysearch.part4;

/**
 * 1231. Divide Chocolate
 *
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
 *
 * You want to share the chocolate with your k friends so you start cutting the chocolate bar into k + 1 pieces using k cuts,
 * each piece consists of some consecutive chunks.
 *
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 *
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 *
 * Example 1:
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], k = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 *
 * Example 2:
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], k = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 *
 * Example 3:
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], k = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 */

import java.util.Arrays;

/**
 * Omkar's Solution
 * 1. What are we trying to optimize?
 *    Need to optimize (maximize) total sweetness of the piece I eat.
 *    This is the minimum (the least sweet piece) of all other pieces.
 * 2. What is the range of values? How small can my total sweetness?
 *    Lower bound: min of all the values in the sweetness array
 *    Upper bound: sum of all the values in the sweetness array
 *    If k=0, upper bound can be (sum of all the values)/2
 * 3. Left zone: Minimum sweetness
 *    Right zone: Max sweetness
 *    I could go as high as possible at the end of left zone, where end < start.
 */
public class DivideChocolate
{
	//Runtime 19 ms beats 11 %
	public static int maximizeSweetness(int[] sweetness, int k) {
		int start = 0; //start is the min(all values in the array)
		int end = Arrays.stream(sweetness).sum();

		for (int sweet : sweetness)
		{
			start = Math.min(start, sweet);
		}

		while (start <= end)
		{
			int mid = start + (end - start)/2;//mid is the sweetness
			/**
			 * Can everyone get a chocolate piece whose sweetness >= mid?
			 * Is it possible to break the choc bar into k+1 pieces such that each
			 * piece has sweetness >= mid?
			 * If not possible (if num of pieces I create < k+1): -> too much sweetness
			 * end = mid - 1
			 * else: //I am in the feasible (left) zone
			 * start = mid + 1
			 */
			int totalSweetness = 0;
			int cut = 0;
			for (int i=0; i < sweetness.length; i++)
			{
				totalSweetness += sweetness[i];
				if (totalSweetness >= mid)
				{
					cut++;
					//reset
					//this is zero because the last sweetness we add should be part of the previous cut,
					//and everyone can get more sweetness (because >= mid)
					totalSweetness = 0;
				}
			}
			if (cut > k)
			{
				start = mid + 1;
			}
			else
			{
				end = mid - 1;
			}
		}
		return end;//because I get the least sweet, and end < start
	}
}
