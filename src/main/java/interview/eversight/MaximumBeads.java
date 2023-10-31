package interview.eversight;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/discuss/interview-question/1874013/Eversight-or-Phone-Screen-or-Maximum-Beads
 *
 * You find some necklaces and each necklace comprises a number of beads. Unfortunately, they are tangled together.
 * You have numbered all the beads with numbers in the range [0..N−1], so that each number corresponds to exactly one bead.
 * Then, for each bead, you have found the number of the next bead following it.
 *
 * This information is given as an array of integers, indexed by bead numbers, and the elements are the numbers of the following beads.
 * Each bead number appears in the array exactly once.
 *
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given an array A consisting of N integers, as described above, returns the maximum number of beads in a single necklace.
 * The function should return 0 if the array is empty.
 *
 * or example, given array A such that:
 * A[0] = 5
 * A[1] = 4
 * A[2] = 0
 * A[3] = 3
 * A[4] = 1
 * A[5] = 6
 * A[6] = 2
 * the function should return 4, because there are 3 necklaces:
 * {5, 6, 2, 0}
 * {4, 1}
 * {3}
 * And the longest one contains four beads.
 *
 * In summary, given an array representing the order of beads in necklaces, find the necklace containing the largest number of beads.
 */
public class MaximumBeads
{
	static int maxBeads(int[] A) {
		int length = A.length;
		if (length == 0) return 0;

		Set<Integer>[] adjList = new HashSet[length];
		for (int i=0; i < length; i++)
		{
			adjList[i] = new HashSet<>();
		}
		int maxLength = 0;
		for (int i=0; i < length; i++)
		{
			int value = A[i];
			adjList[i].add(value);
			int index = value;
			while (index != i)
			{
				value = A[index];
				adjList[i].add(value);
				index = value;
			}
			int eachLength = adjList[i].size();
			if (eachLength > maxLength)
			{
				maxLength = eachLength;
			}
		}

//		int maxLength = 0;
//		for (Set each : adjList)
//		{
//			int eachLength = each.size();
//			if (eachLength > maxLength)
//			{
//				maxLength = eachLength;
//			}
//		}
		return maxLength;
	}

	public static void main(String ... args)
	{
		int[] input = { 5, 4, 0, 3, 1, 6, 2};
		int length = maxBeads(input);
		System.out.println("Length: "+length);
	}


}
