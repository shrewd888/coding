package interviewkickstart.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class ReverseOrderTraversalBTIterative
{
	static ArrayList<ArrayList<Integer>> reverse_level_order_traversal(BinaryTreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		ArrayList<Integer> level = new ArrayList<>();
		if (root == null)
		{
			return result;
		}
		Queue<BinaryTreeNode> queue = new LinkedList<>();
		queue.add(root);

		ArrayList<BinaryTreeNode> nodeList = new ArrayList<>();

		while (!queue.isEmpty())
		{
			BinaryTreeNode node = queue.poll();
			level.add(node.value);

			if (node.left != null)
			{
				nodeList.add(node.left);
			}
			if (node.right != null)
			{
				nodeList.add(node.right);
			}
			if (queue.isEmpty())
			{
				result.add(level);
				//reset
				level = new ArrayList<>();
				for (BinaryTreeNode n : nodeList)
				{
					queue.add(n);
				}
				//reset
				nodeList = new ArrayList<>();
			}
		}
		Collections.reverse(result);
		return result;
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
		ArrayList<ArrayList<Integer>> result = reverse_level_order_traversal(b);
		System.out.println(result);//[[3, 4], [1, 2], [0]]
	}
}
