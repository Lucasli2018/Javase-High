package com.lucas.javase.reflection.constructor;


import java.lang.reflect.Constructor;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		Class cls = Student.class;
		Constructor c = cls.getDeclaredConstructor(String.class, int.class);
		printConstructorInfo(c);
		c.setAccessible(true);
		Student s = (Student) c.newInstance("Xiao Ming", 12);
		s.hello();
		
		System.out.println(cls.getName());
	}

	static void printConstructorInfo(Constructor c) {
		System.out.println(c);
		System.out.println("parameters: " + Arrays.toString(c.getParameterTypes()));
		System.out.println("modifier: " + c.getModifiers());
	}

}
