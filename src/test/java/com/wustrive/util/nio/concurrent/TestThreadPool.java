package com.wustrive.util.nio.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool {
	
	public static void main(String[] args) {
		
		 //构造一个有两个线程的线程池
		 
		ExecutorService exec = Executors.newFixedThreadPool(2);
		for(int i = 0 ; i < 65 ; i++){
			Runnable run = new Runnable(){
				public void run() {
					long time = (long)(Math.random()*1000);
					System.out.println("Sleeping"+time+"ms");
					try {
						Thread.sleep(time);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			};
			//执行线程
			exec.execute(run);
		}
		exec.shutdown();
	}

}
