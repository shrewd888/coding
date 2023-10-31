package leetcode.recursive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 47. Permutations II
 * Given a collection of numbers, nums, that might contain duplicates,
 * return all possible unique permutations in any order.
 *
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */

/**
 * Space Complexity: O(n!*n)
 * Input: O(n), Aux: O(n^2) ? => mutable slate, Output: O(n!*n)
 * Time Complexity: O(n!*n)
 * Leaf workers: O(n!*n) + Internal node worker <= O(n!*n)
 */
public class PermutationsII
{
	public static List<List<Integer>> permute(int[] nums) {

		List<List<Integer>> result = new ArrayList<>();
		if (nums.length==0) return result;

		List<Integer> slate = new ArrayList<>();
		helper(nums, 0, slate, result);
		return result;
	}

	public static void helper(int[] nums, int index, List<Integer> slate, List<List<Integer>> result)
	{
		//Base Case:
		if (index == nums.length)
		{
			List<Integer> copySlate = new ArrayList<>(slate); //O(n)
			result.add(copySlate);
			return;
		}
		Set<Integer> uniqueNum = new HashSet<>();
		//Recursive Case: Internal node worker
		for (int i=index; i < nums.length; i++)
		{
			int numberToPick = nums[i]; //next number to pick
			if (uniqueNum.contains(numberToPick))
			{
				continue;
			}
			uniqueNum.add(numberToPick);
			int numberAtIndex = nums[index];
			//swap
			nums[i] = numberAtIndex;
			nums[index] = numberToPick;

			slate.add(nums[index]);
			helper(nums, index+1, slate, result);
			slate.remove(slate.size()-1);
			//swap back
			nums[i] = numberToPick;
			nums[index] = numberAtIndex;
		}
	}

	public static void main(String ... args)
	{
		int[] nums = new int[] { 1, 1, 2 };

		List<List<Integer>> result = permute(nums);
		//[[1, 1, 2], [1, 2, 1], [2, 1, 1]]

		System.out.println(result);
	}
}
