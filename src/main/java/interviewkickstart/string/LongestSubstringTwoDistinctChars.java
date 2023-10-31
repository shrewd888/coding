package interviewkickstart.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-210509-518320-66-395-3843543
 * Longest Substring With Exactly Two Distinct Characters
 * Given a string s of length n, find the length of the longest substring that contains
 * exactly two distinct characters.
 *
 * Example
 * {
 * "s": "ecebaaaaca"
 * }
 * Output:
 * 6
 * "aaaaca" is the largest substring with exactly 2 distinct characters.
 */
public class LongestSubstringTwoDistinctChars
{
	static Integer get_longest_substring_length_with_exactly_two_distinct_chars(String s) {
		int left = 0;
		int right = 0;
		int maxLength = 0;
		Map<Character, Integer> charCount = new HashMap<>();

		while (right < s.length())
		{
			char c = s.charAt(right);
			if (charCount.containsKey(c))
			{
				charCount.put(c, charCount.get(c) + 1);
			}
			else
			{
				charCount.put(c, 1);
			}
			//we get 2 distinct chars
			if (charCount.size() == 2)
			{
				int totalLength = right - left + 1; //could also count the value of each 2 entries in the map
				maxLength = Math.max(maxLength, totalLength);
			}
			// If size of countMap is more than 2 means substring s[left, right]
			// have more than 2 distinct characters so, we remove characters from left
			// while countMap size is more than 2
			while (charCount.size() > 2)
			{
				char charAtLeft = s.charAt(left);
				charCount.put(charAtLeft, charCount.get(charAtLeft)-1);
				if (charCount.get(charAtLeft) == 0)
				{
					charCount.remove(charAtLeft);//remove this char from map because we want to maintain 2 entries
				}
				left++;
			}
			right++;
		}
		return maxLength;
	}

//	static boolean isSameChars(int left, int right, String s)
//	{
//		char[] chars = s.substring(left, right+1).toCharArray();
//		Set<Character> characterSet = new HashSet<>();
//		for (char c : chars)
//		{
//			if (!characterSet.add(c) && characterSet.size() > 2)
//				return false;
//		}
//		return (characterSet.size()==1);
//	}
//
//	static boolean isTwoDistinctChars(int left, int right, String s)
//	{
//		char[] chars = s.substring(left, right+1).toCharArray();
//		Set<Character> characterSet = new HashSet<>();
//		for (char c : chars)
//		{
//			if (!characterSet.add(c) && characterSet.size() > 2)
//				 return false;
//		}
//		return (characterSet.size()==2);
//	}

	public static void main(String ... args) {
		String s = "ecebaaaaca";
		Integer length = get_longest_substring_length_with_exactly_two_distinct_chars(s);
		System.out.println(length);//6

		s = "aaaaaaaaaaaaaa00000000ba";
		length = get_longest_substring_length_with_exactly_two_distinct_chars(s);
		System.out.println(length);//22
	}
}
