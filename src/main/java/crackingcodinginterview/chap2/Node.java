package crackingcodinginterview.chap2;

public class Node
{
	public Node next;
	public int data;

	public Node(int d)
	{
		this.data = d;
	}

	public void appendToTail(int d)
	{
		Node end = new Node(d);
		Node n = this;
		while (n.next != null)
		{
			n = n.next;
		}
		n.next = end;
	}

	public Node deleteNode(Node head, int d)
	{
		Node n = head;
		if (n.data == d)
		{
			return n.next;
		}
		while (n.next != null)
		{
			if (n.next.data == d)
			{
				n.next = n.next.next;
				return n;
			}
		}
		return n;
	}

	public String printForward()
	{
		if (this.next != null)
		{
			return this.data + "->" + this.next.printForward();
		}
		return Integer.valueOf(this.data).toString();
	}
}
