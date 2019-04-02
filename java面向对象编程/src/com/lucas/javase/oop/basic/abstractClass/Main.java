package com.lucas.javase.oop.basic.abstractClass;


public class Main {

	public static void main(String[] args) {
		calcArea(new Rect(10, 10));
		calcArea(new Circle(10));
	}
	
	static void calcArea(Shape s){
		System.out.printf("this shape area is %.2f%n",s.area());
	}

}
