package leetcode.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/word-break/
 * 139. Word Break
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented
 * into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 */
public class WordBreak
{
	public static boolean wordBreak(String s, List<String> wordDict) {
		if (s == null || s.length() == 0) return false;
		int n = s.length();
		//a 1D boolean array of size (n+1) initialize to false
		boolean[] table = new boolean[n+1];
		table[0] = true;

		//leetcode
		//i = 1 -> l -> l
		//i = 2 -> le -> e, le
		//i = 3 -> lee -> e, ee, lee
		//i = 4 -> leet -> t, et, eet, leet
		for (int i=1; i <= n; i++)
		{
			for (int lastwordlen=1; lastwordlen <= i; lastwordlen++)
			{
				String chopped = s.substring((i-lastwordlen), i);
				if (wordDict.contains(chopped) && table[i - lastwordlen])
				{
					table[i] = true;
				}
			}
		}
		return table[n];
	}

	public static void main(String ... args) {

		String s = "leetcode";
		List<String> wordDict = new ArrayList<>();
		wordDict.add("leet");
		wordDict.add("code");

		boolean b = wordBreak(s, wordDict);
		System.out.println(b);

		String s1 = "applepenapple";
		List<String> wordDict1 = new ArrayList<>();
		wordDict1.add("apple");
		wordDict1.add("pen");

		boolean b1 = wordBreak(s1, wordDict1);
		System.out.println(b1);
	}
}
