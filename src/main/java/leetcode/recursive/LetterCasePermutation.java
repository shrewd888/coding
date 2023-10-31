package leetcode.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. Letter Case Permutation
 * Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create. Return the output in any order.
 *
 * Example 1:
 *
 * Input: s = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * Example 2:
 *
 * Input: s = "3z4"
 * Output: ["3z4","3Z4"]
 */
public class LetterCasePermutation
{
	//Time complexity: O(2^n * 1) + O(2^n * 1) = O(2^n * n)
	// O(2^n * 1) -> leaf nodes
	//internal worker do string concat. #of internal nodes = 50% #of leaf nodes
	// O(2^n * 1)
	public static List<String> letterCasePermutation(String s) {
		if (s == null) return null;
		List<String> result = new ArrayList<>();

		char[] chars = s.toCharArray();
		helper(chars, 0, "", result);
		return result;
	}

	//slate = partial solution
	public static void helper(char[] chars, int index, String slate, List<String> result)
	{
		//Base case: leaf node
		if (index == chars.length)
		{
			result.add(slate);
			return;
		}
		//recursive case: internal node
		if (Character.isDigit(chars[index]))
		{
			helper(chars, index+1, slate + chars[index], result);
		}
		else //alpha
		{
			helper(chars, index+1, slate + Character.toLowerCase(chars[index]), result);
			helper(chars, index+1, slate + Character.toUpperCase(chars[index]), result);
		}
	}


	public static void main(String ... args) {

		List<String> result = letterCasePermutation("a1b2"); //[a1b2, a1B2, A1b2, A1B2]
		System.out.println(result);

		result = letterCasePermutation("3z4"); //[3z4, 3Z4]
		System.out.println(result);
	}
}
