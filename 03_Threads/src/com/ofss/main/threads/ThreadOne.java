package com.ofss.main.threads;

public class ThreadOne extends Thread {
	@Override
	public void run() {
		System.out.println("ThreadOne is running");
		for (int i = 0; i < 50; i++) {
			System.out.println("thread 1 :: " + i);
		}
	}
}
