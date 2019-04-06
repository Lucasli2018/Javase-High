package com.lucas.javase.reflection.clazz;


public class Student implements Hello {

	private String name;

	public Student() {
		this("unnamed");
	}

	public Student(String name) {
		this.name = name;
	}

	public void hello() {
		System.out.println("Hi, " + name + "!");
	}

}
