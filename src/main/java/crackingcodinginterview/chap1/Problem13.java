package crackingcodinginterview.chap1;

import java.util.Arrays;

public class Problem13
{

	public static String sort(String str)
	{
		if (str==null) return null;
		char[] charArr = str.toCharArray();
		Arrays.sort(charArr);

		return new String(charArr);
	}

	public static boolean isPermutation(String a, String b)
	{
		if (a == null || b == null)
		{
			return false;
		}
		if (a.length()!=b.length()) return false;
		return sort(a).equals(sort(b));
	}

	public static void main(String ... args) {
		String a = "gyhb";
		String b = "hybg";
		boolean isPermutation = isPermutation(a, b);
		System.out.println(a + " isPermutation of "+b+" :"+isPermutation);
	}
}
