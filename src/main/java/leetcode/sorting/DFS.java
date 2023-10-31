package leetcode.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class DFS
{
	public List<List<Integer>> levelOrder(TreeNode root) {

		List<List<Integer>> result = new ArrayList<>();
		if (root == null) return result;

		Queue<TreeNode> q = new PriorityQueue<>();
		q.add(root);

		while (!q.isEmpty() )
		{
			List<Integer> level = new ArrayList<>();
			for (int i=0; i < q.size(); i++)
			{
				TreeNode t = q.remove();
				level.add(t.val);
				if (t.left != null)
				{
					q.add(t.left);
				}
				if (t.right != null)
				{
					q.add(t.right);
				}
			}
			result.add(level);
		}
		return result;
	}


	public static void main(String ... args) {

	}
}
