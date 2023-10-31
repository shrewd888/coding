package leetcode.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/3sum/
 *
 * 15. 3Sum
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 *
 * Example 2:
 *
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 *
 * Example 3:
 *
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 */
public class ThreeSum
{
	public static List<List<Integer>> threeSum(int[] nums) {
		Set<List<Integer>> result = new HashSet<>();
		List<List<Integer>> resultList = new ArrayList<>();
		int len = nums.length;
		if (len==0 || len < 3) return resultList;
		Arrays.sort(nums);
		int start = 0;

		while (start < len-1)
		{
			int num0 = nums[start];
			if (num0 > 0) break;
			//reset
			int left = start + 1;
			int right = len-1;
			while (left < right)
			{
				int num1 = nums[left];
				int num2 = nums[right];
				int sum = num0 + num1 + num2;
				if (sum > 0)
				{
					right--;
				}
				else if (sum < 0)
				{
					left++;
					while (left < right && nums[left] == num1) {
						left++;
					}
				}
				else //sum == 0
				{
					List<Integer> inner = Arrays.asList(num0, num1, num2);
					result.add(inner);
					right--;
					left++;
				}
			}
			start++;
		}
		resultList.addAll(result);
		return resultList;
	}

	//Runtime: 1517 ms, faster than 5.00% of Java online submissions
	public static void main(String ... args) {
		int nums0[] = {-2,0,0,2,2};
		List<List<Integer>> result0 = threeSum(nums0);
		System.out.println(result0);//[[-2, 0, 2]]

		int nums[] = {-1,0,1,2,-1,-4};
		List<List<Integer>> result = threeSum(nums);
		System.out.println(result); //[[-1, -1, 2], [-1, 0, 1]]

		int nums1[] = {0,1,1};
		List<List<Integer>> result1 = threeSum(nums1);
		System.out.println(result1); //[]

		int nums2[] = {0,0,0};
		List<List<Integer>> result2 = threeSum(nums2);
		System.out.println(result2); //[[0, 0, 0]]

		int nums3[] = {-1,0,1,2,-1,-4,-2,-3,3,0,4};
		List<List<Integer>> result3 = threeSum(nums3);
		System.out.println(result3);
		//[[-4,0,4],[-4,1,3],[-3,-1,4],[-3,0,3],[-3,1,2],[-2,-1,3],[-2,0,2],[-1,-1,2],[-1,0,1]]
	}
}
