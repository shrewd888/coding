package leetcode.dynamicprogramming;

/**
 * Time Complexity
 * Space Complexity: O (l1 x l2) -> size of the table
 */
public class LCS_DP
{

	public static int longestCommonSubsequence(String text1, String text2) {
		int l1 = text1.length();
		int l2 = text2.length();
		//table is initialized with 0
		int[][] table = new int[l1+1][l2+1];
		int result = helper(table, text1, text2);
		return result;
	}

	public static int helper(int[][] table, String text1, String text2)
	{
		//Base Case: fill-in the bottom edge and the right edge with 0s
		//already filled from initialization? yes

		for (int row = text1.length()-1; row >= 0; row--)
		{
			for (int col = text2.length()-1; col >= 0; col--)
			{
				if (text1.charAt(row) == text2.charAt(col))
				{
					table[row][col] = 1 + table[row + 1][col + 1];
				}
				else
				{
					table[row][col] = Math.max(table[row+1][col], table[row][col+1]);
				}
			}
		}
		return table[0][0];
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
