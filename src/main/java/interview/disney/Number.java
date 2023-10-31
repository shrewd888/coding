package interview.disney;

/*
 * Complete the 'isNumber' function below.
 *
 * The function is expected to return a BOOLEAN.
 * The function accepts STRING s as parameter.
 */
//My 2nd round Disney interview 11/10/22 with Roman Stolper (Lead Software Engineer)
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Number
{
	public static boolean isNumber(String s)
	{
		Set<Character> charSet = new HashSet<>(Arrays.asList('.', ','));
		char[] chars = s.toCharArray();
		if (chars.length==0) return false;
		if (chars.length == 1 && !Character.isDigit(s.charAt(0))) return false;

		Map<Character, Integer> nonDigit = new HashMap<>();

		//123.90 .234 0.34
		//  6789000
		// Character.isDigit(char c)
		for (int i=0; i<chars.length; i++)
		{
			char c = s.charAt(i);

			if (!Character.isDigit(c))
			{
				if (i== 0 && c == '-') continue;//negative

				else if (i < chars.length - 1 && i > 0 && c == '.')
				{
					if (nonDigit.containsKey('.'))
					{
						return false;
					}
					else
					{
						nonDigit.put('.', 1);
						continue;
					}
				}
				else return false;
			}
		}
		return true;
	}
}