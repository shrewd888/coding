package leetcode.slidingwindow_2;

/**
 * https://leetcode.com/problems/subarray-product-less-than-k/
 * 713. Subarray Product Less Than K
 * Given an array of integers nums and an integer k, return the number of contiguous subarrays
 * where the product of all the elements in the subarray is strictly less than k.
 *
 * Example 1:
 * Input: nums = [10,5,2,6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 0
 * Output: 0
 */
/**
 * T(n) = O(n)
 * S(n) = O(1)
 */
public class SubarrayProductLessThanK
{
	public static int numSubarrayProductLessThanK(int[] nums, int k) {
		int windowProduct = 1;
		int globalCount = 0;
		int left = 0;

		for (int i=0; i < nums.length; i++)
		{
			//Work to be done for subarray ending at index i
			windowProduct = windowProduct * nums[i];
			while (left <= i && windowProduct >= k)
			{
				windowProduct = windowProduct/nums[left];
				left++;
			}
			globalCount = globalCount + (i-left+1); //localAnswer =  (i-left+1)
		}
		return globalCount;
	}

	public static void main(String ... args)
	{
		int[] nums = {10,5,2,6};
		int k = 100;
		System.out.println(numSubarrayProductLessThanK(nums, k));//8

		int[] nums1 = {1,2,3};
		int k1 = 0;
		System.out.println(numSubarrayProductLessThanK(nums1, k1));//1

//		int[] nums2 = {1,1,1,1,1,1,1,1};
//		int k2 = 11;
//		System.out.println(minSubArrayLen_Omkar(k2, nums2));//0
//
//		int[] nums3 = {1,2,3,4,5};
//		int k3 = 11;
//		System.out.println(minSubArrayLen_Omkar(k3, nums3));//3
//
//		int[] nums4 = {2,3,1,2,4,3,7};
//		int k4 = 7;
//		System.out.println(minSubArrayLen_Omkar(k4, nums4));//1
	}
}
