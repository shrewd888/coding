package interviewkickstart.recursive;

import java.util.ArrayList;

/**
 * IK Recursion Timed Test
 * Given a set of integers and a target value k, find whether there is a non-empty subset that sums up to k.
 * Example 1:
 * "arr": [2, 4, 8],
 * "k": 6
 * Output: 1 //Because 2 + 4 = 6.
 *
 * Example 2:
 * "arr": [2, 4, 6],
 * "k": 5
 * Output: 0
 */

/**
 * Time Complexity: O (2^n)
 * Space Complexity: O(n)
 */
public class TargetSum
{
	static Boolean check_if_sum_possible(ArrayList<Long> arr, Long k) {

		if (arr == null || arr.size()==0) return Boolean.FALSE;
		//pass in size of slate
		return helper(arr, k, 0l, 0l, 0l);
	}

	static Boolean helper(ArrayList<Long> arr, Long k, long index, long sum, long size)
	{
		if (index == arr.size())
		{
			if (sum == k && size > 0l)
				return Boolean.TRUE;
			else
				return Boolean.FALSE;
		}
		if (size > 0l && sum == k) return Boolean.TRUE;
		//index < arr.size
		//exclude
		if (helper(arr, k, index+1l, sum, size)) return Boolean.TRUE;
		//include
		return helper(arr, k, index+1l,sum + arr.get((int) index), size+1l);
	}

	public static void main(String ... args) {
		ArrayList<Long> arr = new ArrayList<>();
		arr.add(0l);
		boolean b = check_if_sum_possible(arr, 0l);
		System.out.println(b);//true

		arr = new ArrayList<>();
		arr.add(2l);
		b = check_if_sum_possible(arr, 0l);
		System.out.println(b);//false

		arr = new ArrayList<>();
		arr.add(-10l);
		arr.add(10l);
		b = check_if_sum_possible(arr, 0l);
		System.out.println(b);//true

		arr = new ArrayList<>();
		arr.add(3l);
		arr.add(1l);
		b = check_if_sum_possible(arr, 5l);
		System.out.println(b);//false

		ArrayList<Long> arr1 = new ArrayList<>();
		arr1.add(2l);
		arr1.add(4l);
		arr1.add(8l);
		boolean b1 = check_if_sum_possible(arr1, 6l);
		System.out.println(b1);//true

		ArrayList<Long> arr2 = new ArrayList<>();
		arr2.add(1l);
		boolean b2 = check_if_sum_possible(arr2, 0l);
		System.out.println(b2);//false
	}
}
