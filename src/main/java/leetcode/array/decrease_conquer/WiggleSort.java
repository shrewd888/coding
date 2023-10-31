package leetcode.array.decrease_conquer;

/**
 * https://leetcode.com/problems/wiggle-sort/
 * 280. Wiggle Sort
 * Given an integer array nums, reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * You may assume the input array always has a valid answer.
 *
 * Example 1:
 * Input: nums = [3,5,2,1,6,4]
 * Output: [3,5,1,6,2,4]
 * Explanation: [1,6,2,5,3,4] is also accepted.
 *
 * Example 2:
 * Input: nums = [6,6,5,6,3,8]
 * Output: [6,6,5,6,3,8]
 */
/**
 * T(n) = O(n)
 * S(n) = O(1)
 */
public class WiggleSort
{
	//if i is odd: nums[i-1] should be < nums[i]
	//if i is even: nums[i-1] should be > nums[i]
	public static void wiggleSort(int[] nums) {
		for (int i=1; i < nums.length; i++)
		{
			if (i % 2 == 1 && nums[i - 1] > nums[i] || (i % 2 == 0 && nums[i - 1] < nums[i]))
			{
				int temp = nums[i - 1];
				nums[i - 1] = nums[i];
				nums[i] = temp;
			}
		}
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


	public static void main (String[] args)
	{
		int[] nums = {3,5,2,1,6,4};
		wiggleSort(nums);
		print(nums);//[3,5,1,6,2,4]

		nums = new int[]{6,6,5,6,3,8};
		wiggleSort(nums);
		print(nums);//[6,6,5,6,3,8]
	}
}
