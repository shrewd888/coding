package leetcode.binarysearch;

/**
 * 74. Search a 2D Matrix
 *
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 */

/**
 * Omkar's Solution
 * Binary Search Variants Part 1
 * T(n) = O(log mn)
 */
public class Search2DMatrix
{
	//Run 0 ms beats 100%
	public boolean searchMatrix(int[][] matrix, int target)
	{
		int m = matrix.length; //row
		int n = matrix[0].length; //col
		//treating as 1 dimensional matrix (instead of 2D)
		int start = 0;
		int end = (m * n) - 1;
		while (start <= end)
		{
			int mid = start + (end - start) / 2;
			int row = mid / n, col = mid % n; // mid = rowNum * n + col, col from 0 to n-1
			if (matrix[row][col] == target)
			{
				return true;
			}
			else if (matrix[row][col] < target)
			{
				start = mid + 1;
			}
			else
			{
				end = mid - 1;
			}
		}
		return false;
	}
}
