package interviewkickstart.dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * knight's movement: it may move two squares vertically and one square horizontally,
 * or two squares horizontally and one square vertically (with both forming the shape of an L)
 *
 * Given a phone keypad as shown below:
 *
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * – 0 –
 *
 * How many different phone numbers of given length can be formed starting from the given digit?
 * The constraint is that the movement from one digit to the next is similar to the movement of the Knight in chess.
 *
 * For example, if we are at 1, then the next digit can be either 6 or 8;
 * if we are at 6 then the next digit can be 1, 7 or 0.
 *
 * Repetition of digits is allowed, e.g. 1616161616 is a valid number.
 * There is no need to list all possible numbers, just find how many they are.
 * Find a polynomial-time solution, based on Dynamic Programming.
 *
 * Example One
 * {
 * "startdigit": 1,
 * "phonenumberlength": 2
 * }
 * Output:
 * 2
 * Two possible numbers of length 2: 16, 18.
 *
 * Example Two
 * {
 * "startdigit": 1,
 * "phonenumberlength": 3
 * }
 * Output:
 * 5
 * The possible numbers of length 3: 160, 161, 167, 181, 183
 */

/**
 * Recursive Solution resulted in OutOfMemoryError on a big number
 * Need to solve using memoization! -> Using Map
 * Does not need to use slate if we don't need to list all possible numbers
 *
 * Example test cases that don't pass:
 * "phonenumberlength": 30, 29...
 */
public class KnightTourOnAPhoneKeyPad
{
	//use Map to store the previous result
	public static Map<String, Long> map = new HashMap<>();
	static List<Integer>[] adjList = mapKeyPadToKnightMoves();

	static Long count_phone_numbers_of_given_length(Integer startdigit, Integer phonenumberlength) {
		return helper(startdigit, phonenumberlength);
	}

	static Long helper(Integer startdigit, Integer phonenumberlength/*, ArrayList<Integer> slate*/)
	{
		String key = startdigit + ":" + phonenumberlength;
		if (map.containsKey(key))
		{
			//return map.get(key);
		}
		Long result = 0L;
		//Base case
		if (phonenumberlength == 0) return 0L;
		if (phonenumberlength == 1) return 1L;
		for (Integer digit : adjList[startdigit])
		{
//			slate.add(digit);
//			result = result + helper(digit, phonenumberlength-1, slate);
//			slate.remove(slate.size()-1);

			result = result + helper(digit, phonenumberlength-1/*, slate*/);
		}
		map.put(key, result);
		return result;
	}

	static List<Integer>[] mapKeyPadToKnightMoves()
	{
		List<Integer>[] adjList = new ArrayList[10];
		for (int i=0; i < 10; i++)
		{
			adjList[i] = new ArrayList<>();
		}
		adjList[0].add(6);
		adjList[0].add(4);
		adjList[1].add(6);
		adjList[1].add(8);
		adjList[2].add(7);
		adjList[2].add(9);
		adjList[3].add(4);
		adjList[3].add(8);
		adjList[4].add(3);
		adjList[4].add(9);
		adjList[4].add(0);
		adjList[6].add(1);
		adjList[6].add(7);
		adjList[6].add(0);
		adjList[7].add(2);
		adjList[7].add(6);
		adjList[8].add(1);
		adjList[8].add(3);
		adjList[9].add(2);
		adjList[9].add(4);
		return adjList;
	}

	public static void main(String ... args) {
		Long count = count_phone_numbers_of_given_length(1, 2);
		System.out.println(count); //2 -> 16, 18
		count = count_phone_numbers_of_given_length(1, 3);
		System.out.println(count); //5 -> 160, 161, 167, 181, 183
	}
}
