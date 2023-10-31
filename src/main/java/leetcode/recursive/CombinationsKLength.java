package leetcode.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. Combinations
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 * You may return the answer in any order.
 * Input: n = 4, k = 2 // [1,2,3,4]
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * Input: n = 1, k = 1
 * Output: [[1]]
 */
public class CombinationsKLength
{

	public static List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> result = new ArrayList<>();

		List<Integer> nums = new ArrayList<>();
		for(int i=1; i<=n; i++)
		{
			nums.add(i);
		}
		List<Integer> slate = new ArrayList<>();
		helper(nums, 0, k, slate, result);
		return result;
	}

	public static void helper(List<Integer> nums, int index, int k, List<Integer> slate, List<List<Integer>> result)
	{
		//Backtracking Case
		if (slate.size() == k)
		{
			List<Integer> slateCopy = new ArrayList<>(slate);
			result.add(slateCopy);
			return;
		}
		//Base case: Leaf nodes
		if (index == nums.size())
		{
			return;
		}
		//Recursive internal node
		//exclude
		helper(nums, index+1, k, slate, result);
		//include
		slate.add(nums.get(index));
		helper(nums, index+1, k, slate, result);
		slate.remove(slate.size() - 1);
	}

	public static void main(String ... args) {
		List<List<Integer>> result = combine(4, 2);
		System.out.println(result); //[[3, 4], [2, 4], [2, 3], [1, 4], [1, 3], [1, 2]]
	}
}
