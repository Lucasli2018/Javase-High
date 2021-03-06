package com.lucas.javase.annotation.practise;


public class Person {

	@NotNull
	private String name;
	@Range(min = 2, max = 5)
	private int age;
	@ZipCode(6)
	private String zipCode;

	public Person(String name, int age, String zipCode) {
		this.name = name;
		this.age = age;
		this.zipCode = zipCode;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Range(max = 20)
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@ZipCode
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String toString() {
		return "Person(name=" + name + ", age=" + age + ", zipCode=" + zipCode + ")";
	}
}
