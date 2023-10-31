package interview.doordash;

import java.util.HashMap;

/**
 * 1347. Minimum Number of Steps to Make Two Strings Anagram
 * You are given two strings of the same length s and t.
 * In one step you can choose any character of t and replace it with another character.
 *
 * Return the minimum number of steps to make t an anagram of s.
 *
 * An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.
 *
 * Example 1:
 * Input: s = "bab", t = "aba"
 * Output: 1
 * Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
 *
 * Example 2:
 * Input: s = "leetcode", t = "practice"
 * Output: 5
 * Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
 *
 * Example 3:
 * Input: s = "anagram", t = "mangaar"
 * Output: 0
 * Explanation: "anagram" and "mangaar" are anagrams.
 */
public class MinNumberOfStepsToMakeTwoStringsAnagram
{
	public int minSteps(String s, String t) {
		int[] count = new int[26];
		//initialize
		for(char c:s.toCharArray()){
			count[c-'a']++;
		}
		int diff = 0;
		for(char c:t.toCharArray()){
			if(count[c-'a'] > 0)
				count[c-'a']--;
			else
				diff++;
		}
		return diff;
	}

	public int minStepsUsingMap(String s, String t) {
		HashMap<Character, Integer> freqMap1 = new HashMap<>();
		HashMap<Character, Integer> freqMap2 = new HashMap<>();
		for(char ch: s.toCharArray())
			freqMap1.put(ch, freqMap1.getOrDefault(ch, 0)+1);
		for(char ch : t.toCharArray())
			freqMap2.put(ch, freqMap2.getOrDefault(ch, 0)+1);

		for (char ch : freqMap1.keySet()){
			int freq1 = freqMap1.get(ch);
			if(freqMap2.containsKey(ch)){
				int freq2 = freqMap2.get(ch);
				if(freq1 < freq2){
					freqMap2.put(ch, freq2-freq1);
				}else{
					freqMap2.remove(ch);
				}
			}
		}
		int sum = 0;
		for (char ch : freqMap2.keySet())
			sum += freqMap2.get(ch);
		return sum;
	}
}
