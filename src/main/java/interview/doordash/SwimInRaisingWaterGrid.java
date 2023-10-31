package interview.doordash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Least time for dasher to reach destination
 * https://leetcode.com/discuss/interview-question/2251980/Doordash-or-Phone-or-Least-time-for-dasher-to-reach-destination
 *
 *	Here is an n*n map where grid[i][j] represents the number of blockers in the position (i, j).
 *  In every unit of time, the number of blockers in every position will be decreased by one.
 * 	Every position with one or more blockers is not accessible.
 *
 * 	Assume you are a dasher at DoorDash. You can drive from one position to another 4-directionally adjacent position
 * 	if and only if the position is accessible. You will start at the top left position (0, 0) and your goal is to reach the
 * 	bottom right position (n-1, n-1). Assuming you can drive infinite distances in zero time, what would
 * 	be the least time that you can reach the destination?
 *
 *  Constraints:
 *  n == grid.length
 *  n == grid[i].length
 *  1 <= n <= 50
 *  0 <= grid[i][j] < n^2
 *  Each value grid[i][j] is unique.
 *
 *  Example
 *
 *  0  1  2  3  4
 *  24 16 22 21 5
 *  12 13 14 15 23
 *  11 17 18 19 20
 *  10 9  8  7  6
 *
 * input: int[][] grid
 * output: 16 (int)
 * explanation: 0->1->16->13->12->11->10->9->8->7->6, largest cell value in path is 16
 *
 * https://leetcode.com/problems/swim-in-rising-water/solution/
 * 778. Swim in Rising Water
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 *
 * The rain starts to fall. At time t, the depth of the water everywhere is t.
 * You can swim from a square to another 4-directionally adjacent square if and only if
 * the elevation of both squares individually are at most t.
 * You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 *
 * Example 1:
 * Input: grid = [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 *
 * Example 2:
 * Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation: The final route is shown.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 */
public class SwimInRaisingWaterGrid
{
	int[][] cache;
	int[][] board = new int[][]{};
	int minTime = Integer.MAX_VALUE;

	public int dfs(int[][] grid) {
		int N = grid.length;
		int x = grid[0].length;

		this.board = grid; //copy of the input grid
		cache = new int[x][N];
		//initialize cache
		for (int i=0; i < x; i++)
		{
			for (int j=0; j < N; j++)
			{
				cache[i][j] = -1;
			}
		}
		Set<Pair<Integer, Integer>> seen = new HashSet<>();
		seen.add (new Pair<>(0,0));
		return helper(new Pair<>(0,0), seen);
	}

	public int helper(Pair<Integer, Integer> position, Set<Pair<Integer, Integer>> seen)
	{
		int curX = position.x;
		int curY = position.y;
		//cache[curX][curY] = board[curX][curY];
		seen.add(position);
	//	int minTime = Integer.MAX_VALUE;

		if (curX == board.length-1 && curY == board.length-1)
		{
			return board[curX][curY]; //this is the value of the least time
		}

		List<Pair<Integer, Integer>> nextMoves = Arrays.asList(new Pair<>(curX + 1, curY),
				new Pair<>(curX, curY+1), new Pair<>(curX - 1, curY),
				new Pair<>(curX, curY-1))
				.stream()
				.filter(pair ->
				{
					return (pair.x >= 0 && pair.y >= 0 && pair.x < board.length && pair.y < board[0].length
					//&& cache[pair.x][pair.y] == -1
							//&& pair.x != lastPosition.x && pair.y != lastPosition.y
							&& !seen.contains(pair)
					); //only take grid that has not been seen
				})
				.collect(Collectors.toList());

		for (Pair<Integer, Integer> pair : nextMoves)
		{
			int x = pair.x;
			int y = pair.y;
			int nextMoveValue = board[pair.x][pair.y];
			int curValue = cache[curX][curY];
			if (cache[x][y] == -1
					//&& nextMoveValue > curValue
			)
			{
				cache[x][y] = helper(pair, seen);
			}
			minTime = Math.min(minTime, cache[x][y]);
		}
		int leastTime = Math.max(minTime, board[position.x][position.y]);
		//need to remove because seen is shared by all traversal but we want to share the cache among everything
		seen.remove(position);
		return leastTime;
	}

	public static class Pair<X,Y>
	{
		X x;
		Y y;

		Pair(X x, Y y)
		{
			this.x = x;
			this.y = y;
		}

		X getX()
		{
			return x;
		}

		Y getY()
		{
			return y;
		}

		@Override
		public boolean equals(Object o)
		{
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Pair<?, ?> pair = (Pair<?, ?>) o;
			return Objects.equals(x, pair.x) && Objects.equals(y, pair.y);
		}

		@Override
		public int hashCode()
		{
			return Objects.hash(x, y);
		}
	}

	public static void main(String ... args)
	{
		SwimInRaisingWaterGrid s = new SwimInRaisingWaterGrid();
		int[][] grid = new int [][]{{0,2}, {1,3}};
		int result = s.dfs(grid);
		System.out.println(result);//3

		s = new SwimInRaisingWaterGrid();
		grid = new int [][]{{10,12,4,6},{ 9,11,3,5 },{1,7,13,8},{2,0,15,14}};
		result = s.dfs(grid);
		System.out.println(result);//14

		s = new SwimInRaisingWaterGrid();
		grid = new int [][]{{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
		result = s.dfs(grid);
		System.out.println(result);//16

		s = new SwimInRaisingWaterGrid();
		grid = new int [][]{{0,1,2,3,4},{24,16,22,21,5},{12,13,14,15,23},{11,17,18,19,20},{10,9,8,7,6}};
		result = s.dfs(grid);
		System.out.println(result);//16
	}
}
