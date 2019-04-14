package com.lucas.javase.thread.concurrent.Executor;


import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class HelloTask implements Runnable {
	String name;

	public HelloTask(String name) {
		this.name = name;
	}

	public void run() {
		System.out.println("Hello, " + name + "! It is " + LocalTime.now());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		System.out.println("Goodbye, " + name + "! It is " + LocalTime.now());
	}
}

public class Schedule {

	public static void main(String[] args) throws Exception {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
		executor.scheduleAtFixedRate(new HelloTask("Bob"), 2, 5, TimeUnit.SECONDS);//固定时间间隔触发了，不管有没有执行完成
		executor.scheduleWithFixedDelay(new HelloTask("Alice"), 2, 5, TimeUnit.SECONDS);//固定时间间隔触发，一定要执行完成
	}

}
