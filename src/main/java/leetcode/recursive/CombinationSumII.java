package leetcode.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 * Each number in candidates may only be used once in the combination.
 * Note: The solution set must not contain duplicate combinations.
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class CombinationSumII
{

	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		if (candidates.length==0) return result;

		Arrays.sort(candidates);

		List<Integer> slate = new ArrayList<>();
		helper(candidates, target, 0, slate, result);
		return result;
	}

	public static void helper(int[] nums, int target, int index, List<Integer> slate, List<List<Integer>> result)
	{
		//Backtracking
		Integer sum = slate.stream()
				.reduce(0, Integer::sum);
		if (sum == target)
		{
			List<Integer> copySlate = new ArrayList<>(slate);
			result.add(copySlate);
			return;
		}

		//Base case: leaf node
		if (index == nums.length)
		{
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
		helper(nums, target, index+count, slate, result);

		//multiway inclusion
		for (int i=0; i < count; i++)
		{
			slate.add(nums[index]);
			helper(nums, target, index+count, slate, result);
		}
		//clean up slate
		for (int i=0; i < count; i++)
		{
			slate.remove(slate.size()-1);
		}
	}

	public static void main(String ... args)
	{
		int[] candidates = new int[] { 10,1,2,7,6,1,5 };
		List<List<Integer>> result = combinationSum2(candidates, 8);
		//[[2, 6], [1, 7], [1, 2, 5], [1, 1, 6]]
		System.out.println(result);

		int[] candidates1 = new int[] { 2,5,2,1,2 };
		List<List<Integer>> result1 = combinationSum2(candidates1, 5);
		//[[5], [1, 2, 2]]
		System.out.println(result1);
	}
}
