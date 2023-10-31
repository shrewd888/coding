package interview.doordash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

/**
 * https://leetcode.com/discuss/interview-question/2431116/DoorDash-or-US-or-Phone-Screen-or-Shortest-path-between-two-friends
 *
 * Question:
 * Given a adjucency list: (users -> list of user's friends), return the shortest path between two users. There is no weight associated with any edge. Fill the logic in findShortestPath method.
 *
 * Problem statement:
 * /*
 * Implement this method:
 * List findShortestPath(Map<Person, List> connections, int source, int destination){
 * ...
 * }
 */
public class ShortestPathBetweenTwoFriends
{
	public List<Person> findShortestPath(Map<Person, List<Person>> connections, Person source, Person destination){
		List<Person> shortestPath = new ArrayList<>();
		if (source.equals(destination)) {
			return shortestPath;
		}

		List<Person> visited = new ArrayList<>();
		Map<Person, Person> parentChild = new HashMap<>();
		boolean reachDestination = false;

		Queue<Person> q = new LinkedList<>();
		q.add(source);
		visited.add(source);
		parentChild.put(source, null);

		while (!q.isEmpty() && !reachDestination)
		{
			Person p = q.poll();
			List<Person> friends = connections.get(p);
			for (Person friend : friends)
			{
				if (!visited.contains(friend))
				{
					visited.add(friend);
					parentChild.put(friend, p); //friend's parent is p

					//as soon as we reach destination we are done
					if (destination.equals(friend))
					{
						reachDestination = true;
						break;
					}
					q.add(friend);
				}
			}
		}
		//put path into the list, find the parent of destination and print backward
		while (!source.equals(destination))
		{
			shortestPath.add(destination);
			Person parent = parentChild.get(destination);
			destination = parent; //from destination to source
		}
		shortestPath.add(source);
		//reverse (from source to destination) and return
		Collections.reverse(shortestPath);
		return shortestPath;
	}

	public static class Person
	{
		Integer id;
		String name;

		public Person() {};

		public Person(Integer id, String name)
		{
			this.id = id;
			this.name = name;
		}

		public String toString()
		{
			//return "(" + this.name + "," + this.id + ")";
			return this.name;
		}

		@Override
		public boolean equals(Object o)
		{
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Person person = (Person) o;
			return Objects.equals(id, person.id) && Objects.equals(name, person.name);
		}

		@Override
		public int hashCode()
		{
			return Objects.hash(id, name);
		}
	}

	public static void main(String ... args)
	{
		ShortestPathBetweenTwoFriends pathBetweenTwoFriends = new ShortestPathBetweenTwoFriends();
		Person a = new Person(1, "A");
		Person b = new Person(2, "B");
		Person c = new Person(3, "C");
		Person d = new Person(4, "D");
		Person e = new Person(5, "E");
		Person f = new Person(6, "F");

		Map<Person, List<Person>> connections = new HashMap<>();
		List<Person> friends = Arrays.asList(b, c);
		connections.put(a, friends);
		connections.put(b, Arrays.asList(c));
		connections.put(c, Arrays.asList(e));
		connections.put(e, Arrays.asList(f));

		List<Person> shortestPath = pathBetweenTwoFriends.findShortestPath(connections, a, b);
		System.out.println(shortestPath);//[A, B]

		shortestPath = pathBetweenTwoFriends.findShortestPath(connections, a, c);
		System.out.println(shortestPath);//[A, C]

		shortestPath = pathBetweenTwoFriends.findShortestPath(connections, a, f);
		System.out.println(shortestPath);//[A, C, E, F]

		shortestPath = pathBetweenTwoFriends.findShortestPath(connections, a, e);
		System.out.println(shortestPath);//[A, C, E]

		ShortestPathBetweenTwoFriends pathBetweenTwoFriends1 = new ShortestPathBetweenTwoFriends();
		Person a1 = new Person(1, "A");
		Person b1 = new Person(2, "B");
		Person c1 = new Person(3, "C");
		Person e1 = new Person(5, "E");

		Map<Person, List<Person>> connections1 = new HashMap<>();
		List<Person> friends1 = Arrays.asList(b1, c1, e1);
		connections1.put(a1, friends1);
		connections1.put(b1, Arrays.asList(c1));

		List<Person> shortestPath1 = pathBetweenTwoFriends.findShortestPath(connections1, a1, e1);
		System.out.println(shortestPath1);//[A, E]
	}
}
