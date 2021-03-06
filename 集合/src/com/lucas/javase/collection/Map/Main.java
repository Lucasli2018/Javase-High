package com.lucas.javase.collection.Map;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
		List<Person> list = Arrays.asList(new Person("Ming", 12), new Person("dd", 15),new Person("Honssg", 15),new Person("Hong", 15), new Person("Jun", 18));
		Map<String, Person> map = new TreeMap<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		for (Person p : list) {
			map.put(p.getName(), p);
		}
		//System.out.println(map.get("Jun"));
		//System.out.println(map.get("Mark"));
		//keySet遍历
		for (String key : map.keySet()) {
			System.out.println(map.get(key));//HashMap输出顺序不定
		}
		System.out.println();
		//entrySet遍历
		for (Entry<String, Person> p : map.entrySet()) {
			System.out.println(p.getKey()+"--->"+p.getValue());
		}
	}

}
