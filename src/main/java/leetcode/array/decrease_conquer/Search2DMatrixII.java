package leetcode.array.decrease_conquer;
/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * 240. Search a 2D Matrix II
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * Omkar's solution:
 * https://uplevel.interviewkickstart.com/resource/rc-video-210507-518318-247-1560-3843887
 *
 * T(m,n) = O(m+n)
 * S(m,n) = O(1)
 */
public class Search2DMatrixII
{
	public boolean searchMatrix(int[][] matrix, int target) {
		int maxCol = matrix[0].length-1;
		int minRow = 0;
		int maxRow = matrix.length;

		while (maxCol >= 0 && minRow < maxRow)
		{
			if (target == matrix[minRow][maxCol])
			{
				return true;
			}
			else if (target < matrix[minRow][maxCol])
			{
				maxCol--;
			}
			else // target > matrix[minRow][maxCol]
			{
				minRow++;//same column
			}
		}
		return false;
	}
}
