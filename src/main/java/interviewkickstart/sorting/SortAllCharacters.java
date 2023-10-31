package interviewkickstart.sorting;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-210578-518390-5-90
 * Sort All Characters
 * Given a list of characters, sort it in the non-decreasing order based on ASCII values of characters.
 *
 * Example
 * {
 * "arr": ["a", "s", "d", "f", "g", "*", "&", "!", "z", "y"]
 * }
 * Output:
 * ["!", "&", "*", "a", "d", "f", "g", "s", "y", "z"]
 */

/**
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class SortAllCharacters
{

	static ArrayList<Character> sort_array(ArrayList<Character> arr) {
		ArrayList<Character> result = new ArrayList<>();
		int[] intArrays = new int[256];//256 or 128 ??
		if (arr.size() == 0) return new ArrayList<>();

		for (Character c : arr)
		{
			intArrays[c] = intArrays[c] + 1;
		}

		for (int i=0; i < 256; i++)
		{
			int freq = intArrays[i];
			for (int j=0; j<freq; j++)
			{
				result.add((char)i);
			}
		}
		return result;
	}

	//0.27 sec
	public static void main(String ... args) {
		ArrayList<Character> arr = new ArrayList<>();
		arr.add('a');
		arr.add('s');
		arr.add('d');
		arr.add('f');
		arr.add('g');
		arr.add('*');
		arr.add('&');
		arr.add('!');
		arr.add('z');
		arr.add('y');

		ArrayList<Character> result = sort_array(arr);
		System.out.println(result);//[!, &, *, a, d, f, g, s, y, z]
	}
}
