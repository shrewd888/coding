package interview.disney;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Interview 1st round 10/27/2022 with Disney Stream.
 * Interviewer: Bay Pavlick - Lead Software Engineer
 *
 * Question Description:
 * Function overloading or method overloading allows one to create multiple functions of the same
 */
public class Chat
{
		List<String> members;
		List<Integer> files;
		HashMap<Integer, String> message;

		public Chat()
		{
			this.members = new ArrayList<>();
			this.files = new ArrayList<>();
			this.message = new HashMap<>();
		}

		void add(String[] names)
		{
			for (String name : names)
			{
			members.add(name);
			}
		}

		void add (int[] ids)
		{
		for (int id : ids)
		{
		files.add(id);
		}
		}

		void add(int id, String newMessage)
		{
		message.put(id, newMessage);
		}

		void remove(String[] names)
		{
			for (String name : names)
			{
				if (!members.remove(name))
				{
					System.out.println("Member with name "+name+ " does not exist");
				}
			}
		}

		void remove(int[] ids)
		{
			for (int id : ids)
			{
				if (!files.remove((Integer) id))
				{
					System.out.println("File with id "+id+" does not exist");
				}
			}
		}

		void remove(int id)
		{
			if (!message.containsKey(id))
			{
				System.out.println("Message with id "+id+" does not exist");
			}
			else {
				message.remove(id);
			}
		}

		//this is written by Bay Pavlick - Lead Software Engineer at Disney
		public void printConversation() {
			String memberListing = members.stream().map(Object::toString).collect(Collectors.joining( " " ));
			String messageListing = message.values().stream().map(message -> "\'" + message.toString() + "\'").collect(Collectors.joining( " " ));

			System.out.println("Total number of members in the conversation are " + members.size());
			System.out.println("The names of these members are " + memberListing);
			System.out.println("Total number of files in the conversation are " + files.size());
			System.out.println("The messages in the conversation are " + messageListing);
		}



	public static void main(String args[] ) throws Exception {
		Scanner sc = new Scanner(System.in);

		String[] lineArray = sc.nextLine().split(" ");

		Chat newChat = new Chat();
		newChat.add(lineArray);

		lineArray = sc.nextLine().split(" ");

		int[] files = new int[lineArray.length];
		for (int i=0; i < lineArray.length; i++) {
			files[i] = Integer.parseInt(lineArray[i]);
		}
		newChat.add(files);

		String line = sc.nextLine();
		int numberOfMessages = Integer.parseInt(line);

		for (int i=0; i < numberOfMessages; i++) {
			line = sc.nextLine();
			newChat.add(i, line);
		}

		lineArray = sc.nextLine().split(" ");
		newChat.remove(lineArray);

		lineArray = sc.nextLine().split(" ");
		int[] filesRemoved = new int[lineArray.length];
		for (int i=0; i < lineArray.length; i++) {
			filesRemoved[i] = Integer.parseInt(lineArray[i]);
		}
		newChat.remove(filesRemoved);

		lineArray = sc.nextLine().split(" ");
		for (int i=0; i < lineArray.length; i++) {
			newChat.remove(Integer.parseInt(lineArray[i]));
		}

		newChat.printConversation();
	}
}