package com.lucas.javase.oop.basic.polymorphic;


public class Main {

	public static void main(String[] args) {
		Person p = new Person("Xiao Ming");
		Person s = new Student("Xiao Hong");
		run(p);
		run(s);//相同的调用方法，但是执行的结果不同==》多态
	}
	
	static void run(Person p){
		
		System.out.println(p.hello());
		p.run();
	}

}
