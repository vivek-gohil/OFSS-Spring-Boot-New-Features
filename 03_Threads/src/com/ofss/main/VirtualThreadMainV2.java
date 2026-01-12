package com.ofss.main;

import java.util.List;
import java.util.stream.IntStream;

public class VirtualThreadMainV2 {
	public static void main(String[] args) throws InterruptedException {
		Long startTime = System.currentTimeMillis();
		int totalThreadCount = 100000;
		List<Thread> threadList = IntStream.range(0, totalThreadCount)
				.mapToObj(t -> Thread.ofVirtual().unstarted(() -> {

				})).toList();

		threadList.forEach(t -> t.start());

		for (Thread thread : threadList) {
			thread.join(); // make main wait till all threads are started
		}

		Long endTime = System.currentTimeMillis();
		System.out.println("Total Time = " + (endTime - startTime));
	}
}
