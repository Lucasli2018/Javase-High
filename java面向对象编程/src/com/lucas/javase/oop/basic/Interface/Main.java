package com.lucas.javase.oop.basic.Interface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Shape s1 = new Rect(200, 100);
		Shape s2 = new Circle(80);
		System.out.println(s1.area());
		System.out.println(s2.area());
		
		List list=new ArrayList();//list可以调用List的所有方法
		Collection coll=list;//coll只能调用Collection方法
		Iterable it=coll;//it只能调用Iterable方法
	}

}
