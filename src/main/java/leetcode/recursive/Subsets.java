package leetcode.recursive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
78. Subsets
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Given an integer array nums of unique elements, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any orde
*/
//Space: Input: O(n) + Aux Space: O(n) + Output
// Output: O(2^n * n) -> half of the subsets are of length n/2 because we are incl/excl

//Time complexity: O(2^n * n)
//Leaf workers: O(2^n * n)
//Internal workers: O(2^n * 1) -> why?
public class Subsets
{
	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums.length==0) return result;

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
		//exclude nums[i]
		helper(nums, index+1, slate, result);

		//include nums[i]
		slate.add(nums[index]);
		helper(nums, index+1, slate, result);
		slate.remove(slate.size()-1);
	}


	public static void main(String ... args) {
		int[] nums = new int[]{1,2,3};
		List<List<Integer>> result = subsets(nums);
		System.out.println(result);
	}
}
