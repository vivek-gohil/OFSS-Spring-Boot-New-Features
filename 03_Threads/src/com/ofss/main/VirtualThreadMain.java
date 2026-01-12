package com.ofss.main;

public class VirtualThreadMain {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("main start");
		
		System.out.println("Creating Platform thread");
		
		//will not wait till the thread is created - as creating and starting thread is non blocking operation
		Thread platformThread = Thread.ofPlatform()
				.start(() -> System.out.println("Platform thread : " + Thread.currentThread())); //Non blocking

		System.out.println("Creating Virual thread");
		
		Thread virtualThread = Thread.ofVirtual()
				.start(() -> System.out.println("Virtual Thrad : " + Thread.currentThread()));
		virtualThread.join();

		System.out.println("main end");
	}
}
