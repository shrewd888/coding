package crackingcodinginterview.chap2;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesUnsortedLinkedList
{
	public static Node deleteDuplicates(Node head)
	{
		Node node = head;
		Node current = node;

		if (head == null) return null;
		Set<Integer> dataSet = new HashSet<>();
		while (head != null)
		{
			int data = head.data;
			if (dataSet.contains(data))
			{
				node.next = head.next;
			}
			else
			{
				dataSet.add(data);
				node = head;
			}
			head = head.next;
		}
		return current;
	}

	//same as above but no current node to return
	//as a result, it print the number where the last pointer point to
	public static Node deleteDuplicates1(Node head)
	{
		Node previous = null;
		if (head == null) return null;
		Set<Integer> dataSet = new HashSet<>();
		while (head != null)
		{
			int data = head.data;
			if (dataSet.contains(data))
			{
				previous.next = head.next;
			}
			else
			{
				dataSet.add(data);
				previous = head;
			}
			head = head.next;
		}
		return previous;
	}

	public static void main(String ... args){
		Node first = new Node(10);
		Node head = first;
		Node second = null;

		for (int i=2; i<4; i++)
		{
			second = new Node(i);
			first.next = second;
			first = second;
		}
		second = new Node(2);
		first.next = second;
		first = second;

//		second = new Node(4);
//		first.next = second;

		//before removing duplicates
		System.out.println(head.printForward());

		System.out.println("Removing duplicates...");
		Node after = deleteDuplicates(head);
		System.out.println(after.printForward());

		System.out.println("*************************************************");

		Node head1 = new Node(11);
		System.out.println(head1.printForward());
		System.out.println("Removing duplicates...");
		Node after1 = deleteDuplicates(head1);
		System.out.println(after1.printForward());


	}
}
