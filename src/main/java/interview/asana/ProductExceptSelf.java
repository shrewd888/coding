package interview.asana;

/***
 * https://leetcode.com/problems/product-of-array-except-self/
 * 238. Product of Array Except Self
 *
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 *
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 */
public class ProductExceptSelf
{
	//brute force O(n2) : time limit exceeded
	/**
	 * Time Complexity: O (n^2)
	 * Space Complexity: O (1)
	 */
	public static int[] productExceptSelf(int[] nums) {
		int len = nums.length;
		int[] result = new int[len];

		if (len==0) return result;

		int l = 0;
		int r = len-1;
		int index = 0;
		int product = 1;

		while (index < len)
		{
			while (r > index)
			{
				product = product * nums[r];
				r--;
			}
			while (l < index)
			{
				product = product * nums[l];
				l++;
			}
			result[index] = product;
			index++;
			//reset
			r = len-1;
			l = 0;
			product = 1;
		}

		return result;
	}

	public static int[] productExceptSelf_Efficient(int[] nums) {
		int len = nums.length;
		int[] result = new int[len];

		if (len==0) return result;

		//from left to right
		for (int i=0; i < len; i++)
		{
			if (i==0)
			{
				result[i] = 1;
			}
			else //i > 0
			{
				result[i] = result[i-1] * nums[i-1];
			}
		}
		int temp = 1;
		//right to left
		for (int i=len-1; i >= 0; i--)
		{
			if (i == len-1)
			{
				result[i] = result[i] * 1;
				temp = 1;
			}
			else //i < len-1
			{
				temp = temp * nums[i+1];
				result[i] = result[i] * temp;
			}
		}

		return result;
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

	public static void main(String ... args)
	{
		int[] nums = {1,2,3,4};
		int[] result = productExceptSelf(nums);
		print(result);//[24,12,8,6]

		int[] nums1 = {-1,1,0,-3,3 };
		result = productExceptSelf(nums1);
		print(result);//[0,0,9,0,0]

		int[] nums2 = {1,2,3,4};
		int[] result2 = productExceptSelf_Efficient(nums2);
		print(result2);//[24,12,8,6]

		int[] nums3 = {-1,1,0,-3,3 };
		int[] result3 = productExceptSelf_Efficient(nums3);
		print(result3);//[0,0,9,0,0]

	}
}
