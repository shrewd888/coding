package interview.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 12/19/2022 - 3rd coding mock interview - Result: NOT HIRED
 * Feedback from Shubam (Mock Interviewer from Snapchat)
 * Need to increase the overall speed, need significant amount of practice!
 * Whenever I code, I need to speak out loud to explain to the interviewer
 * Good to initiate with T(n) & S(n)
 *
 * Solution approach is correct using HashMap
 */

/**
 * Let’s say you are given a list that consists of (parent, child) pairs and each individual is assigned a unique number. In this list, A parent can have multiple children and a child can have at most 2 parents. Your task is to write a function that takes in this list and return two collections;
 * One containing all individuals with zero parents and
 * One containing all individuals with exactly one known parent
 *
 * parentChildPairs = [
 *     (1, 3), (2, 3), (3, 6), (5, 6),
 *     (5, 7), (4, 5), (4, 8), (4, 9), (9, 11)
 * ]
 *
 * My solution:
 * Key: Child, Values: Parents
 *
 * Map<Integer, List<Integer>>
 * 3 = [1,2]
 * 5 = [4]
 * 6 = [3,5]
 * 7 = [5]
 * 8 = [4]
 * 9 = [4]
 * 11 = [9]
 * 1 = []
 * 2 = []
 * 4 = []
 *
 * for each entry set:
 * 3 = [1,2]
 * 1 => does map contains key?
 *
 *
 * Output may be in any order:
 * findNodesWithZeroAndOneParents(parentChildPairs) => [
 *   [1, 2, 4],       // Individuals with zero parents
 *   [5, 7, 8, 9, 11] // Individuals with exactly one parent
 * ]
 * n: number of pairs in the input
 */
public class ParentChild
{
	static Pair<List<Integer>, List<Integer>> findNodesWithZeroAndOneParents(List<Pair<Integer, Integer>> parentChildPairs)
	{
		List<Integer> zeroParents = new ArrayList<>();
		List<Integer> oneParents = new ArrayList<>();

		//child, parents
		Map<Integer, List<Integer>> childParents = new HashMap<>();

		for (Pair<Integer, Integer> pair : parentChildPairs)
		{
			Integer parent = pair.getKey();
			Integer child = pair.getValue();
			List<Integer> parents = new ArrayList<>();
			parents.add(parent);
			if (childParents.containsKey(child))
			{
				parents.addAll(childParents.get(child));
			}
			childParents.put(child, parents);
			//can also no need to check this 'if', because we don't want to add anything into it
			//if (!childParents.containsKey(parent))
			//{
				childParents.put(parent, childParents.getOrDefault(parent, new ArrayList<>()));
			//}
		}

		for (Map.Entry<Integer, List<Integer>> entry : childParents.entrySet())
		{
			if (entry.getValue().size() == 1)
			{
				oneParents.add(entry.getKey());
			}
			else if (entry.getValue().size() == 0)
			{
				zeroParents.add(entry.getKey());
			}
		}
		return new Pair(zeroParents, oneParents);

	}

	public static class Pair<K,V>
	{
		K key;
		V value;

		Pair(K k, V v)
		{
			this.key = k;
			this.value = v;
		}

		K getKey()
		{
			return key;
		}

		V getValue()
		{
			return value;
		}

		Pair<K,V> of(K k, V v)
		{
			return new Pair<>(k,v);
		}
	}

	public static void main(String[] args) {
		List<Pair<Integer, Integer>> parentChildPairs = Arrays.asList(new Pair(1,3), new Pair(2,3), new Pair(3,6),
				new Pair(5,6), new Pair(5,7), new Pair(4,5), new Pair(4,8), new Pair(4,9), new Pair(9,11));

		Pair<List<Integer>, List<Integer>> result = findNodesWithZeroAndOneParents(parentChildPairs);
		System.out.println("zeroParents: "+result.getKey()); //[1, 2, 4]
		System.out.println("oneParents:" +result.getValue());//[5, 7, 8, 9, 11]
	}

}
