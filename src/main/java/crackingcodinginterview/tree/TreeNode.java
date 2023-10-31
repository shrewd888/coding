package crackingcodinginterview.tree;

public class TreeNode
{
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;

	public int data;
	public int size = 0; //how many nodes total

	public TreeNode(int data)
	{
		this.data = data;
		this.size = 1;
	}

	public int size()
	{
		return this.size;
	}

	public boolean isLeaf()
	{
		return this.left == null && this.right == null;
	}

	public void setLeftChild(TreeNode t)
	{
		this.left = t;
		if (t != null)
		{
			t.parent = this;
		}
	}

	public void setRightChild(TreeNode r)
	{
		this.right = r;
		if (r != null)
		{
			r.parent = this;
		}
	}

	public void insertInOrderOfBST(int value)
	{
		if (value <= this.data)
		{
			if (this.left == null)
			{
				TreeNode l = new TreeNode(value);
				setLeftChild(l);
			}
			else {
				this.left.insertInOrderOfBST(value);
			}
		}
		else
		{
			if (this.right == null)
			{
				TreeNode r = new TreeNode(value);
				setRightChild(r);
			}
			else {
				this.right.insertInOrderOfBST(value);
			}
		}
		size = size + 1;
	}

	//find TreeNode with value = d, based on BST
	public TreeNode find(int d)
	{
		if (d == this.data) return this;
		if (d < this.data)
		{
			if (this.left != null)
			{
				return this.left.find(d);
			}
			else
			{
				return null;
			}
		}
		else
		{
			if (this.right != null)
			{
				return this.right.find(d);
			}
			else
			{
				return null;
			}
		}
	}

	public void print()
	{
		BTreePrinter.printNode(this);
	}

	public static void main(String[] args)
	{
		TreeNode b = new TreeNode(10);
		b.insertInOrderOfBST(7);
		System.out.println("size: "+b.size);

		b.insertInOrderOfBST(12);
		System.out.println("size: "+b.size);

		b.print();

		System.out.println("find");
		TreeNode f = b.find(7);
		System.out.println(f.data);
		f.print();
	}

}


