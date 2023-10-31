package leetcode.string;

import java.util.HashMap;
import java.util.Map;

import crackingcodinginterview.chap8.Hash;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 3. Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters
{
	public static int lengthOfLongestSubstring(String s) {

		int left = 0;
		int right = 0;
		Map<Character, Integer> charCountMap = new HashMap<>();
		int maxLength = 0;
		while (right < s.length())
		{
			char c = s.charAt(right);
			charCountMap.put(c, charCountMap.getOrDefault(c,0) + 1);

			while (charCountMap.get(c) > 1)
			{
				char chatAtLeft = s.charAt(left);
				charCountMap.put(chatAtLeft, charCountMap.get(chatAtLeft) - 1);
				left++;
			}
			int distance = right - left + 1;
			maxLength = Math.max(maxLength, distance);
			right++;
		}
		return maxLength;
	}

	public static void main(String ... args) {
		String s = "ecebaaaaca";
		Integer length = lengthOfLongestSubstring(s);
		System.out.println(length);//4

		s = "aaaaaaaaaaaaaa00000000ba";
		length = lengthOfLongestSubstring(s);
		System.out.println(length);//3
	}
}
