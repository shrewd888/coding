package leetcode.slidingwindow_2;

/**
 * https://leetcode.com/problems/sliding-window-median/
 * 480. Sliding Window Median
 *
 * The median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k.
 * There is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array.
 * Answers within 10^(-5) of the actual value will be accepted.
 *
 * Example 1:
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 * Explanation:
 * Window position                Median
 * ---------------                -----
 * [1  3  -1] -3  5  3  6  7        1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7        3
 *  1  3  -1  -3 [5  3  6] 7        5
 *  1  3  -1  -3  5 [3  6  7]       6
 *
 * Example 2:
 * Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
 * Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


/**
 * https://uplevel.interviewkickstart.com/resource/rc-video-210507-518318-247-1563-3843887
 * Omkar's Solution: Sliding Window II
 * Balanced BST:
 * find min, max -> O(log k)
 * delete, insert, search -> O(log k)
 * T(n) = O(log k)
 * S(n) = O(k)
 */
//TODO: revisit! does not pass test case
public class SlidingWindowMedian
{
	public static double[] medianSlidingWindow(int[] nums, int k) {
		Queue<Integer> minHeap = new PriorityQueue<>();
		Queue<Integer> maxHeap = new PriorityQueue<>(nums.length, Comparator.reverseOrder());

		double[] result = new double[nums.length-k + 1];
		double median = nums[0];

		//sort k elements
		List<Integer> numsK = new ArrayList<>();
		for (int i=0; i < k; i++)
		{
			numsK.add(nums[i]);
		}
		Collections.sort(numsK);

		//process first k elements
		int size = numsK.size();

		if (size % 2 == 0) //even element
		{
			//insert half bottom to maxHeap, half top to minHeap
			for (int i=0; i < size/2; i++)
			{
				maxHeap.add(numsK.get(i));
			}
			for (int i=size/2; i < size; i++)
			{
				minHeap.add(numsK.get(i));
			}
			median = (minHeap.peek() + maxHeap.peek())/2.0;
			//minHeap.peek() + (maxHeap.peek() - minHeap.peek())/2.0;
		}
		else //odd element
		{
			int index = 0;
			for (int i=0; i < size/2; i++)
			{
				maxHeap.add(numsK.get(i));
				index++;
			}
			if (index < size)
			{
				minHeap.add(numsK.get(index));
				for (int i = index+1; i < size; i++)
				{
					minHeap.add(numsK.get(i));
				}
			}
			median = minHeap.peek();
		}
		//find the first median
		result[0] = median;
		int index = 1;
		for (int i=k; i < nums.length; i++)
		{
			//eliminate element at the left outside of the window
			if (maxHeap.contains(nums[i-k]))
			{
				maxHeap.remove(nums[i-k]);
			}
			else
			{
				minHeap.remove(nums[i-k]);
			}
			//insert nums[i]
			if (nums[i] <= median)
			{
				maxHeap.add(nums[i]);
			}
			else
			{
				minHeap.add(nums[i]);
			}

			/**
			 * k is even: size of minHeap = size of maxHeap
			 * remove from either min or maxHeap then adding 1 more element to either min or maxHeap:
			 * difference: +2 in min/max
			 *
			 * k is odd: size of minHeap = 1 + size of maxHeap
			 * remove from either min or maxHeap then adding 1 more element to either min or maxHeap:
			 * difference: +3 in min/max
			 */
			//Maintain size of minHeap always +1 from maxHeap if k is odd
			if (minHeap.size() - maxHeap.size() > 1)
			{
				maxHeap.add(minHeap.poll());
			}
			else if (maxHeap.size() > minHeap.size())
			{
				minHeap.add(maxHeap.poll());
			}
			if (k % 2 == 0) //even
			{
				median = (minHeap.peek() + maxHeap.peek()) /2.0;
			}
			else
			{
				median = minHeap.peek();
			}
			result[index] = median;
			index++;
		}
		return result;
	}

	static void print(double[] arr)
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
		print(medianSlidingWindow(nums, k));//[1.0,-1.0,-1.0,3.0,5.0,6.0]

		int[] nums1 = {1,4,2,3};
		int k1 = 4;
		print(medianSlidingWindow(nums1, k1));//[2.5]

		int[] nums2 = {1,2,3,4,2,3,1,4,2};
		int k2 = 3;
		print(medianSlidingWindow(nums2, k2));//[2.0,3.0,3.0,3.0,2.0,3.0,2.0]
	}
}
