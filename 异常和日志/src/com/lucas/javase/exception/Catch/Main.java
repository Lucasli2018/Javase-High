package com.lucas.javase.exception.Catch;


public class Main {

	public static void main(String[] args) {
		process("10");
		process("abc");
		process("0");
	}

	static void process(String s) {
		try {
			int n = Integer.parseInt(s);
			int m = 100 / n;
			System.out.println(m);
		} catch (NumberFormatException | ArithmeticException e) {
			System.out.println(e);
			System.out.println("Bad input.");
		} finally {
			System.out.println("end process.");
		}
	}
}
