package interview.snowflake;

/**
 * My first round interview on 09/22/2022 - Vishnu DuttPaladugu
 * -> I should be more confident and not worry about looking at other coding problems in my IDE!!!
 *
 * Return the substring of String S that contains all string in String T that has the min length
 *
 * Example:
 * String S = "BBDRETRFEDB"
 * String T = "EBD"
 * Answer: "EDB"
 * Time Complexity: O(n) -> Linear scan using left & right pointer
 * Space Complexity: O(1)
 */

/**
 * while (left <= right)
 *  if (S.substring(left, right+1) contains T)
 *  	left++
 *  else
 *      right++
 */
public class MinimumLengthOfSubstring
{


}
