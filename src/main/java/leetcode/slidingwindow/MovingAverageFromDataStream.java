package leetcode.slidingwindow;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/moving-average-from-data-stream/
 * 346. Moving Average from Data Stream
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Implement the MovingAverage class:
 *
 * MovingAverage(int size) Initializes the object with the size of the window size.
 * double next(int val) Returns the moving average of the last size values of the stream.
 *
 * Example 1:
 *
 * Input
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * Output
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * Explanation
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // return 1.0 = 1 / 1
 * movingAverage.next(10); // return 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 */
/**
 * Time Complexity: Updation time per number = O(1)
 * Space Complexity: O(k)
 */
public class MovingAverageFromDataStream
{
	/**
	 * FIFO = First In First Out
	 * Use Queue of size k
	 */
	int totalSoFar;
	int size;
	Queue<Integer> queue;//can use array with sz=size too, and overlap the value

	public MovingAverageFromDataStream(int size) {
		this.totalSoFar = 0;
		this.size = size;
		this.queue = new LinkedList<>();
	}

	public double next(int val) {
		int queueSz = queue.size();

		//remove oldest item
		if (queueSz == size)
		{
			Integer extractVal = queue.poll();
			totalSoFar = totalSoFar - extractVal;
		}
		queue.add(val);
		totalSoFar = totalSoFar + val;
		double avg = (double) totalSoFar/queue.size();
		System.out.println(avg);
		return avg;
	}

   //Runtime: 145 ms, faster than 7.64% of Java online submissions
	public static void main(String ... args)
	{
		MovingAverageFromDataStream movingAverage = new MovingAverageFromDataStream(3);
		movingAverage.next(1); //1.0
		movingAverage.next(10); //5.5
		movingAverage.next(3); //4.666667
		movingAverage.next(5); //6.0 -> (10 + 3 + 5)/3 = 6.0
	}
}
