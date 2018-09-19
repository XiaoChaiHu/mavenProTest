package com.test.concurrent017;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
	
	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(1);
		//scheduledExecutorService.scheduleAtFixedRate(new Task(), 1, 5, TimeUnit.SECONDS);
		scheduledExecutorService.scheduleWithFixedDelay(new Task("测试1"), 1, 5, TimeUnit.SECONDS);
		scheduledExecutorService.scheduleAtFixedRate(new Task("测试2"), 1, 6, TimeUnit.SECONDS);
	}

}
