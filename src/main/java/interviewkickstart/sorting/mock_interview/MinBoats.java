package interviewkickstart.sorting.mock_interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
# == == == == == == == == == == == == == == == == == == == == == =
# You are given an array people where people[i] is the weight of the ith person,
# and an infinite number of boats where each boat can carry a maximum weight of limit.
# Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.
#
# Return the minimum number of boats to carry every given person.
#
# Example 1:
#
# Input: people = [1,2], limit = 3
# Output: 1
# Explanation: 1 boat (1, 2)
# Example 2:
#
# Input: people = [3,2,2,1], limit = 3
# Output: 3
# Explanation: 3 boats (1, 2), (2) and (3)
# Example 3:
#
# Input: people = [3,5,3,4], limit = 5
# Output: 4
# Explanation: 4 boats (3), (3), (4), (5)
#
# Constraints:
#
# 1 <= people.length <= 5 * 10^4
# 1 <= people[i] <= limit <= 3 * 10^4
# == == == == == == == == == == == == == == == == == == == == == =
*/
//https://leetcode.com/problems/boats-to-save-people/

public class MinBoats
{

	public static Integer findMinBoats(List<Integer> people, int limit)
	{
		//store each person's weight
		//[1,2] => index 1: 1, index 2: 1
		//[3,2,2,1] => index 3:1, 2:2, 1:1
		//key: weight, value: count
		Map<Integer, Integer> map = new HashMap<>();
		int index = 0;


		while (index < people.size())
		{
			Integer weight = people.get(index);

			if (map.containsKey(weight))
			{
				map.put(weight, map.get(weight) + 1);
			}
			else
			{
				map.put(weight, 1);
			}
			index++;

		}

		System.out.println(map);

		List<List<Integer>> result = new ArrayList<>();
		List<Integer> inner = new ArrayList<>();

		index=0;
		while (index < people.size())
		{

			Integer weight = people.get(index);//1
			if (weight == limit &&  map.get(weight) > 0)
			{
				inner.add(weight);
				map.put(weight, map.get(weight)-1);

			}
			else if (weight < limit)
			{

				Integer remain = limit -weight;//3-1=2
				inner.add(weight);//[1]

				map.put(weight, map.get(weight)-1);//[1,0]


				if (map.containsKey(remain) && map.get(remain) > 0)
				{
					inner.add(remain);//[1,2]
					Integer count = map.get(remain) -1;
					map.put(remain, count);
				}
			}
			result.add(inner);
			inner = new ArrayList<>();
			index++;

		}
		System.out.println(result);
		return result.size();
	}


	public static void main(String[] args) {

		List<Integer> people = Arrays.asList(1,2);

		Integer min = findMinBoats(people, 3);

		System.out.println(min);
	}
}

//class Solution:
//		def numRescueBoats(self, people: List[int], limit: int) -> int:
//
//		weight = sorted(people) // O(N * logN)
//		i, j = 0, len(weight) - 1
//		count = 0
//
//		while i <= j: // O(N)
//		count += 1
//		if weight[i] + weight[j] <= limit: i += 1
//		j -= 1
//
//		return count
//
//		}
