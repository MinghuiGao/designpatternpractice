package cn.gaomh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
//		ArrayList<String> list = new ArrayList<String>();// has no remove() method.
		list.add("1gaomh");
		list.add("2gaomh");
		list.add("3");
		System.out.println(list.remove(0));
		System.out.println(list.toString());
		
		/// List中是否可以有null
		List<String> list2 = new ArrayList<String>();
		list2.add("gaomh");
		list2.add("");
		list2.add(null);
		list2.add("aaa");
		
		for(String str : list2){
			System.out.println("---"+ str);
		}
		// ##### List中可以有null，所以在迭代List集合时内部也要非空判断。
		
		
		
		
	}

}
