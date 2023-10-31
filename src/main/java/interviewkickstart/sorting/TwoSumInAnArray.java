package interviewkickstart.sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array and a target number, find the indices of the two values from the array that sum up to the given target number.
 * Example One
 * {
 * "numbers": [5, 3, 10, 45, 1],
 * "target": 6
 * }
 * Output:
 * [0, 4]
 * Sum of the elements at index 0 and 4 is 6.
 *
 * Example Two
 * {
 * "numbers": [4, 1, 5, 0, -1],
 * "target": 10
 * }
 * Output:
 * [-1, -1]
 */
public class TwoSumInAnArray
{

	static ArrayList<Integer> two_sum(ArrayList<Integer> numbers, Integer target) {
		ArrayList<Integer> result = new ArrayList<>();

		if (numbers == null || numbers.size() == 0) {
			return result;
		}
		Map<Integer, Integer> indexMap = new HashMap<>();
		int index = 0;
		while (index <= numbers.size()-1)
		{
			Integer val = target - numbers.get(index);
			if (indexMap.containsKey(val))
			{
				result.add(index);
				result.add(indexMap.get(val));
				return result;
			}
			indexMap.put(numbers.get(index), index);
			index++;
		}
		result.add(-1);
		result.add(-1);
		return result;
	}


	public static void main(String ... args) {
		ArrayList<Integer> integers = new ArrayList<>();
		integers.add(5);
		integers.add(3);
		integers.add(10);
		integers.add(45);
		integers.add(1);
		ArrayList<Integer> result = two_sum(integers, 6);
		System.out.println(result);

	}
}
