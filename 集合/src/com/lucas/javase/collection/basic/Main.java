package com.lucas.javase.collection.basic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		//List<String> list = new LinkedList<>();
		list.add("Apple");
		list.add("Pear");
		list.add("Orange");
		//1 foreach 内部 编译器处理成Iterator
		for (String s : list) {
			System.out.println(s);
		}
		System.out.println();
		//2 Iterator
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
		}
		System.out.println();
		//3 get(int index)
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("List 转Array");
		//List 转Array
		String[] arr=list.toArray(new String[list.size()]);
		for (String string : arr) {
			System.out.println(string);
		}
		System.out.println("Array 转List");		
		//Array 转List
		List<String> list2=Arrays.asList(arr);	
		System.out.println(list2);
	}

}
