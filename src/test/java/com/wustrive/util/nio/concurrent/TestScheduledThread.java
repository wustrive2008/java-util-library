package com.wustrive.util.nio.concurrent;

import java.util.Date;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 * 延迟执行的任务
 * @author wustrive_2008
 * @date   2012-10-22
 * @time   上午10:49:19
 * @version 
 * @tags
 */
public class TestScheduledThread {
	public static void main(String[] args) {
		final ScheduledExecutorService scheduledExcutorService = Executors.newScheduledThreadPool(2);
		final Runnable run = new Runnable(){
			int count = 0 ;
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(new Date()+":"+(++count));
			}
		};
		//延迟1秒执行，每隔2秒执行一次
		final ScheduledFuture beeperHandle = scheduledExcutorService.scheduleAtFixedRate(run, 1, 2, SECONDS);
		//延迟2秒执行，每隔5秒后重新执行
		//final ScheduledFuture beeperHandle2 = scheduledExcutorService.scheduleWithFixedDelay(run, 2, 5, SECONDS);
		
		//执行30秒，30秒后取消任务
		scheduledExcutorService.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
				//beeperHandle2.cancel(true);
				scheduledExcutorService.shutdown();
			}
			}, 30, SECONDS);
	}
}
