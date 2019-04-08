package com.lucas.javase.generic.defined;


public class Main {

	public static void main(String[] args) {
		Pair<Integer> p =Pair.create(1, 2);
		Integer first = p.getFirst();
		Integer last = p.getLast();
		System.out.println(first);
		System.out.println(last);
		System.out.println(p);
		System.out.println(p.getClass() == Pair.class);//泛型所有的类都是同一个class实例，不会单独创建一个新的class实例
	}

}
