package com.lucas.javase.collection.Set;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("pear","pear","pear", "apple", "banana", "orange", "apple", "banana");
		List<String> list2 = removeDuplicate(list1);
		System.out.println(list2);
	}

	/**
	 * 去重
	 * @param list 包含重复元素的集合
	 * @return
	 */
	static List<String> removeDuplicate(List<String> list) {
		//Set<String> set = new HashSet<>(list);
		//Set<String> set = new TreeSet<>(list);
		Set<String> set = new TreeSet<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return -o1.compareTo(o2);
			}
		});
		set.addAll(list);
		return new ArrayList<String>(set);
	}

}
