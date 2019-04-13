package com.lucas.javase.thread.basic;


class HelloThread extends Thread {

	String name;

	public HelloThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 30; i++) {
			System.out.println("Hello, " + name + "!");
		}
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		Thread t1 = new HelloThread("Bob");
		t1.start();
		for (int i = 0; i < 30; i++) {
			System.out.println("Main!");
		}
	}
}
