package leetcode.slidingwindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 * 1456. Maximum Number of Vowels in a Substring of Given Length
 *
 * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 *
 * Example 1:
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 *
 * Example 2:
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 *
 * Example 3:
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: "lee", "eet" and "ode" contain 2 vowels.
 */

/**
 *
 */
public class MaxNumOfVowelsInSubstring
{
	final static Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

	public static int maxVowels(String s, int k) {
		int max = 0;
		int total = 0;
		for (int i=0; i<k; i++)
		{
			char c = s.charAt(i);
			if (vowels.contains(c))
			{
				total++;
			}
		}
		max = Math.max(max, total);
		for (int i=k; i < s.length(); i++)
		{
			char c = s.charAt(i);
			char prev = s.charAt(i-k);
			if (vowels.contains(prev))
			{
				total--;
			}
			if (vowels.contains(c))
			{
				total++;
			}
			max = Math.max(max, total);
		}
		return max;
	}

	public static void main(String ... args)
	{
		String s = "abciiidef";
		int k = 3;
		System.out.println(maxVowels(s, k)); //3

		s = "aeiou";
		k = 2;
		System.out.println(maxVowels(s, k)); //2

		s = "leetcode";
		k = 3;
		System.out.println(maxVowels(s, k)); //2

		s = "rhythms";
		k = 4;
		System.out.println(maxVowels(s, k)); //0

		s = "tryhard";
		k = 4;
		System.out.println(maxVowels(s, k)); //1
	}
}
