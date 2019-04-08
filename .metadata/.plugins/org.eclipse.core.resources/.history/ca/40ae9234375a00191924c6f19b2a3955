package com.feiyangedu.sample;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		String s = "1 + 2 * (9 - 5)";
		Calculator calc = new Calculator();
		Object[] exp = calc.compile(s);
		int result = calc.calculate(exp);
		System.out.println("[calculate] " + s + " => " + expressionToString(exp) + " => " + result);
	}

	static String expressionToString(Object[] exp) {
		List<String> list = new ArrayList<>(exp.length);
		for (Object e : exp) {
			list.add(e.toString());
		}
		return String.join(" ", list);
	}

}
