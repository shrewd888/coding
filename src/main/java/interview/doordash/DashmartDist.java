package interview.doordash;
// A DashMart is a warehouse run by DoorDash that houses items found in convenience stores, grocery stores, and restaurants. We have a city with open roads, blocked-off roads, and DashMarts.

// City planners want you to identify how far a location is from its closest DashMart.

// You can only travel over open roads (up, down, left, right).

// Locations are given in [row, col] format.

// Example 1
// [
// #     0    1    2    3    4    5    6    7    8
//     ['X', ' ', ' ', 'D', ' ', ' ', 'X', ' ', 'X'], # 0
//     ['X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'X'], # 1
//     [' ', ' ', ' ', 'D', 'X', 'X', ' ', 'X', ' '], # 2
//     [' ', ' ', ' ', 'D', ' ', 'X', ' ', ' ', ' '], # 3
//     [' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X'], # 4
//     [' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X']  # 5
// ]

// ' ' represents an open road that you can travel over in any direction (up, down, left, or right).
// 'X' represents an blocked road that you cannot travel through.
// 'D' represents a DashMart.

// # list of pairs [row, col]
// locations = [
//     [200, 200],
//     [1, 4],
//     [0, 3],
//     [5, 8],
//     [1, 8],
//     [5, 5]
// ]

// answer = [-1, 2, 0, -1, 6, 9]

// Provided:
// - city: char[][]
// - locations: int[][2]

// Return:
// - answer: int[]
// Return a list of the distances from a given point to its closest DashMart.

// Expected Answer: In this case, you should return [-1, 2, 0, -1, 6, 9].

// Note: The candidate should figure out all the special cases from this example:

// The location is not in the city (should return -1)
// The location is not accessible by a DashMart (should return -1)
// The location is a DashMart (should return 0)
// The location is a blocked road. *(Treat it the same as an open road location, but you still cannot pass over other blocked roads)
// If the query location is a blocked road, then you can leave the location on any open roads. [1, 8] is a solid example of this. But you cannot travel over blocked roads to get to a DashMart (e.g. [5, 8]) ... "We're asking how far that location is from its closest DashMart if you can only travel over open roads."

//runtime: BFS O(mn)
//space: O(mn) -> Sz of the Queue



// The location is not in the city (should return -1)
// The location is not accessible by a DashMart (should return -1)
// The location is a DashMart (should return 0)
// The location is a blocked road. *(Treat it the same as an open road location, but you still cannot pass over other blocked roads)
// If the query location is a blocked road, then you can leave the location on any open roads. [1, 8] is a solid example of this. But you cannot travel over blocked roads to get to a DashMart (e.g. [5, 8]) ... "We're asking how far that location is from its closest DashMart if you can only travel over open roads."

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DashmartDist {

	static final List<int[]> DIR = Arrays.asList(
			new int[] {1, 0}, //move 1 row below
			new int[] {-1,0}, //move 1 row up
			new int[] {0, 1}, //move 1 col to right
			new int[] {0, -1} //move 1 col to left

	);

	static List<Integer> getClosestDashmart(char[][] cities,  List<int[]> locations) {
		List<Integer> result = new ArrayList<Integer>();


		int m = cities.length; //#of row
		if (m == 0) return result;


		int n = cities[0].length; //#of col
		int[][] newLocs = new int[m][n]; //initialize with 0

		Queue<int[]> q = new LinkedList<>();
		for (int row = 0; row < m ; row++)
		{
			for (int col = 0; col < n ; col++)
			{
				//add all 'D' into the q and traverse from here
				if (cities[row][col] == 'D')
				{
					q.add(new int[] {row, col});
					newLocs[row][col] = 0;

					System.out.println("row: " + row);
					System.out.println("col: " + col);
				}
				else if (cities[row][col] == 'X')
				{
					newLocs[row][col] = -1;
				}
			}
		}


		while (!q.isEmpty())
		{
			int[] grid = q.poll();
			int currRow = grid[0];
			int currCol = grid[1];
			//take all valid neighbors
			for (int[] neighbor : DIR)
			{
				int r = currRow + neighbor[0];
				int c = currCol + neighbor[1];

				//only take valid neighbors which is ' '
				if (r <0 || c<0 || r >=m || c >=n || cities[r][c] !=  ' ' || newLocs[r][c] != -1)
				{

					continue;
				}
				newLocs[r][c] = newLocs[currRow][currCol] + 1;

				System.out.println("r: " + r);
				System.out.println("c: " + c);

				q.add (new int[] {r, c});
			}
		}
		//int index = 0;
		for (int[] loc : locations)
		{
			int r = loc[0];
			int c = loc[1];
			if (r <0 || c<0 || r >=m || c >=n)
			{
				result.add(-1);
			}
			else{
				result.add(newLocs[r][c]);
			}
			// index++;
		}
		return result;
	}

	public static void main(String[] args) {

		char[][] city = {
				// 0    1    2    3    4    5    6    7    8
				{ 'X', ' ', ' ', 'D', ' ', ' ', 'X', ' ', 'X' }, // 0
				{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'X' }, // 1
				{ ' ', ' ', ' ', 'D', 'X', 'X', ' ', 'X', ' ' }, // 2
				{ ' ', ' ', ' ', 'D', ' ', 'X', ' ', ' ', ' ' }, // 3
				{ ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X' }, // 4
				{ ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X' }  // 5
		};

		List<int[]> locations = new ArrayList<>();
		locations.add (new int[]  { 200, 200 });
		locations.add (new int[]  { 1, 4 });
		locations.add (new int[]  { 0, 3 });
		locations.add (new int[]   { 5, 8 });
		locations.add (new int[]  { 1, 8 });
		locations.add (new int[]  { 5, 5 });


		// int[][] locations = {
		//     { 200, 200 },
		//     { 1, 4 },
		//     { 0, 3 },
		//     { 5, 8 },
		//     { 1, 8 },
		//     { 5, 5 }
		// };

		//int[] expectedAnswer = { -1, 2, 0, -1, 6, 9 };
		List<Integer> result = getClosestDashmart(city, locations);
		System.out.println(result);
	}
}
