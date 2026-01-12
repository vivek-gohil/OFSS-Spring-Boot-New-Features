package com.ofss.main;

import com.ofss.main.threads.ThreadOne;
import com.ofss.main.threads.ThreadTwo;

public class ThreadsMain {
	public static void main(String[] args) {

		System.out.println("main start");

		Thread thread = Thread.currentThread();
		System.out.println(thread);

		ThreadOne threadOne = new ThreadOne();
		System.out.println("main thread is starting new thread");
		System.out.println(threadOne);
		threadOne.start(); // Java tells OS to create new thread

		ThreadTwo threadTwo = new ThreadTwo();
		System.out.println("main thread is starting new thread");
		System.out.println(threadTwo);
		threadTwo.start(); // Java tells OS to create new thread

		System.out.println("main end");
	}
}
