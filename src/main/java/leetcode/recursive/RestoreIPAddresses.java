package leetcode.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/restore-ip-addresses/solutions/2868540/restore-ip-addresses/
 *
 * 93. Restore IP Addresses
 * A valid IP address consists of exactly four integers separated by single dots.
 * Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 *
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses,
 * but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * Given a string s containing only digits, return all possible valid IP addresses
 * that can be formed by inserting dots into s.
 *
 * You are not allowed to reorder or remove any digits in s.
 * You may return the valid IP addresses in any order.
 *
 * Example 1:
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 *
 * Example 2:
 * Input: s = "0000"
 * Output: ["0.0.0.0"]c
 *
 * Example 3:
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 */
//COPY answer from leetcode
/**
 * Time complexity: O(M^N . N), N = number of integers, M = #of digits for each integer
 * S(n) = O (M.N)
 */
public class RestoreIPAddresses
{
	public static List<String> restoreIpAddresses(String s) {
		ArrayList<String> result = new ArrayList<>();

		if (s.length()==0) return result;
		helper(s, 0, new ArrayList<>(), result);

		return result;
	}

	public static void helper(String s, int index, List<Integer> dots, List<String> result)
	{
		int remainingLength = s.length() - index;
		int remainingNumberOfIntegers = 4 - dots.size();
		if (remainingLength > remainingNumberOfIntegers * 3 ||
				remainingLength < remainingNumberOfIntegers) {
			return;
		}
		//Backtracking
		if (dots.size() == 3) {
			if (valid(s, index, remainingLength)) {
				StringBuilder sb = new StringBuilder();
				int last = 0;
				for (Integer dot : dots) {
					sb.append(s.substring(last, last + dot));
					last += dot;
					sb.append('.');
				}
				sb.append(s.substring(index));
				result.add(sb.toString());
			}
			return;
		}

		for (int curPos = 1; curPos <= 3 && curPos <= remainingLength; ++curPos) {
			// Append a dot at the current position.
			dots.add(curPos);
			// Try making all combinations with the remaining string.
			if (valid(s, index, curPos)) {
				helper(s, index + curPos, dots, result);
			}
			// Backtrack, i.e. remove the dot to try placing it at the next position.
			dots.remove(dots.size() - 1);
		}
	}


	private static boolean valid(String s, int start, int length) {
		return length == 1 ||
				(s.charAt(start) != '0' &&
						(length < 3 ||
								s.substring(start, start + length).compareTo("255") <= 0));
	}

	public static void main(String ... args) {
		String s = "25525511135";
		System.out.println(restoreIpAddresses(s));
	}

}
