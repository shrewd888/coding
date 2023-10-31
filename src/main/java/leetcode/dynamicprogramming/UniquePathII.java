package leetcode.dynamicprogramming;

/**
 * https://leetcode.com/problems/unique-paths-ii/
 * 63. Unique Paths II
 * You are given an m x n integer array grid.
 * There is a robot initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m-1][n-1]).
 *
 * The robot can only move either down or right at any point in time.
 *
 * An obstacle and space are marked as 1 or 0 respectively in grid.
 * A path that the robot takes cannot include any square that is an obstacle.
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * Example 1:
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 *
 * Example 2:
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 *
 * Time Complexity: O(mn)
 * Space Complexity: O(mn)
 */
public class UniquePathII
{
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int rowSz = obstacleGrid.length;
		int colSz = obstacleGrid[0].length;

		if (rowSz == 0 || rowSz == 1)
			return 0;

		//Initialize cache that we are building to store the solution to subproblems
		//f(i,j) = f(i-1,j) + f(i,j-1) -> #of unique paths from (0,0) to (i,j)
 		int table[][] = new int[rowSz][colSz];
		/**
		 * Base Case:
		 * Top Left corner
		 * Top row
		 * Top col
		 */
		if (obstacleGrid[0][0] == 1) {
			return 0;
		}
		else {
			table[0][0] = 1;
		}
		//fill in row-0 with 1
		for (int col=1; col < colSz; col++)
		{
			//if there is an obstacle we won't move further
			if (obstacleGrid[0][col] == 1)
				 break;
			table[0][col] = 1;
		}
		//fill in col-0 with 1
		for (int row=1; row < rowSz; row++)
		{
			if (obstacleGrid[row][0] == 1)
				break;
			table[row][0] = 1;
		}
		//Recursive Case
		for (int row=1; row < rowSz; row++)
		{
			for (int col=1; col < colSz; col++)
			{
				//there is an obstacle in my own cell
				if (obstacleGrid[row][col] == 1)
					table[row][col] = 0;
				else
					table[row][col] = table[row-1][col] + table[row][col-1];
			}
		}
		return table[rowSz-1][colSz-1];
	}

	public static void main(String ... args) {
		int[][] obstacleGrid = new int[][]{{0,1,0},{0,0,0},{0,0,0}};
		int num = uniquePathsWithObstacles(obstacleGrid);
		System.out.println(num);//3

		obstacleGrid = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
		num = uniquePathsWithObstacles(obstacleGrid);
		System.out.println(num);//2

		obstacleGrid = new int[][]{{0,1},{0,0}};
		num = uniquePathsWithObstacles(obstacleGrid);
		System.out.println(num);//1
	}
}
