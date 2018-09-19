package com.test.producer_consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者
 * @author Administrator
 *
 */
public class Producer implements Runnable{

	private volatile boolean running=true;
	
	private BlockingQueue<String>queue;

	public Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}



	@Override
	public void run() {
		Random random=new Random();
		while (running) {
			try {
				int t=random.nextInt(10000);
				//put会阻断
				Thread.sleep(5000);
				System.out.println("生产者，生产了产品id："+t);
				queue.put("生产者产品id："+t);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		this.running=false;
	}
	
	
	
}
