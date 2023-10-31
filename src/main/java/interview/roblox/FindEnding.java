package interview.roblox;

import java.util.HashSet;
import java.util.Set;

/**
 * My 1st interview round on 11/13/2022 with Karat (Roblox's partner).
 * I did really bad, my speed was slow, my approach seemed incorrect.
 * I wasn't given a 2nd chance eventhough they said there will be a 24 hour response needed if I want to have a 2nd chance.
 * Means I did very bad !!!
 * I need to improve on speed.
 */

/**
 * You are reading a Build Your Own Story book. It is like a normal book except that choices on some pages affect the story, sending you to one of two options for your next page.
 *
 * These choices are really stressing you out, so you decide that you'll either always pick the first option, or always pick the second option.
 *
 * You start reading at page 1 and read forward one page at a time unless you reach a choice or an ending.
 *
 * The choices are provided in a list, sorted by the page containing the choice, and each choice has two options of pages to go to next. In this example, you are on page 3, where there is a choice. Option 1 goes to page 14 and Option 2 goes to page 2.
 *
 * choices1 = [[3, 14, 2]] => [current_page, option_1, option_2]
 * The ending pages are also given in a sorted list:
 *
 * endings = [6, 15, 21, 30]
 *
 * Given a list of endings, a list of choices with their options, and the choice you will always take (Option 1 or Option 2), return the ending you will reach. If you get stuck in a loop, return -1.
 *
 * Example:
 * find_ending(endings, choices1, 1) (always Option 1)
 *   Start: 1 -> 2 -> 3(choice) -> 14 -> 15(end) => Return 15
 *
 * find_ending(endings, choices1, 2) (always Option 2)
 *   Start: 1 -> 2 -> 3(choice) -> 2 -> 3(choice) -> 2... => Return -1
 *
 * Additional inputs:
 * choices2 = [[5, 11, 28], [9, 19, 29], [14, 16, 20], [18, 7, 22], [25, 6, 30]]
 * choices3 = []
 * choices4 = [[2, 10, 15], [3, 4, 10], [4, 3, 15], [10, 3, 15]]
 *
 * Complexity Variable:
 * n = number of pages
 * (endings and choices are bound by the number of pages)
 *
 * All Test Cases - snake_case:
 * find_ending(endings, choices1, 1) => 15
 * find_ending(endings, choices1, 2) => -1
 * find_ending(endings, choices2, 1) => 21
 * find_ending(endings, choices2, 2) => 30
 * find_ending(endings, choices3, 1) => 6
 * find_ending(endings, choices3, 2) => 6
 * find_ending(endings, choices4, 1) => -1
 * find_ending(endings, choices4, 2) => 15
 *
 * All Test Cases - camelCase:
 * findEnding(endings, choices1, 1) => 15
 * findEnding(endings, choices1, 2) => -1
 * findEnding(endings, choices2, 1) => 21
 * findEnding(endings, choices2, 2) => 30
 * findEnding(endings, choices3, 1) => 6
 * findEnding(endings, choices3, 2) => 6
 * findEnding(endings, choices4, 1) => -1
 * findEnding(endings, choices4, 2) => 15
 */
public class FindEnding
{
	public static int find_ending(int[] endings, int[][] choices, int option)
	{
		int start = 1;
		Set<Integer> numSet = new HashSet<>();

		int choiceIndex = 0;
		int ending = 0;
		while (start <= choices[choiceIndex][0])
		{
			numSet.add(start);
			if (start == choices[choiceIndex][0])
			{
				start = choices[choiceIndex][option];

				choiceIndex++;

				while (start < endings[ending])
				{
					ending++;
					if (start==endings[ending]) {

						return endings[ending];
					}

					start++;
				}
				if (numSet.contains(start)) return -1;
				start++;
			}

		}
		return -1;
	}
	public static void main(String[] argv) {
		int[] endings = {6, 15, 21, 30};
		int[][] choices1 = {{3, 14, 2}};
		int[][] choices2 = {{5, 11, 28}, {9, 19, 29}, {14, 16, 20}, {18, 7, 22}, {25, 6, 30}};
		int[][] choices3 = {};
		int[][] choices4 = {{2, 10, 15}, {3, 4, 10}, {4, 3, 15}, {10, 3, 15}};
		System.out.println();
	}
}
