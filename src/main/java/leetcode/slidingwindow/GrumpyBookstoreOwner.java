package leetcode.slidingwindow;

/**
 * 1052. Grumpy Bookstore Owner
 *
 * There is a bookstore owner that has a store open for n minutes.
 * Every minute, some number of customers enter the store.
 * You are given an integer array customers of length n where customers[i]
 * is the number of the customer that enters the store at the start of the ith minute
 * and all those customers leave after the end of that minute.
 *
 * On some minutes, the bookstore owner is grumpy.
 * You are given a binary array grumpy where grumpy[i] is 1 if the bookstore owner is grumpy during the ith minute,
 * and is 0 otherwise.
 *
 * When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise, they are satisfied.
 * The bookstore owner knows a secret technique to keep themselves not grumpy for minutes consecutive minutes,
 * but can only use it once.
 *
 * Return the maximum number of customers that can be satisfied throughout the day.
 *
 * Example 1:
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
 * Output: 16
 * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
 * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *
 * Example 2:
 * Input: customers = [1], grumpy = [0], minutes = 1
 * Output: 1
 */

/**
 * Omkar's Solution
 * T(n) = O(n)
 * S(n) = O(1)
 */
public class GrumpyBookstoreOwner
{
	/**
	 * Pick a window where the max #of dissatisfied customers exist
	 * Count #of dissatisfied customers
	 */
	public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
		int len = customers.length;
		if(len==0 || minutes > len) return 0;

		//Initialization
		int numOfAngry = 0;

		//first N minutes
		for (int i=0; i < minutes; i++)
		{
			if (grumpy[i] == 1)
			{
				numOfAngry += customers[i];
			}
		}
		int numOfSatisfied = 0;
		for (int i=0; i<len; i++)
		{
			if (grumpy[i] == 0)
			{
				numOfSatisfied += customers[i];
			}
		}
		int maxAngryCust = numOfAngry;
		//we want to maximize the #of disatissfied customer within k window because this will be converted to #of satisfied
		for (int i=minutes; i < len; i++)
		{
			//Compute #of dissatisfied customers in window ending at index i
			//this is the total #of dissatisfied customer within the k minutes
			if (grumpy[i] == 1) numOfAngry += customers[i];
			//substract #of angry customer which is NOT part of my window, therefore it needs to be removed
			if (grumpy[i-minutes] == 1) numOfAngry -= customers[i-minutes];

			//find the max #of angry customers in any window
			maxAngryCust = Math.max(maxAngryCust, numOfAngry);
		}
		return maxAngryCust + numOfSatisfied; // total #of satisfied because maxAngryCust can be healed with secret technique
	}

	//Runtime: 2 ms, faster than 100.00% of Java online submissions
	public static void main(String ... args)
	{

	}
}
