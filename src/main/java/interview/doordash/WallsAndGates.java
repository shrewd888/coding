package interview.doordash;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/walls-and-gates/solution/
 * 286. Walls and Gates
 *
 * You are given an m x n grid rooms initialized with these three possible values.
 * -1 A wall or an obstacle.
 *  0 A gate.
 *  INF Infinity means an empty room.
 *
 * We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate.
 * If it is impossible to reach a gate, it should be filled with INF.
 *
 * Example 1:
 * Input: rooms =
 * [[2147483647, -1, 0, 2147483647],
 *  [2147483647, 2147483647, 2147483647, -1],
 *  [2147483647,-1,2147483647,-1],
 *  [0,-1,2147483647,2147483647]]
 *
 * Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 *
 * Example 2:
 * Input: rooms = [[-1]]
 * Output: [[-1]]
 *
 * #of Gates is < #of empty rooms
 * Instead of searching from an empty room to the gates, how about searching the other way round?
 * In other words, we initiate breadth-first search (BFS) from all gates at the same time.
 * Since BFS guarantees that we search all rooms of distance d before searching rooms of distance d + 1,
 * the distance to an empty room must be the shortest.
 */

/**
 * Time complexity : O(mn)O(mn).
 *  Space complexity : O(mn)O(mn).
 *  The space complexity depends on the queue's size. We insert at most m × n points into the queue.
 */

public class WallsAndGates
{
	private static final int EMPTY = Integer.MAX_VALUE;
	private static final int GATE = 0;

	private static final List<int[]> DIRECTIONS = Arrays.asList(
			new int[] { 1,  0}, //move 1 row below (because row start with 0)
			new int[] {-1,  0}, //move 1 row up
			new int[] { 0,  1}, //move 1 col to right
			new int[] { 0, -1} //move 1 col to left
	);

	public static void wallsAndGates(int[][] rooms) {
		int m = rooms.length; //#of row
		if (m == 0) return;
		int n = rooms[0].length; //#of col

		//use bfs
		Queue<int[]> q = new LinkedList<>();
		for (int row = 0; row < m; row++) {
			for (int col = 0; col < n; col++) {
				if (rooms[row][col] == GATE) {
					q.add(new int[] { row, col });
				}
			}
		}

		while (!q.isEmpty()) {
			int[] point = q.poll();
			int row = point[0];
			int col = point[1];
			for (int[] direction : DIRECTIONS) {
				int r = row + direction[0];
				int c = col + direction[1];
				if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
					continue;
				}
				rooms[r][c] = rooms[row][col] + 1;
				q.add(new int[] { r, c });
			}
		}
	}

	public static void print(int[][] result)
	{
		for (int i=0; i < result.length; i++)
		{
			System.out.print("[");
			for (int j=0; j < result[0].length; j++)
			{
				System.out.print(result[i][j]);
				if (j < result[0].length-1)
					System.out.print(",");
			}
			System.out.print("]");
		}
		System.out.println();
	}

	public static void main(String ... args)
	{
		int[][] rooms = new int[][]{{2147483647, -1, 0, 2147483647}, {2147483647, 2147483647, 2147483647, -1}, {2147483647,-1,2147483647,-1}, {0,-1,2147483647,2147483647}};
		wallsAndGates(rooms);

		print(rooms);
	}

}
