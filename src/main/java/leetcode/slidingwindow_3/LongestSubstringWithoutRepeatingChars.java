package leetcode.slidingwindow_3;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 3. Longest Substring Without Repeating Characters
 *
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

import java.util.HashMap;
import java.util.Map;

/**
 * Omkar's Solution
 *
 * Can optimize by keeping track of the index of where the character exist
 * so that the left index can be move after the index of the prev occurence
 *
 * hmap2 = {} -> key: char, value: the index it appears in s most recently
 * if s[i] is in hmap2:
 * left = max(left, 1 + hmap2[s[i]])
 */
//TODO: revisit the optimal solution
public class LongestSubstringWithoutRepeatingChars
{
	//Runtime 9 ms beats 79 %
	public static int lengthOfLongestSubstring(String s) {
		int left = 0;
		char[] chars = s.toCharArray();
		int globalmax = 0;

		Map<Character, Integer> charsMap = new HashMap<>();
		for (int i=0; i < chars.length; i++)
		{
			char c = chars[i];
			charsMap.put(c, charsMap.getOrDefault(c,0)+1);
			while (left <= i && charsMap.get(c) > 1)
			{
				//if  charsMap.get(c) > 1 there must be same char exist on the left
				//find where this char exist started from left pointer
				char charLeft = chars[left];
				charsMap.put(charLeft, charsMap.get(charLeft)-1);
				if (charsMap.get(charLeft) == 0)
				{
					charsMap.remove(charLeft);
				}
				left++;
			}
			globalmax = Math.max(globalmax, i-left+1);
		}
		return globalmax;
	}

	public static void main(String ... args)
	{
		String s = "abcabcbb";
		System.out.println(lengthOfLongestSubstring(s));//3

		s = "bbbbb";
		System.out.println(lengthOfLongestSubstring(s));//1

		s = "pwwkew";
		System.out.println(lengthOfLongestSubstring(s));//3
	}
}
