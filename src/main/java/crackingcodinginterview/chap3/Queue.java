package crackingcodinginterview.chap3;

import crackingcodinginterview.chap2.Node;

/**
 * Queue: FIFO
 * Implement using LinkedList with a new item added to the tail of the linked list
 */
public class Queue
{
	Node first, last;

	//put into a queue (add)
	void enqueue(Object item)
	{
		if (first==null)
		{
			first = new Node((Integer) item);
			last =first;
		}
		else
		{
			last.next = new Node((Integer)item);
			last = last.next;
		}
	}

	//extract/remove from the queue (poll)
	Object dequeue()
	{
		if (first != null)
		{
			Object item = first.data;
			first = first.next;
			return item;
		}
		return null;
	}
}
