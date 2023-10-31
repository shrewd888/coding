package interviewkickstart.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-210478-518289-249-1567
 * Level Order Traversal Of A Binary Tree
 * Given a binary tree, list the node values level by level from left to right.
 *
 */
public class LevelOrderTraversalBT
{
	static ArrayList<ArrayList<Integer>> level_order_traversal(BinaryTreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if (root == null)
		{
			return result;
		}
		Queue<BinaryTreeNode> q = new LinkedList<>();
		q.add(root);

		List<BinaryTreeNode> treeNodes = new ArrayList<>();

		while (!q.isEmpty())
		{
			BinaryTreeNode treeNode = q.poll();
			//list of treeNodes at each level
			treeNodes.add(treeNode);
			if (q.isEmpty())
			{
				ArrayList<Integer> level = new ArrayList<>();
				for (BinaryTreeNode t : treeNodes)
				{
					//put value into a list
					level.add(t.value);
					BinaryTreeNode left = t.left;
					BinaryTreeNode right = t.right;
					if (left != null)
					{
						q.add(left);
					}
					if (right != null)
					{
						q.add(right);
					}
				}
				treeNodes = new ArrayList<>();
				result.add(level);
			}
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
		ArrayList<ArrayList<Integer>> result = level_order_traversal(b);
		System.out.println(result); //[[0], [1, 2], [3, 4]]
	}
}
