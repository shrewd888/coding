package interview.doordash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/discuss/interview-question/1367130/doordash-phone-interview/1026887
 */
public class DoorDashMenuNodes
{


	public static int countDifferentNodes(Node existingMenu, Node newMenu)
	{
		int count = 0;
		if (existingMenu == null && newMenu == null) return count;
		//if (existingMenu == null || newMenu == null || !equals(existingMenu, newMenu)) count++;
		if (existingMenu == null || newMenu == null || !existingMenu.equals(newMenu))
			count++;

		Map<String, Node> child1 = mapChildren(existingMenu);
		Map<String, Node> child2 = mapChildren(newMenu);

		for (String k : child1.keySet()) {
			count+= countDifferentNodes(child1.get(k), child2.getOrDefault(k, null));
		}

		for (String k : child2.keySet()) {
			if (!child1.containsKey(k)) {
				count += countDifferentNodes(null, child2.get(k));
			}
		}
		return count;
	}

	private static Map<String, Node> mapChildren(Node n)
	{
		Map<String, Node> map = new HashMap<>();
		if (n == null)
		{
			return map;
		}
		if (n.children != null)
		{
			for (Node c : n.children)
			{
				map.put(c.key, c);
			}
		}
		return map;
	}

//	public static boolean equals(Node n1, Node n2) {
//		return n2.key.equals(n1.key) && n2.value == n1.value ; //&& n2.active == n1.active;
//	}

	public static void main(String[] args) {

		DoorDashMenuNodes solution = new DoorDashMenuNodes();
		int expectedCount = 0;
		int actualCount = 0;
		/*
		// Example 1
		Node existingMenu1 = new Node("a", 1);
		Node existingMenu1b = new Node("b", 2);
		Node existingMenu1c = new Node("c", 3);
		Node existingMenu1d = new Node("d", 4);
		Node existingMenu1e = new Node("e", 5);
		Node existingMenu1f = new Node("f", 6);
		existingMenu1.children = Arrays.asList(existingMenu1b, existingMenu1c);
		existingMenu1b.children = Arrays.asList(existingMenu1d, existingMenu1e);
		existingMenu1c.children = Arrays.asList(existingMenu1f);

		Node newMenu1 = new Node("a", 1);
		Node newMenu1c = new Node("c", 3);
		Node newMenu1f = new Node("f", 66);
		newMenu1.children = Arrays.asList(newMenu1c);
		newMenu1c.children = Arrays.asList(newMenu1f);

		expectedCount = 4;
		actualCount = solution.countDifferentNodes(existingMenu1, newMenu1);
		System.out.printf("Example 1 : expected=%d, actual=%d%n", expectedCount, actualCount);
		//Example 1 : expected=4, actual=4

		// Example 2
		Node existingMenu2 = new Node("a", 1);
		Node existingMenu2b = new Node("b", 2);
		Node existingMenu2c = new Node("c", 3);
		Node existingMenu2d = new Node("d", 4);
		Node existingMenu2e = new Node("e", 5);
		Node existingMenu2g = new Node("g", 7);
		existingMenu2.children = Arrays.asList(existingMenu2b, existingMenu2c);
		existingMenu2b.children = Arrays.asList(existingMenu2d, existingMenu2e);
		existingMenu2c.children = Arrays.asList(existingMenu2g);

		Node newMenu2 = new Node("a", 1);
		Node newMenu2b = new Node("b", 2);
		Node newMenu2d = new Node("d", 4);
		Node newMenu2e = new Node("e", 5);
		Node newMenu2f = new Node("f", 6);
		Node newMenu2g = new Node("g", 7);
		Node newMenu2h = new Node("h", 8);
		newMenu2.children = Arrays.asList(newMenu2b, newMenu2h);
		newMenu2b.children = Arrays.asList(newMenu2e, newMenu2d, newMenu2f);
		newMenu2h.children = Arrays.asList(newMenu2g);

		expectedCount = 5;
		actualCount = solution.countDifferentNodes(existingMenu2, newMenu2);
		System.out.printf("Example 2 : expected=%d, actual=%d%n", expectedCount, actualCount);
		//Example 2 : expected=5, actual=5
*/
		// Example 3
		Node existingMenu3 = new Node("a", 1);
		Node existingMenu3b = new Node("b", 2);
		Node existingMenu3c = new Node("c", 3);
		Node existingMenu3d = new Node("d", 4);
		existingMenu3.children = Arrays.asList(existingMenu3b);
		existingMenu3b.children = Arrays.asList(existingMenu3c);
		existingMenu3c.children = Arrays.asList(existingMenu3d);

		Node newMenu3 = new Node("a", 1);
		Node newMenu3e = new Node("e", 5);
		Node newMenu3c = new Node("c", 3);
		Node newMenu3d = new Node("d", 4);
		newMenu3.children = Arrays.asList(newMenu3e);
		newMenu3e.children = Arrays.asList(newMenu3c);
		newMenu3c.children = Arrays.asList(newMenu3d);

		expectedCount = 6;
		actualCount = solution.countDifferentNodes(existingMenu3, newMenu3);
		System.out.printf("Example 3 : expected=%d, actual=%d%n", expectedCount, actualCount);
		//Example 3 : expected=6, actual=6
/*
		// Example 4
		Node existingMenu4 = new Node("c", 3);
		Node existingMenu4g = new Node("g", 7);
		existingMenu4.children = Arrays.asList(existingMenu4g);

		Node newMenu4 = new Node("h", 8);
		Node newMenu4g = new Node("g", 7);
		newMenu4.children = Arrays.asList(newMenu4g);

		expectedCount = 4;
		actualCount = solution.countDifferentNodes(existingMenu4, newMenu4);
		System.out.printf("Example 4 : expected=%d, actual=%d%n", expectedCount, actualCount);
		//Example 4 : expected=4, actual=1

		expectedCount = 2;
		Node t1 = new Node("t1", 1, true);
		Node t2 = new Node("t2", 2, false);
		actualCount =  solution.countDifferentNodes(t1, t2);
		System.out.printf("Example 5 : expected=%d, actual=%d%n", expectedCount, actualCount);
		*/

	}
}
