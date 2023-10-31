package interviewkickstart.tree;

import java.util.ArrayList;

public class PostOrderTraversalRecursive
{
	static ArrayList<Integer> postorder(BinaryTreeNode root) {
		ArrayList<Integer> result = new ArrayList<>();

		if (root == null)
		{
			return result;
		}
		ArrayList<Integer> resultLeft = postorder(root.left);
		result.addAll(resultLeft);

		ArrayList<Integer> resultRight = postorder(root.right);
		result.addAll(resultRight);

		result.add(root.value);
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
		ArrayList<Integer> result = postorder(b);
		System.out.println(result); //[3, 4, 1, 2, 0]
	}
}
