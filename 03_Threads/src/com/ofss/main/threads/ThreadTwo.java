package com.ofss.main.threads;

public class ThreadTwo extends Thread {
	@Override
	public void run() {
		System.out.println("ThreadTwo is running");
		for (int i = 0; i < 50; i++) {
			System.out.println("thread 2 :: " + i);
		}
	}
}
