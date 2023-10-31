package interviewkickstart.tree;

import java.util.ArrayList;

public class PreorderTraversalRecursive
{
	static ArrayList<Integer> preorder(BinaryTreeNode root) {
		ArrayList<Integer> result = new ArrayList<>();

		if (root == null)
		{
			return result;
		}
		result.add(root.value);
		ArrayList<Integer> resultLeft = preorder(root.left);
		result.addAll(resultLeft);

		ArrayList<Integer> resultRight = preorder(root.right);
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
		ArrayList<Integer> result = preorder(b);
		System.out.println(result);
	}
}
