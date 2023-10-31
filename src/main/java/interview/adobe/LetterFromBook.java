package interview.adobe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interview at 9/12/22 1st round with a software engineer Kav Kallangada
 */
// Given two string, letter and book, write a method that returns true if letter can be constructed using words from the book.

/*
Example 1:

letter = "John john has an interview today."
book = "john has an interview today at 1 PM. It is his first meeting with Adobe"
result: true

letter = "John John has an interview today."
book = "john has an interview today at 1 PM. It is his first meeting with Adobe"
result: false

letter = "John has an interview today."
book = "John has an meeting today at 1 PM. It is his first call with Adobe"
result: false
*/
public class LetterFromBook
{
	public static boolean letterCanBeConstructedFromBook(String inputLetter, String inputBook)
	{
		//edge cases
		if (inputLetter == null || inputBook == null) return false;


		List<String> letters = Arrays.asList(inputLetter.split(" "));
		Map<String, Integer> letterMap = new HashMap<>();

		for (String letter : letters)
		{
			//make case insensitive
			if (letterMap.containsKey(letter))
			{
				Integer c = letterMap.get(letter) + 1;
				letterMap.put(letter, c);
			}
			else
			{
				letterMap.put(letter, 1);
			}
		}

		List<String> books = Arrays.asList(inputBook.split(" "));
		for (String book : books)
		{

			Integer c = letterMap.get(book);

			//no key or all has been used
			if (c == null || c == 0) return false;

			else if (letterMap.containsKey(book) && c >= 1)
			{
				letterMap.put(book, c-1);
			}
		}
		return true;
	}


	public static void main(String[] args) {
		System.out.println("Hello, world!");
		List<String> str = new ArrayList<>();
		System.out.println(str.get(0));
	}
}
