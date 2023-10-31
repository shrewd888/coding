package leetcode.slidingwindow_3;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 * 76. Minimum Window Substring
 *
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * Example 1:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 *
 * Example 2:
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 *
 * Example 3:
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Omkar's Solution
 * Array Floater 6 - Sliding Window III
 * Decrease & Conquer
 * Find the shortest substring of s ending at index i which covers all the characters of t
 * Global answer = Min(all the local answer)
 *
 * The left index marks the longest substring that is missing some characters of t
 * T(n) = O(n), time to check the characters can be considered as constant because there are 26 chars
 * S(n) = O(1), hashmap stores at most 26 chars
 */
public class MinimumWindowSubstring
{
	static Map<Character, Integer> tCharsMap;
	//TODO: revisit, mine does not pass test cases
	//t <= s
	public static String minWindow(String s, String t) {
		if (t.length() > s.length()) return "";
		if (s.length()==0) return "";

		tCharsMap = initialize(t.toCharArray());

		int left = 0;
		int right = 1;
		String shortestString = s;
		int shortestLength = shortestString.length();

		while (left <= right && right <= s.length())
		{
			String window = s.substring(left, right);
			char[] windowChars = window.toCharArray();
			//if these windowChars contains all characters in t, increase/move left to the right
			if (containsAllTChars(windowChars))
			{
				String result = s.substring(left, right);
				if (result.length() < shortestLength)
				{
					shortestString = result;
				}
				left++;

				while (left < s.length() && !tCharsMap.containsKey(s.charAt(left)))
				{
					left++;
				}
			}
			else
			{
				right++;
			}
		}
		return left > 0 ? shortestString : "";
	}

	//windowChars is the superset of all characters in t
	public static boolean containsAllTChars(char[] windowChars)
	{
		Map<Character, Integer> windowCharsMap = new HashMap<>();
		for (char c : windowChars)
		{
			windowCharsMap.put(c, windowCharsMap.getOrDefault(c,0)+1);
		}
		//for each character in t, check if windowCharsMap has at least one of each of them
		for (Map.Entry<Character, Integer> entry : tCharsMap.entrySet())
		{
			Character t = entry.getKey();
			Integer count = entry.getValue();
			if (!windowCharsMap.containsKey(t))
				return false;
			if (windowCharsMap.get(t) < count) return false;
		}
		return true;
	}

	public static Map<Character, Integer> initialize(char[] tChars)
	{
		Map<Character, Integer> charMap = new HashMap<>();
		for (char t : tChars)
		{
			charMap.put(t, charMap.getOrDefault(t,0)+1);
		}
		return charMap;
	}

	//TODO: revisit, does not pass test cases
	public static String minWindow_Omkar(String s, String t)
	{
		tCharsMap = initialize(t.toCharArray());
		int startIndex = -1;
		int endIndex = -1;

		Map<Character, Integer> sMap = new HashMap<>();
		int left = 0;
		int globalMin = Integer.MAX_VALUE;

		for (int i=0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			sMap.put(c, sMap.getOrDefault(c,0)+1);
			while (left <= i && contains(sMap))
			{
				char leftChar = s.charAt(left);
				sMap.put(leftChar, sMap.getOrDefault(leftChar,0)-1);
				if (sMap.get(leftChar) == 0)
				{
					sMap.remove(leftChar);
				}
				left++;
			}
			if (left > 0)
			{
				if (i-left + 2 < globalMin)
				{
					globalMin = i-left + 2;
					startIndex = left - 1;
					endIndex = i;
				}
			}
		}
		return "";
	}

	public static boolean contains(Map<Character, Integer> sMap)
	{
		//for each character in t, check if windowCharsMap has at least one of each of them
		for (Map.Entry<Character, Integer> entry : tCharsMap.entrySet())
		{
			Character t = entry.getKey();
			Integer count = entry.getValue();
			if (!sMap.containsKey(t))
				return false;
			if (sMap.get(t) < count) return false;
		}
		return true;
	}

	public static void main(String ... args)
	{
		String s0 ="cabwefgewcwaefgcf";
		String t0 ="cae";
		System.out.println(minWindow(s0, t0));//"aefgc" EXEPCTED: "cwae"

		String s ="abc";
		String t ="cba";
		System.out.println(minWindow(s, t));//abc

		String s1 = "aa";
		String t1 = "aa";
		System.out.println(minWindow(s1, t1)); //aa

		String s3 = "AB";
		String t3 = "a";
		System.out.println(minWindow(s3, t3)); //""

		String s12 = "ADOBECODEBANC";
		String t12 = "ABC";
		System.out.println(minWindow(s12, t12)); //BANC

		s = "a";
		t = "a";
		System.out.println(minWindow(s, t)); //a

		s = "a";
		t = "aa";
		System.out.println(minWindow(s, t)); //""

		s = "a";
		t = "b";
		System.out.println(minWindow(s, t)); //""

	}
}
