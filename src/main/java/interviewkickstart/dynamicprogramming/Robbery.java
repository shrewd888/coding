package interviewkickstart.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * There are n houses built in a line, each of which contains some value in it.
 * A thief is going to steal the maximal value in these houses,
 * but he cannot steal in two adjacent houses because the owner of a stolen house will tell
 * his two neighbours on the left and right side. What is the maximal stolen value?
 *
 * For example, if there are four houses with values [6, 1, 2, 7],
 * the maximal stolen value is 13, when the first and fourth houses are stolen.
 */

/**
 * A function f(i) is defined to denote the maximal stolen value from the first house to the i-th house,
 * and the value contained in the i-th house is denoted as values[i].
 * When the thief reaches the ith house, he has two choices: to steal or not.
 *
 * If he steals then he will obtain
 * f(i - 2) + values[i]
 *
 * If he does not steal he can obtain
 * f(i - 1)
 *
 * Thus, the relationship can be written as
 * f(i) = max(f(i - 2) + values[i], f(i - 1))
 */
public class Robbery
{
	static Integer maximum_stolen_value(ArrayList<Integer> values) {

		if (values == null || values.size()==0) return 0;
		if (values.size()==1) return values.get(0);

		int[] memo = new int[values.size()];
		//Base Case
		memo[0] = values.get(0);
		memo[1] = Math.max(values.get(0), values.get(1));

		for (int i=2; i < values.size(); i++)
		{
			memo[i] = Math.max(values.get(i) + memo[i-2], memo[i-1]);
		}

		return memo[values.size()-1];
	}

	public static void main(String ... args) {
		ArrayList<Integer> values = new ArrayList<>(Arrays.asList(6, 1, 2, 7));
		Integer num = maximum_stolen_value(values);
		System.out.println(num); //13
	}
}
