package com.lucas.javase.oop.basic.Extends;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Person p = new Person("xiao ming ");
		Student s = new Student("xiao hong");
		System.out.println(p);
		System.out.println(s);
		s.run();
		
		List list =new ArrayList();//可以调用List的方法
		Collection coll=list;
		Iterable it=coll;//由于抽象的层次不同，可以实现的方法也不同，到了这里，只能调用Iterable的方法
		//Student sp=(Student)p;
		//sp.run();
		System.out.println("p instanceof Student:"+(p instanceof Student));
		if(p instanceof Student){//判断 不执行
			Student sp=(Student)p;
			sp.run();
		}
	}

}
