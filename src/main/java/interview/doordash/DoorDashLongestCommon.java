package interview.doordash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Two Dashers can complete up to N possible ordered pickups on a certain day.
 * These pickups are represented by two arrays of length N, one for each dasher.
 *
 * A pickup is represented by the name of the merchant, for example, "chilis" or "mcdonalds".
 * We could have the following two arrays that represent the pickup jobs for the two dashers:
 *
 * dasher1_pickups:["chilis", "albertsons", "walmart", "albertsons", "chilis", "mcdonalds", "burger king"]
 * dasher2_pickups:["chilis", "walmart", "chilis", "albertsons", "burger king", "applebees", "mcdonalds"]
 *
 * The two dashers ride in the same car and want to complete the same pickup jobs together while respecting the original ordering.
 * At the same time however, they want to complete as many deliveries as possible to maximize their payout.
 * Question: What is the longest sequence of pickups that these two dashers can complete together?
 *
 * Note: The two dashers must complete the deliveries in the above order (from left to right) but are allowed to skip pickup jobs.
 * IE, It is okay for the dashers to go from "walmart" to "mcdonalds" together,
 * but they now forfeit the right to complete any delivery between those two merchants,
 * or complete any delivery before their respective "walmarts".
 *
 * Example 1:
 * dasher1_pickups:["chilis", "albertsons", "walmart", "albertsons", "chilis", "mcdonalds", "burger king"]
 * dasher2_pickups:["chilis", "walmart", "chilis", "albertsons", "burger king", "applebees", "mcdonalds"]
 * returns ["chilis", "walmart", "chilis", "burger king"]
 *
 * Example 2:
 * dasher1_pickups:["chilis", "albertsons", "mcdonalds"]
 * dasher2_pickups:["burger king", "jamba juice", "applebees"]
 * Dasher1 and Dasher2 do not share any of the same pickup jobs, so they do not have any pickup jobs together.
 * returns [].
 */

/**
 * f(i,j):
 * 	if P1[i] == P2[j]:
 * 		1 + f(i+1, j+1)
 *  else:
 *  	max (f(i+1,j), f(i,j+1))
 *
 *  Base Case:
 *  if (i==len(P1) or j==len(P2)) return 0
 *
 *  Because f(i) depends on f(i+1), therefore we start with the bottom (the last index)
 *  to the top (index=0) - bottom up approach
 */

/**
 * Time Complexity: #of steps O(m x n) * time per steps O(k) -> time to compare 2 strings (k = avg length of string, not a constant)
 * => O (m x n x k)
 * Space Complexity: O(m x n) => size of the table
 */
public class DoorDashLongestCommon
{
	public static int longestCommonSubsequence(List<String> merchants1, List<String> merchants2)
	{
		int sz1 = merchants1.size();
		int sz2 = merchants2.size();

		if (sz1 == 0 || sz2 == 0)
		{
			return 0;
		}
		//initialize table with 0
		int[][] table = new int[sz1+1][sz2+1];

		int lcs = helper(table, merchants1, merchants2, sz1, sz2);

		List<String> result = new ArrayList<>();
		print(table, merchants1, merchants2, result, sz1, sz2);
		System.out.println(result);

		return lcs;
	}

	public static int helper(int[][] table, List<String> merchants1, List<String> merchants2, int sz1, int sz2)
	{
		//merchants1 in row, merchants2 in col
		for (int row = sz1-1; row >=0; row--)
		{
			for (int col = sz2-1; col >=0; col--)
			{
				if (merchants1.get(row).equals(merchants2.get(col)))
				{
					table[row][col] = 1 + table[row+1][col+1];
				}
				else
				{
					table[row][col] = Math.max(table[row+1][col], table[row][col+1]);
				}
			}
		}
		return table[0][0];
	}

	public static void print(int[][] table, List<String> merchants1, List<String> merchants2, List<String> result, int sz1, int sz2)
	{
		//start from table[0][0]
		int row=0, col=0;
		while (row < sz1 && col < sz2)
		{
			String m1 = merchants1.get(row);
			String m2 = merchants2.get(col);
			if (m1.equals(m2))
			{
				result.add(merchants1.get(row));
				row++;
				col++;
			}
			else
			{
				if (table[row+1][col] > table[row][col+1])
				{
					row++;
				}
				else
				{
					col++;
				}
			}
		}
	}

	public static void main(String[] args) {
		List<String> d1 = Arrays.asList("chilis", "albertsons", "walmart", "albertsons", "chilis", "mcdonalds", "burger king");
		List<String> d2 = Arrays.asList("chilis", "walmart", "chilis", "albertsons", "burger king", "applebees", "mcdonalds");
		int lcs = longestCommonSubsequence(d1, d2);
		//[chilis, walmart, albertsons, mcdonalds]
		System.out.println(lcs);//4

		List<String> d11 = Arrays.asList("chilis", "albertsons", "mcdonalds");
		List<String> d21 = Arrays.asList("burger king", "jamba juice", "applebees");
		int lcs1 = longestCommonSubsequence(d11, d21);
		//[]
		System.out.println(lcs1);//0
	}
}
