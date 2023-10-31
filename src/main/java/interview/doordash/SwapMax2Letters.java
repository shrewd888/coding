package interview.doordash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/discuss/interview-question/2008962/DoorDash-or-Staff-or-Phone
 *
 * Two restaurants R1 and R2 are similar if we can swap a maximum of two letters (in different positions) of R1, so that it equals R2.
 * 1
 * Input: "hotpot", ["hottop", "hotopt", "hotpit", "httoop", "hptoot", "hotozt"]
 * Output: ["hottop", "hotopt", "hptoot"]
 *
 * 2
 * Input: "biryani", ["biryani", "biryeni", "biriyani", "biriany", "briynai"]
 * Output: ["biryani", "biriany"]
 */

/**
 * 859. Buddy Strings
 * https://leetcode.com/problems/buddy-strings/
 *
 * 1790. Check if One String Swap Can Make Strings Equal
 * https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/
 */
public class SwapMax2Letters
{
	public static Set<String> getSimilarRestaurants(String restaurant, Set<String> restaurants) {
		final Set<String> similarRestaurants = new HashSet<>();
		final boolean hasDupChars = hasDuplicateChars(restaurant);

		for (String rest : restaurants)
		{
			if (rest.equals(restaurant))
			{
				if (hasDupChars) { //means the same characters can be swap if two restaurants are exactly equals
					similarRestaurants.add(rest);
				}
			}
			else
			{
				int index1 = -1, index2 = -1;
				boolean isSame = true;
				for (int i = 0; i < restaurant.length(); i++) {

					if (rest.length() == restaurant.length() /*&& sort(rest).equals(sort(restaurant))*/)
					{
						if (rest.charAt(i) != restaurant.charAt(i))
						{
							if (index1 == -1)
							{
								index1 = i;
							}
							else if (index2 == -1)
							{
								index2 = i;
							}
							else
							{
								isSame = false;
								break;
							}
						}
					}
				}

				if (index1 != -1 && index2 != -1
						&& restaurant.charAt(index1) == rest.charAt(index2)
						&& restaurant.charAt(index2) == rest.charAt(index1)
						&& isSame)
				{
//					char[] restChars = rest.toCharArray();
//					restChars[index1] = restaurant.charAt(index1);
//					restChars[index2] = restaurant.charAt(index2);
//					if (new String(restChars).equals(restaurant))
//					{
						similarRestaurants.add(rest);
				//	}
				}
			}
		}
		return similarRestaurants;
	}

	private static String sort(String s)
	{
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}

	private static boolean hasDuplicateChars(String s){
		int[] map = new int[26];
		for (int i = 0; i < s.length(); i++) {
			map[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < 26; i++) {
			if (map[i] > 1) {
				return true;
			}
		}
		return false;
	}

	public static void main(String ... args)
	{
		//Input: "hotpot", ["hottop", "hotopt", "hotpit", "httoop", "hptoot", "hotozt"]
		//Output: ["hottop", "hotopt", "hptoot"]
		String restaurant = "hotpot";
		Set<String> restaurants = Set.of("hottop", "hotopt", "hotpit", "httoop", "hptoot", "hotozt");
		Set<String> similar = getSimilarRestaurants(restaurant, restaurants);
		System.out.println(similar); //[hptoot, hottop, hotopt]

		//Input: "biryani", ["biryani", "biryeni", "biriyani", "biriany", "briynai"]
		//Output: ["biryani", "biriany"]
		String restaurant1 = "biryani";
		Set<String> restaurants1 = Set.of("biryani", "biryeni", "biriyani", "biriany", "briynai");
		Set<String> similar1 = getSimilarRestaurants(restaurant1, restaurants1);
		System.out.println(similar1);//[biryani, biriany]
	}
}
