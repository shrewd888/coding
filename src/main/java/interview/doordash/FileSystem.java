package interview.doordash;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 1166. Design File System
 *
 * You are asked to design a file system that allows you to create new paths and associate them with different values.
 *
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
 * For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.
 *
 * Implement the FileSystem class:
 *
 * bool createPath(string path, int value)
 * Creates a new path and associates a value to it if possible and returns true.
 * Returns false if the path already exists or its parent path doesn't exist.
 *
 * int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.
 *
 * Example 1:
 * Input:
 * ["FileSystem","createPath","get"]
 * [[],["/a",1],["/a"]]
 * Output:
 * [null,true,1]
 *
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.createPath("/a", 1); // return true
 * fileSystem.get("/a"); // return 1
 *
 * Example 2:
 * Input:
 * ["FileSystem","createPath","createPath","get","createPath","get"]
 * [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
 * Output:
 * [null,true,true,2,false,-1]
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.createPath("/leet", 1); // return true
 * fileSystem.createPath("/leet/code", 2); // return true
 * fileSystem.get("/leet/code"); // return 2
 * fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
 * fileSystem.get("/c"); // return -1 because this path doesn't exist.
 */
/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */
public class FileSystem
{
	boolean isNewPath = false;
	int value = -1;
	Map<String, FileSystem> innerTries;

	public FileSystem() {
		innerTries = new HashMap<>();
	}

	public boolean createPath(String path, int value) {

		String[] pathArr = path.replaceFirst("^/", "").split("/");
		List<String> arrayOfPath = Arrays.asList(pathArr);
		Queue<String> queue = new ArrayDeque<>(arrayOfPath);

		return createPath(queue, value);
	}

	private boolean createPath(Queue<String> path, int value)
	{
		if (path.isEmpty()) {
			if (this.value == -1)
			{
				this.value = value;
				return true;
			}
			else {
				return false;
			}
		}
		String firstPath = path.remove(); //now queue size = 0
		if (!innerTries.containsKey(firstPath))
		{
			if (path.size() == 0)
			{
				isNewPath = true;
				innerTries.put(firstPath, new FileSystem());
			}
			else
			{
				return false;
			}
		}
		return innerTries.get(firstPath).createPath(path, value);
	}

	public int get(String path) {
		if (path.length() == 0) {
			return this.value;
		}

		String[] pathArr = path.replaceFirst("^/", "").split("/");
		List<String> arrayOfPath = Arrays.asList(pathArr);

		Queue<String> queue = new ArrayDeque<String>(arrayOfPath);
		return get(queue);
	}

	private int get(Queue<String> queuePath)
	{
		if (queuePath.isEmpty()) {
			return this.value;
		}
		String firstPath = queuePath.remove();
		if (!innerTries.containsKey(firstPath))
		{
			return -1;
		}
		else
		{
			return innerTries.get(firstPath).get(queuePath);
		}
	}

	public static void main(String ... args)
	{
		FileSystem trie = new FileSystem();

		String path = "/leet";
		int value = 1;
		boolean create = trie.createPath(path, value);
		System.out.println(create); //true
		int result = trie.get(path);
		System.out.println(result); //1

		path = "/leet/code";
		value = 2;
		create = trie.createPath(path, value);
		System.out.println(create); //true
		result = trie.get(path);
		System.out.println(result); //2

		create = trie.createPath(path, 3);
		System.out.println(create); //false
		result = trie.get(path);
		System.out.println(result); //2


	}

}
