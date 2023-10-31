package crackingcodinginterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PracticeAscii
{

	private static boolean isNegative(Double amount)
	{
		if (amount != null)
		{
			return Double.compare(amount, 0.0) < 0;
		}
		return false;
	}


	public static void main(String ... args) {

		int digit = '1' - '0';
		int digit1 = '<' - '0';

		boolean b = Character.isDigit('>');
		System.out.println(digit);
		System.out.println(digit1);

		List<Integer> l1 = Arrays.asList(1,2,3);
		List<Integer> l2 = new ArrayList<>(Arrays.asList(1,2,3));
		List<Integer> l3 = new ArrayList<>(l1);
		System.out.println(l1.equals(l2));
		System.out.println(l1.equals(l3));
		System.out.println(l2.equals(l3));
		System.out.println(l1==l2);
		System.out.println(l1==l3);
		System.out.println(l2==l3);
		l1 = l2;
		System.out.println();
		System.out.println(l1==l2);
		System.out.println(l1.equals(l2));

		double d = 12;
		System.out.println(d);

		boolean isNegative = isNegative(d);
		System.out.println(isNegative);
	}
}
