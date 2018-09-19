package com.test.concurrent017;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
	
	public static void main(String[] args) {
		ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(1, 2, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2));
		Task task1=new Task("任务1");
		Task task2=new Task("任务2");
		Task task3=new Task("任务3");
		Task task4=new Task("任务4");
		Task task5=new Task("任务5");
		Task task6=new Task("任务6");
		Task task7=new Task("任务7");
		poolExecutor.execute(task1);
		poolExecutor.execute(task2);
		poolExecutor.execute(task3);
		poolExecutor.execute(task4);
		poolExecutor.execute(task5);
		//poolExecutor.execute(task6);
		//poolExecutor.execute(task7);
		poolExecutor.shutdown();
		
	}

}
