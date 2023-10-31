package interviewkickstart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class AttendMeeting
{

	public static void main(String ... args) {

		ArrayList<ArrayList<Integer>> intervals = new ArrayList<>();
		ArrayList<Integer> interval = new ArrayList<>();
		interval.add(1);
		interval.add(5);
		intervals.add(interval);

		interval = new ArrayList<>();
		interval.add(10);
		interval.add(15);
		intervals.add(interval);

		interval = new ArrayList<>();
		interval.add(5);
		interval.add(8);
		intervals.add(interval);

		System.out.println(intervals);//[[1, 5], [10, 15], [5, 8]]

		intervals.sort(new Comparator<ArrayList<Integer>>()
		{
			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2)
			{
				return Integer.compare(o1.get(0), o2.get(0));
			}
		});

		System.out.println(intervals); //[[1, 5], [5, 8], [10, 15]]
	}
}
