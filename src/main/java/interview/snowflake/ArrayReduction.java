package interview.snowflake;

/**
 * https://leetcode.com/discuss/interview-question/2550995/snowflake-OA
 *
 * Array reduction
 * Given an array of size N return the lexicographically largest (MAXIMUM) array
 * which can be obtained using the below mentioned algorithm
 *
 * Until array is empty
 * initialize an array result as empty
 * choose k such that 1<=k<=N (where n is size of array)
 * append MEX of the first k elements of the array to the result
 * remove first k elements from the array
 *
 * Here MEX is the smallest non -negative integer that is not present in the array.
 * For example if array is [0,1,2,4,5] then MEX is 3
 * test case 1
 * N=4; input array is [0,1,1,0]
 * output is [2,2]
 * set k as 2 then MEX([0,1]) is 2,add 2 to result and remove [0,1] from array
 * set k as 2 then MEX([1,0]) is 2,add 2 to result and remove [1,0] from array.
 * array is now empty return [2,2]
 *
 * test case 2
 * input  n=8 arr[2,2,3,4,0,1,2,0]
 * output [5,1]
 */
public class ArrayReduction
{
}
