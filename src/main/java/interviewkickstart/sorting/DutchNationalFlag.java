package interviewkickstart.sorting;

import java.util.ArrayList;
import java.util.Collections;

public class DutchNationalFlag
{
	//i move around, to find R or B
	//If i = R, swap with lo. If i = B, swap with hi
	static ArrayList<Character> dutch_flag_sort(ArrayList<Character> balls)
	{
		if (balls == null || balls.size()==1) return balls;
		int lo = 0, i = 0;
		int hi = balls.size() - 1;

		while (i <= hi)
		{
			Character c = balls.get(i);
			if (c == 'R')
			{
				Collections.swap(balls, i, lo);
				i++; lo++;
			}
			else if (c == 'B')
			{
				Collections.swap(balls, i, hi);
				hi--; i++;
			}
			else //c == 'G'
			{
				i++;
			}
		}
		return balls;
	}

	public static void main(String ... args) {
		dutch_flag_sort(new ArrayList<>());
	}
}
