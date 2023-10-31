package leetcode.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N-Queens
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.'
 * both indicate a queen and an empty space, respectively.
 *
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 *
 * Input: n = 1
 * Output: [["Q"]]
 */

/**
 * Time Complexity:
 * Without pruning/backtracking: O(n^n), with pruning: O(n!)
 * Space Complexity:
 *
 */
public class NQueens
{
	public static List<List<String>> solveNQueens(int n) {
		List<List<String>> result = new ArrayList<>();
		helper(0, n, new ArrayList<>(), result);
		return result;
	}

	//Q0 to Q(i-1) is already being placed
	//Qi...Q(n-1)
	//index = Qi, slate = column numbers for queens 0...i-1
	//slate = column value because we know Q0 is on row-0, Q1 in row-1,...
	// _ _ _ _ _ _ _ -> each slate is a column no,
	// there are N columns: N x N x N ... (each slate has N choices, ignore the constraints)
	public static void helper(int i, int n, List<String> slates, List<List<String>> result)
	{
		//Backtracking Case
		//if there is a conflict on the slate, I will backtrack
		//Detect conflict between Qi-1 and earlier Queens
		int lastQueen = i-1; //or length(slate)-1
		//for earlierQ in 0 to lastQueen-1:
		//is there a conflict between earlierQ and lastQueen. They are already in separate rows
		for (int k=0; k < (i-1); k++)
		{
			//Check for column conflict
			if (slates.get(k) == slates.get(lastQueen))
			{
				return;
			}
			//Check for diagonal conflict
			int rowdiff = Math.abs(lastQueen - k);
			int coldiff = Math.abs(Integer.valueOf(slates.get(lastQueen)) - Integer.valueOf(slates.get(k)));
			if (rowdiff == coldiff)
				return;
		}

		//Base Case
		if (i == n)
		{
			//need to format the string, now assume array is a sequence of column numbers
			List<String> copySlates = new ArrayList<>(slates);
			result.add(copySlates);
			return;
		}
		//Recursive
		for (int j=0; j<n; j++)
		{
			//Queen i will be placed in col-j
			slates.add(String.valueOf(j));
			helper(i+1, n, slates, result);
			slates.remove(slates.size()-1);
		}
	}

	public static void main(String ... args)
	{
		List<List<String>> result = solveNQueens(2);
		System.out.println(result);
		System.out.println("size: "+result.size());
	}
}
