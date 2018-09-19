package com.test.demo18;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestMain {

	public static void main(String[] args) {
		Master master=new Master(new Work(), 10);
		for (int i = 0; i < 100; i++) {
			Task task=new Task();
			task.setId(i);
			task.setPrice(i);
			master.submit(task);
		}
		
		master.excude();
		long start= System.currentTimeMillis();
		while (true) {
			if (master.completed()) {
				long end=System.currentTimeMillis();
				System.out.println("总耗时："+(end-start));
				break;
			}
		}
		
		ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(1, 1, 0, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>());

	}

}
