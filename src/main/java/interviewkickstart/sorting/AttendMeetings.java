package interviewkickstart.sorting;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Given a list of meeting intervals where each interval consists of a start and an end time,
 * check if a person can attend all the given meetings such that only one meeting can be attended at a time.
 * Example One
 * {
 * "intervals": [[1, 5], [5, 8], [10, 15]]
 * }
 * Output:
 * 1
 * Example Two
 * {
 * "intervals": [[1, 5], [4, 8]]
 * }
 * Output:
 * 0
 */
public class AttendMeetings
{
	static Integer can_attend_all_meetings(ArrayList<ArrayList<Integer>> intervals) {
		// Write your code here.
		if (intervals.size() == 1)
		{
			return 1;
		}
		//sort
		intervals.sort(new Comparator<ArrayList<Integer>>()
		{
			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2)
			{
				return Integer.compare(o1.get(0), o2.get(0));
			}
		});

		for(int i=0; i < intervals.size()-1; i++)
		{
			ArrayList<Integer> interval1 = intervals.get(i);
			ArrayList<Integer> interval2 = intervals.get(i+1);
			if (interval1.get(1) > interval2.get(0))
			{
				return 0;
			}
		}

		return 1;
	}

}
