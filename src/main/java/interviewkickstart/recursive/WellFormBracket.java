package interviewkickstart.recursive;

import java.util.ArrayList;

/**
 * IK Recursion Timed Test
 * Given a positive integer n, return ALL strings of length 2 * n with well-formed round brackets.
 *
 * {
 * "n": 3
 * }
 * Output:
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */

/**
 * k = 2n (n=3, k=6)
 * Time Complexity = O (k * 2^k)
 * Space Complexity = recursion: O(k), auxiliary space: (k * 2^k) length of string * each solution
 * Number of solution = 2^k
 */
public class WellFormBracket
{
	static ArrayList<String> find_all_well_formed_brackets(Integer n) {
		ArrayList<String> result = new ArrayList<>();

		if (n==0) return result;
		helper(n, n, new ArrayList<>(), result);

		return result;
	}

	static void helper(int lb, int rb, ArrayList<Character> slate, ArrayList<String> result)
	{
		//Base Case
		if (lb == 0 && rb == 0)
		{
			StringBuilder sb = new StringBuilder();
			ArrayList<Character> slateCopy = new ArrayList<>(slate);
			for (Character c : slateCopy)
			{
				sb.append(c);
			}
			result.add(sb.toString());
			return;
		}
		if (lb == rb)
		{
			slate.add('(');
			helper(lb-1, rb, slate, result);
			slate.remove(slate.size()-1);
		}
		else
		{
			if (lb > 0)
			{
				slate.add('(');
				helper(lb-1, rb, slate, result);
				slate.remove(slate.size()-1);
			}
			//include right
			slate.add(')');
			helper(lb, rb-1, slate, result);
			slate.remove(slate.size()-1);
		}
	}

	public static void main(String ... args) {
		ArrayList<String> result = find_all_well_formed_brackets(3);
		System.out.println(result); // [((())), (()()), (())(), ()(()), ()()()]

		result = find_all_well_formed_brackets(2);
		System.out.println(result); // [(()), ()()]
	}

}
