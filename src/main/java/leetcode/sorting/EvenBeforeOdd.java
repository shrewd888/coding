package leetcode.sorting;

import java.util.ArrayList;
import java.util.Collections;

public class EvenBeforeOdd
{

	static ArrayList<Integer> segregate_evens_and_odds(ArrayList<Integer> numbers) {
		// Write your code here.
		int x = 0;
		int y = 1;
		while (y < numbers.size())
		{
			Integer left = numbers.get(x);
			Integer right = numbers.get(y);
			if (left  % 2 == 0)
			{
				x++;
				y++;
			}
			else //x = odd
			{
				if (right % 2 == 0)
				{
					//swap
					Collections.swap(numbers, x, y);
					x++;
					y++;
				}
				else //both odd
				{
					y++;
				}
			}
		}

		return numbers;
	}

	public static void main(String ... args) {
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		ArrayList<Integer>  result = segregate_evens_and_odds(numbers);
		System.out.println(result);
	}
}
