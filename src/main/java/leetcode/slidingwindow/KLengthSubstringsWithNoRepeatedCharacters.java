package leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/
 * 1100. Find K-Length Substrings With No Repeated Characters
 *
 * Given a string s and an integer k, return the number of substrings in s of length k with no repeated characters.
 * Example 1:
 * Input: s = "havefunonleetcode", k = 5
 * Output: 6
 * Explanation: There are 6 substrings they are: 'havef','avefu','vefun','efuno','etcod','tcode'.
 *
 * Example 2:
 * Input: s = "home", k = 5
 * Output: 0
 * Explanation: Notice k can be larger than the length of s. In this case, it is not possible to find any substring.
 */
/**
 * https://uplevel.interviewkickstart.com/resource/rc-video-210507-518318-247-1562-3843887
 * Omkar's Solution
 * T(n) = O(n)
 * S(n) = O(1) or O(n) HashMap can grow to the size of unique n, depends on how many unique n
 */
public class KLengthSubstringsWithNoRepeatedCharacters
{
	public static int numKLenSubstrNoRepeats(String s, int k) {
		if (k > s.length()) return  0;
		Map<Character, Integer> charCountMap = new HashMap<>();

		int globalCount = 0;

		//initialize the first window k
		for (int i=0; i < k; i++)
		{
			char c = s.charAt(i);
			charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
		}
		//#of keyset is enough otherwise if less than #of keyset the total will be < k
		if (charCountMap.keySet().size() == k) globalCount++;

		for (int i=k; i < s.length(); i++)
		{
			char c = s.charAt(i);
			charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
			//remove character that is not part of my window
			char prev = s.charAt(i-k);
			charCountMap.put(prev, charCountMap.get(prev) - 1);//this prev char must exist in the hashtable

			if (charCountMap.get(prev) == 0)
			{
				charCountMap.remove(prev); //remove prev key that it outside of the window if only 1 such character exist
			}
			if (charCountMap.keySet().size() == k) globalCount++;
		}
		return globalCount;
	}

	public static void main(String ... args)
	{
		String s = "havefunonleetcode";
		int k = 5;
		System.out.println(numKLenSubstrNoRepeats(s, k)); //6

	}
}
