package com.wustrive.util.nio.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future Task,先做重要的事情，再做其他事情。
 * @author wustrive_2008
 * @date   2012-10-22
 * @time   上午10:45:47
 * @version 
 * @tags
 */
public class TestFutureTask {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final ExecutorService exec = Executors.newFixedThreadPool(5);
		Callable<String> callable = new Callable<String>(){
			public String call() throws Exception {
				// TODO Auto-generated method stub
				Thread.sleep(1000*5);
				return "do other things";
			}
		};
		
		Future<String> task = exec.submit(callable);
		Thread.sleep(1000*3);
		System.out.println("Let's do important things.");
		String obj = (String) task.get();
		System.out.println(obj);
		exec.shutdown();
	}
}
