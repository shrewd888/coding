package interviewkickstart.dynamicprogramming;

import java.util.ArrayList;

/**
 * Given a variety of coin types defining a currency system, find the minimum number of coins required
 * to express a given amount of money.
 * Assume infinite supply of coins of every type.
 *
 * Example
 * {
 * "coins": [1, 3, 5],
 * "value": 9
 * }
 * Output:
 * 3
 */
public class MinimumCoins
{
	static Integer minimum_coins(ArrayList<Integer> coins, Integer value) {
		int[] table = new int[value+1];
		for (int i=0; i<table.length; i++)
		{
			table[i] = Integer.MAX_VALUE;
		}

		//Base Case
		table[0] = 0;
		for (int i=1; i<=value; i++)
		{
			//compute and cache table[i]
			for (Integer coin : coins)
			{
				int a = i - coin;
				if (a >= 0)
				{
					table[i] = Math.min(table[i], table[a]);
				}
			}
			table[i]++;
		}
		return table[value];
	}

	public static void main(String ... args) {
		ArrayList<Integer> coins = new ArrayList<>();
		coins.add(1);
		coins.add(3);
		coins.add(5);
		Integer num = minimum_coins(coins, 9);
		System.out.println(num); //3
	}
}
