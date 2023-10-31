package interviewkickstart.tree;

import java.util.ArrayList;

public class TreeNode
{
	Integer value;
	ArrayList<TreeNode> children;

	TreeNode(Integer value) {
		this.value = value;
		this.children = new ArrayList(3);
	}
}
