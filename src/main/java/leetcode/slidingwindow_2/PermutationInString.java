package leetcode.slidingwindow_2;

/**
 * https://leetcode.com/problems/permutation-in-string/
 * 567. Permutation in String
 *
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 * Example 1:
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 *
 * Example 2:
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 */

import java.util.HashMap;
import java.util.Map;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-video-210507-518318-247-1563-3843887
 * Omkar's Sliding Window II
 * This is a decision problem because it returns true/false
 * T(N) = O(s1.length() + s2.length())
 * S(N) = O(1)
 */
public class PermutationInString
{
	public static boolean checkInclusion(String s1, String s2) {
		if (s2.length() < s1.length()) return false;

		Map<Character, Integer> s1Count = new HashMap<>();
		Map<Character, Integer> map = new HashMap<>();

		int k = s1.length();
		//initialize: T(s1) = O(s1)
		for (int i=0; i < k; i++)
		{
			char c = s1.charAt(i);
			s1Count.put(c, s1Count.getOrDefault(c,0) + 1);
		}
		//initialize map that belongs to s2
		for (int i=0; i < k; i++)
		{
			char c = s2.charAt(i);
			map.put(c, map.getOrDefault(c,0) + 1);
		}
		if (s1Count.equals(map))
		{
			return true;
		}

		for (int i = k; i < s2.length(); i++)
		{
			char outsideWindow = s2.charAt(i-k);
			map.put(outsideWindow, map.get(outsideWindow) - 1);
			if (map.get(outsideWindow) == 0)
			{
				//remove
				map.remove(outsideWindow);
			}

			char c = s2.charAt(i);
			map.put(c, map.getOrDefault(c,0) + 1);
			if (s1Count.equals(map))
				return true;
		}
		return false;
	}

	public static void main(String ... args)
	{
		String s1 = "a";
		String s2 = "ab";
		System.out.println(checkInclusion(s1, s2)); //true

		s1 = "ab";
		s2 = "eidbaooo";
		System.out.println(checkInclusion(s1, s2)); //true
	}
}
