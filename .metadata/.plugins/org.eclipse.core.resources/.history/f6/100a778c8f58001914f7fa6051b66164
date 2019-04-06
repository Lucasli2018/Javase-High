package com.feiyangedu.sample;

import java.lang.reflect.Field;

public class Main {

	public static void main(String[] args) throws Exception {
		Person p1 = new Person("Xiao Ming", 25);
		Person p2 = new Person(null, 15);
		checkPerson(p1);
		checkPerson(p2);
	}

	static void checkPerson(Person p) throws Exception {
		System.out.println("check " + p + "...");
		Class c = Person.class;
		for (Field f : c.getFields()) {
			checkField(f, p);
		}
	}

	static void checkField(Field f, Person p) throws Exception {
		if (f.isAnnotationPresent(NotNull.class)) {
			Object r = f.get(p);
			if (r == null) {
				System.out.println("Error: field " + f.getName() + " is null.");
			}
		}
		if (f.isAnnotationPresent(Range.class)) {
			Range range = f.getAnnotation(Range.class);
			int n = (Integer) f.get(p);
			if (n < range.min() || n > range.max()) {
				System.out.println("Error: field " + f.getName() + " is out of range.");
			}
		}
	}
}
