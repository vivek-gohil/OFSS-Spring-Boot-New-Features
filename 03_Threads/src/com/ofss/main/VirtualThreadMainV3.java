package com.ofss.main;

import java.util.List;
import java.util.stream.IntStream;

public class VirtualThreadMainV3 {
	public static void main(String[] args) throws InterruptedException {
		Long startTime = System.currentTimeMillis();
		int totalThreadCount = 10;
		List<Thread> threadList = IntStream.range(0, totalThreadCount)
				.mapToObj(t -> Thread.ofVirtual().unstarted(() -> {
					if (t == 0) {
						System.out.println(Thread.currentThread());
					}

					// Database operation
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (t == 0) {
						System.out.println(Thread.currentThread());
					}
				})).toList();

		threadList.forEach(t -> t.start());

		for (Thread thread : threadList) {
			thread.join(); // make main wait till all threads are started
		}

		Long endTime = System.currentTimeMillis();
		System.out.println("Total Time = " + (endTime - startTime));
	}
}
