package leetcode.sorting.interval;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1094. Car Pooling
 * There is a car with capacity empty seats.
 * The vehicle only drives east (i.e., it cannot turn around and drive west).
 *
 * You are given the integer capacity and an array trips where
 * trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has
 * numPassengersi passengers and the locations to pick them up and drop them off are
 * fromi and toi respectively.
 * The locations are given as the number of kilometers due east from the car's initial location.
 *
 * Return true if it is possible to pick up and drop off all passengers for all the given trips,
 * or false otherwise.
 *
 */
public class CarPooling
{
	public boolean carPooling(int[][] trips, int capacity) {
		if (trips.length == 0) {
			return false;
		}

		Queue<CarPool> q = new PriorityQueue<>((c1, c2) -> {
			if (c1.location == c2.location) {
				return c1.numPassengers - c2.numPassengers;
			}

			return c1.location - c2.location;
		});

		for (int i=0; i < trips.length; i++)
		{
			CarPool cp = new CarPool(trips[i][1], trips[i][0]);
			q.add(cp);
			cp = new CarPool(trips[i][2], (-1 * trips[i][0]));
			q.add(cp);
		}

		int remainingCapacity = capacity;
		while (!q.isEmpty())
		{
			CarPool cp = q.poll();
			remainingCapacity = remainingCapacity - cp.numPassengers;
			if (remainingCapacity < 0) return false;
		}
		return true;
	}
}
