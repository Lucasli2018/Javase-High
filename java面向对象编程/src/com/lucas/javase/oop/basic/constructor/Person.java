package com.lucas.javase.oop.basic.constructor;


public class Person {

	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public Person(int age){
		this("unnamed", age);
	}
	

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public int getAge() {
		return this.age;
	}
}
