package leetcode.sorting;

/**
 * https://leetcode.com/problems/sort-an-array/
 * 912. Sort an Array
 * Given an array of integers nums, sort the array in ascending order and return it.
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity
 * and with the smallest space complexity possible.
 */
//TODO: FIX ME - Runtime error
public class SortAnArray
{
	public static int[] sortArray(int[] nums) {
		int[] numArrays = new int[100000];
		int[] result = new int[nums.length];

		for (int i=0; i<nums.length; i++)
		{
			int num = nums[i];
			numArrays[num]++;
		}
		int index = 0;
		for (int i=0; i < numArrays.length; i++)
		{
			for (int j=0; j < numArrays[i]; j++)
			{
				result[index] = i;
				index++;
			}
		}
		return result;
	}

	static void print(int[] arr)
	{
		System.out.print("[");
		for (int i=0; i < arr.length; i++)
		{
			System.out.print(arr[i]);
			if (i < arr.length-1)
				System.out.print(",");
		}
		System.out.print("] ");
	}


	public static void main(String ... args) {
//		int[] nums = {5,2,3,1};
//		int[] result = sortArray(nums);
//		print(result);//[1,2,3,5]
		String binary = Integer.toBinaryString(50000);//1100001101010000
		System.out.println(binary);
		binary = Integer.toBinaryString(-50000);//11111111111111110011110010110000
		System.out.println(binary);

		binary = Integer.toBinaryString(-5);//11111111111111111111111111111011
		System.out.println(binary);
		binary = Integer.toBinaryString(5);//101
		System.out.println(binary);

		binary = Integer.toBinaryString(-6);//11111111111111111111111111111010
		System.out.println(binary);
		binary = Integer.toBinaryString(6); //110
		System.out.println(binary);


	}
}
