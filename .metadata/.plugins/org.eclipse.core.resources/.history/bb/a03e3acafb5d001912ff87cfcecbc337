package com.feiyangedu.sample;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class TimerThread extends Thread {
	@Override
	public void run() {
		while (true) {
			System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("Main start");
		TimerThread t = new TimerThread();
		t.start();
		Thread.sleep(5000);
		System.out.println("Main end");
	}
}
