package leetcode.array.cyclesort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/set-mismatch/
 * 645. Set Mismatch
 *
 * You have a set of integers s, which originally contains all the numbers from 1 to n.
 * Unfortunately, due to some error, one of the numbers in s got duplicated
 * to another number in the set, which results in repetition of one number and loss of another number.
 *
 * You are given an integer array nums representing the data status of this set after the error.
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 * => Return : duplicate number and then the missing number
 *
 * Example 1:
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 *
 * Example 2:
 * Input: nums = [1,1]
 * Output: [1,2]
 */
public class SetMismatch
{
	public static int[] findErrorNums(int[] nums)
	{
		int N = nums.length;
		List<Integer> result = new ArrayList<>();

		for (int i = 0; i < N; i++)
		{
			int num_i = nums[i];
			while (num_i != (i + 1))
			{
				int nums_at_num_i = nums[num_i - 1];
				if (nums_at_num_i == num_i)
				{
					//result.add(num_i);
					break;
				}
				else
				{
					nums[num_i - 1] = num_i;
					nums[i] = nums_at_num_i;
					num_i = nums_at_num_i;
				}
			}
		}
		for (int i = 0; i < N; i++)
		{
			if (nums[i] - 1 != i)
			{
				result.add(nums[i]); //duplicate number
				result.add(i + 1); //missing number
			}
		}
		return result.stream().mapToInt(i->i).toArray();
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
		System.out.println();
	}

	public static void main (String[] args)
	{
		int[] nums = {1,2,2,4};
		int[] result = findErrorNums(nums);
		print(result);//[2,3]

		int[] nums1 = {1,1};
		int[] result1 = findErrorNums(nums1);
		print(result1);//[1,2]

		int[] nums2 = {8,7,3,5,3,6,1,4};
		int[] result2 = findErrorNums(nums2);
		print(result2);//[3,2]

		int[] nums3 = {2,2};
		int[] result3 = findErrorNums(nums3);
		print(result3);//[2,1]
	}
}
