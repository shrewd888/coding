package leetcode.binarysearch.part3;

/**
 * https://leetcode.com/problems/koko-eating-bananas/description/
 * 875. Koko Eating Bananas
 *
 * Koko loves to eat bananas.
 * There are n piles of bananas, the ith pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour,
 * she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas, she eats all of them instead and will not eat
 * any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
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
 */

/**
 * Want to optimize k
 */
public class KokoEatingBananas
{
	public static int minEatingSpeed(int[] piles, int h) {
		int startK = 1; //eat 1 banana/hour
		int endK = 1;
		//find max no of banana Koko can eat in 1 hour
		int sizeOfPiles = piles.length;
		double maxHourPerPile =  h/sizeOfPiles;
		for (int pile : piles)
		{
			int numOfBananaPerPile = pile;
			endK = (int) Math.max(endK, Math.ceil(numOfBananaPerPile/maxHourPerPile));
			/**
			 * can also take the max size which is the max #of banana in a pile
			 * and assign this to endK -> eat max banana/hour
			 */
		}

		while (startK <= endK)
		{
			//find no of banana to eat in an hour
			int midK = startK + (endK-startK)/2;
			//Now compute no of hours taken to eat all banana at the speed of midK
			int hourSpent = 0;
			for (int pile : piles)
			{
				hourSpent = (int) (hourSpent + Math.ceil(pile*1.0/midK)); //pile * 1.0 is needed to take the ceiling!
			}
			//example: [7], h=2:
			//hourSpent = 2 eventhough the speed is 6,5,4 bananas/hour
			if (hourSpent <= h) //eat too fast, need to reduce the speed
			{
				endK = midK - 1;
			}
			else  //hourSpent > h -> eat too slow, need to increase the speed
			{
				startK = midK + 1;
			}
		}
		return startK;
	}

	public static void main(String ... args)
	{
		int[] piles = {3,6,7,11};
		int h = 8;
		System.out.println(minEatingSpeed(piles, h));
	}
}
