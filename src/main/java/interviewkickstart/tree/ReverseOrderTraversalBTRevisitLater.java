package interviewkickstart.tree;

import java.util.ArrayList;

public class ReverseOrderTraversalBTRevisitLater
{
	static ArrayList<ArrayList<Integer>> levelResult = new ArrayList<>();
	static int level = 0;

	static ArrayList<ArrayList<Integer>> reverse_level_order_traversal(BinaryTreeNode root) {

		ArrayList<ArrayList<Integer>> result = new ArrayList<>();

		ArrayList<Integer> partial = new ArrayList<>();
		if (root == null)
		{
			return result;
		}
		ArrayList<ArrayList<Integer>> left = reverse_level_order_traversal(root.left);
		System.out.println("Left: "+left);
		if (left.size() != 0)
		{
			ArrayList<Integer> partialLeft = left.get(0);
			partial.addAll(partialLeft);
		}

		ArrayList<ArrayList<Integer>> right = reverse_level_order_traversal(root.right);
		System.out.println("Right: "+right);
		if (right.size() != 0)
		{
			ArrayList<Integer> partialRight = right.get(0);
			partial.addAll(partialRight);
		}
		System.out.println("partial: "+partial);
		result.add(partial);
		System.out.println("result: "+result);


		if (root != null)
		{
			partial.clear();
			partial.add(root.value);
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
		ArrayList<ArrayList<Integer>> result = reverse_level_order_traversal(b);
		System.out.println(result);
	}
}
