package leetcode.array;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * 309. Best Time to Buy and Sell Stock with Cooldown
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * Find the maximum profit you can achieve. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously
 * (i.e., you must sell the stock before you buy again)
 *
 * Example 1:
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 *
 * Example 2:
 * Input: prices = [1]
 * Output: 0
 */

/**
 * g(i) = max(g(i-1), prices[i] - prices[i-1] + max(g(i-3), f(i-1)))
 * f(i) = prices[i] - prices[i-1] + max(g(i-3), f(i-1))
 *
 * g(i) = max(g(i-1), f(i))
 * Base Case:
 * f(0) = 0
 * g(0) = 0
 *
 * Time Complexity: O(n)
 * Aux Space = O(n), can be brought down to O(1)
 */
//TODO: Revisit
public class BestTimeToBuyAndSellStockWithCooldown
{
	public int maxProfit(int[] prices) {
		int n = prices.length;
		int[] g = new int[n];
		int[] f = new int[n];
		//Base Case
		f[0] = 0;
		g[0] = 0;
		for (int i=1; i < n; i++)
		{
			//if "buy" ending at index i-1, any previous transaction happened at index i-3
			//f[i] = max profit with any #of transactions up to index i with a "sell" happening on day i

			f[i] = prices[i] - prices[i-1] + Math.max(getgIndex(i-3, g), f[i-1]);
			g[i] = Math.max(g[i-1], f[i]);
		}
		return g[n-1];
	}

	public int getgIndex(int index, int[] g)
	{
		if (index < 0) return 0;
		return g[index];
	}
}
