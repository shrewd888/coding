package interview.doordash;

/***
 * https://leetcode.com/discuss/interview-question/1559690/DoorDash-or-Phone-Screen-or-Fulfill-orders
 * https://leetcode.com/discuss/interview-question/2028170/Doordash-or-Phone-screen
 *
 * You're given an array of pending orders at restaurants.
 * A dasher must complete all orders in given hours h. You must use all hours.
 * Return the minimum speed at which the dasher must fulfill the orders.
 * Return -1 if not possible.
 *
 * Examples:
 * orders = [4, 3, 1]. So index 0 has 4 orders, index 1 has 3 orders and index 3 has 1 order
 * hours = 4
 * result speed would be 3 orders per hour
 * You would need to spend 2 hours at the first restaurant to fulfill 4 orders, 1hr at second restaurant and so on.
 * You cannot use "left over" capacity from restaurants.
 *
 * orders = [2,2,2]
 * hours = 3
 * result speed in this case would be 2 orders per hour
 *
 * orders = [100, 2, 2]
 * hours = 4
 * result speed would be 50 orders per hour
 *
 * orders = [100, 100]
 * hours = 3
 * result would be -1
 *
 *
 * 875. Koko Eating Bananas
 *
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k.
 * Each hour, she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas
 * during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 * Example 1:
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 *
 * Example 2:
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 *
 * Example 3:
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 * Time Complexity: O (log n) -> n = th
 */
public class MinSpeed_KokoEatingBananas
{
	public static int minEatingSpeed(int[] piles, int h)
	{
		//initialize
		//find the minimum speed: eat 1 banana per hour (left)
		double left=1, right = 1;

		//Example: [30,11,23,4,20], h = 6 -> take lower ceiling of 6/5 = 1
		//find the max speed -> the largest #of banana in a pile -> eat all in 1 hour
		//We can minimize this max speed :
		// h/(size of pile) = x hour -> largest #of banana/x = y banana in 1 hour
		int sizeOfPiles = piles.length;
		double maxHourPerPile = h/sizeOfPiles;                  
		for (int pile : piles)
		{
			double numOfBananaPerPile = pile;
			right = Math.max(right, Math.ceil(numOfBananaPerPile/maxHourPerPile));
		}

		while (left < right)
		{
			double middle = (int) (left + (right-left)/2); //in 1 hour, eat 'middle' number of banana
			double totalHourSpent = 0;

			for (int pile : piles)
			{
				totalHourSpent = totalHourSpent + Math.ceil(pile/middle);
			}

			if (totalHourSpent <= h) //need to slow down, eat less banana/hour
			{
				right = middle;
			}
			else //take too much time (too slow to eat banana in 1 hour), increase #of banana eat/hour
			{
				left = middle + 1;
			}
		}
		return (int) right; //choose right because right=eat more banana/hour than left
	}

	public static void main(String... args)
	{
		//System.out.println(6/5);//1

		int[] piles = {3,6,7,11};
		int minNumOfBananaPerHour = minEatingSpeed(piles, 8);
		System.out.println(minNumOfBananaPerHour);//4 - eat 4 banana in 1 hour

		int[] piles1 = {30,11,23,4,20};
		int minNumOfBananaPerHour1 = minEatingSpeed(piles1, 5);
		System.out.println(minNumOfBananaPerHour1);//30

		int[] piles2 = {30,11,23,4,20};
		int minNumOfBananaPerHour2 = minEatingSpeed(piles2, 6);
		System.out.println(minNumOfBananaPerHour2);//23

		int[] piles3 = {312884470};
		int minNumOfBananaPerHour3 = minEatingSpeed(piles3, 312884469);
		System.out.println(minNumOfBananaPerHour3);  //2 ??

		int[] piles4 = {100, 100};
		int minNumOfBananaPerHour4 = minEatingSpeed(piles4, 3);
		System.out.println(minNumOfBananaPerHour4); //100 ?? should be impossible

		int[] piles5 = {100, 2, 2};
		int minNumOfBananaPerHour5 = minEatingSpeed(piles5, 4);
		System.out.println(minNumOfBananaPerHour5); //50
	}
}
