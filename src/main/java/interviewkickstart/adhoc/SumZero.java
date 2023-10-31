package interviewkickstart.adhoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return any non-empty subarray whose elements are indexes that sum up to zero.
 *
 * Example One
 * {
 * "arr": [5, 1, 2, -3, 7, -4]
 * }
 * Output:
 * [1, 3]
 * Sum of [1, 2, -3] subarray is zero. It starts at index 1 and ends at index 3 of the given array,
 * so [1, 3] is a correct answer. [3, 5] is another correct answer.
 *
 * Example Two
 * {
 * "arr": [1, 2, 3, 5, -9]
 * }
 * Output:
 * [-1]
 */
public class SumZero
{
	static ArrayList<Integer> sum_zero(ArrayList<Integer> arr) {
		ArrayList<Integer> result = new ArrayList<>();
		if (arr == null || arr.size() == 0) {
			result.add(-1);
			return result;
		}
		if (arr.size()==1 && arr.get(0)==0)
		{
			result.add(0);
			result.add(0);
			return result;
		}

		Map<Integer, Integer> sumIndex = new HashMap<>();
		sumIndex.put(arr.get(0), 0);

		Integer prevSum = arr.get(0);

		for (int i=1; i < arr.size(); i++)
		{
			Integer element = arr.get(i);
			Integer currentSum = element + prevSum;
			prevSum = currentSum;
			if (currentSum == 0)
			{
				result.add(0);
				result.add(i);
				return result;
			}
			if (sumIndex.containsKey(currentSum))
			{
				if (element != 0)
				{
					Integer index = sumIndex.get(currentSum);
					result.add(index + 1);
					result.add(i);
					return result;
				}
				else { //element == 0
					result.add(i);//element at i = 0
					result.add(i);//0 stays 0
					return result;
				}
			}
			sumIndex.put(currentSum, i);
		}
		if (result.size()==0)
		{
			result.add(-1);
		}
		return result;
	}

	public static void main(String ... args)
	{
		ArrayList<Integer> input = new ArrayList<>();
		input.add(4);
		input.add(2);
		input.add(-3);
		input.add(1);
		input.add(6);
		ArrayList<Integer> result = sum_zero(input);
		System.out.println(result);//[1, 3]
	}
}
