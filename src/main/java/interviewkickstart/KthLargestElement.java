package interviewkickstart;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class KthLargestElement
{
	static Integer kth_largest_in_an_array(ArrayList<Integer> numbers, Integer k) {

//		if (numbers.size() == 1 && k == 1)
//		{
//			return numbers.get(0);
//		}
		// Write your code here.
		Integer count = 1;
		PriorityQueue p = new PriorityQueue();
		for (Integer i : numbers)
		{
			p.add(i);
			if (count == k)
			{
				p.poll();
				break;
			}
			count++;
		}
		return (Integer) p.peek();
	}

	public static void main(String ... args) {

	}
}
