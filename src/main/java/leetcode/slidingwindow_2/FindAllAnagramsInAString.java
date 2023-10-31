package leetcode.slidingwindow_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * 438. Find All Anagrams in a String
 *
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Example 1:
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 * Example 2:
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
/**
 * Omkar's Sliding Window II
 */
public class FindAllAnagramsInAString
{
	public static List<Integer> findAnagrams(String s, String p)
	{
		List<Integer> result = new ArrayList<>();
		if (s.length() < p.length()) return result;

		Map<Character, Integer> pCount = new HashMap<>();
		Map<Character, Integer> map = new HashMap<>();

		int k = p.length();
		//initialize: T(p) = O(p)
		for (int i=0; i < k; i++)
		{
			char c = p.charAt(i);
			pCount.put(c, pCount.getOrDefault(c,0) + 1);
		}
		//initialize map that belongs to s, up to p's length
		for (int i=0; i < k; i++)
		{
			char c = s.charAt(i);
			map.put(c, map.getOrDefault(c,0) + 1);
		}

		if (pCount.equals(map))
		{
			result.add(0);
		}

		for (int i=k; i < s.length(); i++)
		{
			char outsideWindow = s.charAt(i-k);
			map.put(outsideWindow, map.get(outsideWindow) - 1);
			if (map.get(outsideWindow) == 0)
			{
				//remove
				map.remove(outsideWindow);
			}

			char c = s.charAt(i);
			map.put(c, map.getOrDefault(c,0) + 1);
			if (pCount.equals(map))
				result.add(i-k + 1); //start from the beginning of this window index
		}
		return result;
	}

	public static void main(String ... args)
	{
		String s = "cbaebabacd";
		String p = "abc";
		System.out.println(findAnagrams(s, p)); //[0, 6]

		s = "abab";
		p = "ab";
		System.out.println(findAnagrams(s, p)); //[0, 1, 2]
	}
}
