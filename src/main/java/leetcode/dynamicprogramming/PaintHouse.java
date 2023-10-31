package leetcode.dynamicprogramming;

/**
 * https://leetcode.com/problems/paint-house/
 * 256. Paint House
 * There is a row of n houses, where each house can be painted one of three colors: red, blue, or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.
 *
 * For example, costs[0][0] is the cost of painting house 0 with the color red;
 * costs[1][2] is the cost of painting house 1 with color green, and so on...
 * Return the minimum cost to paint all houses.
 *
 * Example 1:
 * Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 * Minimum cost: 2 + 5 + 3 = 10.
 *
 * Example 2:
 * Input: costs = [[7,6,2]]
 * Output: 2
 */
public class PaintHouse
{
	public static int minCost(int[][] costs)
	{
		int rowLength = costs.length;
		int colLength = costs[0].length;

		int[][] table = new int[rowLength][colLength];
		//cost[i][0] : row i, Red
		//cost[i][1] : row i, Blue
		//cost[i][2] : row i, Green
		//Base Case
		table[0][0] = costs[0][0];
		table[0][1] = costs[0][1];
		table[0][2] = costs[0][2];
		for (int i = 1; i < rowLength; i++)
		{
			table[i][0] = costs[i][0] + Math.min(table[i-1][1], table[i-1][2]);
			table[i][1] = costs[i][1] + Math.min(table[i-1][0], table[i-1][2]);
			table[i][2] = costs[i][2] + Math.min(table[i-1][0], table[i-1][1]);
		}
		return Math.min(Math.min(table[rowLength - 1][0], table[rowLength - 1][1]), table[rowLength - 1][2]);
	}

	public static void main(String ... args) {
		int[][] costs = new int[][]{{17,2,17},{16,16,5},{14,3,19}};
		int min = minCost(costs);
		System.out.println(min);
	}
}
