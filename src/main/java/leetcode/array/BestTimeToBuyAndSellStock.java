package leetcode.array;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * 121. Best Time to Buy and Sell Stock
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing
 * a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 */
/**
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
/**
 * Example: [7, 1, 5, 3, 6, 4]
 * f: Have to sell at day i
 * f(local): [0, -6, 4, 2, 5, 3]
 * global:   [0,  0, 4, 4, 5, 5]
 */
public class BestTimeToBuyAndSellStock
{
	public static int maxProfit(int[] prices) {
		int n = prices.length;
		//Base Case
		int f = 0;
		int globalbest = 0;

		for (int i=1; i < n; i++)
		{
			f = prices[i] - prices[i-1] + Math.max(0, f);//Have to sell at day i
			globalbest = Math.max(globalbest, f);
		}
		return globalbest;
	}

	public static void main (String[] args)
	{
		int[] nums = {7,1,5,3,6,4};
		System.out.println(maxProfit(nums));
	}
}
