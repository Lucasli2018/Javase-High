package com.lucas.javase.thread.interrupt;


class HelloThread extends Thread {
	public volatile boolean running=true;
	@Override
	public void run() {
		while (running) {
			System.out.println("Hello!");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Interrupted!");
				break;//这句话很关键的
			}
		}
		System.out.println("Thread end");
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		HelloThread t = new HelloThread();
		t.start();
		Thread.sleep(1000);
		t.running=false;
		System.out.println("Main end");
	}
}
