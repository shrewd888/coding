package interview.doordash;

/**
 * Counting Problem
 * 1359. Count All Valid Pickup and Delivery Options
 *
 * Given n orders, each order consist in pickup and delivery services.
 * Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: n = 1
 * Output: 1
 * Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
 *
 * Example 2:
 * Input: n = 2
 * Output: 6
 * Explanation: All possible orders:
 * (P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
 * This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.
 *
 * Example 3:
 * Input: n = 3
 * Output: 90
 */
/**
 * For any given sequence of (n-1) orders, total #of ways of picking pickup & delivery
 * times for my order:
 * S = (2n-1) + (2n-2) + (2n-3) + ... + 1
 * S = 1 + 2 + ... + (2n-1) -> flip the order
 * 2S = 2n + 2n + ... + 2n
 * 2S = 2n(2n-1)
 * S = n(2n-1)
 *
 * f(n) = Total #of ways of arranging pickup & delivery for n orders
 * Recursive mathematical function
 *
 * for every (n-1) I have n(2n-1) choices
 *
 * f(n) = f(n-1) * n(2n-1)
 *
 * table = 1D array of size n+1
 * Base Case:
 * table[1] = 1
 *
 * for i in 2 to n:
 * 	 table[i] = (table[i-1] * i * (2i-1)) % (10^9 + 7)
 * return table[n] -> final answer
 * no need to allocate the whole space, just store the previous value
 *
 * Time Complexity T(n) = O(n) -> look like a linear time but actually it is not because we are given a size of input = integer
 * How much size is the size of integer?
 * The result is exponential size of the integer
 *
 * Space Complexity: No aux space
 *
 * Base Case:
 * f(1) = 1
 *
 * for i in 2 to n:
 * 	f = (f * i(2i-1)) % (10^9 + 7) -> 1000000007
 * return f
 */

/**
 * Time Complexity = O(n)
 * Actually not O(n) because we are given an input as an integer -> how much space to allocate an integer?
 * Space Complexity = O(1), no auxiliary space
 */
public class CountAllValidPickupAndDelivery
{
	public static int countOrders(int n) {
		long[] table = new long[n+1];
		//Base Case
		table[1] = 1;

		for (int i=2; i<=n; i++)
		{
			table[i] = (long) ((table[i-1]*i*(2*i-1)) % (Math.pow(10,9) + 7));
		}
		return (int) table[n];
	}

	public static void main(String[] args) {
		int n = 1;
		System.out.println(countOrders(n));//1
		n = 2;
		System.out.println(countOrders(n));//6
		n = 3;
		System.out.println(countOrders(n));//90
	}
}
