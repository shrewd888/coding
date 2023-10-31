package interviewkickstart;

import java.util.ArrayList;
import java.util.List;

public class Subset
{
	public static List<List<Integer>> subset(List<Integer> input)
	{
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> partial = new ArrayList<>();
		helper(result, partial, input,0);
		return result;
	}

	public static void helper(List<List<Integer>> result, List<Integer> partial,
			List<Integer> input, int index)
	{
		if (input.size()==0 || index == input.size())
		{
			List<Integer> newPartial = new ArrayList<>();
			for (int i=0; i<partial.size(); i++)
			{
				newPartial.add(partial.get(i));
			}
			result.add(newPartial);
			return;
		}
		//exclude
		helper(result, partial, input, index+1);

		//include
		partial.add(input.get(index));
		helper(result, partial, input, index+1);
		partial.remove(partial.size()-1);
	}

	public static void main(String ... args) {
		List<Integer> set = new ArrayList<>();
		set.add(1);
		set.add(2); //{}, {1}, {2}, {1,2}
		List<List<Integer>> result = subset(set);

		System.out.println(result);
	}
}
