package interview.doordash;

/**
 * 124. Binary Tree Maximum Path Sum
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence
 * has an edge connecting them. A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 */
/**
 * https://leetcode.com/discuss/interview-question/2220689/DoorDash-or-Phone-Screen
 * Had a problem very similar to binary tree max path sum (linked below).
 * It is the same problem with one added constraint,
 * which is that the maximum path must start and end at a leaf node.
 */
public class BinaryTreeMaxPathSum
{
	static int max_sum = Integer.MIN_VALUE;

	public static int maxPathSum(TreeNode node) {
		max_gain(node);
		return max_sum;
	}

	public static int max_gain(TreeNode node) {
		if (node == null) return 0;
		// max sum on the left and right sub-trees of node
		int left_gain = Math.max(max_gain(node.left), 0); //because there are negative values
		int right_gain = Math.max(max_gain(node.right), 0); //because there are negative values

		// the price to start a new path where `node` is a highest node
		int price_newpath = node.val + left_gain + right_gain;

		// update max_sum if it's better to start a new path
		max_sum = Math.max(max_sum, price_newpath);

		// for recursion :
		// return the max gain if continue the same path/length
		int maxGain = node.val + Math.max(left_gain, right_gain);
		return maxGain;
	}

	public static void main(String ... args)
	{
		//1
		TreeNode l1 = new TreeNode(9);
		TreeNode r1 = new TreeNode(20);
		TreeNode root = new TreeNode(-10, l1, r1);

		TreeNode l2 = new TreeNode(15);
		r1.left = l2;
		TreeNode r2 = new TreeNode(7);
		r1.right = r2;

		int maxPathSum = maxPathSum(root);
		System.out.println(maxPathSum); //42

		//2
		TreeNode l11 = new TreeNode(19);
		TreeNode r11 = new TreeNode(20);
		TreeNode root1 = new TreeNode(-10, l11, r11);

		TreeNode l21 = new TreeNode(15);
		r11.left = l21;
		TreeNode r21 = new TreeNode(7);
		r11.right = r21;

		TreeNode r22 = new TreeNode(-5);
		r21.right = r22;
		int maxPathSum1 = maxPathSum(root1);
		System.out.println(maxPathSum1); //44

		//3
		TreeNode l111 = new TreeNode(19);
		TreeNode r111 = new TreeNode(20);
		TreeNode root11 = new TreeNode(-10, l111, r111);

		TreeNode l211 = new TreeNode(15);
		r111.left = l211;
		TreeNode r211 = new TreeNode(-7);
		r111.right = r211;

		TreeNode l212 = new TreeNode(-2);
		l211.left = l212;
		TreeNode r212 = new TreeNode(-1);
		l211.right = r212;

		TreeNode r221 = new TreeNode(-5);
		r211.right = r221;
		int maxPathSum2 = maxPathSum(root11);
		System.out.println(maxPathSum2); //44
		//if must include leaf nodes: maxPathSum = 44-1 = 43
	}
}
