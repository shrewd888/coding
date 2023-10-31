package interviewkickstart.tree;

import java.util.ArrayList;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-210478-518289-249-1577
 * Inorder Traversal Of A Binary Tree
 * Given a binary tree, return the inorder traversal of its node values.
 */
public class InorderTraversalRecursive
{
	static ArrayList<Integer> inorder(BinaryTreeNode root) {
		ArrayList<Integer> result = new ArrayList<>();
		if (root == null)
		{
			return result;
		}
		ArrayList<Integer> resultLeft = inorder(root.left);
		result.addAll(resultLeft);
		result.add(root.value);

		ArrayList<Integer> resultRight = inorder(root.right);
		result.addAll(resultRight);
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
		ArrayList<Integer> result = inorder(b);
		System.out.println(result); //[3, 1, 4, 0, 2]
	}
}
