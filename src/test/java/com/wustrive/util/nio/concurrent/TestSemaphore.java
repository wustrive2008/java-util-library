package com.wustrive.util.nio.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * java信号量的实现
 * @author wustrive_2008
 * @date   2012-10-22
 * @time   上午10:53:59
 * @version 
 * @tags
 */
public class TestSemaphore {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		//最多允许5个同时并发
		final Semaphore semaphore = new Semaphore(5);
		//模拟20个客户端访问
		for (int i = 0; i < 21; i++) {
			final int NO = i;
			Runnable run = new Runnable(){
				public void run() {
					// TODO Auto-generated method stub
					try {
						//获取许可
						semaphore.acquire();
						System.out.println("Accessing:"+NO);
						Thread.sleep(3*1000);
						//释放
						semaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			exec.submit(run);
		}
		exec.shutdown();
	}

}
