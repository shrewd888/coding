package leetcode.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/
 * 118. Pascal's Triangle
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly
 * above it as shown:
 *
 * Example 1:
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 * Example 2:
 * Input: numRows = 1
 * Output: [[1]]
 */
public class PascalTriangle
{
	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> result = new ArrayList<>();
		int i=0;

		List<Integer> integers = new ArrayList<>();
		//i=0 < 1 && 1 < 5
		//i=1 < 2 && 2 < 5
		//i=2 < 3 && 3 < 5
		//i=3 < 4 && 4 < 5
		while (i < numRows)
		{
			integers.add(1);
			if (integers.size()==i+1)
			{
				result.add(integers);
				integers = new ArrayList<>();
				i++;
			}
		}
		//row=1 -> list size = 2
		//row=2 -> list size = 3
		for (int row=2; row < result.size(); row++)
		{
			List<Integer> numPerRow = result.get(row);
			List<Integer> numRowAbove = result.get(row-1);
			for (int col=1; col < numPerRow.size()-1; col++)
			{
				Integer numAbove = numRowAbove.get(col-1) + numRowAbove.get(col);
				numPerRow.set(col, numAbove);
			}
		}
		return result;
	}

	public static void main(String ... args) {
		List<List<Integer>> result = generate(5);
		System.out.println(result);
	}
}

