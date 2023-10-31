package interview.doordash;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/discuss/interview-question/1302614/DoorDash-Onsite-Interview-(new-question-again!)
 *
 * Given a streaming data of the form (timestamp, value),
 * find the maximum value in the stream in the last X seconds.
 *
 * Assume time is monotonically increasing.
 * Assume time is in the order of seconds.
 * max_value() function finds the max in the last X seconds.
 *
 * StreamProcessor(5) // last 5 seconds
 * set_value(0, 5)
 * set_value(1, 6)
 * set_value(2, 4)
 * max_value(3) = 6 -> always the current time
 *
 * class StreamProcessor:
 * def init(self, x):
 * ​ self.x = x
 *
 * def set_value(self, t, v):
 * ​ pass
 *
 * def max_value(self, cur_t):
 * ​ pass
 */
public class StreamProcessor_IK
{
	private  int x; //last x second
	private  Queue<Data> maxHeap = new PriorityQueue<>((i1, i2) -> Integer.compare(i2.value, i1.value));

	public StreamProcessor_IK(int x)
	{
		this.x = x;
	}

	/**
	 * x = 5
	 * set_value t = 0 -> 0 > (0-5 = -5) -> not expired
	 * set_value t = 1 -> 0 > (1-5 = -4) -> not expired
	 * set_value t = 3 -> 0 > (3-5 = -2) -> not expired
	 * set_value t = 4 -> 0 > (4-5 = -1) -> not expired
	 * set_value t = 5 -> 0 <= (5-5 = 0) -> expired (remove t=0 from the head)
	 * set_value t = 6 -> 1 <= (6-5 = 1) -> expired (remove t=1 from the head)
	 */
	//timestamp, value
	void set_value(int t, int v)
	{
		Data data = new Data(t,v);
		maxHeap.add(data);//store the max value at the top

		//for every timestamp that is expired, remove it from the head of the queue
		//peek the timestamp at the top of the heap
		while (!maxHeap.isEmpty() && maxHeap.peek().timestamp <= (t - x))
		{
			maxHeap.remove();
		}
	}

	/**
	 * max value of the last X second from currentTime
	 * What is the value of the last X from currentTime
	 */
	//Time Complexity: O(n log n)
	 int max_value(int currentTime) {
		while (!maxHeap.isEmpty() && maxHeap.peek().timestamp <= (currentTime - x))
		{
			maxHeap.remove();
		}
		if (maxHeap.isEmpty())
		{
			return -1;
		}
		return maxHeap.peek().value;
	}


	public class Data
	{
		int timestamp;
		int value;

		public Data(int timestamp, int value)
		{
			this.timestamp = timestamp;
			this.value = value;
		}

		@Override
		public boolean equals(Object o)
		{
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Data data = (Data) o;
			return timestamp == data.timestamp && value == data.value;
		}

		@Override
		public int hashCode()
		{
			return Objects.hash(timestamp, value);
		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		StreamProcessor_IK sp = new StreamProcessor_IK(5);
		sp.set_value(0, 5);
		System.out.println(sp.max_value(0)); //5

		sp.set_value(1, 6);
		sp.set_value(2, 4);
		sp.set_value(5, 5);
		System.out.println(sp.max_value(8)); //5
		sp.set_value(9, 19); //expired
		sp.set_value(15, 4);
		sp.set_value(17, 25);
		sp.set_value(19, 6);
		sp.set_value(20, 4);
		System.out.println(sp.max_value(20)); //25

		sp = new StreamProcessor_IK(5);
		sp.set_value(0, 5);
		sp.set_value(1, 6);
		sp.set_value(2, 4);
		System.out.println(sp.max_value(3)); //6

		sp = new StreamProcessor_IK(5);
		sp.set_value(0, 50);
		sp.set_value(1, 6);
		sp.set_value(2, 4);
		sp.set_value(5, 5);
		sp.set_value(9, 19);
		sp.set_value(15, 4);
		sp.set_value(16, 25);
		sp.set_value(19, 6);
		sp.set_value(20, 4);

		System.out.println(sp.max_value(52));//-1
		long end = System.currentTimeMillis();

		System.out.println("Time (ms): "+(end-start));//2 ms
	}

}
