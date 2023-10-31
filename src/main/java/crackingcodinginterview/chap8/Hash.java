package crackingcodinginterview.chap8;

import java.util.LinkedList;
import java.util.List;

import interviewkickstart.sorting.LinkedListNode;

/**
 * Design and implement a hash table which uses chaining (linked lists) to handle collisions
 */
public class Hash<K,V>
{
	private final int MAX_SIZE = 10;
	List<Cell<K,V>>[] items;

	public Hash()
	{
		items = new LinkedList[MAX_SIZE];
	}

	public int hashCodeOfKey(K key)
	{
		//any hash function here
		return key.toString().length() % items.length;
	}

	//Hashtable is thread safe, HashMap is not
	public synchronized void put(K key, V value)
	{
		int index = hashCodeOfKey(key);
		if (items[index] == null)
		{
			items[index] = new LinkedList<Cell<K,V>>();
		}
		List<Cell<K,V>> nodes = items[index];
		for (Cell<K,V> cell : nodes)
		{
			//replace the old key's value with new key's value if both key are equals
			K existingKey = cell.getKey();
			if (cell.equivalent(key)) //collided
			{
				nodes.remove(cell);
				break;
			}
		}
		Cell<K,V> newItem = new Cell<>(key, value);
		nodes.add(newItem);
	}

	public V get(K key)
	{
		int x = hashCodeOfKey(key);
		List<Cell<K,V>> nodes = items[x];
		for (Cell<K,V> cell : nodes)
		{
			if (cell.equivalent(key))
			{
				return cell.getValue();
			}
		}
		return null;
	}
}
