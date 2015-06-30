package com.wustrive.util.nio.concurrent;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞队列
 * @author wustrive_2008
 * @date   2012-10-22
 * @time   上午10:33:42
 * @version 
 * @tags
 */
public class TestBlockingQueue {

	static long randomTime() {
		return (long) (Math.random() * 1000);
	}

	public static void main(String[] args) {
		// 能容纳100个文件
		final BlockingQueue<File> queue = new LinkedBlockingQueue<File>(10);
		// 线程池
		final ExecutorService exec = Executors.newFixedThreadPool(5);
		final File root = new File("C:\\JavaLib");
		// 完成标志
		final File exitFile = new File("");
		// 读个数
		final AtomicInteger rc = new AtomicInteger();
		// 写个数
		final AtomicInteger wc = new AtomicInteger();
		// 读线程
		Runnable read = new Runnable() {
			public void run() {
				scanFile(root);
				scanFile(exitFile);
			}

			public void scanFile(File file) {
				if (file.isDirectory()) {
					File[] files = file.listFiles(new FileFilter() {
						public boolean accept(File pathname) {
							return pathname.isDirectory()
									|| pathname.getPath().endsWith(".java");
						}
					});
					for (File one : files)
						scanFile(one);
				} else {
					try {
						int index = rc.incrementAndGet();
						System.out.println("Read0: " + index + " "
								+ file.getPath());
						queue.put(file);
					} catch (InterruptedException e) {
					}
				}
			}
		};
		exec.submit(read);
		// 四个写线程
		for (int index = 0; index < 4; index++) {
			// write thread
			final int NO = index;
			Runnable write = new Runnable() {
				String threadName = "Write" + NO;

				public void run() {
					while (true) {
						try {
							Thread.sleep(randomTime());
							int index = wc.incrementAndGet();
							File file = (File) queue.take();
							// 队列已经无对象
							if (file == exitFile) {
								// 再次添加"标志"，以让其他线程正常退出
								queue.put(exitFile);
								break;
							}
							System.out.println(threadName + ": " + index + " "
									+ file.getPath());
						} catch (InterruptedException e) {
						}
					}
				}
			};
			exec.submit(write);
		}
		exec.shutdown();
	}

}
