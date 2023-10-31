package interview.doordash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/discuss/interview-question/1522955/Doordash-Onsite
 *
 *  https://leetcode.com/problems/walls-and-gates/solution/
 *  286. Walls and Gates
 *
 *  https://leetcode.com/problems/shortest-path-to-get-food/
 *
 *
 * // # A DashMart is a warehouse run by DoorDash that houses items found in
 * // # convenience stores, grocery stores, and restaurants. We have a city with open
 * // # roads, blocked-off roads, and DashMarts.
 * // #
 * // # City planners want you to identify how far a location is from its closest
 * // # DashMart.
 * // #
 * // # You can only travel over open roads (up, down, left, right).
 * // #
 * // # Locations are given in [row, col] format.
 * // #
 * // # Example:
 * // #
 * // # [
 * // # # 0 1 2 3 4 5 6 7 8
 * // # ['X', ' ', ' ', 'D', ' ', ' ', 'X', ' ', 'X'], # 0
 * // # ['X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'X'], # 1
 * // # [' ', ' ', ' ', 'D', 'X', 'X', ' ', 'X', ' '], # 2
 * // # [' ', ' ', ' ', 'D', ' ', 'X', ' ', ' ', ' '], # 3
 * // # [' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X'], # 4
 * // # [' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X'] # 5
 * // # ]
 * // #
 * // # ' ' represents an open road that you can travel over in any direction (up, down, left, or right).
 * // # 'X' represents an blocked road that you cannot travel through.
 * // # 'D' represents a DashMart.
 * // #
 * // # # list of pairs [row, col]
 * // # locations = [
 * // # [2, 2],
 * // # [4, 0],
 * // # [0, 4],
 * // # [2, 6],
 * // # ]
 * // #
 * // # answer = [1, 4, 1, 5]
 * // #
 * // # Implement Function:
 * // # - get_closest_dashmart(city, locations)
 * // #
 * // # Provided:
 * // # - city: List[str]
 * // # - locations: List[List[int]]
 * // #
 * // # Return:
 * // # - answer: List[int]
 */

/**
 * Locations from Dashmart -> how far?
 * Which are less? Location? or Dashmart?
 * If location, start from location.
 * If dashmart, start from dashmart -> easier go from Dashmart
 */
public class DashmartDistance
{

	private static final char EMPTY = ' ';
	private static final char DASH = 'D';
	private static final char LOC = 'L';

	private static final List<int[]> DIRECTIONS = Arrays.asList(
			new int[] { 1,  0}, //move 1 row below (because row start with 0)
			new int[] {-1,  0}, //move 1 row up
			new int[] { 0,  1}, //move 1 col to right
			new int[] { 0, -1} //move 1 col to left
	);


	//find the value/distance at each location
	static List<Integer> get_closest_dashmart(char[][] city,  List<int[]> locations)
	{
		List<Integer> result = new ArrayList<>();
		int m = city.length; //#of row
		if (m == 0) return result;
		int n = city[0].length; //#of row

		//use bfs
		Queue<int[]> q = new LinkedList<>();
		for (int[] location : locations) {
			int row = location[0];
			int col = location[1];

			q.add(new int[] {row, col});
		}

		while (!q.isEmpty()) {
			int[] point = q.poll();
			int row = point[0];
			int col = point[1];
			for (int[] direction : DIRECTIONS) { //direction to move in the city
				int r = row + direction[0];
				int c = col + direction[1];
				if (r < 0 || c < 0 || r >= m || c >= n || city[r][c] != EMPTY) {//only empty path
					continue;
				}
				if (city[r][c] == EMPTY)
				{
					city[row][col] = '0';
					int x = Character.getNumericValue(city[row][col]);
					city[r][c] = (char) (x + 1);
				}
				else
				{
					int x = Character.getNumericValue(city[row][col]);
					city[r][c] = (char) (x + 1);
				}

				q.add(new int[] { r, c });
			}
		}


		for (int[] location : locations) {
			int row = location[0];
			int col = location[1];
			result.add((int) city[row][col]);
		}
		return result;
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

	public static void print(char[][] result)
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
//		char c= '0';
//		char r = (char) (c + 1);
//		System.out.println(r);

		char[][] city = new char[][] {
				{ 'X', ' ', ' ', 'D', ' ', ' ', 'X', ' ', 'X' }, //0
				{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'X' }, //1
				{ ' ', ' ', ' ', 'D', 'X', 'X', ' ', 'X', ' ' }, //2
				{ ' ', ' ', ' ', 'D', ' ', 'X', ' ', ' ', ' ' }, //3
				{ ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X' }, //4
				{ ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X' }  //5
		};
		List<int[]> locations = new ArrayList<>();
		locations.add(new int[] {2, 2});
		locations.add(new int[] {4, 0});
		locations.add(new int[] {0, 4});
		locations.add(new int[] {2, 6});

		List<Integer> result =  get_closest_dashmart(city, locations);

		System.out.println(result);//[1, 4, 1, 5]

		print(city);
	}

}
