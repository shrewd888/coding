package leetcode.array;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/description/
 * 74. Search a 2D Matrix
 *
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * Take index of row, take index of column, target should exist within this box
 */
public class Search2DMatrix
{
	//Time limit exceeded
	public static boolean searchMatrix(int[][] matrix, int target) {

		int row = matrix.length;
		if (row == 0) return false;
		int col = matrix[0].length;

		int r = 0, c = 0;
		int firstElement = matrix[0][0];
		if (target == firstElement) return true;

		//first num on each row: matrix[0][0] ... matrix[row-1][0] - column=0 (stay the same)
		int left = 0, right = row-1; //vertical
		int mid = left + (right - left)/2;
		while (left < right)
		{
			int element = matrix[mid][0];
			if (target == element)
			{
				return true;
			}
			else if (target < element)
			{
				right = mid-1;
			}
			else //target > element
			{
				left = mid+1;
			}
		} //take row_num = left value
		for (int i=1; i < row; i++)
		{
			int element = matrix[i][0];
			if (target == element)
			{
				return true;
			}
			else if (target < element && i > 0)
			{
				r = i-1; //find row number
				break;
			}
		}
		//r stay the same because we've known the row num
		//int low = matrix[r][0];
		int high = matrix[r][col-1];
		int midColIndex = (0 + (col-1))/2;

		while (c  < col)
		{
			int element = matrix[r][c]; //r stays the same
			if (target == element) return true;
			else if (target < element)
			{
				c = midColIndex - 1;
			}
			else
			{
				c = midColIndex + 1;
			}
			midColIndex = (0 + c)/2;
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
		int target = 3;
		boolean result = searchMatrix(matrix, target);
		System.out.println(result);//true

	}

}
