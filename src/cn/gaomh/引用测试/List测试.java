package cn.gaomh.引用测试;

import java.util.ArrayList;

public class List测试 {

	/**
	 * 功能描述：
	 * @param args 
	 * @版本 1.0
	 * @创建者 gaominghui
	 * @创建时间 2015-1-20 上午11:33:45
	 * @版权所有 ©2015 CTFO
	 * @修改者 gaominghui
	 * @修改时间 2015-1-20 上午11:33:45
	 * 修改描述 
	 */

	public static void main(String[] args) {
		ArrayList<Bean> list = new ArrayList<Bean>();
		Bean b;
		for( int i = 0;i<10;i++){
			b = new Bean();
			b.name = ""+i;
			b.setAge("age:"+i);
			list.add(b);
			b = null;
		}
		System.out.println("debug");// 需要这样写。
	}

}
