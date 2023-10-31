package interviewkickstart.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * You are given a dictionary called words and two string arguments called start and stop. All given strings have equal length.
 *
 * Transform string start to string stop one character per step using words from the dictionary.
 * For example, "abc" → "abd" is a valid transformation step because only one character is changed ("c" → "d"),
 * but "abc" → "axy" is not a valid one because two characters are changed ("b" → "x" and "c" → "y").
 *
 * You need to find the shortest possible sequence of strings (two or more) such that:
 *
 * First string is start.
 * Last string is stop.
 * Every string (except the first one) differs from the previous one by exactly one character.
 * Every string (except, possibly, first and last ones) are in the dictionary of words.
 * Example One
 * {
 * "words": ["cat", "hat", "bad", "had"],
 * "start": "bat",
 * "stop": "had"
 * }
 * Output:
 * ["bat", "bad", "had"]
 * OR
 * ["bat", "hat", "had"]
 *
 * In "bat", change "t" → "d" to get "bad".
 * In "bad", change "b" → "h"to get "had".
 *
 * OR
 *
 * In "bat", change "b" → "h" to get "hat".
 * In "hat", change "t" → "d" to get "had".'
 *
 */
public class ShortestStringTransformationUsingADictionary
{
	static ArrayList<String> string_transformation(ArrayList<String> words, String start, String stop) {
		return bfs(words, start, stop);
	}

	static ArrayList<String> bfs(ArrayList<String> words, String start, String stop) {
		ArrayList<String> result = new ArrayList<>();
		if (!words.contains(stop))
		{
			words.add(stop);
		}
		Set<String> visited = new HashSet<>();
		//parent of "key" is equal to "value"
		Map<String, String> parentMap = new HashMap<>();

		Queue<String> q = new LinkedList<>();
		q.add(start);
		// since sometimes start and stop can be equal and we might need to visit `start` twice,
		// we can't add start in visited otherwise we won't be able to visit it twice
		// example: "abc" -> "abd" -> "abc"
		// visited.add(start);

		while (!q.isEmpty())
		{
			String inQueue = q.poll();
			// since start and stop can be equal sometimes, the below condition will break the loop immediately
			// in those cases as the first element in queue is start which is same as stop
			// if (inQueue.equals(stop)) break;

			// this condition will break the loop when we have visited `stop`, this will cover the case when start
			// and stop are same, as in the beginning `start` is not in the visited and due to which the loop will
			// continue and will break only after we visit the `start` for second time
			if (visited.contains(stop)) break;

			//check all strings in dictionary that are one character away from this string
			for (String word : words)
			{
				if (!visited.contains(word) && isOnlyOneCharacterDifference(inQueue, word))
				{
					visited.add(word);
					q.add(word);
					parentMap.put(word, inQueue);
				}
			}

		}
		//find the parent of "stop"
		String parentOfStop = parentMap.get(stop);
		// there can be valid transformation even when start is equal to stop
		// example: "abc" -> "abd" -> "abc"
		if (parentOfStop == null /*|| start.equals(stop)*/)
		{
			result.add("-1");
			return result;
		}
		result.add(stop);

		// iterating through the list of word and only updating `stop` to `parentOfStop` when we find
		// `stop` in the list of words in not correct
		// for example, start = "abc", stop = "ade", words = ["ade", "abe", "abc"]
		// your code will miss some of the transformations
		// let's dry run
		// result = ["ade"]
		// stop = "ade", lastIndex = 2, words.get(lastIndex).equals(stop) is false so we don't do anything
		// stop = "ade", lastIndex = 1, words.get(lastIndex).equals(stop) is false so we don't do anything
		// stop = "ade", lastIndex = 0, loop breaks
		// now we add `start` in result, so result = ["ade", "abc"]
		// after reversing, result = ["abc", "ade"]
		// so we missed the transformation of "abc" -> "abe" and "abe" -> "ade"

		// the fix is to just go to the intermediate parent and stop when we reach stop
		// iterating over the list from back to front is unncessary as well as wrong
		// we don't need to verify that the string is in the words list


		//loop through list of words from the back
		int lastIndex = words.size()-1;
		while (true /*lastIndex > 0*/)
		{
//			String word = words.get(lastIndex);
//			if (word.equals(stop))
//			{
				String parent = parentMap.get(stop);
				if(parent.equals(start)) break;

				result.add(parent);
				stop = parent;
//			}
//			lastIndex--;
		}
		result.add(start);
		Collections.reverse(result);
		return result;
	}

	static boolean isOnlyOneCharacterDifference(String origin, String inDict)
	{
		if (origin.length() != inDict.length()) return false;
		int length = origin.length();
		int difference = 0;
		for (int i=0; i < length; i++)
		{
			if (origin.charAt(i) != inDict.charAt(i))
			{
				if (difference == 1) return false;
				difference++;
			}
		}
		// If difference == 0, it means strings are same. So, difference == 1 is needed.
		return difference == 1;
	}

	public static void main(String ... args) {
		ArrayList<String> words = new ArrayList<>(Arrays.asList("aaa"));
		String start = "baa";
		String stop = "aab";
		ArrayList<String> result = string_transformation(words, start, stop);
		System.out.println(result);//[baa, aaa, aab]

		ArrayList<String> words1 = new ArrayList<>(Arrays.asList("cat", "hat", "bad", "had"));
		String start1 = "bat";
		String stop1 = "had";
		ArrayList<String> result1 = string_transformation(words1, start1, stop1);
		System.out.println(result1);//[bat, hat, had]

		words = new ArrayList<>();
		start = "bbb";
		stop = "bbc";
		result = string_transformation(words, start, stop);
		System.out.println(result);//[bbb, bbc]
	}
}
