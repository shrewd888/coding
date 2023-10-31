package interview.roku;

import java.util.LinkedList;
import java.util.List;

/**
 * My first round interview at Roku 9/29/22 - Kenneth Man
 * Mini System Design: Fault Tolerance?
 *
 * Implement 'put' method of Hashtable
 * What's the difference between Hashtable and HashMap
 * Hashtable: thread synchronization
 * HashMap: no thread, can have null key
 */
public class HashTable {

	List<Integer>[] node;
	int size;

	public HashTable(int n) {
		node = new LinkedList[n];
		this.size = n;
	}

	void put(Integer key, Integer value)
	{
		int index = key % size;//separate function
		synchronized(key) {
			List<Integer> vals = new LinkedList<>();
			vals.add(value);
		}
	}


}