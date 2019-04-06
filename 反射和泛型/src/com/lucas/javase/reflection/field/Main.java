package com.lucas.javase.reflection.field;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Main {

	public static void main(String[] args) throws Exception {
		Student s = new Student();
		Class cls = s.getClass();
		Field f = cls.getDeclaredField("address");
		f.setAccessible(true);//强制访问private字段
		System.out.println(f.get(s));
		f.set(s, "shanghai");
		//printFieldInfo(f);
		s.hello();
		
		Field f2 = cls.getDeclaredField("number");
		System.out.println(f2.get(null));//由于静态字段不需要实例，所以传入null
		f2.set(null, 123);
		System.out.println(f2.get(null));//由于静态字段不需要实例，所以传入null
	}

	static void printFieldInfo(Field f) {
		System.out.println("field name: " + f.getName());
		System.out.println("field type: " + f.getType());
		System.out.println("field modifier: " + f.getModifiers());
		System.out.println("is public? " + Modifier.isPublic(f.getModifiers()));
		System.out.println("is protected? " + Modifier.isProtected(f.getModifiers()));
		System.out.println("is private? " + Modifier.isPrivate(f.getModifiers()));
		System.out.println("is static? " + Modifier.isStatic(f.getModifiers()));
		System.out.println("is final? " + Modifier.isFinal(f.getModifiers()));
	}

}
