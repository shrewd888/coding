package interviewkickstart.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

//Use sort: Time Complexity: O(n log n)
public class TopKFrequentElementUseSort
{
	static ArrayList<Integer> find_top_k_frequent_elements(ArrayList<Integer> arr, Integer k)
	{
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

		List<Integer> sorted = freqMap.entrySet()
				.stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new))
				.keySet().stream()
				.collect(Collectors.toList())
				.subList(0, k);
		return new ArrayList<>(sorted);
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
