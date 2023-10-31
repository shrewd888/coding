package interviewkickstart.dynamicprogramming;

/**
 * Length (n): 1,2,3,4
 * Price (p): 1,5,8,9
 * Calculate max profit to cut the rod
 *
 */
public class RodCuttingMaxProfit
{
	//cost of cutting 0 length to n length
	public static int[] price = new int[]{ 0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };

	public static int maxProfit(int n)
	{
		if (n==0) return 0;

		//store max profit to cut length 0, 1, 2, ... n
		int[] table = new int[n+1];
		table[0] = price[0]; table[1] = price[1];

		//length of rod start with 2
		//i = 2: cut = 1 (cut 1 rod), remaining = 2-1 = 1 -> take the max price from i=1 + price of remaining cut
		for (int i=2; i <= n; i++)
		{
			table[i] = price[i]; //no need to cut
			for (int cut=1; cut <= i; cut++) //cut from 1 to i
			{

				int prevCut = i-cut; //length of rod - prevCut
				//take the best from: no cut or price from prevCut + price of current cut
				int priceToCut = table[prevCut] + price[cut];

				table[i] = Math.max( table[i], priceToCut);
			}
		}
		return table[n];
	}

	public static void main(String ... args) {
		int maxProfit = maxProfit(4);
		System.out.println(maxProfit);//10

		maxProfit = maxProfit(5);
		System.out.println(maxProfit);//13
	}
}
