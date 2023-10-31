package leetcode.array.decrease_conquer;

/**
 * https://leetcode.com/problems/maximal-square/
 * 221. Maximal Square
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * Example 1:
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 *
 * Example 2:
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 *
 * Example 3:
 * Input: matrix = [["0"]]
 * Output: 0
 */
/**
 * https://uplevel.interviewkickstart.com/resource/rc-video-210507-518318-247-1560-3843887
 * Omkar's solution
 * T(m,n) = O (mxn)
 * S(m,n) = O (mxn) -> Aux space
 */
public class MaximalSquare
{
	public static int maximalSquare(char[][] matrix) {
		//initialize table m x n
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] table = new int[row][col];

		int globalmax = 0;
		//fill in row 0
		for (int i=0; i<col; i++)
		{
			if (matrix[0][i] == '1')
			{
				table[0][i] = 1;
				globalmax = 1;
			}
			else
			{
				table[0][i] = 0;
			}
		}

		//fill in col 0
		for (int i=0; i<row; i++)
		{
			if (matrix[i][0] == '1')
			{
				table[i][0] = 1;
				globalmax = 1;
			}
			else
			{
				table[i][0] = 0;
			}
		}

		for (int i=1; i<row; i++)
		{
			for (int j=1; j<col; j++)
			{
				if (matrix[i][j] == '1')
				{
					table[i][j] = 1 + Math.min(Math.min(table[i-1][j-1], table[i][j-1]), table[i-1][j]);
					globalmax = Math.max(globalmax, table[i][j]);
				}
				else
				{
					table[i][j] = 0;
				}
			}
		}

		print(table);//[1,0,1,0,0][1,0,1,1,1][1,1,1,2,2][1,0,0,1,0]
		return globalmax * globalmax;
	}

	public static void print(int[][] result)
	{
		for (int i=0; i < result.length; i++)
		{
			System.out.print("[");
			for (int j=0; j < result[0].length; j++)
			{
				System.out.print(result[i][j]);
				if (j < result[0].length-1)
					System.out.print(",");
			}
			System.out.print("]");
		}
		System.out.println();
	}


	public static void main(String ... args) {
		char[][] matrix = {{'1','0','1','0','0'}, {'1','0','1','1','1'}, {'1','1','1','1','1'},{'1','0','0','1','0'}};
		System.out.println(maximalSquare(matrix));//4
	}
}
