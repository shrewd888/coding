package interviewkickstart.recursive;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Generate ALL possible subsets of a given set.
 * The set is given in the form of a string s containing distinct lowercase characters 'a' - 'z'.
 * {
 * "s": "xy"
 * }
 * Output: ["", "x", "y", "xy"]
 */
public class GenerateAllSubsetsOfASet
{
	public static ArrayList<String> generate_all_subsets(String s) {
		ArrayList<String> result = new ArrayList<>();

		if (s == null) return result;
		if (s.length()==0)
		{
			result.add("");
			return result;
		}

		char[] chars = s.toCharArray();
		helper(chars, 0, new ArrayList<>(), result);
		return result;
	}

	public static void helper(char[] inputChars, int index, ArrayList<Character> chars, ArrayList<String> result)
	{
		//Base case
		if (index == inputChars.length)
		{
			StringBuilder sb = new StringBuilder();
			for (Character c : chars)
			{
				sb.append(c);
			}
			result.add(sb.toString());
			return;
		}
		//exclude
		helper(inputChars, index+1, chars, result);
		//include
		chars.add(inputChars[index]);
		helper(inputChars, index+1, chars, result);
		chars.remove(chars.size()-1);
	}

	public static void main(String ... args) {
		String input = "xy";
		ArrayList<String> result = generate_all_subsets(input);
		System.out.println(result);//[, y, x, xy]

		input = "";
		result = generate_all_subsets(input);
		System.out.println(result);//[]
	}
}
