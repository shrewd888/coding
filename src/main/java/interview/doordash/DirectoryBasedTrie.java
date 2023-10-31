package interview.doordash;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * See 1166. Design File System
 * https://leetcode.com/discuss/interview-question/1657195/DoorDash-or-Senior-Software-Engineer-or-E5-or-Onsite-Interview-or-Dec-2021
 *
 * In the Directory-based find, add, update and delete the value at the leaf
 * i.e
 * ADD API : /demo/test/update/count, currVal : Add the value : currVal at this level, if parent path (/demo/test/update) available otherwise throws an exception
 * GET API : /demo/test/update/count : returns the value at this level, if available otherwise throws an exception
 * UPDATE API : /demo/test/update/count, newVal : update the value at this level with newVal, if path available otherwise throws an exception
 * DELETE API :  /demo/test/update/count : delete the value at last leaf level so, then after remaining path will be  /demo/test/update/
 *
 * https://leetcode.com/discuss/interview-question/1553862/doordash-phone-screen
 * We want to implement an in-memory tree key value store for Doordash Restaurant Menus.
 * Definitions:
 *
 * path is a / separate string describing the node. Example /Tres Potrillos/tacos/al_pastor
 * Values are all strings
 * API spec:
 * get(path): String -> returns the value of the node at the given path
 *
 * create(path, value) -> creates a new node and sets it to the given value.
 * Should error out if the node already exists or if the node’s parent does not exist.
 * That is /Sweetgreen/naan_roll cannot be created if /Sweetgreen has not already been created
 *
 * delete(path) -> deletes a node, but ONLY if it has no children
 *
 *
 * Instead of copy paste we can use Runnable
 */
public class DirectoryBasedTrie
{
	String value;
	Map<String, DirectoryBasedTrie> innerTries;

	public DirectoryBasedTrie() {
		innerTries = new HashMap<>();
	}

	/**
	 * GET API : /demo/test/update/count : returns the value at this level,
	 * if available otherwise throws an exception
	 * Get ->
	 * Preprocess input for ease of coding (aka convert String -> Queue)
	 * recursively go down until you reach the end of the path.
	 * Then do the action (add, delete, get, update). If the path doesn't exist, throw an exception or add it
	 */
	public String get(String path) {
		if (path.length() == 0) {
			return this.value;
		}
		//using Queue so that we don't need to parse the string (by using split, remove prefix etc)
		//We use Queue instead of an array, which we don't need to keep track of an index, or removal
		//need to copy the elements
		//Use ArrayDeque instead of LinkedList because javadoc says ArrayDeque is more optimized

		String[] pathArr = path.replaceFirst("^/", "").split("/");
		List<String> arrayOfPath = Arrays.asList(pathArr);

		Queue<String> queue = new ArrayDeque<String>(arrayOfPath);
		return get(queue);
	}

	private String get(Queue<String> queuePath)
	{
		if (queuePath.isEmpty()) {
			return this.value;
		}
		String firstPath = queuePath.remove();
		if (!innerTries.containsKey(firstPath))
		{
			throw new IllegalArgumentException("Does not contain path: " + firstPath);
		}
		else
		{
			return innerTries.get(firstPath).get(queuePath);
		}
	}

	/**
	 * DELETE API :  /demo/test/update/count : delete the value at last leaf level so,
	 * then after remaining path will be  /demo/test/update/
	 */
	public String delete(String path)
	{
		if (path.length() == 0) {
			this.value = null;
		}
		String[] pathArr = path.replaceFirst("^/", "").split("/");
		List<String> arrayOfPath = Arrays.asList(pathArr);

		Queue<String> queue = new ArrayDeque<>(arrayOfPath);
		return delete(queue);
	}

	private String delete(Queue<String> queuePath)
	{
		if (queuePath.isEmpty()) {
			 this.value = null;
			 return null;
		}
		String firstPath = queuePath.remove();
		if (!innerTries.containsKey(firstPath))
		{
			throw new IllegalArgumentException("Does not contain path: " + firstPath);
		}
		else
		{
			return innerTries.get(firstPath).delete(queuePath);
		}
	}


	/**
	 * UPDATE API : /demo/test/update/count, newVal : update the value at this level with newVal,
	 * if path available otherwise throws an exception
	 */
	public void update(String path, String value) {
		add(path, value);
	}

	/**
	 * ADD API : /demo/test/update/count, currVal :
	 * Add the value : currVal at this level, if parent path (/demo/test/update) available
	 * otherwise throws an exception
	 */
	/**
	 * https://stackoverflow.com/questions/9389503/how-to-prevent-java-lang-string-split-from-creating-a-leading-empty-string
	 * String[] test = "/Test/Stuff".split("/");
	 * results in an array with "", "Test", "Stuff".
	 */
	public void add(String path, String value) {
		String[] pathArr = path.replaceFirst("^/", "").split("/");
		List<String> arrayOfPath = Arrays.asList(pathArr);
		Queue<String> queue = new ArrayDeque<>(arrayOfPath);
		add(queue, value);
	}

	/**
	 * Top level innerTries is an empty path
	 * Every trie corresponds to a section of a character
	 * Create a trie for every section
	 * /demo/test/update/count -> 4 tries
	 * If we have a lot of words (e.g google webcrawler), by using trie it gets really compressed
	 */
	private void add(Queue<String> path, String value)
	{
		if (path.isEmpty()) {
			this.value = value;
			return;
		}
		String firstPath = path.remove(); //now queue size = 0
		if (!innerTries.containsKey(firstPath))
		{
			if (path.size() == 0)
			{
				innerTries.put(firstPath, new DirectoryBasedTrie());
			}
			else
			{
				throw new IllegalArgumentException("Does not contain value: " + value);
			}
		}
		innerTries.get(firstPath).add(path, value);
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


	public static void main(String ... args) {
		DirectoryBasedTrie trie = new DirectoryBasedTrie();

//		String path ="/demo/test/update/count";
//		String value = "curVal";
//		trie.add(path, value);

		String path = "/a";
		String value = "1";
		trie.add(path, value);
		String result = trie.get(path);//return 1
		System.out.println(result);

		trie = new DirectoryBasedTrie();

		path = "/leet";
		value = "1";
		trie.add(path, value);
		result = trie.get(path);//return 1
		System.out.println(result);

		path = "/leet/code";
		value = "2";
		trie.add(path, value);
		result = trie.get(path);//return 2
		System.out.println(result);

		path = "/c/d";
		value = "1";
		trie.add(path, value);
		result = trie.get(path);//throw exception
		System.out.println(result);

	}
}
