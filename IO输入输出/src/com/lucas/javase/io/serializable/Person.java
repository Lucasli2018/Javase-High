package com.lucas.javase.io.serializable;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = -264706335817L;//如果修改了这里，反序列化的时候会不匹配

	public Person(String name) {
		System.out.println("Create "+name);
		this.name = name;
	}

	private final String name;

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
}
