package interview.doordash;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/discuss/interview-question/1302614/DoorDash-Onsite-Interview-(new-question-again!)
 * Given a streaming data of the form (timestamp, value), find the maximum value in the stream in the last X seconds.
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
 *
 */
public class StreamProcessor
{
	private  int x; //last x second
	private  List<Data> dataList;

	private  Queue<Data> maxHeap = new PriorityQueue<>((i1, i2) -> Integer.compare(i2.value, i1.value));

	public StreamProcessor(int x)
	{
		this.x = x;
		dataList = new ArrayList<>();
	}

	//timestamp, value
	void set_value(int t, int v) {
		Data data = new Data(t,v);
		dataList.add(data);
	}

	/**
	 * (0,5), (1,6), (2,4) -> t = 3
	 */
	//return max value in the last t second
	 int max_value(int t) {
		if (t > x) return -1;

		Data lastData = !dataList.isEmpty() ? dataList.get(dataList.size()-1) : null;

		//if data size = 1, return data.value
		int size = dataList.size();
		if (size == 1) return dataList.get(0).value;

		//if the last timestamp <= t, add all element
		if (lastData != null && lastData.timestamp <= t)
		{
			for (Data data : dataList)
			{
				maxHeap.add(data);
			}
		}
		else //lastData's timestamp is bigger than t
		{
			Data last = lastData;
			for (int i=size-1; i > 0; i--)
			{
				Data data = dataList.get(i-1);
				if (data.timestamp <= t && last.timestamp > t)
				{
					maxHeap.add(data);
				}
				else //still big
				{
					last = data;
				}
			}
		}

		if (!maxHeap.isEmpty()) return maxHeap.peek().value;
		return -1;
	}

	public  class Data
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

		StreamProcessor sp = new StreamProcessor(22);
		sp.set_value(0, 5);
		System.out.println(sp.max_value(0)); //5
		sp.set_value(1, 6);
		sp.set_value(2, 4);
		sp.set_value(5, 5);
		System.out.println(sp.max_value(8)); //5 but this code resulted in 6
		sp.set_value(9, 19);
		sp.set_value(15, 4);
		sp.set_value(17, 25);
		sp.set_value(19, 6);
		sp.set_value(20, 4);



		sp = new StreamProcessor(5);
		sp.set_value(0, 5);
		sp.set_value(1, 6);
		sp.set_value(2, 4);
		System.out.println(sp.max_value(3)); //6
	}
}
