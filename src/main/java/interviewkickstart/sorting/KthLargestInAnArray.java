package interviewkickstart.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an array of integers, find the k-th largest number in it.
 * Example One
 * {
 * "numbers": [5, 1, 10, 3, 2],
 * "k": 2
 * }
 * Output: 5
 * Example Two
 * {
 * "numbers": [4, 1, 2, 2, 3],
 * "k": 4
 * }
 * Output: 2
 */
public class KthLargestInAnArray
{
	//k-th largest
	//PriorityQueue order items based on natural ordering, the top element is the min element
	//to find k-th largest we need to store k element in PQ and get the top element
	static Integer kth_largest_in_an_array(ArrayList<Integer> numbers, Integer k) {

		if (numbers==null || numbers.size() == 0) return null;
		int count = 0;
		Queue<Integer>  q = new PriorityQueue<>();
		while (count < numbers.size())
		{
			q.add(numbers.get(count));
			count++;
			if (q.size() > k)
			{
				q.poll();
			}
		}
		return q.peek();
	}

	public static void main(String ... args) {
//		ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(5, 1, 10, 3, 2));
//		Integer result = kth_largest_in_an_array(numbers, 2);
//		System.out.println(result);//5
//
//		numbers = new ArrayList<>(Arrays.asList(4, 1, 2, 2, 3));
//		result = kth_largest_in_an_array(numbers, 4);
//		System.out.println(result);//2

		ArrayList<Integer>  numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 10));
		Integer result = kth_largest_in_an_array(numbers, 2);
		System.out.println(result);//5
	}
}
