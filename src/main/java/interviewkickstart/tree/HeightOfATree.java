package interviewkickstart.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-282811-541520-53-331
 * Height Of A Tree
 * Given a tree, find its height: number of edges in the longest path from the root to any node.
 */
public class HeightOfATree
{

	static Integer find_height(TreeNode root)
	{
		// Write your code here.
		if (root == null)
		{
			return 0;
		}
		Integer result = helper(root);

		return result;
	}

	static Integer helper(TreeNode node) {
		int level = 0;
		ArrayList<TreeNode> nodes = node.children;
		if (nodes != null && nodes.size() > 0)
		{
			for (TreeNode childNode : nodes)
			{
				level = Math.max(level, 1 + helper(childNode));
			}
		}
		return level;
	}

	public static void main(String ... args) {
		TreeNode r = new TreeNode(1);
		TreeNode l11 = new TreeNode(2);
		TreeNode l12 = new TreeNode(3);
		TreeNode l13 = new TreeNode(5);
		TreeNode l21 = new TreeNode(4);
		TreeNode l31 = new TreeNode(6);
		TreeNode l32 = new TreeNode(7);

		ArrayList<TreeNode> nodeList = new ArrayList<>();
		nodeList.add(l11);
		nodeList.add(l12);
		nodeList.add(l13);
		r.children = nodeList;

		ArrayList<TreeNode> nodeList1 = new ArrayList<>();
		nodeList1.add(l21);
		l13.children = nodeList1;

		ArrayList<TreeNode> nodeList2 = new ArrayList<>();
		nodeList2.add(l31);
		l12.children = nodeList2;

		ArrayList<TreeNode> nodeList3 = new ArrayList<>();
		nodeList3.add(l32);
		l31.children = nodeList3;

		Integer result = find_height(r);
		System.out.println(result);
	}
}
