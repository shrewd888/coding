package leetcode.counting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 273. Integer to English Words
 *
 * Convert a non-negative integer num to its English words representation.
 *
 * Example 1:
 *
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 *
 * Input: num = 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 *
 * Input: num = 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 */
//DOES NOT PASS ALL TEST CASES!
public class IntegerToEnglishWords
{
	public static final Map<Integer, String> numberToEnglishWord = new HashMap<>();
	static
	{
		numberToEnglishWord.put(1, "One");
		numberToEnglishWord.put(2, "Two");
		numberToEnglishWord.put(3, "Three");
		numberToEnglishWord.put(4, "Four");
		numberToEnglishWord.put(5, "Five");
		numberToEnglishWord.put(6, "Six");
		numberToEnglishWord.put(7, "Seven");
		numberToEnglishWord.put(8, "Eight");
		numberToEnglishWord.put(9, "Nine");
		numberToEnglishWord.put(10, "Ten");
		numberToEnglishWord.put(20, "Twenty");
		numberToEnglishWord.put(30, "Thirty");
		numberToEnglishWord.put(40, "Forty");
		numberToEnglishWord.put(50, "Fifty");
		numberToEnglishWord.put(60, "Sixty");
		numberToEnglishWord.put(70, "Seventy");
		numberToEnglishWord.put(80, "Eighty");
		numberToEnglishWord.put(90, "Ninety");
		numberToEnglishWord.put(11, "Eleven");
		numberToEnglishWord.put(12, "Twelve");
		numberToEnglishWord.put(13, "Thirteen");
		numberToEnglishWord.put(14, "Fourteen");
		numberToEnglishWord.put(15, "Fifteen");
		numberToEnglishWord.put(16, "Sixteen");
		numberToEnglishWord.put(17, "Five");
		numberToEnglishWord.put(18, "Eighteen");
		numberToEnglishWord.put(19, "Nineteen");
	}


	public static String threeDigitsToName(int num)
	{
		if (num == 0) return "";
		StringBuilder sb = new StringBuilder();

		int hundreds = num/100;
		int tens = (num % 100)/10;
		int ones = num % 10;

		if (hundreds > 0)
		{
			sb.append(numberToEnglishWord.get(hundreds)).append(" Hundred").append(" ");
		}
		if (tens == 1)
		{
			sb.append(numberToEnglishWord.get(num % 10));
			return sb.toString();
		}
		if (tens > 0)
		{
			sb.append(numberToEnglishWord.get(tens * 10)).append(" ");
		}
		if (ones > 0)
		{
			sb.append(numberToEnglishWord.get(ones));
		}
		return sb.toString();
	}

	public static final List<String> suffix = Arrays.asList("Billion", "Million", "Thousand");

	public static String numberToWords(int num) {
		if (num == 0) return "Zero";

		int denominator = 1000000000;
		StringBuilder sb = new StringBuilder();

		for (int i=0; i < 3; i++)
		{
			//Extract the relevant 3 digit number, call the function to get its name, and suffic it
			//with the string from the suffix array

			int quotient = num/denominator;
			num = num % denominator;
			if (quotient > 0)
			{
				sb.append(threeDigitsToName(quotient)).append(" ");
				sb.append(suffix.get(i)).append(" ");
			}
			denominator = denominator/1000;
		}

		sb.append(threeDigitsToName(num));
		return sb.toString();
	}

	public static void main(String ... args) {
		String result = numberToWords(3999);
		System.out.println(result); //Three Thousand Nine Hundred Ninety Nine

		result = numberToWords(123);
		System.out.println(result); //One Hundred Twenty Three

		result = numberToWords(12345); //CHECK!!!
		System.out.println(result); //" Two ThousandThree Hundred Forty Five"
	}
}
