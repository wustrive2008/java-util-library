package com.wustrive.util.nio.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author wustrive_2008
 * @date   2012-10-22
 * @time   上午10:35:00
 * @version 
 * @tags
 */
public class TestCountDownLatch {
	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch begin = new CountDownLatch(1);
		final CountDownLatch end = new CountDownLatch(10);
		final ExecutorService exec = Executors.newFixedThreadPool(10);
		for(int index = 0 ; index < 10 ; index++){
			final int NO = index + 1;
			Runnable run = new Runnable(){
				public void run() {
					try {
						begin.await();
						Thread.sleep((long)(Math.random()*10000));
						System.out.println("NO."+NO+"arrived");
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}finally{
						end.countDown();
					}
					
				}
			};
			
			exec.submit(run);

			
		}

		System.out.println("Start");
		begin.countDown();
		end.await();
		System.out.println("Over");
		exec.shutdown();


	}
}
