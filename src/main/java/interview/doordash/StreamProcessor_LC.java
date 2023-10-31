package interview.doordash;

import java.util.Deque;
import java.util.LinkedList;

public class StreamProcessor_LC
{
	int x;
	Deque<int[]> queue;
	StreamProcessor_LC(int x){
		this.x = x;
		queue = new LinkedList<int[]>();
	}

	public void set_value(int t, int val){
		//set good state of the queue -> only last x elements needed in queue
		//remove all values that are expired
		int buffer = t - x;
		//O(n)
		while(!queue.isEmpty() && queue.peek()[0] < buffer ){
			queue.removeFirst(); //O(1)
		}

		//set max at front
		//within the valid timeframe, only keep the values that are bigger than this new value
		//O(n)
		while(!queue.isEmpty() && queue.peekLast()[1] < val){
			queue.removeLast(); //O(1) //remove from front or remove from last is going to be same
		}

		queue.add(new int[]{t, val});
	}

	public int max_value(int curr_time){
		int buffer = curr_time - x;
		while(!queue.isEmpty() && queue.peek()[0] < buffer){
			queue.remove();
		}

		if(queue.isEmpty()){
			return -1;
		}
		return queue.peek()[1];
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		StreamProcessor_LC sp = new StreamProcessor_LC(5);
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

		sp = new StreamProcessor_LC(5);
		sp.set_value(0, 5);
		sp.set_value(1, 6);
		sp.set_value(2, 4);
		System.out.println(sp.max_value(3)); //6

		sp = new StreamProcessor_LC(5);
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

		System.out.println("Time (ms): "+(end-start));//1 ms
	}
}
