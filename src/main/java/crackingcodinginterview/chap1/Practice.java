package crackingcodinginterview.chap1;

import java.util.ArrayList;
import java.util.List;

public class Practice
{
	private int a;
	private String b;

	public Practice() {}

	public Practice(int a, String b)
	{
		this.a = a;
		this.b = b;
	}

	public static void main(String ... args){
		Practice p = new Practice(100, "hello");
		Practice p1 = new Practice(200, "hello1");
		Practice p2 = new Practice(300, "hello2");
		Practice p3 = new Practice(400, "hello3");
		Practice p4 = new Practice(500, "hello4");

		List<Practice> first = new ArrayList<>();
		first.add(p);
		first.add(p1);

		List<Practice> second = first;
		second.add(p2);

		first.add(p3);

		List<Practice> third = new ArrayList<>(first);
		third.add(p4);

		System.out.println("first list: "+first+ ", length: "+first.size());
		System.out.println("second list: "+second+ ", length: "+second.size());
		System.out.println("third list: "+third+ ", length: "+third.size());
	}
}
