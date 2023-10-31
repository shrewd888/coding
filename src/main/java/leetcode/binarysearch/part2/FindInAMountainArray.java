package leetcode.binarysearch.part2;

/**
 * 1095. Find in Mountain Array
 * (This problem is an interactive problem.)
 *
 * You may recall that an array arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.
 * If such an index does not exist, return -1.
 *
 * You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
 *
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.
 * Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * Example 1:
 * Input: array = [1,2,3,4,5,3,1], target = 3
 * Output: 2
 * Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
 *
 * Example 2:
 * Input: array = [0,1,2,4,2,1], target = 3
 * Output: -1
 * Explanation: 3 does not exist in the array, so we return -1.
 */

/**
 * Omkar's Solution
 * Binary Search Variants Part 2
 */
public class FindInAMountainArray
{
	public static int findInMountainArray(int target, MountainArray mountainArr) {
		int start = 1;
		int end = mountainArr.length()-2;
		int peakIndex = -1;
		//BS #1 : finding the peak
		while (start <= end)
		{
			int mid = start + (end-start)/2;
			int midVal = mountainArr.get(mid);
			int prevVal = mountainArr.get(mid-1);
			int nextVal = mountainArr.get(mid+1);

			if (prevVal < midVal && midVal > nextVal)
			{
				peakIndex = mid;
				//if (midVal == target) return mid;
				break;
			}
			else if (prevVal < midVal)
			{
				start = mid + 1;
			}
			else
			{
				end = mid - 1;
			}
		}
		//if the target is at the peak, return the peakIndex;
		if (mountainArr.get(peakIndex) == target) return peakIndex;

		//BS #2 : Searching for the target value in the ascending order
		start = 0;
		end = peakIndex - 1;
		int left = -1;
		//now we are searching for the target value
		while (start <= end)
		{
			int mid = start + (end-start)/2;
			int midVal = mountainArr.get(mid);
			if (midVal == target)
			{
				left = mid;
				break;
			}
			else if (midVal < target)
			{
				start = mid + 1;
			}
			else
			{
				end = mid - 1;
			}
		}

		//BS #3 : Searching for the target value in the descending order
		start = peakIndex + 1;
		end = mountainArr.length() - 1;
		int right = -1;

		while (start <= end)
		{
			int mid = start + (end-start)/2;
			int midVal = mountainArr.get(mid);
			if (midVal == target)
			{
				right = mid;
				break;
			}
			else if (midVal < target)
			{
				end = mid - 1;
			}
			else
			{
				start = mid + 1;
			}
		}
		if (left == -1 && right != -1) return right;
		else if (left != -1 && right == -1) return left;
		else if (left < right) return left;
		else if (left > right) return right;
		else if (left == right && left != -1) return left;
		else return -1;
	}

	public static void main(String ... args)
	{
		int[] nums = {1,2,3,4,5,3,1};
		int target = 3;
		//System.out.println(findInMountainArray(target, nums));
	}
}
