package com.lucas.javase.oop.basic.Interface;


public interface Shape {

	double area();
	
	default double perimeter(){
		return 0;
	}

}
