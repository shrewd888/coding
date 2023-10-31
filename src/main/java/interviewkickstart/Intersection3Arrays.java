package interviewkickstart;

import java.util.ArrayList;

public class Intersection3Arrays
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
		}

		return result;
	}

	public static void main(String ... args) {

		ArrayList<Integer> a1 = new ArrayList<>();
		a1.add(2);
		a1.add(5);
		a1.add(10);

		ArrayList<Integer> a2 = new ArrayList<>();
		a2.add(2);
		a2.add(3);
		a2.add(4);
		a2.add(10);

		ArrayList<Integer> a3 = new ArrayList<>();
		a3.add(2);
		a3.add(4);
		a3.add(10);

		ArrayList<Integer>  r = find_intersection(a1, a2, a3);
		System.out.println(r);
	}
}
