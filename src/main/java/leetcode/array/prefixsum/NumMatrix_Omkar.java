package leetcode.array.prefixsum;

/**
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 * 304. Range Sum Query 2D - Immutable
 *
 * Given a 2D matrix matrix, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of matrix inside the rectangle defined by its upper
 * left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Implement the NumMatrix class:
 *
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * int sumRegion(int row1, int col1, int row2, int col2)
 * Returns the sum of the elements of matrix inside the rectangle defined by its
 * upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * You must design an algorithm where sumRegion works on O(1) time complexity.
 */
/**
 * https://uplevel.interviewkickstart.com/resource/rc-video-210507-518318-247-1561-3843887
 * range_sum(row1, col1, row2, col2):
 * = prefix_sum(row2, col2) - prefix_sum(row2, col1-1) - prefix_sum(row1-1, col2) + prefix_sum(row1-1, col1-1)
 *
 * prefixsum[x][y] = matrix[x][y] +  prefixsum[x][y-1] + prefixsum[x-1][y] - prefixsum[x-1][y-1]
 *
 */
public class NumMatrix_Omkar
{
	int[][] matrix;
	int[][] prefixSum;

	public NumMatrix_Omkar(int[][] matrix) {
		this.matrix = matrix;
		this.prefixSum = new int[matrix.length][matrix[0].length];

		prefixSum[0][0] = matrix[0][0];

		for (int row = 1; row < matrix.length; row++)
		{
			prefixSum[row][0] = prefixSum[row-1][0] + matrix[row][0];
		}

		for (int col = 1; col < matrix[0].length; col++)
		{
			prefixSum[0][col] = prefixSum[0][col-1] + matrix[0][col];
		}

		for (int row = 1; row < matrix.length; row++)
		{
			for (int col = 1; col < matrix[0].length; col++)
			{
				prefixSum[row][col] = matrix[row][col] + prefixSum[row][col-1] + prefixSum[row-1][col] - prefixSum[row-1][col-1];
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if (prefixSum.length == 0 || matrix.length == 0) return 0;

		if (row1==row2 && col1==col2)
		{
			return prefixSum[row2][col2];
		}
		if (row1==0 && col1==0)
		{
			return prefixSum[row2][col2];
		}
		if (row1==0)
		{
			return prefixSum[row2][col2] - prefixSum[row2][col1-1];
		}
		if (col1==0)
		{
			return prefixSum[row2][col2] -  prefixSum[row1-1][col2];
		}
		return prefixSum[row2][col2] - prefixSum[row2][col1-1] - prefixSum[row1-1][col2] + prefixSum[row1-1][col1-1];
	}


	public static void main (String[] args)
	{
		/**
		 * DOES NOT PASS
		 * Input
		 * ["NumMatrix","sumRegion"]
		 * [[[[-1]]],[0,0,0,0]]
		 *
		 * Output
		 * [null,0]
		 *
		 * Expected
		 * [null,-1]
		 */


	}
}
