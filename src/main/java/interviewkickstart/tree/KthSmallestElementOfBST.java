package interviewkickstart.tree;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallestElementOfBST
{

	static Integer kth_smallest_element(BinaryTreeNode root, Integer k) {
		Queue<BinaryTreeNode> q = new PriorityQueue<>(new Comparator<BinaryTreeNode>()
		{
			@Override
			public int compare(BinaryTreeNode o1, BinaryTreeNode o2)
			{
				return o1.value - o2.value;
			}
		});
		q.add(root);


		return 0;
	}


	public static void main(String ... args) {
		BinaryTreeNode b = new BinaryTreeNode(0);
		BinaryTreeNode b1 = new BinaryTreeNode(1);
		BinaryTreeNode b2 = new BinaryTreeNode(2);
		b.left = b1;
		b.right = b2;
		BinaryTreeNode b1L = new BinaryTreeNode(3);
		BinaryTreeNode b1R = new BinaryTreeNode(4);
		b1.left = b1L;
		b1.right = b1R;
		Integer result = kth_smallest_element(b, 2);
		System.out.println(result); //[3, 1, 4, 0, 2]
	}
}


