package com.lucas.javase.oop.basic.Extends;


public class Main {

	public static void main(String[] args) {
		Person p = new Person("xiao ming ");
		Student s = new Student("xiao hong");
		System.out.println(p);
		System.out.println(s);
		s.run();
		
		//Student sp=(Student)p;
		//sp.run();
		if(p instanceof Student){//判断 不执行
			Student sp=(Student)p;
			sp.run();
		}
	}

}
