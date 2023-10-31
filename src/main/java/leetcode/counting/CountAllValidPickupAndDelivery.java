package leetcode.counting;

/**
 * Counting Problem
 * 1359. Count All Valid Pickup and Delivery Options
 *
 * Given n orders, each order consist in pickup and delivery services.
 * Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).
 * Since the answer may be too large, return it modulo 10^9 + 7.
 */
/**
 * f(n) = Total #of ways of arranging pickup & delivery for n orders
 * Recursive mathematical function
 * f(n) = f(n-1) * n(2n-1)
 *
 * table = 1D array of size n+1
 * Base Case:
 * table[1] = 1
 *
 * for i in 2 to n:
 * 	table[i] = (table[i-1] * i * (2i-1)) % (10^9 + 7)
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
 *
 */
public class CountAllValidPickupAndDelivery
{


}
