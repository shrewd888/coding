package interviewkickstart.sorting;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Given an array of numbers, rearrange them in-place so that even numbers appear before odd ones.
 * Example
 * {
 * "numbers": [1, 2, 3, 4]
 * }
 * Output:
 * [4, 2, 3, 1]
 */
public class EvenBeforeOdd
{

	static ArrayList<Integer> segregate_evens_and_odds(ArrayList<Integer> numbers) {

		if (numbers.size() == 0 || numbers.size() == 1)
			return numbers;

		int p1 = 0;
		int p2 = 1;
		while (p2 < numbers.size())
		{
			if (numbers.get(p1) % 2 == 1) //odd
			{
				if (numbers.get(p2) % 2 == 0) //even
				{
					Collections.swap(numbers, p1, p2);
					p1++;
					p2++;
				}
				else //both odd
				{
					p2++; //move only 1 pointer
				}
			}
			else //p1 even, p2 could be odd/even
			{
				p1++;
				p2++;
			}
		}
		return numbers;
	}


	public static void main(String ... args) {

	}
}
