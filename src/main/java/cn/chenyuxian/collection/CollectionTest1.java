package cn.chenyuxian.collection;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionTest1 {
	public static void main(String[] args) {
		Collection<String> list = new ArrayList<>();
		list.add("a");
		list.add("a");
		list.add("b");
		list.add("b");
		list.add("c");
		System.out.println("a:" + listTest(list, "xxx"));
	}
	
	public static int listTest(Collection<String> list, String ch) {
		int count = 0;
		for(String c : list) {
			if(c.equals(ch))
				count++;
		}
		return count;
	}
}
