package cn.gaomh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Test {

	public static void main(String[] args) throws ParseException {
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
		String dateTime = "2015-03-18 10:34:10";
//		String month = "13";
		String month = dateTime.substring(5, 7);
		Integer valueOf = Integer.valueOf(month);
		System.out.println(valueOf.toString());
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月dd日 HH:mm:SS");
		String datetimme = "2015年3月18日 13:23:51";
		
		Date parse = sdf.parse(datetimme);
		
		System.out.println("---");
		
		
	}

}
