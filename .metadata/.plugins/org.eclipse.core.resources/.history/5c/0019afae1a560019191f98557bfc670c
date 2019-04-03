package com.feiyangedu.sample;

public class Main {

	public static void main(String[] args) {
		try {
			process1();
		} catch (BaseException e) {
			//
		} catch (Exception e) {
			//
		}
	}

	static void process1() {
		try {
			process2();
		} catch (IllegalArgumentException e) {
			throw new UserNotFoundException();
		}
	}

	static void process2() {
		throw new IllegalArgumentException();
	}
}
