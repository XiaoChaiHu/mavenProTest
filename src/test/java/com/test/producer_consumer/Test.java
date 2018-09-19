package com.test.producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

	public static void main(String[] args) {
		BlockingQueue<String>queue=new LinkedBlockingQueue<>(10);
		Producer p1=new Producer(queue);
		Consumer c1=new Consumer(queue);
		Producer p2=new Producer(queue);
		Consumer c2=new Consumer(queue);
		Producer p3=new Producer(queue);
		Consumer c3=new Consumer(queue);
		ExecutorService executorService=Executors.newCachedThreadPool();
		//启动生产者
		executorService.execute(p1);
		//executorService.execute(p2);
		//executorService.execute(p3);
		
		//启动消费者
		executorService.execute(c1);
		executorService.execute(c2);
		executorService.execute(c3);
		
		try {
			Thread.sleep(1000*60);
			executorService.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}
