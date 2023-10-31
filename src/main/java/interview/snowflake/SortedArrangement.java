package interview.snowflake;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.eolymp.com/en/problems/10615#:~:text=There%20is%20a%20container%20that,removed%20elements%20are%20added%20back.
 *
 * https://leetcode.com/discuss/interview-question/371225/sorted-arrangements-infosys-specialist-programmer-on-campus-hackerrank
 *
 * https://leetcode.com/discuss/interview-question/2146199/Snowflake-or-OA-or-2022-or-Sorted-Arrangement
 * There is a container which is open from both the ends and that always is in sorted order.
 * To insert an element, its position is determined, then each of the element to the left or right of that position is removed.
 * The new element is inserted, then the removed elements are added back.
 * Each removal or insertion is an operation.
 * Determine the minimum number of operations after inserting a list of integers into an empty list.
 *
 * Example:
 * An ordered array of integers has been created containing the values [2,5,6,10].
 * The value 3 must be inserted. It is determined that 3 goes between 2 and 5.
 * Either remove the 2, insert the 3 and insert 2 for a total of 3 operations, or remove the 5,6 and 10,
 * insert 3 and insert 5,6,10 for a total of 7 operations.
 * The minimal value, 3, is the result chosen.
 *
 * For case of [10,6,2,3,7,1,2] answer is 13
 *
 * Insert 10
 * Insert 6
 * Insert 2
 * Remove 2
 * Insert 3
 * Insert 2
 * Remove 10
 * Insert 7
 * Insert 10
 * Insert 1
 * Remove 1
 * Insert 2
 * Insert 2
 * Constraints for n and arr[i] are 1-10 power 5
 */

public class SortedArrangement
{
	//n = size of the array
	public static int sortedArrangement(int[] input, int n)
	{
		int minOps = 0;
		List<Integer> result = new ArrayList<>();
		if (n==0 || input.length==0) return minOps;

		result.add(input[0]);
		minOps += 1;
		for (int i=1; i < n; i++)
		{
			int index = findIndex(result, input[i]);
			int size = result.size();
			int distanceFromLeft = index;
			int distanceFromRight = size - distanceFromLeft;
			result.add(index, input[i]);
			if (distanceFromLeft < distanceFromRight)
			{
				//insert number from left
				minOps += 2 * index + 1;
			}
			else //insert number from right
			{
				minOps += 2 * distanceFromRight + 1;
			}
		}
		System.out.println(result);
		return minOps;
	}

	//where in the currentList this number should be placed? In which index?
	public static int findIndex(List<Integer> result, int num)
	{
		int start=0, end = result.size()-1;
		while (start <= end)
		{
			int mid = start + (end-start)/2;
			if (result.get(mid) >= num)
			{
				end = mid-1;
			}
			else
				start = mid + 1;
		}
		return start;
	}

	public static void main(String ... args) {
		int[] input = {2,4,1,3};
		int n = 4;
		int result = sortedArrangement(input, n);
		System.out.println(result);//[1, 2, 3, 4] - 6 min ops

		int[] input1 = {10,6,2,3,7,1,2};
		int n1 = 7;
		int result1 = sortedArrangement(input1, n1);
		System.out.println(result1);//[1, 2, 2, 3, 6, 7, 10] - 13 min ops

	}
}
