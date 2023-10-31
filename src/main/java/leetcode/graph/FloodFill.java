package leetcode.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/flood-fill/
 * 733. Flood Fill
 *
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 * You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the pixel image[sr][sc].
 *
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color
 * as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on.
 * Replace the color of all of the aforementioned pixels with color.
 *
 * Return the modified image after performing the flood fill.
 *
 * Example 1:
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation: From the center of the image with position (sr, sc) = (1, 1)
 * (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel
 * (i.e., the blue pixels) are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
 *
 * Example 2:
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
 * Output: [[0,0,0],[0,0,0]]
 * Explanation: The starting pixel is already colored 0, so no changes are made to the image.
 */
public class FloodFill
{
	public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
		int len = image.length;
		if (len == 0) return new int[][]{};

		int pointColor = image[sr][sc];
		if (pointColor == color) return image;

		Queue<Point> q = new ArrayDeque<>();
		image[sr][sc] = color;
		q.add(new Point(sr, sc, pointColor));

//		for (int row=0; row < len; row ++)
//		{
//			for (int col=0; col < image[0].length; col++)
//			{
				bfs(q, image, color);
//			}
//		}
		return image;
	}

	public static void bfs(Queue<Point> queue, int[][] image, int color)
	{
		while (!queue.isEmpty())
		{
			Point point = queue.remove();
			List<Point> neighbors =  findNeighbors(image, point.x, point.y);
			for (Point neighbor : neighbors)
			{
				if (neighbor.color == point.color)
				{
					queue.add(neighbor);
					image[neighbor.x][neighbor.y] = color;
				}
			}
		}
	}

	public static List<Point> findNeighbors(int[][] image, int sr, int sc)
	{
		List<Point> neighbors = new ArrayList<>();
		//same row, different col
		if (sc-1 >= 0)
		{
			neighbors.add(new Point(sr, sc-1, image[sr][sc-1]));
		}
		if (sc+1 < image[0].length)
		{
			neighbors.add(new Point(sr, sc+1, image[sr][sc+1]));
		}
		//same col, diff row
		if (sr-1 >= 0)
		{
			neighbors.add(new Point(sr-1, sc, image[sr-1][sc]));
		}
		if (sr+1 < image.length)
		{
			neighbors.add(new Point(sr+1, sc, image[sr+1][sc]));
		}
		return neighbors;
	}

	public static class Point
	{
		int x;
		int y;
		int color;
		public Point(int x, int y, int color)
		{
			this.x = x;
			this.y = y;
			this.color = color;
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

	//Runtime: 2 ms, faster than 32.62% of Java online submissions
	public static void main(String ... args)
	{
		int[][] image = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		int sr = 1, sc = 1, color = 2;
		int[][] imageNew = floodFill(image, sr, sc, color);
		print(imageNew);//[2,2,2][2,2,0][2,0,1]

		int[][] image1 = { { 0, 0, 0 }, { 0, 0, 0 } };
		int sr1 = 0, sc1 = 0, color1 = 0;
		int[][] imageNew1 = floodFill(image1, sr1, sc1, color1);
		print(imageNew1);//[0,0,0][0,0,0]
	}
}
