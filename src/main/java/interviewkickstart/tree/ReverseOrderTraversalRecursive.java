package interviewkickstart.tree;

import java.util.ArrayList;
import java.util.Collections;

public class ReverseOrderTraversalRecursive
{

	static ArrayList<ArrayList<Integer>> levels = new ArrayList<ArrayList<Integer>>();

	static ArrayList<ArrayList<Integer>> reverse_level_order_traversal(BinaryTreeNode root)
	{
		if (root == null) return levels;
		helper(root, 0);
		System.out.println("Result Levels before reverse: "+levels);
		Collections.reverse(levels);
		System.out.println("Result Levels after reverse: "+levels);
		return levels;
	}

	static void helper(BinaryTreeNode node, int level) {
		// start the current level
		if (levels.size() == level)
		{
			System.out.println();
			System.out.println("Levels: "+levels);
			System.out.println("Levels sz: "+levels.size());
			levels.add(new ArrayList<>());
			System.out.println("Levels: "+levels);
			System.out.println("Levels sz: "+levels.size());
		}
		System.out.println();
		// append the current node value
//		ArrayList<Integer> levelNode = levels.get(level);
//		levelNode.add(node.value);
		levels.get(level).add(node.value);
		System.out.println("levels: "+levels);

		// process child nodes for the next level
		if (node.left != null)
			helper(node.left, level+1);
		if (node.right != null)
			helper(node.right, level+1);
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
