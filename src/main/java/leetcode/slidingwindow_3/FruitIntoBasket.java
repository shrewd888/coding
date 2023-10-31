package leetcode.slidingwindow_3;

/**
 * https://leetcode.com/problems/fruit-into-baskets/description/
 * 904. Fruit Into Baskets
 * You are visiting a farm that has a single row of fruit trees arranged from left to right.
 * The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 *
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 *
 * You only have two baskets, and each basket can only hold a single type of fruit.
 * There is no limit on the amount of fruit each basket can hold.
 * Starting from any tree of your choice, you must pick exactly one fruit from every tree
 * (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
 * Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 *
 * Example 1:
 * Input: fruits = [1,2,1]
 * Output: 3
 * Explanation: We can pick from all 3 trees.
 *
 * Example 2:
 * Input: fruits = [0,1,2,2]
 * Output: 3
 * Explanation: We can pick from trees [1,2,2].
 * If we had started at the first tree, we would only pick from trees [0,1].
 *
 * Example 3:
 * Input: fruits = [1,2,3,2,2]
 * Output: 4
 * Explanation: We can pick from trees [2,3,2,2].
 * If we had started at the first tree, we would only pick from trees [1,2].
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Omkar's Solution
 * Array Floater 6 - Sliding Windows III
 *
 * Decrease & Conquer
 * What is the max #of fruit that can be collected specifically ending at the ith tree
 * T(n) = O(n)
 * S(n) = O(1) => using hashtable but the size of the hashtable is not > 3
 */
public class FruitIntoBasket
{

	public static int totalFruit(int[] fruits) {
		int globalmax = 0;
		int left = 0;
		//int windowsize = 0;
		Map<Integer, Integer> fruitTypeMap = new HashMap<>();
		for (int i=0; i < fruits.length; i++)
		{
			int fruit = fruits[i];
			//work to be done to calculate max #of fruits ending at index i
			fruitTypeMap.put(fruits[i], fruitTypeMap.getOrDefault(fruit,0) + 1);
			while (left <= i && fruitTypeMap.size() > 2)
			{
				int fruitLeft = fruits[left];
				fruitTypeMap.put(fruitLeft, fruitTypeMap.get(fruitLeft) - 1);
				if (fruitTypeMap.get(fruitLeft) == 0)
				{
					fruitTypeMap.remove(fruitLeft);
				}
				//windowSize-- -> no need because we calculate with : i-left + 1
				left++;
			}
			globalmax = Math.max(globalmax,  i-left + 1);
		}
		return globalmax;
	}

	public static void main(String ... args)
	{
		int[] fruits = {1,2,3,2,2};
		System.out.println(totalFruit(fruits));//4

		int[] fruits1 = {0,1,2,2};
		System.out.println(totalFruit(fruits1));//3
	}
}
