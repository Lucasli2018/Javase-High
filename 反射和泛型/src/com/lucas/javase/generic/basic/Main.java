package com.lucas.javase.generic.basic;


import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<String> strList = new ArrayList<String>();
		strList.add("abc");
		strList.add("xyz");
		//strList.add(new Integer(12));编译器会自动检查类型
		String first = strList.get(0);
		System.out.println(first);
	}
}
