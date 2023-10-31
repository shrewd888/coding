package interviewkickstart;

import java.util.ArrayList;

public class SubsetTargetSum
{
	static Boolean check_if_sum_possible(ArrayList<Long> arr, Long k) {
		// Write your code here.
		Boolean b = helper(arr, k, 0L, 0L, 0L);
		return b;
	}

	static Boolean helper(ArrayList<Long> arr, Long k, Long index,
			Long sum, Long count)
	{
		if (sum==k && count != 0L)
		{
			return true;
		}
		if (index == arr.size())
		{
			if (sum==k)
			{
				return true;
			}
			return false;
		}
		//include
		Boolean b1 = helper(arr, k, index+1L, sum+arr.get(index.intValue()), count+1L);
		Boolean b2 = false;
		if (!b1)
		{
			return helper(arr, k, index+1L, sum, count);
		}
		else
		{
			return b1;
		}

	}



	public static void main(String ... args) {

	}
}
