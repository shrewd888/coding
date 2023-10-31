package leetcode.array.cyclesort;

/**
 * https://leetcode.com/problems/first-missing-positive/description/
 * 41. First Missing Positive
 *
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 *
 * Example 1:
 * Input: nums = [1,2,0]
 * Output: 3
 * Explanation: The numbers in the range [1,2] are all in the array.
 *
 * Example 2:
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Explanation: 1 is in the array but 2 is missing.
 *
 * Example 3:
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * Explanation: The smallest positive integer 1 is missing.
 */

/**
 * Find the smallest missing positive integer in the range from 1 to n+1
 */
public class FirstMissingPositive
{
	public static int firstMissingPositive(int[] nums) {
		int N = nums.length;
		//focus on number 1 to N
		for (int i=0; i < N; i++)
		{
			int num_i = nums[i];
			while (num_i-1 != i)
			{
				int index = num_i-1;
				if (index >=0 && index <= N-1 && nums[index] != nums[i])
				{
					//swap
					int num_at_index = nums[index];
					nums[index] = num_i;
					nums[i] = num_at_index;
					num_i = num_at_index;
				}
				else
				{
					break;
				}
			}
		}

		for (int i=0; i < N; i++)
		{
			if (nums[i]-1 != i)
			{
				return i+1;
			}
		}
		return N+1;
	}

	//Runtime 2ms beats 87.53%
	public static void main (String[] args)
	{
		int[] nums0 = {1};
		int result0 = firstMissingPositive(nums0);
		System.out.println(result0);//2

		int[] nums = {1,2,0};
		int result = firstMissingPositive(nums);
		System.out.println(result);//3

		int[] nums1 = {3,4,-1,1};
		int result1 = firstMissingPositive(nums1);
		System.out.println(result1);//2

		int[] nums2 = {7,8,9,11,12};
		int result2 = firstMissingPositive(nums2);
		System.out.println(result2);//1

		int[] nums3 = {1,2,3,4,5};
		int result3 = firstMissingPositive(nums3);
		System.out.println(result3);//6
	}
}
