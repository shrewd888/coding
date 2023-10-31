package leetcode.binarysearch.part3;

/**
 * 774. Minimize Max Distance to Gas Station
 *
 * You are given an integer array stations that represents the positions of the gas stations on the x-axis.
 * You are also given an integer k.
 * You should add k new gas stations. You can add the stations anywhere on the x-axis, and not necessarily on an integer position.
 *
 * Let penalty() be the maximum distance between adjacent gas stations after adding the k new stations.
 * Return the smallest possible value of penalty(). Answers within 10^(-6) of the actual answer will be accepted.
 *
 * Example 1:
 * Input: stations = [1,2,3,4,5,6,7,8,9,10], k = 9
 * Output: 0.50000
 *
 * Example 2:
 * Input: stations = [23,24,36,39,46,56,57,65,84,98], k = 1
 * Output: 14.00000
 */

/**
 * Omkar's Solution
 * Binary Search Variants Part 3
 * 1. Optimize (minimize) the max distance between adjacent gas stations
 * 2. Upper bound: the max distance between adjacent gas stations before adding any k stations
 * 3. Lower bound: 0 or the smallest distance between adjacent gas stations and divided by k
 *
 * Eventually the value of D will be very small...
 * This could be Bisection Search, it may not be possible to achieve the exact value.
 * With Bisection Search we need to stop once start is very close to end.
 */
public class MinimizeMaxDistanceToGasStation
{
	public static double minmaxGasDist(int[] stations, int k) {
		double startD = 0;
		double endD = 0; //the max distance between adjacent gas stations before adding any k stations

		for (int i=0; i < stations.length-1; i++)
		{
			endD = Math.max(endD, stations[i+1] - stations[i]);
		}

		while (startD <= endD - 0.000001)
		{
			double midD = startD + (endD-startD)/2.0; //startD and endD could be floating point value
			/**
			 * Given the value of midD, figure out how many gas stations are needed to achieve midD (the optimal distance).
			 * If that number > k: I am in the left zone (d becomes too small that no matter how many I placed k gas stations )
			 * else: I am in the right zone
			 *
			 * If I need #gas stations > k (I am in the left zone), then need to increase the distance -> start = mid
			 * (not doing the + 1 because the distance could be < 1)
			 * If I need #gas stations < k (I am in the right zone), then need to decrease the distance -> end = mid
			 */
			/**
			 * Now calculate the #of gas stations given midD:
			 * (int) Math.ceil(distance/midD) - 1
			 */
			int numStations = 0;
			for (int i=0; i < stations.length-1; i++)
			{
				int d = stations[i+1] - stations[i];
				numStations = (int) (numStations + Math.ceil(d/midD) - 1);
			}
			if (numStations > k)
			{
				startD = midD;
			}
			else
			{
				endD = midD;
			}
		}
		return startD; //or endD
	}

	public static void main(String ... args)
	{
		int[] stations = {1,2,3,4,5,6,7,8,9,10};
		int k = 9;
		System.out.println(minmaxGasDist(stations, k));//0.4999990463256836
	}
}
