package com.lucas.javase.exception.Assert;


public class Main {

	public static void main(String[] args) {
		double x = abs(-123.45);
		assert x >= 0 : "x must >= 0 but x = " + x;//默认JVM不会执行，需要输入参数 -ea:完整类名
		System.out.println(x);
	}

	static double abs(double d) {
		return d < 0 ? d : -d;
	}
}
