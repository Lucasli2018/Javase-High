package com.feiyangedu.sample;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		Student s = new Student();
		Class cls = s.getClass();
		Method m = cls.getMethod("setAddress", String.class);
		printMethodInfo(m);
		s.hello();
	}

	static void printMethodInfo(Method m) {
		System.out.println(m);
		System.out.println("method name: " + m.getName());
		System.out.println("return type: " + m.getReturnType());
		System.out.println("parameters: " + Arrays.toString(m.getParameterTypes()));
		System.out.println("method modifier: " + m.getModifiers());
	}

}
