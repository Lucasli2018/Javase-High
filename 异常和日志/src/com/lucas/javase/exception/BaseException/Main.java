package com.lucas.javase.exception.BaseException;


public class Main {

	public static void main(String[] args) {
		try {
			process1();
		} catch (BaseException e) {
			//
			e.printStackTrace();
		} catch (Exception e) {
			//
			e.printStackTrace();
		}
	}

	static void process1() {
		try {
			process2();
		} catch (IllegalArgumentException e) {
			throw new UserNotFoundException(e);
		}
	}

	static void process2() {
		throw new IllegalArgumentException();
	}
}
