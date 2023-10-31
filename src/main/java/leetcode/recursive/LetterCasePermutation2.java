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
public class LetterCasePermutation2
{
//Space: Input: O(n), Output: O(2^n * n), Aux Space: O(n) = O(2^n * n)
//Time: Leaf workers: O(2^n * n) + Internal node workers: O(2^n)

	public static List<String> letterCasePermutation(String s) {
		if (s == null) return null;
		List<String> result = new ArrayList<>();

		char[] chars = s.toCharArray();
		//pass in empty slate
		helper(chars, 0, new ArrayList<>(), result);
		return result;
	}

	//slate = partial solution
	public static void helper(char[] chars, int index, List<Character> slates, List<String> result)
	{
		//Base case: leaf node
		if (index == chars.length)
		{
//			String resultStr = slates.toString();
//			result.add(resultStr);
			//[[a, 1, b, 2], [a, 1, B, 2], [A, 1, b, 2], [A, 1, B, 2]]
			//[[3, z, 4], [3, Z, 4]]

			//this should make fresh string out of slates -> O(n)]
			//String res = "";
			StringBuilder sb = new StringBuilder();
			for (Character character : slates)
			{
				//res = res + character;
				sb.append(character);
			}
			result.add(sb.toString());
			//result.add(res);
			//[a1b2, a1B2, A1b2, A1B2]
			// [3z4, 3Z4]
			return;
		}
		//recursive case: internal node
		if (Character.isDigit(chars[index]))
		{
			slates.add(Character.valueOf(chars[index]));
			helper(chars, index+1, slates, result);
			slates.remove(slates.size()-1);
		}
		else //alpha
		{
			slates.add(Character.valueOf(Character.toLowerCase(chars[index])));
			helper(chars, index+1, slates, result);
			slates.remove(slates.size()-1); //rub-off what I wrote

			slates.add(Character.valueOf(Character.toUpperCase(chars[index])));
			helper(chars, index+1, slates, result);
			slates.remove(slates.size()-1); //rub-off what I wrote
		}
	}

	public static void main(String ... args) {
		List<String> result = letterCasePermutation("a1b2");
		System.out.println(result);

		result = letterCasePermutation("3z4");
		System.out.println(result);
	}
}
