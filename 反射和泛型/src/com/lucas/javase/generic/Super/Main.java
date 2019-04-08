package com.lucas.javase.generic.Super;


public class Main {

	public static void main(String[] args) {
		Pair<Integer> p = new Pair<>(0, 0);
		set(p, 123, 456);
		System.out.println(p);
		Pair<Number> n = new Pair<>(1.2, 3.4);
		set(n, 99, 88);
		System.out.println(n);
	}

	static void set(Pair<? super Integer> p, Number first, Number last) {
		//p.setFirst(first);
		//p.setLast(last);//不能把Integer的父类Number转为Integer
		p.getFirst();
	}

}
