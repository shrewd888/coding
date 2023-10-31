package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 200. Number of Islands
 * Given an m x n 2D binary grid grid which represents a map of '1's (land)
 * and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 * Example 2:
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 */
public class NumberOfIslands
{
	public int numIslands(char[][] grid) {
		int numIsland = 0;
		if (grid.length == 0) return numIsland;

		//use bfs, FIFO
		Queue<Point> q = new LinkedList<>();
		for (int row=0; row < grid.length; row ++)
		{
			for (int col=0; col < grid[0].length; col++)
			{
				if (grid[row][col] == '1')
				{
					Point p = new Point(row, col);
					q.add(p);
					grid[row][col] = '0';
					bfs(q, p, grid);
					numIsland++;
				}
			}
		}
		return numIsland;
	}

	public static void bfs(Queue<Point> queue, Point point, char[][] grid)
	{
		while (!queue.isEmpty())
		{
			Point p = queue.poll();
			List<Point> neighbors = getNeighbors(p, grid);
			for (Point pt : neighbors)
			{
				if (grid[pt.x][pt.y] == '1')
				{
					queue.add(pt);
					grid[pt.x][pt.y] = '0';
				}
			}
		}
	}

	public static List<Point> getNeighbors(Point point, char[][] grid)
	{
		List<Point> neighbors = new ArrayList<>();
		int x = point.x;//row
		int y = point.y;//col
		//valid neighbors
		//same y
		if (x-1 >= 0)
		{
			neighbors.add(new Point(x-1,y));
		}
		if (x+1 < grid.length)
		{
			neighbors.add(new Point(x+1,y));
		}
		//same x
		if (y-1 >= 0)
		{
			neighbors.add(new Point(x,y-1));
		}
		if (y+1 < grid[0].length)
		{
			neighbors.add(new Point(x,y+1));
		}

		return neighbors;
	}

	public static class Point
	{
		int x;
		int y;
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}

	//Runtime: 27 ms, faster than 5.53% of Java online submissions
	public static void main(String ... args)
	{

	}
}
