package leetcode.array.cyclesort;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 * 448. Find All Numbers Disappeared in an Array
 *
 * Given an array nums of n integers where nums[i] is in the range [1, n],
 * return an array of all the integers in the range [1, n] that do not appear in nums.
 *
 * Example 1:
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 *
 * Example 2:
 * Input: nums = [1,1]
 * Output: [2]
 *
 */
public class FindAllNumbersDisappearedInArray
{
	public static List<Integer> findDisappearedNumbers(int[] nums)
	{
		int N = nums.length;
		List<Integer> result = new ArrayList<>();

		for (int i=0; i < N; i++)
		{
			int num_i = nums[i];
			while (num_i != (i+1))
			{
				int nums_at_num_i = nums[num_i-1];
				if (nums_at_num_i == num_i)
				{
					break;
				}
				else
				{
					nums[num_i-1] = num_i;
					nums[i] = nums_at_num_i;
					num_i =  nums_at_num_i;
				}
			}
		}
		for (int i=0; i < N; i++)
		{
			if (nums[i]-1 != i)
			{
				result.add(i+1);
			}
		}
		return result;
	}

	public static void main (String[] args)
	{
		int[] nums = {4,3,2,7,8,2,3,1};
		List<Integer> result = findDisappearedNumbers(nums);
		System.out.println(result);//[5, 6]

		int[] nums1 = {1,1};
		List<Integer> result1 = findDisappearedNumbers(nums1);
		System.out.println(result1);//[2]
	}
}
