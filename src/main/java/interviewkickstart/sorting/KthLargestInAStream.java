package interviewkickstart.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Sorting
 * {
 * "k": 2,
 * "initial_stream": [4, 6],
 * "append_stream": [5, 2, 20]
 * }
 * Output: [5, 5, 6]
 *
 * Append	Stream	Sorted Stream	2nd largest
 * 5	[4, 6, 5]			[4, 5, 6]			5
 * 2	[4, 6, 5, 2]		[2, 4, 5, 6]		5
 * 20	[4, 6, 5, 2, 20]	[2, 4, 5, 6, 20]	6
 */
public class KthLargestInAStream
{
	static ArrayList<Integer> kth_largest(Integer k, ArrayList<Integer> initial_stream, ArrayList<Integer> append_stream) {
		int index = 0;

		ArrayList<Integer> result = new ArrayList<>();

		Queue<Integer> q = new PriorityQueue<>();

		while (index < initial_stream.size())
		{
			q.add(initial_stream.get(index));
			index++;
		}
		//reset
		index = 0;
		while (index < append_stream.size())
		{
			q.add(append_stream.get(index));
			while (q.size() > k)
			{
				q.poll();
			}
			result.add(q.peek());
			index++;
		}
		return result;
	}

	public static void main(String ... args) {
		ArrayList<Integer> initial = new ArrayList<>(Arrays.asList(4, 6));
		ArrayList<Integer> append = new ArrayList<>(Arrays.asList(5, 2, 20));
		ArrayList<Integer>result = kth_largest(2, initial, append); //[5, 5, 6]

		System.out.println(result);
	}
}
