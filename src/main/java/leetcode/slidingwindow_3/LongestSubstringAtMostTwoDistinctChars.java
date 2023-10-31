package leetcode.slidingwindow_3;

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/description/
 * 159. Longest Substring with At Most Two Distinct Characters
 *
 * Given a string s, return the length of the longest substring that contains at most two distinct characters.
 *
 * Example 1:
 * Input: s = "eceba"
 * Output: 3
 * Explanation: The substring is "ece" which its length is 3.
 *
 * Example 2:
 * Input: s = "ccaabbb"
 * Output: 5
 * Explanation: The substring is "aabbb" which its length is 5.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Omkar's Solution
 * T(n) = O(n)
 * S(n) = O(1)
 */
public class LongestSubstringAtMostTwoDistinctChars
{
	//Runtime 104 ms beats 57 %
	public static int lengthOfLongestSubstringTwoDistinct(String s) {
		char[] chars = s.toCharArray();

		Map<Character, Integer> charsMap = new HashMap<>();
		int left = 0;
		int globalMax = 0;

		//find max length substring ending at index i & containing at most 2 distinct characters
		for (int i=0; i<chars.length; i++)
		{
			charsMap.put(chars[i], charsMap.getOrDefault(chars[i],0)+1);
			while (left <= i && charsMap.size() > 2)
			{
				char charAtLeft = chars[left];
				charsMap.put(charAtLeft, charsMap.get(charAtLeft) - 1);
				if (charsMap.get(charAtLeft) == 0)
				{
					charsMap.remove(charAtLeft);
				}
				left++;
			}
			globalMax = Math.max(globalMax, i - left + 1);
		}
		return globalMax;
	}
}
