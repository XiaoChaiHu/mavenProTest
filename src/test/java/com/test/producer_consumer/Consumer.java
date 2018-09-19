package com.test.producer_consumer;

import java.util.concurrent.BlockingQueue;

/**
 * 消费者
 * 
 * @author Administrator
 *
 */
public class Consumer implements Runnable {

	private BlockingQueue<String> queue;

	private volatile boolean running = true;
	
	
	public Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}



	@Override
	public void run() {
		while (running) {
			try {
				String item= queue.take();
				Thread.sleep(4000);
				System.out.println("消费者，消费了："+item);
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
