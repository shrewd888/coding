package leetcode.counting;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 13. Roman to Integer
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 *
 * Example 1:
 *
 * Input: s = "III"
 * Output: 3
 * Explanation: III = 3.
 * Example 2:
 *
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * Example 3:
 *
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 * Time Complexity = O(length of the roman numeral string)
 */
//TIME EXCEEDS LIMIT!
public class RomanToInteger
{
	private static final Map<Character, Integer> romanInteger;
	static {
		Map<Character, Integer> aMap = new HashMap<>();
		aMap.put('I', 1);
		aMap.put('V', 5);
		aMap.put('X', 10);
		aMap.put('L', 50);
		aMap.put('C', 100);
		aMap.put('D', 500);
		aMap.put('M', 1000);
		romanInteger = Collections.unmodifiableMap(aMap);
	}

	public int romanToInt(String s) {
		int total = 0;
		if (s == null || s.isEmpty())
			return total;

		char[] chars = s.toCharArray();
		int n = chars.length;
		int index = 0;

		while (index < n)
		{
			char c = chars[index];
			int val = romanInteger.get(c);
			if (index + 1 < n && romanInteger.get(chars[index+1]) > val)
				total = total - val;
			else
				total += val;
		}
		return total;
	}
}
