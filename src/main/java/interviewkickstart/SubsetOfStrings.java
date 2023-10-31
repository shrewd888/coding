package interviewkickstart;

import java.util.ArrayList;
import java.util.List;

public class SubsetOfStrings
{
	public static ArrayList<String> generate_all_subsets(String s)
	{
		char[] chars = s.toCharArray();

		ArrayList<String> result = new ArrayList<>();
		helper(result, chars, "", 0);
		return result;
	}

	public static void helper(ArrayList<String> result, char[] input,
			 String partial, int index)
	{
		if (input.length==0 || index == input.length)
		{
			result.add(partial);
			return;
		}
		//exclude
		helper(result, input, partial,index+1);

		//include
		StringBuilder sb = new StringBuilder();
		sb.append(partial);
		sb.append(input[index]);
		partial = sb.toString();
		helper(result, input, partial, index+1);
		//remove
		partial = partial.substring(0, partial.length()-1);
	}

	public static void main(String ... args) {
		String s = "xyz";
		ArrayList<String> result = generate_all_subsets(s);
		System.out.println(result);
	}
}
