package interview.doordash;

/**
 * f(i,j):
 * 	if P1[i] == P2[j]:
 * 		1 + f(i+1, j+1)
 *  else:
 *  	max (f(i+1,j), f(i,j+1))
 *
 *  Base Case:
 *  if (i==len(P1) or j==len(P2)) return 0
 */

public class DoorDashLCSRecursive
{
	public static int lcs(String[] m1, String[] m2)
	{
		return helper(m1, m2, 0, 0);
	}

	public static int helper(String[] m1, String[] m2, int i, int j)
	{
		//Base Case
		if (i == m1.length || j == m2.length)
		{
			return 0;
		}
		if (m1[i] == m2[j])
		{
			return 1 + helper(m1, m2, i+1, j+1);
		}
		else
		{
			return Math.max(helper(m1, m2, i+1, j), helper(m1, m2, i, j+1));
		}
	}

	public static void main(String ... args)
	{
		String[] m1 = new String[]{"chilis", "albertsons", "walmart", "albertsons", "chilis", "mcdonalds", "burger king"};
		String[] m2 = new String[]{"chilis", "walmart", "chilis", "albertsons", "burger king", "applebees", "mcdonalds"};
		int result = lcs(m1, m2);
		System.out.println(result);//4
		// [chilis, walmart, albertsons, mcdonalds]

		m1 = new String[]{"chilis", "albertsons", "mcdonalds"};
		m2 = new String[]{"burger king", "jamba juice", "applebees"};
		result = lcs(m1, m2);
		System.out.println(result);//0
	}
}
