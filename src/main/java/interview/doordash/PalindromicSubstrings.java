package interview.doordash;

/**
 * 647. Palindromic Substrings
 * Given a string s, return the number of palindromic substrings in it.
 * A string is a palindrome when it reads the same backward as forward.
 * A substring is a contiguous sequence of characters within the string.
 *
 * Example 1:
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
public class PalindromicSubstrings
{
	//Approach 1: Check All Substrings
	/**
	 * Each substring is denoted by a pair of variables pointing to the start and end indices of the sub-string.
	 * 	A single character substring is denoted by start and end indices being equal in value.
	 *
	 * 	Time Complexity: O(N^3)O(N
	 * 3
	 *  ) for input string of length NN.
	 * Since we just need to traverse every substring once, the total time taken is sum of the length of all substrings.
	 *
	 * In a string of length NN, then there are:
	 *
	 * NN substrings of size 11.
	 * N-1N−1 substrings of size 22.
	 * N-2N−2 substrings of size 33.
	 * ...
	 * 11 substring of size NN (which is the entire string).
	 *
	 * Space Complexity: O(1)O(1).
	 */
	private static boolean isPalindrome(String s, int start, int end) {
		while (start < end) {
			if (s.charAt(start) != s.charAt(end))
				return false;

			++start;
			--end;
		}

		return true;
	}
	//main function
	public static int countSubstrings(String s) {
		int ans = 0;

		for (int start = 0; start < s.length(); ++start)
			for (int end = start; end < s.length(); ++end)
				ans += isPalindrome(s, start, end) ? 1 : 0;

		return ans;
	}

	/**
	 * Approach #2: Dynamic Programming
	 *
	 * While checking all substrings of a large string for palindromicity,
	 * we might need to check some smaller substrings for the same, repeatedly.
	 * If we store the result of processing those smaller substrings, we can reuse those while processing larger substrings.
	 *
	 * 	Here's an example: for the string "axbobxa", the substring "bob" needs to checked for the substring "xbobx" and the string "axbobxa".
	 * 	In fact, to check all three of these strings, the single character string "o" needs to be checked.
	 *
	 * 	Base Case:
	 * 	Single letter substrings are palindromes by definition. i.e. dp(i, i) = trued
	 *  Double letter substrings composed of the same character are palindromes.
	 *  i.e. dp(i,i+1) :
	 *  true  if s[i] = s[i+1]
	 *  false otherwise
	 *
	 *  Optimal substructure. A string is considered a palindrome if:
	 *  Its first and last characters are equal, and
	 *  The rest of the string (excluding the boundary characters) is also a palindrome.
	 *
	 *  dp[i,j] = true if dp[i+1, j-1] ^ s[i] = s[j]
	 *  false otherwise
	 *
	 *  If we compute (and save) the states for all smaller strings first, larger strings can be processed by reusing previously saved states.
	 */
	public int countSubstringsDP(String s) {
		int n = s.length(), ans = 0;

		if (n <= 0)
			return 0;

		boolean[][] dp = new boolean[n][n];

		// Base case: single letter substrings
		for (int i = 0; i < n; ++i, ++ans)
			dp[i][i] = true;

		// Base case: double letter substrings
		for (int i = 0; i < n - 1; ++i) {
			dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
			ans += (dp[i][i + 1] ? 1 : 0);
		}

		// All other cases: substrings of length 3 to n
		for (int len = 3; len <= n; ++len)
			for (int i = 0, j = i + len - 1; j < n; ++i, ++j) {
				dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
				ans += (dp[i][j] ? 1 : 0);
			}

		return ans;
	}

	public static void main(String[] args) {
		String s = "aaa";
		int c = countSubstrings(s);
		System.out.println(c); //6

		s = "abxba";
		c = countSubstrings(s);
		System.out.println(c); //7 : [a, b, x, b ,a, abxba, bxb]
	}

}
