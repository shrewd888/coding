package interview.asana;

import java.util.Arrays;
import java.util.List;

/**
 * My interview 1st round on 9/9/22
 *
 * https://www.geeksforgeeks.org/nearly-sorted-algorithm/
 *
 * System Design
 * What happen behind the scene when Client 1 is making changes on the Asana tool, and Client 2 want to see the changes right away?
 */
public class AlmostSortedArray
{

	public static void main(String ... args)
	{
		String book = "andy want";
		List<String> arr = Arrays.asList(book.split(" "));
		System.out.println(arr);

	}
}
