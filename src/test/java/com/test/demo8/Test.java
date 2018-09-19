package com.test.demo8;

public class Test {

	public static void main(String[] args) {
		MyQueue<Integer> queue=new MyQueue<>(5);
		Thread thread1=new Thread(new Runnable() {
			@Override
			public void run() {
				int i=0;
				do {
					queue.add(i);
					i++;
				} while (i<10);
			}
		},"t1");
		
		Thread thread2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i=0;
				do {
					queue.getObject();
					i++;
				} while (i<10);
			}
		},"t2");
		
		thread1.start();
		thread2.start();
	}
}
