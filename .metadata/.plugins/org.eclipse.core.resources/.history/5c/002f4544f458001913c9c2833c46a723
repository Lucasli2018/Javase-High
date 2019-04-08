package com.feiyangedu.sample;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Main {

	public static void main(String[] args) {
		Pair<String> p = new Pair<>("Xiao", "Ming");
		IntPair ip = new IntPair(1, 2);
		System.out.println(p instanceof Pair);
		System.out.println(ip instanceof Pair);
		System.out.println(ip instanceof IntPair);
		// 获取class IntPair extends Pair<Integer>定义的类型T - Integer:
		Type type = IntPair.class.getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) type;
		Type[] types = pt.getActualTypeArguments();
		Class<?> clazz = (Class<?>) types[0];
		System.out.println(clazz);
	}

}
