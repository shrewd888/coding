package leetcode.binarysearch.part4;

/**
 * https://leetcode.com/problems/split-array-largest-sum/
 * 410. Split Array Largest Sum
 *
 * Given an integer array nums and an integer k, split nums into k non-empty subarrays such that
 * the largest sum of any subarray is minimized.
 * Return the minimized largest sum of the split.
 * A subarray is a contiguous part of the array.
 *
 * Example 1:
 * Input: nums = [7,2,5,10,8], k = 2
 * Output: 18
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
 *
 * Example 2:
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 9
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
 *
 */
import java.util.Arrays;

/**
 * Omkar's Solution: Optimization Problem
 * 1. Minimize (optimize) the largest sum array of the sum array
 * 2. Lower bound: the max number in the array
 *    Upper bound: the sum of all numbers in the array
 *
 * This is similar with CapacityToShipPackagesWithinDDays
 * #of days in this case is numOfSubArray
 *
 * T(n) = O(#of iterations * time per iteration) = O (log (size of range) * n)
 */
public class SplitArrayLargestSum
{
	//Runtime 2 ms beats 57 %
	public static int splitArray(int[] nums, int k) {
		int start = 0; //start is the max(num)
		int end = Arrays.stream(nums).sum();

		for (int num : nums)
		{
			start = Math.max(start, num);
		}

		while (start <= end)
		{
			//mid is the sum
			int mid = start + (end - start)/2;
			//Can the largest sum be mid, or will this cause some trouble?
			/**
			 * Simulate the creation of the subarrays, keeping the subarray sums <= mid
			 * and then check if what you did was illegal/violated some constraints
			 * Constraint: Can not use > m subarrays
			 * If #of subarray we created > m, then we are in the left zone -> start = mid + 1
			 * Otherwise, we are in the right zone -> end = mid - 1
			 */
			//#of subarray = #of subarray necessary to have every subarray sum not exceed mid
			int noSubArray = 1;
			int load = 0;
			for (int i=0; i < nums.length; i++)
			{
				load += nums[i];
				if (load > mid)
				{
					//reset
					load = nums[i];
					noSubArray += 1;
				}
			}
			if (noSubArray > k)// we have a lot of subarray and the sum is small so we need to make the sum bigger
			{
				start = mid + 1;
			}
			else
			{
				end = mid - 1;
			}
		}
		return start; // Return the minimized largest sum of the split.
	}

	public static void main(String ... args)
	{
		int[] stations = {7,2,5,10,8};
		int k = 2;
		System.out.println(splitArray(stations, k));
	}
}
