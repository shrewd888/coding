package leetcode;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/
 * 146. LRU Cache
 *
 *
 */
public class LRUCache
{
	static int capacity;
	Map<Integer, Integer> map = new LinkedHashMap<>();

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		if (!map.containsKey(key)) return -1;
		//remove then reinsert at the back
		Integer value = map.get(key);
		map.remove(key);
		map.put(key, value);
		return value;
	}

	public void put(int key, int value) {

		if (map.containsKey(key))
		{
			map.remove(key);
		}
		//remove the least recently used, which is the front
		else if (map.size() == capacity)//!map.containsKey(key)
		{
			Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
			if (iterator.hasNext()) {
				Map.Entry<Integer, Integer> next = iterator.next();
				map.remove(next.getKey());
			}
		}
		map.put(key, value);
	}

	//Runtime 130 ms beats 29%
	public static void main(String ... args)
	{
		LRUCache obj = new LRUCache(2);
		obj.put(1,1);
		obj.put(2,2);
		Integer val = obj.get(1);
		System.out.println(val); //1

		obj.put(3,3);
		val = obj.get(2);
		System.out.println(val);//-1

		obj.put(4,4);
		val = obj.get(1);
		System.out.println(val);//-1

		val = obj.get(3);
		System.out.println(val);//3

		val = obj.get(4);
		System.out.println(val);//4

		/***/
		obj = new LRUCache(2);
		val = obj.get(2);
		System.out.println(val);//-1
		obj.put(2,6);
		val = obj.get(1);
		System.out.println(val); //-1

		obj.put(1,5);
		obj.put(1,2);
		val = obj.get(1);
		System.out.println(val);//2

		val = obj.get(2);
		System.out.println(val);//6

		/***/
		obj = new LRUCache(2);
		obj.put(2,1);
		obj.put(1,1);
		obj.put(2,3);
		obj.put(4,1);
		val = obj.get(1);
		System.out.println(val);//1
	}
}
