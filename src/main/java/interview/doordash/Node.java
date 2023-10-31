package interview.doordash;

import java.util.List;
import java.util.Objects;

public class Node
{
	public String key;
	public int value;
	public boolean active;
	public List<Node> children;

	public Node() {}

	public Node(String key, int value)
	{
		this.key = key;
		this.value = value;
	}

	public Node(String key, int value, boolean active)
	{
		this.key = key;
		this.value = value;
		this.active = active;
	}

	public Node(String key, int value, List<Node> children)
	{
		this(key, value);
		this.children = children;
	}

	public Node(String key, int value, boolean active, List<Node> children)
	{
		this(key, value, children);
		this.active = active;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Node node = (Node) o;
//		boolean a = value == node.value && active == node.active && key.equals(node.key);
//		boolean b = this.children.stream()
//				.allMatch(n ->
//						n.value == node.value && active == node.active && key.equals(node.key));
//		return a && b;

		return value == node.value && active == node.active && key.equals(node.key);
				//&& Objects.equals(children, node.children);
	}


	@Override
	public int hashCode()
	{
		return Objects.hash(key, value, active, children);
	}

	public String toString() {
		return this.key + ", " + this.value + ", " + this.active + ", " + this.children;
	}
}

