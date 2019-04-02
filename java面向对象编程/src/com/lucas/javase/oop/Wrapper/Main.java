package com.lucas.javase.oop.Wrapper;


public class Main {

	public static void main(String[] args) {
		Integer n = 5 + Integer.valueOf(10);//先拆箱 进行+运算，然后装箱到Integer
		Number m = n;//向上转型
		System.out.println(m.floatValue());//拆箱
	}

}
