package leetcode.array.cyclesort;

import java.util.List;

/**
 * 287. Find the Duplicate Number
 *
 * Given an array of integers nums containing n + 1 integers
 * where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 *
 * Example 1:
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 * How can we prove that at least one duplicate number must exist in nums? Pigeonhole principle
 */
public class FindTheDuplicateNumber
{
	public static int findDuplicate(int[] nums) {
		int N = nums.length;
		for (int i=0; i < N; i++)
		{
			int num_i = nums[i];
			while (num_i - 1 != i)
			{
				int num_at_index_num_i = nums[num_i-1];

				if (num_i == num_at_index_num_i)
				{
					return num_i;
					//break;
				}

				nums[i] = num_at_index_num_i;
				nums[num_i-1] = num_i;
				num_i = nums[i];
			}
		}
//		int dup = -1;
//		for (int i=0; i<N; i++)
//		{
//			if (nums[i]-1 != i)
//			{
//				dup = nums[i];
//				return dup;//only 1 dup
//			}
//		}
//		return dup;
		return -1;
	}
	public static void main (String[] args)
	{
		int[] nums = {4,3,1,4,2};
		int result = findDuplicate(nums);
		System.out.println(result);//4

		int[] nums1 = {2,5,9,6,9,3,8,9,7,1};
		int result1 = findDuplicate(nums1);
		System.out.println(result1);//9
	}
}
