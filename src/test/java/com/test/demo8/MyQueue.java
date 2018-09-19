package com.test.demo8;

import java.util.LinkedList;

public class MyQueue<E> {
	
	private LinkedList<E> listData;
	private int length;
	
	public MyQueue(int length) {
		this.length=length;
		this.listData=new LinkedList<>();
	}
	
	public synchronized void add(E object) {
		System.out.println(Thread.currentThread().getName()+"size="+listData.size());
		if (listData.size()<length) {
			listData.offer(object);
			this.notify();
		}else {
			try {
				System.out.println(Thread.currentThread().getName()+" wait");
				this.wait();
				System.out.println(Thread.currentThread().getName()+" I am Sober");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized E getObject() {
		System.out.println(Thread.currentThread().getName()+"size="+listData.size());
		if (listData.size()==0) {
			try {
				System.out.println(Thread.currentThread().getName()+" wait");
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"I am Sober");
			return null;
		}else {
			this.notify();
			return listData.poll();
			
		}
		
	}

}
