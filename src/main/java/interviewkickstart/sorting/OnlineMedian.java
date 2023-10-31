package interviewkickstart.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a list of numbers, the task is to insert these numbers into a stream and
 * find the median of the stream after each insertion.
 * If the median is a non-integer, consider it’s floor value.
 *
 * The median of a sorted array is defined as the middle element when the number of elements is odd
 * and the mean of the middle two elements when the number of elements is even.
 * Example
 * {
 * "stream": [3, 8, 5, 2]
 * }
 * Output:
 * [3, 5, 5, 4]
 */

/**
 * Time Complexity: O(n log n)
 * Auxiliary Space: O(n) -> minHeap & maxHeap
 */
public class OnlineMedian
{
	static ArrayList<Integer> online_median(ArrayList<Integer> stream) {
		ArrayList<Integer> result = new ArrayList<>();
		if (stream == null || stream.size() == 0) return result;

		int index = 0;
		int sz = stream.size();
		/**
		 * anything in the maxHeap are numbers < median
		 * anything in the minHeap are numbers > median
		 */
		Queue<Integer> maxHeap = new PriorityQueue<>(sz, Collections.reverseOrder());//extract max
		Queue<Integer> minHeap = new PriorityQueue<>();//extract min
		//first time, choose to add to maxHeap and maintain maxHeap to have 1 extra element for total of odd elements
		Integer num = stream.get(index);
		result.add(num);//median
		maxHeap.add(num);

		index++;
		int minSz = minHeap.size();
		int maxSz = maxHeap.size();
		while (index < sz) //index start with 1
		{
			Integer number = stream.get(index);
			Integer median = result.get(result.size()-1);
			//even element: (index+1), because index starts with 0
			if ( ((index+1) % 2 == 0) && minSz+1 == maxSz) //maintain same size for even elements
			{
				if (number > median) //median = maxHeap.peek()
				{
					minHeap.add(number);
				}
				//number < median, should go to maxHeap, but to maintain the size
				//of the heap, we need to take element from maxHeap and put into minHeap,
				//then we add this number into maxHeap
				else
				{
					minHeap.add(maxHeap.poll());
					maxHeap.add(number);
				}
				Integer newMedian = (minHeap.peek() + maxHeap.peek())/2;
				result.add(newMedian);
			}
			else //odd elements, add to maxHeap
			{
				/**
				 * want to add to maxHeap but number is > min number in minHeap
				 * so we put the min number in minHeap, put in maxHeap and put this
				 * new number in minHeap
				 */
				if (number > minHeap.peek())
				{
					maxHeap.add(minHeap.poll());
					minHeap.add(number);
				}
				else
				{
					maxHeap.add(number);
				}
				Integer newMedian = maxHeap.peek();
				result.add(newMedian);
			}
			index++;
		}
		return result;
	}


	public static void main(String ... args) {
		ArrayList<Integer> streams = new ArrayList<>(Arrays.asList(3,8,5,2));
		ArrayList<Integer> result = online_median(streams);
		System.out.println(result);
	}
}
