package leetcode.recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 22. Generate Parentheses
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * Input: n = 1
 * Output: ["()"]
 */

/**
 * Time Complexity: O(nth Catalan number) or to make it simpler: O(2^(2n)) * O(2n) = O(n * 4^n)
 * Space Complexity:
 */
public class GenerateParenthesis
{
	public static final Character LEFT = '(';
	public static final Character RIGHT = ')';

	public static List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();
		if (n==0) return result;
		helper(n, n, new ArrayList<>(), result);
		return result;
	}

	//slates = partial solution
	//subproblems def: #of left & right parenthesis remaining
	public static void helper(int numleft, int numright, List<Character> slates, List<String> result)
	{
		//Backtracking -> Constraint
		if (numleft > numright) return;

		//Base Case
		if (numleft == 0 && numright == 0)
		{
			List<Character> copySlates = new ArrayList<>(slates);
			String parenthesis = copySlates.stream()
					.map(String::valueOf)
					.collect(Collectors.joining());
			result.add(parenthesis);
		}
		//Recursive Case: We can put ) before ( -> try! -> not working
		//Add ')'
		else if (numleft > 0) //do we need this? try
		{
			slates.add(LEFT);
			helper(numleft-1, numright, slates, result);
			slates.remove(slates.size()-1);
		}
		//Add '('
		else if (numright > 0) //do we need this? try
		{
			slates.add(RIGHT);
			helper(numleft, numright-1, slates, result);
			slates.remove(slates.size()-1);
		}
	}

	public static void main(String ... args) {
		List<String> result = generateParenthesis(3);
		System.out.println(result); //[((()))]
	}
}
