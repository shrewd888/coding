package interview.doordash;

import java.util.Arrays;

/**
 * 973. K Closest Points to Origin
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
 * return the k closest points to the origin (0, 0).
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 *
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 *
 * Example 1:
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 */

/**
 * Time Complexity: O(N) in average case and O(N^2) in the worst case, where N is the length of points
 * Space Complexity: O(N)
 */
public class KClosestPointsToOrigin
{
	public static int[][] kClosest(int[][] points, int k) {
		return quickSelect(points, k);
	}

	private static int[][] quickSelect(int[][] points, int k) {
		int left = 0, right = points.length - 1;
		int pivotIndex = points.length;
		while (pivotIndex != k) {
			// Repeatedly partition the array
			// while narrowing in on the kth element
			pivotIndex = partition(points, left, right);
			if (pivotIndex < k) {
				left = pivotIndex;
			} else {
				right = pivotIndex - 1;
			}
		}

		// Return the first k elements of the partially sorted array
		return Arrays.copyOf(points, k);
	}

	private static int partition(int[][] points, int left, int right) {
		int[] pivot = choosePivot(points, left, right);
		int pivotDist = squaredDistance(pivot);

		System.out.println("pivot: ["+pivot[0]+","+pivot[1]+"]");
		System.out.println("pivotDist:"+pivotDist);

		while (left < right) {
			// Iterate through the range and swap elements to make sure
			// that all points closer than the pivot are to the left
			if (squaredDistance(points[left]) >= pivotDist) {
				int[] temp = points[left];
				points[left] = points[right];
				points[right] = temp;
				print(points);
				right--;
			} else {
				left++;
			}
		}

		// Ensure the left pointer is just past the end of
		// the left range then return it as the new pivotIndex
		if (squaredDistance(points[left]) < pivotDist)
			left++;
		return left;
	}

	private static int[] choosePivot(int[][] points, int left, int right) {
		int pivotIndex = left + (right - left) / 2;
		System.out.println("left:"+left+", right:"+right+", pivotIndex:"+pivotIndex);
		// Choose a pivot element of the array
		return points[left + (right - left) / 2];
	}

	private static int squaredDistance(int[] point) {
		// Calculate and return the squared Euclidean distance
		return point[0] * point[0] + point[1] * point[1];
	}

	public static void main(String ... args)
	{
//		int[][] points = new int[][] { {1,3}, {-2,2} };
//		int[][] result = kClosest(points, 1);
//		print(result);//[-2,2]
//
//		int[][] points1 = new int[][] { {3,3}, {5,-1}, {-2,4} };
//		int[][] result1 = kClosest(points1, 2);
//		print(result1);//[3,3][-2,4]

		/**
		 *left:0, right:4, pivotIndex:2
		 * pivot: [-1,4]
		 * pivotDist:17
		 * [-5,-2][2,2][-1,4][6,-3][5,1]
		 * [6,-3][2,2][-1,4][-5,-2][5,1]
		 * [-1,4][2,2][6,-3][-5,-2][5,1]
		 * [2,2][-1,4][6,-3][-5,-2][5,1]
		 * left:1, right:4, pivotIndex:2
		 * pivot: [6,-3]
		 * pivotDist:45
		 * [2,2][-1,4][5,1][-5,-2][6,-3]
		 * left:1, right:3, pivotIndex:2
		 * pivot: [5,1]
		 * pivotDist:26
		 * [2,2][-1,4][-5,-2][5,1][6,-3]
		 * [2,2][-1,4]
		 */
		int[][] points2 = new int[][] { {5,1}, {2,2}, {-1,4}, {6, -3}, {-5, -2} };
		int[][] result2 = kClosest(points2, 2);
		print(result2);//[2,2][-1,4]

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

}
