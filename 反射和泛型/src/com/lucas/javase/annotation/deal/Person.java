package com.lucas.javase.annotation.deal;


public class Person {

	@NotNull
	public String name;

	@Range(max = 20)
	public int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String toString() {
		return "Person(name=" + name + ", age=" + age + ")";
	}
}
