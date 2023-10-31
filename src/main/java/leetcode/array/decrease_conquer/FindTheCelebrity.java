package leetcode.array.decrease_conquer;

/**
 * https://leetcode.com/problems/find-the-celebrity/
 *
 * 277. Find the Celebrity
 * Suppose you are at a party with n people labeled from 0 to n - 1 and among them,
 * there may exist one celebrity. The definition of a celebrity is that all
 * the other n - 1 people know the celebrity, but the celebrity does not know any of them.
 *
 * Now you want to find out who the celebrity is or verify that there is not one.
 * The only thing you are allowed to do is ask questions like: "Hi, A. Do you know B?"
 * to get information about whether A knows B.
 * You need to find out the celebrity (or verify there is not one) by asking
 * as few questions as possible (in the asymptotic sense).
 *
 * You are given a helper function bool knows(a, b) that tells you whether A knows B.
 * Implement a function int findCelebrity(n). There will be exactly one celebrity if they are at the party.
 *
 * Return the celebrity's label if there is a celebrity at the party. If there is no celebrity, return -1.
 */
/**
 * https://uplevel.interviewkickstart.com/resource/rc-video-210507-518318-247-1560-3843887
 * Omkar's solution
 * T(n) = O(n)
 * S(n) = O(1)
 */
public class FindTheCelebrity
{
	public static int findCelebrity(int n) {
		int survivor = 0;
		for (int i=1; i < n; i++)
		{
			//ask survivor if s(he) knows i
			//if yes, update the survivor, it means survivor is not a celebrity
			if (knows(survivor, i))
			{
				survivor = i;
			}
		}
		//validate the survivor: if survivor is a celebrity, then the row will be all 0 except
		//for (survivor, survivor) cell = 1 because celebrity only knows herself.
		for (int i=0; i < n; i++)
		{
			if (i != survivor)
			{
				if (knows(survivor, i) || !(knows(i, survivor)))
				{
					return -1;
				}
			}
		}
		return survivor;
	}


	public static boolean knows(int a, int b)
	{
		return true;
	}

}
