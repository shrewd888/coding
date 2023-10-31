package interviewkickstart.sorting;

import java.util.ArrayList;

/**
 * Given three arrays sorted in the ascending order, return their intersection sorted array in the ascending order.
 * Example One
 * {
 * "arr1": [2, 5, 10],
 * "arr2": [2, 3, 4, 10],
 * "arr3": [2, 4, 10]
 * }
 * Output:
 * [2, 10]
 *
 * Example Two
 * {
 * "arr1": [1, 2, 3],
 * "arr2": [],
 * "arr3": [2, 2]
 * }
 * Output:
 * [-1]
 *
 * Example Three
 * {
 * "arr1": [1, 2, 2, 2, 9],
 * "arr2": [1, 1, 2, 2],
 * "arr3": [1, 1, 1, 2, 2, 2]
 * }
 * Output:
 * [1, 2, 2]
 */
public class IntersectionOfThreeSortedArrays
{
	static ArrayList<Integer> find_intersection(ArrayList<Integer> arr1, ArrayList<Integer> arr2, ArrayList<Integer> arr3) {
		// Write your code here.


		ArrayList<Integer> result = new ArrayList<>();

		int sz1 = arr1.size();
		int sz2 = arr2.size();
		int sz3 = arr3.size();

		if (sz1 == 0 || sz2 == 0 || sz3 == 0)
		{
			result.add(-1);
			return result;
		}

		int index1=0;
		int index2=0;
		int index3=0;
		while (index1 < sz1 && index2 < sz2 && index3 < sz3)
		{
			Integer p1 = arr1.get(index1);
			Integer p2 = arr2.get(index2);
			Integer p3 = arr3.get(index3);

			if (p1.equals(p2) && p2.equals(p3))
			{
				result.add(p1);
				index1++;
				index2++;
				index3++;
			}
			else if (p1 > p2)
			{
				index2++;
			}
			else if (p1 > p3)
			{
				index3++;
			}
			else if (p1 < p2)
			{
				index1++;
			}
			else if (p2 < p3)
			{
				index2++;
			}
			else if (p1 < p3)
			{
				index1++;
			}
			else if (p3 < p2)
			{
				index3++;
			}
		}
		if (result.size()==0)
		{
			result.add(-1);
		}
		return result;
	}
}
