package interview.doordash;

import java.util.HashMap;
import java.util.Map;

/**
 * 208. Implement Trie (Prefix Tree)
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
 * There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 * Example 1:
 *
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 */
public class Trie
{
	boolean isWord = false;
	char curVal;
	Map<Character, Trie> innerTries;

	public Trie() {
		innerTries = new HashMap<>();
	}

	public void insert(String word) {
		if (word.length() == 0) {
			isWord = true;
			return;
		}

		char firstChar = word.charAt(0);
		if (!innerTries.containsKey(firstChar))
		{
			innerTries.put(firstChar, new Trie());
		}
		innerTries.get(firstChar).insert(word.substring(1));
	}

	public boolean search(String word) {
		if (word.length() == 0) return isWord;
		char firstChar = word.charAt(0);

		if (innerTries.containsKey(firstChar))
		{
			return innerTries.get(firstChar).search(word.substring(1));
		}
		return false;
	}

	public boolean startsWith(String prefix) {
		if (prefix.length() == 0) return true;
		char firstChar = prefix.charAt(0);

		if (innerTries.containsKey(firstChar))
		{
			return innerTries.get(firstChar).startsWith(prefix.substring(1));
		}
		return false;
	}

	/**
	 * Your Trie object will be instantiated and called as such:
	 * Trie obj = new Trie();
	 * obj.insert(word);
	 * boolean param_2 = obj.search(word);
	 * boolean param_3 = obj.startsWith(prefix);
	 */
	public static void main(String ... args) {
		Trie trie = new Trie();
		trie.insert("apple");

		boolean b = trie.search("apple");
		System.out.println(b);// return True

		b = trie.search("app");
		System.out.println(b);// return False

		b = trie.startsWith("app");
		System.out.println(b);// return True

		trie.insert("app");

		b = trie.search("app");
		System.out.println(b);// return True
	}
}
