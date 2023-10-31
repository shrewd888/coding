package leetcode.binarysearch.part2;

/**
 * 852. Peak Index in a Mountain Array
 *
 * An array arr a mountain if the following properties hold:
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 *
 * Given a mountain array arr,
 * return the index i such that arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
 *
 * You must solve it in O(log(arr.length)) time complexity.
 *
 * Example 1:
 * Input: arr = [0,1,0]
 * Output: 1
 *
 * Example 2:
 * Input: arr = [0,2,1,0]
 * Output: 1
 *
 * Example 3:
 * Input: arr = [0,10,5,2]
 * Output: 1
 */
public class PeakIndexInAMountainArray
{
	/**
	 * Left zone: ascending zone
	 * Right zone: descending zone
	 * No need to look at all elements, binary search should work
	 */
	public static int peakIndexInMountainArray_Omkar(int[] arr)
	{
		if (arr.length < 3) return -1;
		int start = 1;
		int end = arr.length-2;
		while (start <= end)
		{
			int mid = start + (end-start)/2;

			//index at 0 or arr.length-1 can not be a peak
			//we are at a peak
			//can also check if (mid !=0 && mid != arr.length-1 && ...) but since start=1, end=arr.length-2
			//no need to check
			if (arr[mid-1] < arr[mid] && arr[mid] > arr[mid+1])
			{
				return mid;
			}
			//ascending zone
			//can also check if (mid == 0 || arr[mid] < arr[mid+1])
			else if (arr[mid] < arr[mid+1] && arr[mid] > arr[mid-1]) //or else if (arr[mid] < arr[mid+1])
			{
				start = mid + 1;
			}
			else //arr[mid-1] > arr[mid] descending zone
			{
				end = mid - 1;
			}
		}
		return -1;//it should never come here since there always be a peak in the input array
	}


	//Runtime 12 ms beats 5.4 %
	public static int peakIndexInMountainArray(int[] arr) {
		if (arr.length <= 2) return -1;
		int i;
		int j;
		//figure out how far we can walk along the ascending slope
		for (i=0; i < arr.length - 2; i++)
		{
			//Compare arr[i] with arr[i+1]
			if (arr[i] >= arr[i+1])
				break;
		}
		for (j=arr.length-1; j > 0; j--)
		{
			//Compare arr[j] with arr[j-1]
			if (arr[j] >= arr[j-1])
				break;
		}
		return (i==j && i != -1 ? i : -1);
		//or
		//return (i==j && (i > 0 && i < arr.length-1)) ? i : -1;
	}
}
