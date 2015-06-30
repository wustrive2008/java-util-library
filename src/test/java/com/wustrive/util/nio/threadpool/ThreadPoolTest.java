package com.wustrive.util.nio.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
	public static int i = 0;
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t5 = new MyThread();
		Thread t4 = new MyThread();
		
		Thread t6 = new MyThread();
		Thread t7 = new MyThread();
		Thread t8 = new MyThread();
		
		Thread t9 = new MyThread();
		Thread t10 = new MyThread();
		Thread t11 = new MyThread();
		
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		pool.execute(t6);
		pool.execute(t7);
		pool.execute(t8);
		pool.execute(t9);
		pool.execute(t10);
		pool.execute(t11);
		pool.shutdown();
		
	}

}
