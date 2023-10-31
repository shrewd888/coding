package interviewkickstart.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * Given an integer array and a number k, find the k most frequent elements in the array.
 * Example One
 * {
 * "arr": [1, 2, 3, 2, 4, 3, 1],
 * "k": 2
 * }
 * Output:
 * [3, 1]
 *
 * Example Two
 * {
 * "arr": [1, 2, 1, 2, 3, 1],
 * "k": 1
 * }
 * Output:
 * [1]
 */
/**
 * Use counting sort: Time Complexity: O(n)
 * Auxiliary Space: O(n) -> storing elements in a map and in 2D array
 */
public class TopKFrequentElement
{
	/**
	 * k -> index at (n-k) forward if array is sorted in desc order
	 * Example: 2nd largest elements (k=2)
	 * [1,2,3,4,5] -> 4 -> at index 3 = 5-2
	 * @return
	 */
	static ArrayList<Integer> find_top_k_frequent_elements(ArrayList<Integer> arr, Integer k) {
		Map<Integer, Integer> freqMap = new HashMap<>();
		int index = 0;
		int max = 0; //keep track so that we can initialize array with this size
		while (index < arr.size())
		{
			Integer num = arr.get(index);
			Integer count = 1;
			if (freqMap.containsKey(num))
			{
				count = freqMap.get(num) + 1;
			}
			freqMap.put(num, count);
			if (count > max)
			{
				max = count;
			}
			index++;
		}

		//go through each element in the map, build array with index = number with the count = index
		//e.g: index = 0 value is number with freq = 0;
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		index = 0;
		//initialize array so that we can put values at specific index
		while (index < max+1)
		{
			result.add(new ArrayList<>());
			index++;
		}

		for (Map.Entry<Integer, Integer> entry : freqMap.entrySet())
		{
			Integer val = entry.getValue();
			ArrayList<Integer> nums = result.get(val);
			nums.add(entry.getKey());
		}
		//k most frequent = total #of elements start from the bottom of the list
		int size = result.size();
		ArrayList<Integer> finalResult = new ArrayList<>();
		int reverseIndex = size-1;

		while (finalResult.size() < k)
		{
			ArrayList<Integer> elements = result.get(reverseIndex);
			int elementsNeeded = k - finalResult.size();
			if (elements.size() < elementsNeeded)
			{
				finalResult.addAll(elements);
			}
			else
			{
				finalResult.addAll(elements.subList(elements.size()-elementsNeeded, elements.size()));
			}
			reverseIndex--;
		}
		return finalResult;
	}


	public static void main(String ... args) {
		ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 1, 2, 3, 1));
		ArrayList<Integer> result = find_top_k_frequent_elements(integers, 1);
		System.out.println(result);//[1]

		ArrayList<Integer> integers1 = new ArrayList<>(Arrays.asList(5, 4, 1, 3, 2, 7, 6));
		ArrayList<Integer> result1 = find_top_k_frequent_elements(integers1, 7);
		System.out.println(result1);//[1, 2, 3, 4, 5, 6, 7]

	}
}
