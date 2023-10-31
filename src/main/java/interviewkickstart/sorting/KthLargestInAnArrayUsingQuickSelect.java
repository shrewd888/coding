package interviewkickstart.sorting;

/**
 * For linear time complexity we are going to use Quick Select - only focus on 1 direction
 * (1/2 of Quick Sort) because we focus on the side (left/right) of the partition where the kth element exist
 * -> see Lomoto partition
 * (Similar to what we do in the Quick Sort algorithm) method to solve this problem:
 *
 * First, we need to choose the so-called pivot and partition elements of nums into 3 parts:
 * elements, smaller than the pivot, equal to the pivot, and bigger than the pivot.
 * (sometimes two groups are enough: less and more or equal)
 *
 * Next step is to see how many elements we have in each group:
 * if k <= L, where L is size of left, then we can be sure that we need to look into the left part.
 * If k > L + M, where M is size of mid group, then we can be sure, that we need to look into the right part.
 * Finally, if none of these two conditions holds, we need to look in the mid part,
 * but because all elements there are equal, we can immediately return mid[0].
 *
 * Complexity:
 * Time complexity is O(n) on average because each time
 * we reduce the searching range approximately 2 times. This is not strict proof,
 * for more details you can do some googling because it's very hard to do all that maths here.
 * n + n/2 + n/4 + n/8 ~ 2n ~ O(n)
 *
 * No auxiliary array because we are doing in place
 * Space complexity is O(n) as well ?
 */

import java.util.ArrayList;
import java.util.Arrays;

/**
 * class Solution:
 *     def findKthLargest(self, nums, k):
 *         if not nums: return
 *         pivot = random.choice(nums)
 *         left =  [x for x in nums if x > pivot]
 *         mid  =  [x for x in nums if x == pivot]
 *         right = [x for x in nums if x < pivot]
 *
 *         L, M = len(left), len(mid)
 *
 *         if k <= L:
 *             return self.findKthLargest(left, k)
 *         elif k > L + M:
 *             return self.findKthLargest(right, k - L - M)
 *         else:
 *             return mid[0]
 */

/**
 * Time Complexity: O(n) in average case and O(n^2) in the worst case, where n is the length of points
 * Space Complexity: O(1), No Auxiliary Space, since sorting happen in place.
 */
public class KthLargestInAnArrayUsingQuickSelect
{
	//using Quick Select
	static Integer kth_largest_in_an_array(ArrayList<Integer> numbers, Integer k) {
		int left = 0, right = numbers.size()-1;
		int pivotIndex = -1;
		/**
		 * need to sort any elements before index k in desc order
		 */
		while (pivotIndex != k)
		{
			pivotIndex = partition(numbers, left, right);
			if (pivotIndex < k)
			{
				left = pivotIndex;
			}
			else
			{
				right = pivotIndex - 1;
			}
		}
		return numbers.get(k-1);
	}

	static int partition(ArrayList<Integer> numbers, int left, int right) {
		Integer pivotElement = choosePivot(numbers, left, right);
		System.out.println("pivot: "+pivotElement);

		while (left < right)
		{
			// Iterate through the range and swap elements to make sure
			// that all numbers greater than the pivot are moved to the left
			if (numbers.get(left) <= pivotElement)
			{
				Integer temp = numbers.get(left);
				numbers.set(left, numbers.get(right));
				numbers.set(right, temp);
				right--;
				System.out.println(numbers);
			}
			else
			{
				left++;
			}
		}
		if (numbers.get(left) > pivotElement)
			left++;
		return left;
	}

	static Integer choosePivot(ArrayList<Integer> numbers, int left, int right)
	{
		int pivotIndex = left + (right - left) / 2;
		System.out.println("left:"+left+", right:"+right+", pivotIndex:"+pivotIndex);
		return numbers.get(pivotIndex);
	}

	public static void main(String ... args) {
		ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(5, 1, 10, 3, 2));
		Integer result = kth_largest_in_an_array(numbers, 2);
		System.out.println("result:"+result);//5 -> [10, 5, 2, 3, 1]

		numbers = new ArrayList<>(Arrays.asList(4, 1, 2, 2, 3));
		result = kth_largest_in_an_array(numbers, 4);
		System.out.println("result:"+result);//2 -> [4, 3, 2, 2, 1]

		ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 10));
		Integer result1 = kth_largest_in_an_array(numbers1, 2);
		System.out.println("result:"+result1);//5 -> [10, 3, 5, 2, 1]
	}
}
