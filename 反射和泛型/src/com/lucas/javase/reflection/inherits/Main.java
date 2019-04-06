package com.lucas.javase.reflection.inherits;


import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		Class cls = Student.class;
		printSuperClass(cls);
		printSuperClass(Person.class);
		printInterfaces(cls);
		printAllInterfaces(cls);
		// Person p = new Student() ?
		System.out.println(Person.class.isAssignableFrom(Student.class));
		// Student s = new Person() ?
		System.out.println(Student.class.isAssignableFrom(Person.class));
	}

	static void printSuperClass(Class c) {
		System.out.print(c.getSimpleName());
		Class s = c.getSuperclass();
		if (s == null) {
			System.out.println();
		} else {
			System.out.print(" : ");
			printSuperClass(s);
		}
	}

	static void printInterfaces(Class cls) {
		Class[] ifs = cls.getInterfaces();
		System.out.println(cls.getSimpleName() + " implements " + Arrays.toString(ifs));
	}

	static void printAllInterfaces(Class cls) {
		System.out.print(cls.getSimpleName() + ": ");
		_printAllInterfaces(cls);
		System.out.println();
	}

	static void _printAllInterfaces(Class cls) {
		Class[] ifs = cls.getInterfaces();
		for (Class i : ifs) {
			System.out.print(i.getSimpleName());
			System.out.print(", ");
			_printAllInterfaces(i);
		}
		Class sup = cls.getSuperclass();
		if (sup != null) {
			_printAllInterfaces(sup);
		}
	}
}
