package com.lucas.javase.generic.Extends;



public class Main {

	public static void main(String[] args) {
		Pair<Number> p = new Pair<>(123, 45.67);
		int sum = add(p);
		System.out.println(sum);
		System.out.println(add(new Pair<Integer>(123, 456)));
		System.out.println(add(new Pair<Double>(12.3, 45.6)));
	}

	static int add(Pair<? extends Number> p) {
		Number first = p.getFirst();
		Number last = p.getLast();
		//p.setFirst(first);//编译器报错，原理也是擦拭法，编译器无法判断应该设置为Integer还是Float
		return first.intValue() + last.intValue();
	}

}
