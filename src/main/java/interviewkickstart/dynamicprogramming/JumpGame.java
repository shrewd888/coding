package interviewkickstart.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a list of maximum jump lengths from different houses,
 * determine if you can reach the last house in one or more jumps starting from the first one.
 * Maximum jump length of 2 from a house, for example, means that you can either
 * jump to the next house or to the one after next.
 *
 * Example One
 * {
 * "maximum_jump_lengths": [2, 3, 1, 0, 4, 7]
 * }
 * Output:
 * 1
 */

/**
 * https://leetcode.com/problems/jump-game/
 * 55. Jump Game
 */
public class JumpGame
{
	//Greedy Solution
	static Boolean can_reach_last_house(ArrayList<Integer> maximum_jump_lengths) {
		int length = maximum_jump_lengths.size();
		//Base Case
		int leftMostGoodIndex = length-1; // at index (length-1)
		for (int currentIndex = length-2; currentIndex >= 0 ; currentIndex--)
		{
			Integer maxJumpLength = maximum_jump_lengths.get(currentIndex);
			int theFarthestReachableIndex = currentIndex + maxJumpLength;
			if (theFarthestReachableIndex >= leftMostGoodIndex)
			{
				leftMostGoodIndex = currentIndex;
			}
		}
		return leftMostGoodIndex==0;
	}

	/**
	 * Iterative Dynamic Programming Solution
	 * The result (good or bad) of any index is dependent only on the results of greater indices.
	 * This implies that we can calculate the result for a certain index if we have the results
	 * of indices greater than it. This idea suggests we traverse the indices in reverse order,
	 * starting from the last one.
	 * This approach eliminates the need for recursion.
	 */
	/**
	 * Time Complexity: O(n^2)
	 * Space Complexity: Auxiliary Space: O(n)
	 */
	//DOES NOT PASS A VERY LONG INPUT!
	static Boolean can_reach_last_house_DP(ArrayList<Integer> maximum_jump_lengths) {
		int length = maximum_jump_lengths.size();
		int[] table = new int[length]; //it is initialize with 0 (false)
		//Base Case
		table[length-1] = 1;

		for (int currentIndex = length-2; currentIndex >= 0 ; currentIndex--)
		{
			Integer maxJumpLength = maximum_jump_lengths.get(currentIndex);
			Integer arriveAtIndex = currentIndex + maxJumpLength;
			if (arriveAtIndex > length-1)
			{
				arriveAtIndex = length-1;
			}
			//from my grid can I get to the next grid with 1 step? If my maxJumpLength > 1 then yes
			//if yes, I am = 1, otherwise I am = 0

			//is it possible to reach the end from my grid?
			//If the element[arriveAtIndex]==0 then the answer is No (because from the arrivalIndex we can't jump forward)
			//unless that element is the last element

			//look to every index start at next currentIndex until arriveAtIndex
			//if any of them is 1 then i am also = 1
			for (int myIndex=currentIndex+1; myIndex <= arriveAtIndex; myIndex++)
			{
				if (table[myIndex] == 1)
				{
					table[currentIndex] = 1;
					break;
				}
			}
		}
		print(table); //[1,1,0,0,1,1]
		return table[0]==1;
	}

	static void print(int[] arr)
	{
		System.out.print("[");
		for (int i=0; i < arr.length; i++)
		{
			System.out.print(arr[i]);
			if (i < arr.length-1)
				System.out.print(",");
		}
		System.out.print("] ");
	}

	public static void main(String ... args) {
		ArrayList<Integer> maxJump = new ArrayList<>(Arrays.asList(2, 3, 1, 0, 4, 7));
		Boolean bool = can_reach_last_house_DP(maxJump);
		System.out.println(bool); //true
	}
}
