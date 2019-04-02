package com.lucas.javase.oop.basic.abstractClass;

public class Rect extends  Shape{

	private final double height;
	private final double weight;
	public Rect(double height, double weight) {
		super();
		this.height = height;
		this.weight = weight;
	}

	@Override
	public double area() {
		return height*weight;
	}

}
