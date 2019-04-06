package com.feiyangedu.sample;

public class Student extends Person implements Hello {

	public static int number = 0;

	public String name;
	private String address = "beijing";

	public Student() {
		this("unnamed");
	}

	public Student(String name) {
		this(name, 20);
	}

	private Student(String name, int age) {
		this.name = name;
		this.age = age;
		number++;
	}

	public void hello() {
		System.out.println("Hi, " + name + " from " + address + "!");
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
