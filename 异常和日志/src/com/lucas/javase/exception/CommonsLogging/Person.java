package com.lucas.javase.exception.CommonsLogging;


public class Person {

	private final String name;

	public Person(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name is null.");
		}
		this.name = name;
	}

	public String hello() {
		return "Hello, " + name + "!";
	}
}
