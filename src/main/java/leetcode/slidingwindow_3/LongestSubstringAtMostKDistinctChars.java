package leetcode.slidingwindow_3;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * 340. Longest Substring with At Most K Distinct Characters
 *
 * Given a string s and an integer k, return the length of the longest
 * substring of s that contains at most k distinct characters.
 *
 * Example 1:
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: The substring is "ece" with length 3.
 *
 * Example 2:
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: The substring is "aa" with length 2.
 */
/**
 * Omkar's Solution
 * T(n) = O(n)
 * S(n) = O(k)
 */
public class LongestSubstringAtMostKDistinctChars
{
	//Runtime 17 ms beats 65 %
	public static int lengthOfLongestSubstringKDistinct(String s, int k) {
		char[] chars = s.toCharArray();

		Map<Character, Integer> charsMap = new HashMap<>();
		int left = 0;
		int globalMax = 0;

		//find max length substring ending at index i & containing at most 2 distinct characters
		for (int i=0; i < chars.length; i++)
		{
			charsMap.put(chars[i], charsMap.getOrDefault(chars[i],0)+1);
			while (left <= i && charsMap.size() > k)
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
