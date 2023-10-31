package interviewkickstart.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-282811-541520-53-330
 * Balanced BST From A Sorted Array
 * Given a sorted list of distinct integers, build a balanced binary search tree (BST).
 *
 * A BST is called balanced if the number of nodes in the left and right subtrees of every node differs by at most one.
 */
public class BalancedBSTFromSortedArray
{
	static BinaryTreeNode build_balanced_bst(List<Integer> a) {

		// Write your code here.
		int size = a.size();
		int start = 0;
		int end = size;
		int medium = start + (end-start)/2;

		if (size == 0)
		{
			return null;
		}
		BinaryTreeNode root = new BinaryTreeNode(a.get(medium));
		root.left = build_balanced_bst(a.subList(start, medium));
		root.right = build_balanced_bst(a.subList(medium+1, end));

		return root;
	}

	public static void main(String ... args) {
		ArrayList<Integer> array = new ArrayList<>();
		array.add(8);
		array.add(10);
		array.add(12);
		array.add(15);
		array.add(16);
		array.add(20);
		array.add(25);

		BinaryTreeNode result = build_balanced_bst(array);
		System.out.println(result);
	}
}
