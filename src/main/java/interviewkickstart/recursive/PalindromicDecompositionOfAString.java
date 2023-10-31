package interviewkickstart.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * {
 * "s": "abracadabra"
 * }
 * Output:
 * ["a|b|r|a|c|ada|b|r|a", "a|b|r|aca|d|a|b|r|a", "a|b|r|a|c|a|d|a|b|r|a"]
 */
public class PalindromicDecompositionOfAString
{
	public static final String BAR = "|";

	static List<String> generate_palindromic_decompositions(String s) {
		List<String> result = new ArrayList<>();
		if (s==null || s.length()==0) return result;
		List<String> slate = new ArrayList<>();
		helper(s, 0, slate, result);
		return result;
	}
	//slate: ["k", "a", "y", "a", "k]
	static void helper(String str, int index, List<String> slate, List<String> result)
	{
		if (index == str.length())
		{
			StringBuffer sb = new StringBuffer();
			for (String s : slate)
			{
				sb.append(s);
				sb.append(BAR);
			}
			sb.deleteCharAt(sb.length()-1); //delete extra "|"
			result.add(sb.toString());
			return;
		}
		//str.length = 5
		for (int i=index; i<str.length(); i++)
		{
			//index = 0, i=0 -> k
			//index = 1, i=1 -> a
			//index = 2, i=2 -> y
			//index = 3, i=3 -> a
			//index = 4, i=4 -> k

			//index = 3, i=4 -> ak (not palindrome)
			//index = 2, i=3 -> ya
			//index = 2, i=4 -> yak
			//index = 1, i=2 -> ay
			//index = 1, i=3 -> aya (palindrome)
			//index = 4, i=4 -> k   (palindrome)
			//index = 1, i=4 -> ayak
			//index = 0, i=1 -> ka
			//index = 0, i=2 -> kay
			//index = 0, i=3 -> kaya
			//index = 0, i=4 -> kayak

//			System.out.println("index:"+index+", i:"+i);
//			String snippet = str.substring(index, i+1);
//			System.out.println("Snippet : "+snippet);
			if (isPalindrome(str, index, i))
			{
				String palindromeSnippet = str.substring(index, i+1);//(0,1), (1,2), (2,3), ...
				//System.out.println("Snippet palindrome: "+palindromeSnippet);
				slate.add(palindromeSnippet);// ["k", "a", "y", "a", "k]

				helper(str, i+1, slate, result);
				slate.remove(slate.size()-1);
			}
		}
	}

	static boolean isPalindrome(String s, int start, int end)
	{
		while (start <= end) {
			if (s.charAt(start) != s.charAt(end))
				return false;

			++start;
			--end;
		}
		return true;
	}

	public static void main(String ... args) {
		List<String> result = generate_palindromic_decompositions("kayak");
		System.out.println(result);//[k|a|y|a|k, k|aya|k, kayak]

		List<String> result1 = generate_palindromic_decompositions("abracadabra");
		System.out.println(result1);//[a|b|r|a|c|a|d|a|b|r|a, a|b|r|a|c|ada|b|r|a, a|b|r|aca|d|a|b|r|a]
	}

}
