package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//doesn't work
public class LCS
{
	private static List<List<String>> solve(String[] strs1, String[] strs2) {
		int[][] dp = new int[strs1.length + 1][strs2.length + 1];
		int max = 0;
		for(int i=1;i<=strs1.length;i++) {
			for(int j=1;j<=strs2.length;j++) {
				if(strs1[i-1].equals(strs2[j-1])) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
				max = Math.max(max, dp[i][j]);
			}
		}
		List<List<String>> res = new ArrayList<>();
		for(int i=dp.length-1;i>=0;i--) {
			for(int j=dp[0].length-1;j>=0;j--) {
				if(dp[i][j] == max) {
					List<String> lst = new ArrayList<>();
					while(dp[i][j] > 0 && i >= 0 && j >= 0) {
						lst.add(0, strs1[i-1]);
						dp[i][j] = 0;
						i--; j--;
					}
					res.add(lst);
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String[] d1 = new String[]{"chilis", "albertsons", "walmart", "albertsons", "chilis", "mcdonalds", "burger king"};
		String[] d2 = new String[]{"chilis", "walmart", "chilis", "albertsons", "burger king", "applebees", "mcdonalds"};
		List<List<String>> result = solve(d1, d2);

		System.out.println(result);
	}
}
