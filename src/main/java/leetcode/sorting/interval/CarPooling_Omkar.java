package leetcode.sorting.interval;

/**
 * 1094. Car Pooling
 * There is a car with capacity empty seats.
 * The vehicle only drives east (i.e., it cannot turn around and drive west).
 *
 * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi]
 * indicates that the ith trip has numPassengersi passengers and the locations to pick them up
 * and drop them off are fromi and toi respectively.
 * The locations are given as the number of kilometers due east from the car's initial location.
 *
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 *
 * Example 1:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 *
 * Example 2:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 */
/**
 * Time Complexity: O(n log n)
 * Space Complexity: Aux space = O(n)
 */

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Tracking maximum #of passenger that can travel at any moment
 */
public class CarPooling_Omkar
{
	//trips: numPassengers, start, end
	public static boolean carPooling(int[][] trips, int capacity)
	{
		int numOfPassenger = 0;
		if (trips.length == 0 || capacity==0) return false;

		//sort based on start location
		Arrays.sort(trips, (a,b) -> Integer.compare(a[1], b[1]));

		//queue is sorted by end location
		Queue<CarPool> q = new PriorityQueue<>((c1, c2) -> {
			if (c1.location == c2.location) {
				return c1.numPassengers - c2.numPassengers;
			}

			return c1.location - c2.location;
		});

		int nextStart;
		for (int i=0; i<trips.length; i++)
		{
			if (i==trips.length-1)
			{
				nextStart = Integer.MAX_VALUE;
			}
			else
			{
				nextStart = trips[i+1][1];
			}
			//start trips[i]
			//add to queue: endLoc & numOfPassenger
			q.add(new CarPool(trips[i][2],trips[i][0]));
			numOfPassenger = numOfPassenger + trips[i][0];

			if (numOfPassenger > capacity) return false;

			//terminate trips up to the next start location, if there is endTrip in the PQ
			while (!q.isEmpty() && q.peek().location <= nextStart)
			{
				CarPool carPool = q.poll();//extract endLoc & numPassenger
				//substract numPassenger
				numOfPassenger = numOfPassenger - carPool.numPassengers;
			}
		}
		return true;
	}


	public static void main(String ... args)
	{
		int[][] trips = {{2,1,5},{3,3,7}};
		int capacity = 4;
		boolean c = carPooling(trips, capacity);
		System.out.println(c); //false

	}
}
