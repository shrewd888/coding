package leetcode.array.cyclesort;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/couples-holding-hands/
 * 765. Couples Holding Hands
 *
 * There are n couples sitting in 2n seats arranged in a row and want to hold hands.
 * The people and seats are represented by an integer array row where row[i]
 * is the ID of the person sitting in the ith seat. The couples are numbered in order,
 * the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2n - 2, 2n - 1).
 *
 * Return the minimum number of swaps so that every couple is sitting side by side.
 * A swap consists of choosing any two people, then they stand up and switch seats.
 *
 * Example 1:
 * Input: row = [0,2,1,3]
 * Output: 1
 * Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
 *
 * Example 2:
 * Input: row = [3,2,0,1]
 * Output: 0
 * Explanation: All couples are already seated side by side.
 *
 */
public class CouplesHoldingHands
{
	public static int minSwapsCouples(int[] row) {
		int numswap = 0;

		//map of ID and seat number (index)
		Map<Integer, Integer> idSeat = new HashMap<>();

		for (int i=0; i<row.length; i++)
		{
			idSeat.put(row[i], i);
		}
		/**
		 * while the personOnRight is not the expected partner of person on the left
		 * Let d = seat number where the personOnRight should be sent
		 * Swap personOnRight with the stranger on that seat:
		 *  	numSwap++
		 *  	update the two entries in the hash table
		 *  personOnRight = stranger
		 */
		for (int i=0; i < row.length/2; i++)
		{
			int personOnLeft = row[2*i]; //0
			int personOnRight = row[2*i+1]; //2
			int personOnRightForLeft; //find this person
			//personOnLeft stay, need to find the right partner for the personOnLeft
			if (personOnLeft % 2 == 0) //person id is even
			{
				personOnRightForLeft = personOnLeft + 1;//1
			}
			else
			{
				personOnRightForLeft = personOnLeft - 1;
			}
			int partnerForPersonOnRight;
			int strangerIndex;
			/**
			 * instead of while loop:
			 * if row[2*i + 1] != personOnRightForLeft
			 * //Check where personOnRightForLeft is sitting
			 * wifeLoc = hmap[personOnRightForLeft]
			 * swap row[2*i + 1], row[wifeLoc]
			 * numSwap++
			 * hmap[row[2*i + 1]] = 2*i + 1
			 * hmap[row[wifeLoc]] = wifeLoc
			 */
			while (personOnRight != personOnRightForLeft)
			{
				//who is the partner of personOnRight ?
				if (personOnRight % 2 == 0) //even
					partnerForPersonOnRight = personOnRight + 1;//3
				else
					partnerForPersonOnRight = personOnRight - 1;
				int indexOfPartnerForPersonOnRight = idSeat.get(partnerForPersonOnRight);//3
				if (indexOfPartnerForPersonOnRight % 2 == 0)
				{
					strangerIndex = indexOfPartnerForPersonOnRight + 1;
				}
				else
				{
					strangerIndex = indexOfPartnerForPersonOnRight - 1;//2
				}
				//swap
				int temp = row[strangerIndex];
				row[strangerIndex] = personOnRight;
				row[2*i+1] = temp;
				numswap++;
				//update hashmap
				idSeat.put(personOnRight, strangerIndex);
				idSeat.put(temp, 2*i+1);
				personOnRight = temp;
			}
		}
		return numswap;
	}

	//Runtime 1ms beats 81.5 %
	public static void main (String[] args)
	{
		int[] row = {0,2,1,3};
		int numSwap = minSwapsCouples(row);
		System.out.println(numSwap);//1
	}
}
