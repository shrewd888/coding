package crackingcodinginterview.chap3;

import crackingcodinginterview.chap2.Node;

/**
 * Stack: LIFO
 * Implement using LinkedList
 */
public class Stack
{
	Node top;

	Object pop()
	{
		if (top != null)
		{
			Object item = top.data;
			top = top.next;
			return item;
		}
		return null;
	}

	Object peek()
	{
		return top.data;
	}

	void push(Object item)
	{
		Node t = new Node((Integer) item);//TODO: create inner Node class
		t.next = top;
		top = t;
	}
}
