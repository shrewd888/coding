package leetcode;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * 11. Container With Most Water
 *
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Example 1:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 *
 * Example 2:
 * Input: height = [1,1]
 * Output: 1
 */
public class ContainerWithMostWater
{
	public static int maxArea(int[] height) {
		int n = height.length;
		int left = 0;
		int right = n-1;
		int globalMax = 0;

		while (left < right)
		{
			int heightLeft = height[left];
			int heightRight = height[right];
			int area = Math.min(heightLeft, heightRight) * (right-left);
			globalMax = Math.max(globalMax, area);
			if (heightLeft <= heightRight)
			{
				left++;
			}
			else
			{
				right--;
			}
		}
		return globalMax;
	}

	public static void main (String[] args)
	{
		int[] height = {1,8,6,2,5,4,8,3,7};
		System.out.println(maxArea(height));//49
	}

}
