package com.lucas.javase.reflection.clazz;


public class Teacher implements Hello {

	private String name;

	public Teacher() {
		this("unnamed");
	}

	public Teacher(String name) {
		this.name = name;
	}

	public void hello() {
		System.out.println("Hello, " + name + "!");
	}

}
