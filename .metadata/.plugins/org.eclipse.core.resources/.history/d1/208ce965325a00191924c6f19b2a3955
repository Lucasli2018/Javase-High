package com.feiyangedu.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("abc", "xyz", "abc", "www", "edu", "www", "abc");
		List<String> list2 = removeDuplicateButKeepOriginOrder(list1);
		System.out.println(list2);
		// 期待输出："abc", "xyz", "www", "edu"
	}

	// FIXME:
	static List<String> removeDuplicateButKeepOriginOrder(List<String> list) {
		Set<String> set = new HashSet<>(list);
		return new ArrayList<String>(set);
	}

}
