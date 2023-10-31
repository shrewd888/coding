package interviewkickstart.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversalTree
{
	static ArrayList<ArrayList<Integer>> level_order(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if (root == null)
		{
			return result;
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		List<TreeNode> treeNodes = new ArrayList<>();

		while (!q.isEmpty())
		{
			TreeNode treeNode = q.poll();
			//list of treeNodes at each level
			treeNodes.add(treeNode);
			if (q.isEmpty())
			{
				ArrayList<Integer> level = new ArrayList<>();
				for (TreeNode t : treeNodes)
				{
					//put value into a list
					level.add(t.value);
					for (TreeNode node : t.children)
					{
						if (node != null)
						{
							q.add(node);
						}
					}
				}
				treeNodes = new ArrayList<>();
				result.add(level);
			}
		}
		return result;
	}


	public static void main(String ... args) {
		TreeNode r = new TreeNode(1);
		ArrayList<TreeNode> rchildren = new ArrayList<>();
		TreeNode l1 = new TreeNode(3);
		TreeNode l2 = new TreeNode(4);
		TreeNode l3 = new TreeNode(2);
		rchildren.add(l1);
		rchildren.add(l2);
		rchildren.add(l3);
		r.children = rchildren;

		rchildren = new ArrayList<>();
		TreeNode l21 = new TreeNode(5);
		TreeNode l22 = new TreeNode(6);
		rchildren.add(l21);
		rchildren.add(l22);
		l2.children = rchildren;

		ArrayList<ArrayList<Integer>> result = level_order(r);
		System.out.println(result); //[[1], [3, 4, 2], [5, 6]]
	}
}
