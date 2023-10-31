package interview.roblox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/word-search/solutions/27811/my-java-solution/?page=2
 * 79. Word Search
 */
public class WordSearch_DFS
{
	static boolean[][] visited;

	public static boolean exist(char[][] board, String word) {
		visited = new boolean[board.length][board[0].length];
		List<int[]> coords = new ArrayList<>();

		for(int row = 0; row < board.length; row++){
			for(int col = 0; col < board[row].length; col++){
				if ((word.charAt(0) == board[row][col]) && search(board, word, row, col, 0, coords))
				{
					Collections.reverse(coords);
					coords.forEach(s -> print(s));
					return true;
				}
			}
		}

		return false;
	}

	private static boolean search(char[][]board, String word, int row, int col, int index, List<int[]> coords)
	{
		if(index == word.length())
		{
			return true;
		}

		if (row >= board.length || row < 0 || col >= board[row].length || col < 0
				|| board[row][col] != word.charAt(index)
				|| visited[row][col])
		{
			return false;
		}

		visited[row][col] = true;
		if(search(board, word, row-1, col, index+1, coords) ||
				search(board, word, row+1, col, index+1, coords) ||
				search(board, word, row, col-1, index+1, coords) ||
				search(board, word, row, col+1, index+1, coords))
		{
			coords.add(new int[]{row, col});
			System.out.println(row + "," + col);
			return true;
		}

		visited[row][col] = false;
		return false;
	}

	static void print(int[] arr)
	{
		System.out.print("[");
		for (int i=0; i < arr.length; i++)
		{
			System.out.print(arr[i]);
			if (i < arr.length-1)
				System.out.print(",");
		}
		System.out.print("] ");
	}

	public static void main(String ... args)
	{
		char[][] board = { {'A','B','C','E' }, { 'S','F','C','S' }, {'A','D','E','E'} };
		String word = "ABCCED";
		System.out.println(exist(board, word));//true

		char[][] board1 = { {'A','B','C','E' }, { 'S','F','C','S' }, {'A','D','E','E'} };
		String word1 = "SEE";
		System.out.println(exist(board1, word1));//true

		char[][] board2 = { {'A'} };
		String word2 = "A";
		System.out.println(exist(board2, word2));//true

		char[][] board3 = { {'C','A','A' }, { 'A','A','A' }, {'B','C','D'} };
		String word3 = "AAB";
		System.out.println(exist(board3, word3));//true

		char[][] board4 = { {'A','B','C', 'E' }, { 'S','F','C', 'S' }, {'A','D','E','E'} };
		String word4 = "ABCB";
		System.out.println(exist(board4, word4));//false

		char[][] board5 = { {'C','A','A' }, { 'A','A','A' }, {'B','C','D'} };
		String word5 = "AAB";
		System.out.println(exist(board5, word5));//true

		char[][] board6 = { {'A','B','C','E'}, { 'S','F','E', 'S' }, {'A','D','E', 'E'} };
		String word6 = "ABCESEEEFS";
		System.out.println(exist(board6, word6));//true

	}

}
