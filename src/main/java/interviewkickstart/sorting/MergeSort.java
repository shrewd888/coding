package interviewkickstart.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of numbers, sort it using the Merge Sort algorithm.
 * Example
 * {
 * "arr": [5, 8, 3, 9, 4, 1, 7]
 * }
 * Output:
 * [1, 3, 4, 5, 7, 8, 9]
 */
public class MergeSort
{
	static ArrayList<Integer> merge_sort(ArrayList<Integer> arr) {
		if (arr == null) return null;
		if (arr.size() == 1) return arr;
		helper(arr, 0, arr.size()-1);
		return arr;
	}

	static ArrayList<Integer> helper(ArrayList<Integer> arr, int start, int end)
	{
		//leaf worker
		if (start == end) return arr;
		int mid = start + (end-start)/2;
		helper(arr, start, mid);
		helper(arr, mid+1, end);
		//merge
		int i = start;
		int j = mid+1;
		ArrayList<Integer> aux = new ArrayList<>(arr.size());
		while (i <= mid && j <= end)
		{
			if (arr.get(i) < arr.get(j))
			{
				aux.add(arr.get(i));
				i++;
			}
			else {
				aux.add(arr.get(j));
				j++;
			}
		}

		while (i <= mid)
		{
			aux.add(arr.get(i));
			i++;
		}
		while (j <= end)
		{
			aux.add(arr.get(j));
			j++;
		}
		//copy from aux to original array
		int index = 0;
		while (start <= end)
		{
			arr.set(start, aux.get(index));
			start++;
			index++;
		}
		return arr;
	}

	public static void main(String ... args) {
		List<Integer> input = Arrays.asList(5, 8, 3, 9, 4, 1, 7);
		ArrayList<Integer> arr = new ArrayList<>();
		arr.addAll(input);
		ArrayList<Integer> result = merge_sort(arr);

		System.out.println(result);
	}
}
