package interview.roblox;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/word-search/
 * 79. Word Search
 */
//TODO: redo , doesn't pass all test cases
public class WordSearch
{
	public static boolean exist(char[][] board, String word) {
		int len = board.length;
		char[] wordChars = word.toCharArray();
		boolean[][] visited = new boolean[len][board[0].length];

		Queue<Point> q = new ArrayDeque<>();
		boolean result = false;
		int index = 0;
		for (int row=0; row < len; row ++)
		{
			for (int col=0; col < board[0].length; col++)
			{
				if (board[row][col] == wordChars[index])
				{
					q.add(new Point(row, col, board[row][col]));
					visited[row][col] = true;
					result =  bfs(q, board, visited, wordChars, 0);
					if (result) return result;
				}
				//reset
				visited = new boolean[len][board[0].length];
			}

		}
		return result;
	}

	public static boolean bfs(Queue<Point> queue, char[][] board, boolean[][] visited, char[] wordChars, int index)
	{
		List<Point> points = new ArrayList<>();
		while (!queue.isEmpty())
		{
			Point point = queue.remove();
			//if (point.character != wordChars[index]) break;

			index++;
			points.add(point);

			if (index < wordChars.length)
			{
				List<Point> neighbors = findNeighbors(board, point.x, point.y, visited);
				for (Point neighbor : neighbors)
				{
					if (neighbor.character == wordChars[index])
					{
						queue.add(neighbor);
						visited[neighbor.x][neighbor.y] = true;
					}
				}
			}
			else
			{
				if (point.character == wordChars[wordChars.length-1])
					return true;
			}
		}
		return false;
	}

	public static List<Point> findNeighbors(char[][] board, int row, int col, boolean[][] visited)
	{
		List<Point> neighbors = new ArrayList<>();
		//same row, different col
		if (col-1 >= 0 && !visited[row][col-1])
		{
			neighbors.add(new Point(row, col-1, board[row][col-1]));
		}
		if (col+1 < board[0].length && !visited[row][col+1])
		{
			neighbors.add(new Point(row, col+1, board[row][col+1]));
		}
		//same col, diff row
		if (row-1 >= 0 && !visited[row-1][col])
		{
			neighbors.add(new Point(row-1, col, board[row-1][col]));
		}
		if (row+1 < board.length && !visited[row+1][col])
		{
			neighbors.add(new Point(row+1, col, board[row+1][col]));
		}
		return neighbors;
	}


	public static class Point
	{
		int x;
		int y;
		char character;
		public Point(int x, int y, char character)
		{
			this.x = x;
			this.y = y;
			this.character = character;
		}
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
		System.out.println(exist(board6, word6));//should TRUE but return false

	}

}
