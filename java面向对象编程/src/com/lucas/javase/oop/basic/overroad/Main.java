package com.lucas.javase.oop.basic.overroad;


public class Main {

	public static void main(String[] args) {
		Person ming = new Person();
		ming.setName("小明");
		System.out.println(ming.getName());

		Person hong = new Person();
		hong.setName("Xiao", "Hong");
		System.out.println(hong.getName());
		
		String[] s={"hello","dave","peter","alice"};
		hong.setName(s);
		System.out.println(hong.getName());
	}

}
