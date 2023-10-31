package leetcode.array.cyclesort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
 * 442. Find All Duplicates in an Array
 *
 * Given an integer array nums of length n where all the integers of nums are in the range [1, n]
 * and each integer appears once or twice, return an array of all the integers that appears twice.
 *
 * You must write an algorithm that runs in O(n) time and uses only constant extra space.
 *
 * Example 1:
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [2,3]
 *
 * Example 2:
 * Input: nums = [1,1,2]
 * Output: [1]
 *
 * Example 3:
 * Input: nums = [1]
 * Output: []
 */
public class FindAllDuplicatesInArray
{
	public static List<Integer> findDuplicates(int[] nums) {
		int N = nums.length;
		Set<Integer> result = new HashSet<>();//can use list

		for (int i=0; i < N; i++)
		{
			int num_i = nums[i];
			while (num_i != (i+1))
			{
				int nums_at_num_i = nums[num_i-1];
				if (nums_at_num_i == num_i)
				{
					result.add(num_i); //or break without adding
					break;
				}
				else
				{
					nums[num_i-1] = num_i;//put the number in the correct index -> num[index] = index+1 -> num[0] = 1
					nums[i] = nums_at_num_i;
					num_i =  nums_at_num_i;
				}
			}
		}
		//then we can use for-loop
		/**
		 * 	for (int i=0; i < N; i++)
		 * 	{
		 * 		if (nums[i] != i+1)
		 * 		{
		 * 			result.add(nums[i]);
		 * 		}
		 * 	}
		 */
		return new ArrayList<>(result);
	}

	public static void main (String[] args)
	{
		int[] nums = {4,3,2,7,8,2,3,1};
		List<Integer> result = findDuplicates(nums);
		System.out.println(result);//[2,3]

		int[] nums1 = {1,1,2};
		List<Integer> result1 = findDuplicates(nums1);
		System.out.println(result1);//[1]

		int[] nums2 = {1};
		List<Integer> result2 = findDuplicates(nums2);
		System.out.println(result2);//[]
	}
}
