package leetcode.array.cyclesort;

/**
 * https://leetcode.com/problems/missing-number/
 * 268. Missing Number
 *
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 *
 * Example 1:
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers,
 * so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
 *
 * Example 2:
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers,
 * so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
 *
 * Example 3:
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers,
 * so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums
 */
/**
 * Cycle Sort: 0 to (n-1)
 * This problem: 0 to n
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MissingNumber
{
	public static int missingNumber(int[] nums) {
		int N = nums.length;
		for (int i=0; i<N; i++)
		{
			int num_i = nums[i];
			while (num_i != i && num_i != N)//num_i != N => we don't want the number to be at index N which is out of range
			{
				//swap
				int temp = nums[num_i];
				nums[num_i] = num_i;
				nums[i] = temp;
				num_i = temp;
			}
		}
		//second scan, the missing number is the index where num N reside, because the array maxIndex = N-1
		for (int i=0; i < N; i++)
		{
			int numIndex = nums[i];
			if (numIndex != i)
			{
				return i;
			}
		}
		return N;
	}

	public static void main (String[] args)
	{
		int[] nums = {3,0,1};
		int n = missingNumber(nums);
		System.out.println(n);//2

		int[] nums1 = {0,1};
		int n1 = missingNumber(nums1);
		System.out.println(n1);//2

		int[] nums2 = {9,6,4,2,3,5,7,0,1};
		int n2 = missingNumber(nums2);
		System.out.println(n2);//8
	}
}
