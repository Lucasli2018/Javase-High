package com.lucas.javase.thread.join;


class HelloThread extends Thread {

	String name;

	public HelloThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		try {
			System.out.println("waiting...");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hello, " + name + "!");
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		Thread t1 = new HelloThread("Bob");
		System.out.println("START");
		t1.start();
		t1.join(); // 等待线程t1执行结束
		System.out.println("END");
	}
}
