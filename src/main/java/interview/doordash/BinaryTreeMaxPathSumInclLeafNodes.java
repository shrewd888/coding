package interview.doordash;

/**
 * https://leetcode.com/discuss/interview-question/2220689/DoorDash-or-Phone-Screen
 * Had a problem very similar to binary tree max path sum (linked below).
 * It is the same problem with one added constraint,
 * which is that the maximum path must start and end at a leaf node.
 */
/**
 * if a node is a leaf, left and right gain are going to be null.
 *
 * If the left_gain is not null, but right_gain is null, then take left_gain + node.value.
 *
 * If the right_gain is not null, but left_gain is null, then take right_gain + node.value.
 *
 *  If they are both null, then simply take node.value
 */
public class BinaryTreeMaxPathSumInclLeafNodes
{
	static int max_sum = Integer.MIN_VALUE;

	public static int maxPathSumInclLeafs(TreeNode node) {
		max_gain_include_leafs(node);
		return max_sum;
	}

	public static int max_gain_include_leafs(TreeNode node) {
		if (node == null) return 0; //need to change here

		// max sum on the left and right sub-trees of node
		Integer left_gain = null;
		Integer right_gain = null;

		if (node.left != null)
			left_gain = max_gain_include_leafs(node.left);
		if (node.right != null)
			right_gain = max_gain_include_leafs(node.right);

		//the price to start a new path where `node` is a highest node
		//we need start leaf and end leaf that's why we need to check if both are not null
		if (left_gain != null && right_gain !=null)
		{
			int price_newpath = node.val + right_gain + left_gain;
			// update max_sum if it's better to start a new path
			max_sum = Math.max(max_sum, price_newpath);
		}
		// for recursion :
		// return the max gain if continue the same path/length
		Integer maxGain = null;
		if (left_gain != null && right_gain != null)
		{
			 maxGain = node.val + Math.max(left_gain, right_gain);
		}
		else if (left_gain == null && right_gain != null)
		{
			maxGain = node.val + right_gain;
		}
		else if (left_gain != null && right_gain == null)
		{
			maxGain = node.val + left_gain;
		}
		else //both null
		{
			maxGain = node.val;
		}

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

		TreeNode r3 = new TreeNode(-10);
		r2.right = r3;

		int maxPathSum = maxPathSumInclLeafs(root);
		System.out.println(maxPathSum); //34

		//2
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
		int maxPathSum2 = maxPathSumInclLeafs(root11);
		System.out.println(maxPathSum2); //43
		//if must include leaf nodes: maxPathSum = 44-1 = 43
	}
}
