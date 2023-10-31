package interview.doordash;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/discuss/interview-question/2103489/DoorDash-or-Phone-Screen
 *
 * https://leetcode.com/discuss/interview-question/2003115/Door-Dash-Onsite-or-E4-or-Software-Engineer-Streaming-and-Processing
 *
 * https://leetcode.com/discuss/interview-question/1379696/DoorDASH-Onsite
 *
 * A number of cities are arranged on a graph that has been divided up like an ordinary Cartesian plane.
 * Each city is located at an integral (x, y) coordinate intersection.
 * City names and locations are given in the form of three arrays: c, x, and y,
 * which are aligned by the index to provide the city name (c[i]), and its coordinates, (x[i], y[i]).
 *
 * Determine the name of the nearest city that shares either an x or a y coordinate with the queried city.
 *
 * If no other cities share an x or y coordinate, return 'NONE'.
 * If two cities have the same distance to the queried city, q[i],
 * consider the one with an lexographically shorter name (i.e. 'ab' < 'aba' < 'abb') as the closest choice.
 *
 * The distance is the Manhattan distance, the absolute difference in x plus the absolute difference in y.
 *
 * Example:
 * Input: n = 3; c = ['c1', 'c2', 'c3']; x = [3, 2, 1] ; y = [3, 2, 3]; q = ['c1', 'c2', 'c3']
 * Expected Answer: ['c3', 'NONE', 'c1']
 *
 * Explanation: The three points at (x[i], y[i]) are (3, 3), (2, 2) and (1, 3)
 * represent the coordinates of the cities on the graph. The nearest city to c1 is c3,
 * which shares a y value (distance = (3-1) + (3-3) = 2).
 * City c2 does not have a nearest city as none share an x or y with c2, so this query returns 'NONE'.
 * A query of c3 returns c1 based on the first calculation.
 * The return array after all queries are complete is ['c3', 'NONE', 'c1'].
 *
 * Function Description
 *
 * closest_straight_city has the following parameter(s):
 *     string c[n]: an array of strings that represent the names of each city[i]
 *     int x[n]: the x coordinates of each city[i]
 *     int y[n]: the y coordinates of each city[i]
 *     string q[m]: the names of each city to query
 * Returns:
 *     string[m]: an array of m strings where the index of i element denotes the return value of the index of i query
 */

/**
 * cityLength = xLength = yLength = m
 * queryLength = n
 * Time Complexity = O(m x n)
 * Space Complexity = O(n) -> output
 */

/**
 * https://leetcode.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/
 * 1779. Find Nearest Point That Has the Same X or Y Coordinate
 *
 * You are given two integers, x and y, which represent your current location on a Cartesian grid: (x, y).
 * You are also given an array points where each points[i] = [ai, bi] represents that a point exists at (ai, bi).
 * A point is valid if it shares the same x-coordinate or the same y-coordinate as your location.
 *
 * Return the index (0-indexed) of the valid point with the smallest Manhattan distance from your current location.
 * If there are multiple, return the valid point with the smallest index. If there are no valid points, return -1.
 *
 * The Manhattan distance between two points (x1, y1) and (x2, y2) is abs(x1 - x2) + abs(y1 - y2).
 */
public class NearestNeighbourCity
{

	public static List<String> closestCities(String[] citiNames, int[] x, int[] y, String[] query)
	{
		List<String> result = new ArrayList<>();
		for (int i=0; i<query.length; i++)
		{
			String city = getClosestCity(citiNames, x, y, query[i]);
			result.add(city);
		}
		return result;
	}

	private static String getClosestCity(String[] citiNames, int[] x, int[] y, String query)
	{
		int index = -1;
		//find the index of query so that we know the index of x & y
		for (int i=0; i<citiNames.length; i++)
		{
			if (citiNames[i].equalsIgnoreCase(query))
			{
				index = i;
				break;
			}
		}
		if (index == -1) return "NONE"; //queryCity does not exits in citiNames, therefore can't calculate the distance

		int xQuery = x[index];
		int yQuery = y[index];
		String closestCity = "NONE";
		int minDistance = Integer.MAX_VALUE;
		for (int i=0; i < x.length; i++)
		{
			if (i==index) continue;//don't want to compare with itself
			if (x[i] == xQuery || y[i] == yQuery) //shares either an x or a y with queried city
			{
				int distance = calculateDistance(x[i], y[i], xQuery, yQuery);
				if (distance < minDistance)
				{
					minDistance = distance;
					closestCity = citiNames[i];
				}
				else if (distance == minDistance)
				{
					if (citiNames[i].compareTo(closestCity) < 0)
					{
						closestCity = citiNames[i];
					}
				}
			}
		}
		return closestCity;
	}

	private static int calculateDistance(int x1, int y1, int x2, int y2) {
		// Calculate and return the manhattan distance
		return Math.abs((x2-x1) + (y2-y1));
	}

	public static void main(String ... args)
	{
		String[] cities = {"axx", "axy", "az", "axd", "aa", "abc", "abs", "p"};
		int[] xValues = {0,1,2,4,5,0,1,0};
		int[] yValues = {1,2,5,3,4,2,0,2};
		String[] queries = {"axx", "axy", "abs", "zmm"};//[abc, abc, axy, NONE]
		System.out.println(closestCities(cities, xValues, yValues, queries));

		String[] cities1 = {"c1", "c2", "c3"};
		int[] xValues1 = {3,2,1};
		int[] yValues1 = {3,2,3};
		String[] queries1 = {"c1", "c2", "c3"};
		System.out.println(closestCities(cities1, xValues1, yValues1, queries1)); //[c3, NONE, c1]

		String[] cities2 = {"p1", "p2", "p3"};
		int[] xValues2 = {30,20,10};
		int[] yValues2 = {30,20,30};
		String[] queries2 = {"p3", "p2", "p1"};
		System.out.println(closestCities(cities2, xValues2, yValues2, queries2)); //[p1, NONE, p3]

		String[] cities3 = {"p1", "p2", "p3", "p4", "p5"};
		int[] xValues3 = {10,20,30, 40,50};
		int[] yValues3 = {10,20,30,40,50};
		String[] queries3 = {"p1", "p2", "p3", "p4", "p5"};
		System.out.println(closestCities(cities3, xValues3, yValues3, queries3)); //[NONE, NONE, NONE, NONE, NONE]
	}
}
