package interviewkickstart.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class ZigZagLevelOrderTraversal
{
	static ArrayList<ArrayList<Integer>> zigZagLevelOrderTraversal(BinaryTreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		ArrayList<Integer> level = new ArrayList<>();
		if (root == null)
		{
			return result;
		}
		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.add(root);
		boolean flip = false;
		while (!q.isEmpty())
		{
			Queue<BinaryTreeNode> internalQ = new LinkedList<>();
			while (!q.isEmpty())
			{
				BinaryTreeNode node = q.poll();
				level.add(node.value);
				if (node.left != null)
				{
					internalQ.add(node.left);
				}
				if (node.right != null)
				{
					internalQ.add(node.right);
				}
			}
			if (flip)
			{
				Collections.reverse(level);
			}
			result.add(level);
			//reset
			flip = !flip;
			level = new ArrayList<>();
			q.addAll(internalQ);
		}
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
		ArrayList<ArrayList<Integer>> result = zigZagLevelOrderTraversal(b);
		System.out.println(result);
	}
}
