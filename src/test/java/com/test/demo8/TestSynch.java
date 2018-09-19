package com.test.demo8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class TestSynch {

	public void justTest() {
		ConcurrentHashMap<String, String>concurrentHashMap=new ConcurrentHashMap<>();
		CopyOnWriteArrayList<String>copyOnWriteArrayList=new CopyOnWriteArrayList<>();
		ConcurrentLinkedQueue<String>concurrentLinkedQueue=new ConcurrentLinkedQueue<>();
		//
		ArrayBlockingQueue<String>arrayBlockingQueue=new ArrayBlockingQueue<>(10);
		LinkedBlockingQueue<String>linkedBlockingQueue=new LinkedBlockingQueue<>();
		SynchronousQueue<String>synchronousQueue=new SynchronousQueue<>();
		//PriorityBlockingQueue<String>priorityBlockingQueue=new PriorityBlockingQueue<>(0, comparator);
		//DelayQueue<Delayed>delayeds=new DelayQueue<>();
		
		System.out.println(Thread.currentThread().getName());
		synchronized (this) {
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+"在synchronized后");
	}
	
	public static void main(String[] args) {
		TestSynch testSynch=new TestSynch();
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				testSynch.justTest();
			}
		}, "t1");
		
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				testSynch.justTest();
			}
		}, "t2");
		t1.start();
		t2.start();
	}

}
