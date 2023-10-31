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
public class NumMatrix
{
	int[][] matrix;

	public NumMatrix(int[][] matrix) {
		this.matrix = matrix;

		for (int row = 0; row < matrix.length; row++)
		{
			for (int col = 1; col < matrix[0].length; col++)
			{
				matrix[row][col] = matrix[row][col-1] + matrix[row][col];
			}
		}
	}
	//Runtime 300 ms beats 11% -> T(n) = O(mxn) -> NOT O(1)
	public int sumRegion(int row1, int col1, int row2, int col2) {
		int sum = 0;
		int prevCol = (col1 - 1);

		for (int row=row1; row <= row2; row++)
		{
			if (prevCol >= 0)
			{
				sum = sum + matrix[row][col2] - matrix[row][prevCol];
			}
			else
			{
				sum = sum + matrix[row][col2];
			}
		}
		return sum;
	}
}
