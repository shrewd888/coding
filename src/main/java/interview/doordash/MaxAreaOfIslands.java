package interview.doordash;

/**
 * 695. Max Area of Island
 *
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 * Example 1:
 * Input: grid = [
 * [0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 *
 * Example 2:
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 */

/**
 * Time Complexity: O(R*C)O(R∗C), where RR is the number of rows in the given grid,
 * and CC is the number of columns. We visit every square once.
 * Space complexity: O(R*C)O(R∗C), the space used by seen to keep track of visited squares,
 * and the space used by the call stack during our recursion.
 */
public class MaxAreaOfIslands
{
	int[][] grid;
	boolean[][] seen;

	public int area(int r, int c) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length ||
				seen[r][c] || grid[r][c] == 0)
			return 0;
		seen[r][c] = true;
		return (1 + area(r+1, c) + area(r-1, c)
				+ area(r, c-1) + area(r, c+1));
	}

	public int maxAreaOfIsland(int[][] grid) {
		this.grid = grid;
		seen = new boolean[grid.length][grid[0].length];//initialize
		int ans = 0;
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {

				ans = Math.max(ans, area(r, c));
			}
		}
		return ans;
	}
}
