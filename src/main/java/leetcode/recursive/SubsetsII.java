package leetcode.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class SubsetsII
{
	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums.length==0) return result;

		Arrays.sort(nums);

		List<Integer> slate = new ArrayList<>();
		helper(nums, 0, slate, result);
		return result;
	}

	public static void helper(int[] nums, int index, List<Integer> slate, List<List<Integer>> result)
	{
		//Base case: leaf node
		if (index == nums.length)
		{
			//need to copy because slate is mutable across the code
			List<Integer> copySlate = new ArrayList<>(slate);
			result.add(copySlate);//slate is a complete subset
			return;
		}
		//Recursive: internal node
		//count how many copies of nums[i] there are
		int count = 0;
		for (int i=index; i < nums.length; i++)
		{
			if (nums[index] != nums[i])
				break; //we are dealing with non-duplicate number
			count++;
		}
		//exclude
		helper(nums, index+count, slate, result);

		//multiway inclusion
		for (int i=0; i < count; i++)
		{
			slate.add(nums[index]);
			helper(nums, index+count, slate, result);
		}
		//clean up slate
		for (int i=0; i < count; i++)
		{
			slate.remove(slate.size()-1);
		}
	}

	public static void main(String ... args)
	{
		int[] nums = new int[] { 1, 2, 2 };

		List<List<Integer>> result = subsetsWithDup(nums);
		//[[], [2], [2, 2], [1], [1, 2], [1, 2, 2]]
		System.out.println(result);
	}
}
