package crackingcodinginterview.tree;

public class BinaryTreeBalanced
{
	public static int getHeight(TreeNode root)
	{
		if (root == null)
		{
			return 0; //Base case
		}
		return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
	}

	public static boolean isBalanced(TreeNode root)
	{
		if (root == null) return true;
		int heightDiff = getHeight(root.left) - getHeight(root.right);
		if (Math.abs(heightDiff) > 1)
		{
			return false;
		}
		else
		{
			return isBalanced(root.left) && isBalanced(root.right);
		}
	}

	public static void main(String[] args)
	{
		TreeNode b = new TreeNode(10);
		b.insertInOrderOfBST(7);
		b.insertInOrderOfBST(12);
		b.insertInOrderOfBST(8);
		b.insertInOrderOfBST(5);

		b.print();

		boolean balance = isBalanced(b);
		System.out.println("balance :"+balance);
	}
}
