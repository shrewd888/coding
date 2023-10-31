package interviewkickstart.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given k linked lists where each one is sorted in the ascending order,
 * merge all of them into a single sorted linked list.
 * Example
 * {
 * "lists": [
 * [1, 3, 5],
 * [3, 4],
 * [7]
 * ]
 * }
 * Output:
 * [1, 3, 3, 4, 5, 7]
 */
/**
 * Time Complexity: O(nk log k)
 * Auxiliary Space: O(k) -> minHeap of size k
 * Output space does not have extra auxiliary space
 */

/**
 * 23. Merge k Sorted Lists
 */
public class MergeKSortedLinkedList
{
	static LinkedListNode merge_k_lists(ArrayList<LinkedListNode> lists) {

		if (lists == null || lists.size() == 0) return null;

		Queue<Pair<Integer, LinkedListNode>> minHeap = new PriorityQueue<>(new Comparator<Pair<Integer, LinkedListNode>>()
		{
			@Override
			public int compare(Pair<Integer, LinkedListNode> o1, Pair<Integer, LinkedListNode> o2)
			{
				return o1.key - o2.key;
			}
		});

		for (int i=0; i < lists.size(); i++)
		{
			LinkedListNode node = lists.get(i);
			if (node != null)
			{
				minHeap.add(new Pair<Integer, LinkedListNode>(node.value, node));
			}
		}
		//initialize output list
		LinkedListNode head = new LinkedListNode(0); //dummy node, random 0 value
		LinkedListNode tail = head;

		//repeatedly extract minimum element from the heap & append it to the output list
		while (!minHeap.isEmpty())
		{
			Pair<Integer, LinkedListNode> element = minHeap.remove();
			LinkedListNode p = element.getValue();
			tail.next = p;
			tail = p;
			p = p.next; //p in p.next can't be null because we check null when adding to minHeap
			tail.next = null;
			if (p != null)
			{
				minHeap.add(new Pair(p.value, p));
			}
		}
		head = head.next;
		return head;
	}

	public static class Pair<K,V>
	{
		K key;
		V value;

		Pair(K k, V v)
		{
			this.key = k;
			this.value = v;
		}

		K getKey()
		{
			return key;
		}

		V getValue()
		{
			return value;
		}
	}

	public static void main(String ... args) {
		ArrayList<LinkedListNode> nodes = new ArrayList<>();
		LinkedListNode  result = merge_k_lists(nodes);
		System.out.println(result);
	}
}
