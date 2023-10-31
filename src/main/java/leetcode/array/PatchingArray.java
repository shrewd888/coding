package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.youtube.com/watch?v=N-tcCOCNSZY
 * 330. Patching Array
 *
 * Given a sorted integer array nums and an integer n, add/patch elements to the array such that any number in the range [1, n]
 * inclusive can be formed by the sum of some elements in the array.
 * Return the minimum number of patches required.
 *
 * Example 1:
 * Input: nums = [1,3], n = 6
 * Output: 1
 * Explanation:
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 *
 * Example 2:
 * Input: nums = [1,5,10], n = 20
 * Output: 2
 * Explanation: The two patches can be [2, 4].
 *
 * Example 3:
 * Input: nums = [1,2,2], n = 5
 * Output: 0
 */
public class PatchingArray
{
	public static List<Integer> minPatches(int[] nums, int n) {
		if (n == 0 || nums.length == 0) new ArrayList<>();

		List<Integer> result = new ArrayList<>();
		List<Integer> output = new ArrayList<>();
		int reach = 1;
		int nextElement = 1;
		int sum = 0;

		for (int i=0; i < nums.length; i++)
		{
			int num = nums[i];
			if (num > reach) //missing element
			{
				result.add(reach);
				reach = reach + sum; //the biggest reach
			}
			sum = sum + nextElement;
			reach = reach + sum;
			nextElement++;
		}
		while (reach < n)
		{
			int newNum = reach + 1;
			result.add(newNum);
			reach = reach + newNum;
		}
		return result;
	}

	public static class Pair<K,V>
	{
		K key;
		V value;

		Pair(K k, V v)
		{
			this.key = k;
			this.value = v;
		}

		K getKey()
		{
			return key;
		}

		V getValue()
		{
			return value;
		}
	}

	public static void main(String ... args) {
		int n = 20;
		int[] nums = {1,5,10};
		List<Integer> result = minPatches(nums, n);
		System.out.println(result);

	}
}
