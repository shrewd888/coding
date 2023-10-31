package interview.doordash;

import java.util.Stack;

/**
 * https://leetcode.com/discuss/interview-question/1727436/DoorDash-or-Senior-Software-Engineer-or-Phone-Screen
 *
 * 735. Asteroid Collision
 *
 * We are given an array asteroids of integers representing asteroids in a row.
 * For each asteroid, the absolute value represents its size,
 * and the sign represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions.
 * If two asteroids meet, the smaller one will explode. If both are the same size, both will explode.
 * Two asteroids moving in the same direction will never meet.
 *
 * Example 1:
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 *
 * Example 2:
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 *
 * Example 3:
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 */

/**
 * Time Complexity: O(n)
 * Auxiliary Space: O(n) -> Space Complexity: O(n)
 */
public class AsteroidCollision
{
	public static int[] asteroidCollision(int[] asteroids) {
		if (asteroids == null || asteroids.length == 0) return new int[]{};

		Stack<Integer> stack = new Stack<>();

		for (int i=0; i < asteroids.length; i++)
		{
			int element = asteroids[i];
			boolean push = true;

			while (!stack.isEmpty() && stack.peek() > 0 && element < 0)
			{
				int top = stack.peek();
				if (Math.abs(top) >= Math.abs(element))
				{
					push = false;
					if (Math.abs(top) == Math.abs(element))
					{
						stack.pop();
					}
					break;
				}
				else {
					stack.pop();
				}
			}
			if (push) stack.push(element);
		}

		int[] result = new int[stack.size()];
		for (int i=result.length-1; i >= 0; i--)
		{
			result[i] = stack.pop();
		}
		return result;
	}

	public static void main(String ... args)
	{
		int[] piles = {5,10,-5};
		int[] result = asteroidCollision(piles);
		for (int i=0; i<result.length; i++)
			System.out.print(result[i] + ","); //[5,10]

		System.out.println();
		int[] piles1 = {8,-8};
		int[] result1 = asteroidCollision(piles1);
		for (int i=0; i<result1.length; i++)
			System.out.println(result1[i] + ",");//[]

		System.out.println();
		int[] piles2 = {10,2,-5};
		int[] result2 = asteroidCollision(piles2);
		for (int i=0; i<result2.length; i++)
			System.out.println(result2[i] + ",");//[10]

		int[] piles3 = {-2,-1,1,2};
		int[] result3 = asteroidCollision(piles3);
		for (int i=0; i<result3.length; i++)
			System.out.print(result3[i] + ",");//[-2,-1,1,2]
	}
}
