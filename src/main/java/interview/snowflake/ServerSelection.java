package interview.snowflake;

/**
 * https://leetcode.com/discuss/interview-question/2594968/Snowflake-or-OA-or-Server-Selection
 */
/**
 * Time Complexity: O( (m-1) * m)
 */
public class ServerSelection
{
	//n rows >= m col
	public static int findMinFromMax(int[][] grid, int n, int m)
	{
		int minFromMax = Integer.MAX_VALUE;
		int subRowLength = m-1;
		int rowIndex = 0;

		int max = Integer.MIN_VALUE;
		int globalMax = Integer.MIN_VALUE;

		while (rowIndex < n)
		{
			for (int col = 0; col < m; col++)
			{
				int maxRowIndex = rowIndex + subRowLength - 1;
				for (int row = rowIndex; (row <= maxRowIndex && maxRowIndex < n); row++)
				{
					max = Math.max(max, grid[row][col]);
				}
				minFromMax = Math.min(minFromMax, max);
				//reset
				max = Integer.MIN_VALUE;
			}
			//global MAX from min
			globalMax = Math.max(minFromMax, globalMax);
			rowIndex++;
			//reset
			minFromMax = Integer.MAX_VALUE;
		}
		return globalMax;
	}

	public static void main(String ... args)
	{
		int[][] grid = {{1,3,1},{3,1,1},{1,2,2},{1,1,3}};
		int n = 4, m = 3;
		int globalMax = findMinFromMax(grid, n, m);
		System.out.println(globalMax);//2
	}
}
