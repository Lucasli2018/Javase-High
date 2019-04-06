package com.lucas.javase.annotation.create;


public class Person {

	@NotNull
	public String name;

	@Range(max = 20)
	public int age;

	public Person(@NotNull String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	

}
