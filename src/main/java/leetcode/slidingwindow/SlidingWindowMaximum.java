package leetcode.slidingwindow;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * 239. Sliding Window Maximum
 * You are given an array of integers nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right. You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 */
public class SlidingWindowMaximum
{
	//Time Limit Exceeded, pass test cases
	public static int[] maxSlidingWindow(int[] nums, int k) {
		int max = Integer.MIN_VALUE;
		int[] totalMax = new int[nums.length - (k-1)];//placeholder for the result: nums.length - k + 1
		int index = 0;
		for (int i=0; i < k; i++)
		{
			max = Math.max(max, nums[i]);
		}
		totalMax[index] = max;
		for (int i=k; i < nums.length; i++)
		{
			int prev = nums[i-k];
			int current = nums[i];
			//prev element is not the max element therefore the max element is already within the window
			//so only compare current element and max
			if (prev != max)
			{
				max = Math.max(max, current);
			}
			else //prev == max, need to find new max within the window
			{
				//reset max
				max = Integer.MIN_VALUE;
				//j start with index of prev element + 1 until current element
				for (int j=(i-k+1); j <= i; j++)
				{
					max = Math.max(max, nums[j]);
				}
			}
			index++;
			totalMax[index] = max;
		}
		return totalMax;
	}

	/**
	 * https://uplevel.interviewkickstart.com/resource/rc-video-210507-518318-247-1562-3843887
	 * Omkar's video:
	 * - Need to insert a new element
	 * - Delete an element (given its value)
	 * - Find max quickly: can use maxHeap with size k
	 * Insert into maxHeap: log k
	 * Delete/remove from maxHeap: log k -> need instant access to its location in the heap
	 *
	 * O(log k) -> goal is to do this in O(1)
	 */
	//Time Limit Exceeded  ~  37 / 51 testcases passed
	public static int[] maxSlidingWindow_Heap(int[] nums, int k) {
		int[] totalMax = new int[nums.length - (k-1)];
		int index = 0;
		Queue<Integer> maxHeap = new PriorityQueue<>(nums.length, Collections.reverseOrder());
		for (int i=0; i < k; i++)
		{
			maxHeap.add(nums[i]);
		}
		totalMax[index] = maxHeap.peek();

		for (int i=k; i < nums.length; i++)
		{
			int indexPrev = i-k;
			maxHeap.remove(nums[indexPrev]); //element is outside of this window

			//find new max within the window, because prev is already removed from the window
			maxHeap.add(nums[i]);

			index++;
			totalMax[index] =  maxHeap.peek();
		}
		return totalMax;
	}

	/**
	 * Omkar's Solution
	 * When a new element is added to the window, any other elements that is smaller
	 * than the new element has no chance of becoming the max number.
	 * Use Deque
	 * T(n) = O(n) -> can not be > O(n)
	 * S(n) = O(k)
	 */
	//Runtime 30 ms beats 95.68%
	public static int[] maxSlidingWindow_Omkar(int[] nums, int k) {
		Deque<Integer> deck = new ArrayDeque<>();
		int index = 0;
		int[] totalMax = new int[nums.length - (k-1)]; //nums.length - k + 1
		//initialize an empty deck
		for (int i=0; i < k; i++)
		{
			while (!deck.isEmpty() && deck.peekLast() < nums[i])
			{
				deck.removeLast();
			}
			deck.addLast(nums[i]);
		}
		totalMax[index] = deck.peekFirst();

		for (int i=k; i < nums.length; i++)
		{
			//remove prev element and add nums[i]
			int prevIndex = i-k;
			int prev = nums[prevIndex];
			//prev is the max element previously,
			// otherwise this element has been removed before because it wasn't the max element
			if (prev == deck.peekFirst())
			{
				deck.removeFirst();
			}
			//if prev element is the max element it has been removed (from above) and deck is empty, while loop won't execute
			//otherwise prev max element can be compared with current element since it is within the same window,
			//which the below while loop is doing
			while (!deck.isEmpty() && deck.peekLast() < nums[i])
			{
				deck.removeLast();
			}
			deck.addLast(nums[i]);


			index++;
			totalMax[index] = deck.peekFirst();
		}
		return totalMax;
	}

	static void print(int[] arr)
	{
		System.out.print("[");
		for (int i=0; i < arr.length; i++)
		{
			System.out.print(arr[i]);
			if (i < arr.length-1)
				System.out.print(",");
		}
		System.out.print("] ");
	}

	public static void main(String ... args)
	{
		int[] nums = {1,3,-1,-3,5,3,6,7};
		int k = 3;
		print(maxSlidingWindow_Omkar(nums, k)); //[3,3,5,5,6,7]

		int[] nums1 = {1};
		int k1 = 1;
		print(maxSlidingWindow_Omkar(nums1, k1)); //[1]

		int[] nums2 = {1, -1};
		int k2 = 1;
		print(maxSlidingWindow_Omkar(nums2, k2)); //[1,-1]
	}
}
