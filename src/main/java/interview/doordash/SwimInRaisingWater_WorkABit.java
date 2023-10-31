package interview.doordash;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SwimInRaisingWater_WorkABit
{
	int[][] cache;
	int[][] board = new int[][]{};

	public int swimInWater(int[][] grid) {
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
		return helper(new Pair<>(0,0));
	}

	public int helper(Pair<Integer, Integer> position)
	{
		int curX = position.x;
		int curY = position.y;
		cache[curX][curY] = board[curX][curY];

		int minTime = Integer.MAX_VALUE;

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
							&& cache[pair.x][pair.y] == -1); //only take grid that has not been seen
				})
				.collect(Collectors.toList());

		for (Pair<Integer, Integer> pair : nextMoves)
		{
			int nextMoveValue = board[pair.x][pair.y];
			int curValue = cache[curX][curY];
			if (cache[pair.x][pair.y] == -1 && nextMoveValue > curValue)
			{
				cache[pair.x][pair.y] = helper(pair);
			}
			minTime = Math.min(minTime, cache[pair.x][pair.y]);
		}
		int leastTime = Math.max(minTime, board[position.x][position.y]);
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
}
