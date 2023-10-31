package leetcode.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 * For each step, you may move to an adjacent number of the row below.
 * More formally, if you are on index i on the current row,
 * you may move to either index i or index i + 1 on the next row.
 *
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 */

/**
 * f(r,c) = Min path sum from apex to (r,c)
 *
 * Time complexity: O(r^2)
 * Space complexity: O(r^2)
 */
public class Triangle
{
	public static int minimumTotal(List<List<Integer>> triangle) {
		int numRows = triangle.size();
		List<List<Integer>> table = new ArrayList<>();
		List<Integer> innerTable = new ArrayList<>();
		//Base Cases
		List<Integer> firstElement = triangle.get(0);
		innerTable.add(0, firstElement.get(0));
		table.add(innerTable);

		//Fill in the edges of the inner list, start from row 1, col = row+1
		int row = 1;
		while (row < numRows)
		{
			innerTable = new ArrayList<>();
			int col = row + 1;
			List<Integer> innerTriangle = triangle.get(row);
			Integer leftEdge = innerTriangle.get(0);
			Integer rightEdge = innerTriangle.get(innerTriangle.size()-1);

			List<Integer> prevInnerTableRow = table.get(row-1);
			Integer leftPrevInnerTableEdge = prevInnerTableRow.get(0);
			Integer rightPrevInnerTableEdge = prevInnerTableRow.get(prevInnerTableRow.size()-1);

			innerTable.add(0, leftPrevInnerTableEdge + leftEdge);
			int index = 1;
			while (index < col-1)
			{
				innerTable.add(index, 0);
				index++;
			}
			innerTable.add(col-1, rightPrevInnerTableEdge + rightEdge);
			table.add(innerTable);
			row++;
		}

		//Start filling the remaining table
		for (int rowT=2; rowT < table.size(); rowT++)
		{
			List<Integer> prevInner = table.get(rowT-1);
			List<Integer> inner = table.get(rowT);
			List<Integer> innerT = triangle.get(rowT);
			for (int col=1; col < inner.size()-1; col++)
			{
				Integer val = innerT.get(col) + Math.min(prevInner.get(col-1), prevInner.get(col));
				inner.set(col, val);
			}
		}

		int min = table.get(table.size()-1)
				.stream()
				.mapToInt(v -> v)
				.min().getAsInt();
		return min;
	}

	public static void main(String ... args) {
		List<List<Integer>> triangle = new ArrayList<>();
		List<Integer> inner = Arrays.asList(2);
		triangle.add(inner);
		inner = Arrays.asList(3, 4);
		triangle.add(inner);
		inner = Arrays.asList(6, 5, 7);
		triangle.add(inner);
		inner = Arrays.asList(4, 3, 8, 1);
		triangle.add(inner);

		int result = minimumTotal(triangle);
		System.out.println(result);

		triangle = new ArrayList<>();
		inner = Arrays.asList(-10);
		triangle.add(inner);
		result = minimumTotal(triangle);
		System.out.println(result);

		triangle = new ArrayList<>();
		inner = Arrays.asList(2);
		triangle.add(inner);
		inner = Arrays.asList(1, 0);
		triangle.add(inner);
		inner = Arrays.asList(1, 2, 1);
		triangle.add(inner);
		inner = Arrays.asList(3, 3, 1, 2);
		triangle.add(inner);
		inner = Arrays.asList(4, 5, 1, 2, 3);
		triangle.add(inner);

		result = minimumTotal(triangle);
		System.out.println(result);

		List<List<Integer>> triangle1 = new ArrayList<>();
		List<Integer> inner1 = Arrays.asList(-1);
		triangle.add(inner1);
		inner1 = Arrays.asList(2, 3);
		triangle.add(inner1);
		inner1 = Arrays.asList(1, -1, -3);
		triangle.add(inner1);

		int result1 = minimumTotal(triangle);
		System.out.println(result1);

	}
}
