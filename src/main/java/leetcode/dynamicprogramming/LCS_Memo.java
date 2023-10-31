package leetcode.dynamicprogramming;

public class LCS_Memo
{
	public static int longestCommonSubsequence(String text1, String text2) {
		int[][] memo = new int[text1.length()][text2.length()];
		// We need to initialise the memo array to -1's so that we know
		// whether or not a value has been filled in. Keep the base cases
		// as 0's to simplify the later code a bit.
		for (int i = 0; i < text1.length(); i++) {
			for (int j = 0; j < text2.length(); j++) {
				memo[i][j] = -1;
			}
		}
		return helper(memo, 0, 0, text1, text2);
	}

	//p1 = index1, p2 = index2
	public static int helper(int[][] memo, int p1, int p2, String text1, String text2)
	{
		// Check whether or not we've already solved this subproblem.
		// This also covers the base cases where p1 == text1.length
		// or p2 == text2.length.
		if (p1 >= text1.length() || p2 >= text2.length())
		{
			return 0;
		}
//		if (memo[p1][p2] != -1) {
//			return memo[p1][p2];
//		}

		//Recursive
		char c1 = text1.charAt(p1);
		char c2 = text2.charAt(p2);
		int result = 0;
		if (c1 == c2)
		{
			result = 1 + helper(memo, p1+1, p2+1, text1, text2);
		}
		else
		{
			int x = helper(memo, p1+1, p2, text1, text2);
			int y = helper(memo, p1, p2+1, text1, text2);
			result = Math.max(x, y);
		}
		// Add the best answer to the memo before returning it.
		memo[p1][p2] = result;
		return memo[p1][p2];
	}


	public static void main(String ... args) {

		String text1 = "abcde", text2 = "ace";
		int l = longestCommonSubsequence(text1, text2);
		System.out.println(l);

//		String text11 = "pmjghexybyrgzczy", text21 = "hafcdqbgncrcbihkd";
//		int l1 = longestCommonSubsequence(text11, text21);
//		System.out.println(l1);
	}
}
