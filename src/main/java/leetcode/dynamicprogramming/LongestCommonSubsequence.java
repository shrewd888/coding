package leetcode.dynamicprogramming;

/**
 * 1143. Longest Common Subsequence
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 * A subsequence of a string is a new string generated from the original string with some characters
 * (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 */
public class LongestCommonSubsequence
{

	public static int longestCommonSubsequence(String text1, String text2) {
		if (text1 == null || text2 == null) return 0;
		StringBuffer sb = new StringBuffer();
		int l = helper(text1, text2, sb);
		System.out.println(sb);
		return l;

	}

	public static int helper(String text1, String text2, StringBuffer sb)
	{
		if (text1.length()==0 || text2.length()==0)
		{
			return 0;
		}
		//Recursive
		char c1 = text1.charAt(0);
		char c2 = text2.charAt(0);
		if (c1 == c2)
		{
			//sb.append(c1);
			return 1 + helper( text1.substring(1), text2.substring(1), sb);
		}
		else
		{
			int x = helper(text1.substring(1), text2, sb);
			int y = helper(text1, text2.substring(1), sb);

			//return Math.max(helper(text1.substring(1), text2), helper(text1, text2.substring(1)));
			return Math.max(x, y);
		}
	}


	public static void main(String ... args) {

		String text1 = "abcde", text2 = "ace";
		int l = longestCommonSubsequence(text1, text2);
		System.out.println(l);

		String text11 = "pmjghexybyrgzczy", text21 = "hafcdqbgncrcbihkd";
		int l1 = longestCommonSubsequence(text11, text21);
		System.out.println(l1);
	}

}
