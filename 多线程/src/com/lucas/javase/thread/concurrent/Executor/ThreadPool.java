package com.lucas.javase.thread.concurrent.Executor;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class PrintTask implements Runnable {
	String name;

	public PrintTask(String name) {
		this.name = name;
	}

	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("Hello, " + name + "!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
}

public class ThreadPool {

	public static void main(String[] args) throws Exception {
		ExecutorService executor =new ThreadPoolExecutor(0, 10,//最多允许10个线程
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
		executor.submit(new PrintTask("Bob"));
		executor.submit(new PrintTask("Alice"));
		executor.submit(new PrintTask("Tim"));
		executor.submit(new PrintTask("Robot"));
		Thread.sleep(10000);
		executor.shutdown();
	}
}
