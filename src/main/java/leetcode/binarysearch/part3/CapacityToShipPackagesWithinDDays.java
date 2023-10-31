package leetcode.binarysearch.part3;

import java.util.Arrays;

/**
 * 1011. Capacity To Ship Packages Within D Days
 * A conveyor belt has packages that must be shipped from one port to another within days days.
 *
 * The ith package on the conveyor belt has a weight of weights[i].
 * Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
 * We may not load more weight than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.
 *
 * Example 1:
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * Output: 15
 * Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 *
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into
 * parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 *
 * Example 2:
 * Input: weights = [3,2,2,4,1,4], days = 3
 * Output: 6
 * Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 * Example 3:
 *
 * Input: weights = [1,2,3,1,1], days = 4
 * Output: 3
 * Explanation:
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 */

public class CapacityToShipPackagesWithinDDays
{
	//my solution: Runtime 27 ms beats 12 %
	public static int shipWithinDays(int[] weights, int days)
	{
		int start = 1;//start should be the max of the item in the weights array, if taken the min item then the max item can't fit
		int end = Arrays.stream(weights).sum();
		for (int weight : weights)
		{
			start = Math.max(start, weight);
		}
		while (start <= end)
		{
			int mid = start + (end-start)/2;//the optimal capacity
			int totalDay = 0;
			int sum = 0;
			for (int weight : weights)
			{
				if (sum + weight > mid)
				{

					totalDay = totalDay + 1;
					//reset
					sum = weight;
				}
				else
				{
					sum = sum + weight;
				}
			}
			totalDay = totalDay + 1;
			if (totalDay <= days) //too much capacity, need to reduce
			{
				end = mid - 1;
			}
			else
			{
				start = mid + 1;
			}
		}
		//start > end, need to take larger value
		return start;
	}

	/**
	 * The smallest possible value should be the largest value in the array -> could take more than D days to ship all items
	 * The largest value is the sum of all weight in the array, which is to be delivered in a day
	 *
	 * Runtime 23 ms beats 18 %
	 */
	public static int shipWithinDays_Omkar(int[] weights, int days)
	{
		int start = 1;
		int end = Arrays.stream(weights).sum();
		for (int weight : weights)
		{
			start = Math.max(start, weight);
		}

		//Need to compute how many days need to ship all items
		while (start <= end)
		{
			int mid = start + (end-start)/2; //this is the capacity/weight
			int totalDay = 1; //start at day 1
			int load = 0;
			for (int weight : weights)
			{
				load += weight;
				if (load > mid) //here we compare the weight with the mid value because mid suppose to be the optimal weight/capacity
				{
					totalDay++;
					load = weight;//reset load
				}
			}
			if (totalDay <= days) //too much capacity (which takes less day), need to reduce the capacity
			{
				end = mid - 1;
			}
			else
			{
				start = mid + 1;
			}
		}
		//start > end, need to take larger value
		return start;
	}

	public static void main(String ... args)
	{
		int[] weights = {1,2,3,1,1};
		int days = 4;
		System.out.println(shipWithinDays(weights, days));//3

		int[] weights1 = {1,2,3,4,5,6,7,8,9,10};
		int days1 = 5;
		System.out.println(shipWithinDays(weights1, days1));//15
	}
}
