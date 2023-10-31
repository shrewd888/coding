package interviewkickstart.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-210509-518320-67-399-3843787
 *
 * Indices Of Words In Text String
 *
 * Given some text and a bunch of words, find where each of the words appear in the text.
 * Function must return a list of lists of integers. One list for each one of the words. i-th list
 * must contain all indices of characters in text where i-th word starts, in the ascending order.
 * If i-th word isn’t found in the text at all, then i-th list must be [-1].
 *
 * Example
 * {
 * "text": "you are very very smart",
 * "words": ["you", "are", "very", "handsome"]
 * }
 * Output:
 *
 * [ [0], [4], [8, 13], [-1] ]
 *
 * "you" is found in the given text starting at the index 0.
 * "are" is found in the given text starting at the index 4.
 * "very" is found in the given text two times, starting at the indices 8 and 13.
 * "handsome" isn’t found in the given text.
 */
//TODO: Redo this! Wrong solution
public class IndicesOfWordsInTextString
{
	static ArrayList<ArrayList<Integer>> find_words(String text, ArrayList<String> words) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();

		Map<String, ArrayList<Integer>> textIndices = new HashMap<>();
		String[] characters = text.split(" ");
		for (int i=0; i < characters.length; i++)
		{
			String str = characters[i];
			if (words.get(i).equals(str))
			{
				if (i > 0)
				{
					int index = i-1;
					int len = 0;
					while (index >= 0)
					{
						len = len + words.get(index).length();
						index--;
					}
					if (textIndices.containsKey(str))
					{
						ArrayList<Integer> val = textIndices.get(str);
						val.add(len+i);
						textIndices.put(str, val);
					}
					else
					{
						ArrayList<Integer> val = new ArrayList<>();
						val.add(len + i);
						textIndices.put(str, val);
					}
				}
				else
				{
					ArrayList<Integer> val = new ArrayList<>();
					val.add(i);
					textIndices.put(str, val);
				}
			}
			else
			{
				ArrayList<Integer> val = new ArrayList<>();
				val.add(-1);
				textIndices.put(str, val);
			}
		}
		for (String str : characters)
		{
			result.add(textIndices.get(str));
		}
		return result;
	}

	public static void main(String ... args) {
		String text = "you are very very smart";
		ArrayList<String> words = new ArrayList<>();
		words.add("you");
		words.add("are");
		words.add("very");
		words.add("handsome");

		ArrayList<ArrayList<Integer>> result = find_words(text, words);
		System.out.println(result);
	}

}
