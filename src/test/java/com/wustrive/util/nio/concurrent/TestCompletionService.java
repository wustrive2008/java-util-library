package com.wustrive.util.nio.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 异步并发加载图片
 * @author wustrive_2008
 * @date   2012-10-22
 * @time   上午10:33:16
 * @version 
 * @tags
 */
public class TestCompletionService {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService exec = Executors.newFixedThreadPool(10);
		CompletionService<String> server = new ExecutorCompletionService<String>(exec);
		for (int i = 0; i < 5; i++) {
			final int NO = i;
			Callable<String> downImg = new Callable<String>(){
				public String call() throws Exception {
					// TODO Auto-generated method stub

					Thread.sleep((long) (Math.random() * 10000));
					return "Downloaded Image " + NO;
				}
			};
			server.submit(downImg);
		}
		

		Thread.sleep(1000 * 2);
		System.out.println("Show web content");
		for (int index = 0; index < 5; index++) {
			Future<String> task = server.take();
			String img = (String) task.get();
			System.out.println(img);
		}


		System.out.println("End");
		// 关闭线程池
		exec.shutdown();



	}

}
