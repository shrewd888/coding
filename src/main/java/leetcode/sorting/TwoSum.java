package leetcode.sorting;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 * 1. Two Sum
 */
public class TwoSum
{
	public static int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> numIndex = new HashMap<>();

		for (int i=0; i < nums.length; i++)
		{
			int remain = target - nums[i];
			if (numIndex.containsKey(remain))
			{
				return new int[] {i, numIndex.get(remain)};
			}
			numIndex.put(nums[i], i);
		}
		return new int[]{};
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

	//Runtime: 4 ms, faster than 82.44% of Java online submissions
	public static void main(String ... args) {
		int nums[] = {2,7,11,15}; int target = 9;
		int[] result = twoSum(nums, target);
		print(result); //[1,0]
	}
}
