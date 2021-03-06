package com.lucas.javase.collection.Set.practise;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("abc", "xyz", "abc", "www", "edu", "www", "abc");
		List<String> list2 = removeDuplicateButKeepOriginOrder(list1);
		System.out.println(list2);
		// 期待输出："abc", "xyz", "www", "edu"
	}

	// FIXME:请将List的元素去重，但保留元素在List中的原始顺序
	static List<String> removeDuplicateButKeepOriginOrder(List<String> list) {
		Set<String> set = new LinkedHashSet<>(list);
		return new ArrayList<String>(set);
	}

}
