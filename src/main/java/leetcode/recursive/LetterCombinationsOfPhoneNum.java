package leetcode.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 17. Letter Combinations of a Phone Number
 * Given a string containing digits from 2-9 inclusive,
 * return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Input: digits = ""
 * Output: []
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 */


public class LetterCombinationsOfPhoneNum
{
	private static final Map<Character, String> phoneNum;
	static {
		Map<Character, String> map = new HashMap<>();
		map.put('2', "abc");
		map.put('3', "def");
		map.put('4', "ghi");
		map.put('5', "jkl");
		map.put('6', "mno");
		map.put('7', "pqrs");
		map.put('8', "tuv");
		map.put('9', "wxyz");
		phoneNum = Collections.unmodifiableMap(map);
	}

	private static List<String> result = new ArrayList<>();

	public static List<String> letterCombinations(String digits)
	{
		if (digits == null || digits.length()==0) return result;

		char[] charArray = digits.toCharArray(); //23
		List<String> phoneStrs = new ArrayList<>(); //["abc", "def"]
		for (int i=0; i<charArray.length; i++)
		{
			phoneStrs.add(phoneNum.get(charArray[i]));
		}

		List<Character> slate = new ArrayList<>();
		helper(phoneStrs, 0, slate);
		return result;
	}

	//phoneStr = ["abc", "def"]
	public static void helper(List<String> phoneStr, int index, List<Character> slate)
	{
		//Base case: Leaf nodes
		if (index == phoneStr.size())
		{
			StringBuilder sb = new StringBuilder();
			for (Character c : slate)
			{
				sb.append(c);
			}
			result.add(sb.toString());
			return;
		}
		//Recursive internal node
		String phoneLetters = phoneStr.get(index);
		char[] chars = phoneLetters.toCharArray();
		for (int i=0; i < chars.length; i++)
		{
			slate.add(chars[i]);
			helper(phoneStr, index + 1, slate);
			slate.remove(slate.size() - 1);
		}
	}

	public static void main(String ... args) {
		List<String> result = letterCombinations("23");
		System.out.println(result);
	}
}
