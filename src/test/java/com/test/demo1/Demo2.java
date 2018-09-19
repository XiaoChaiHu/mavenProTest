package com.test.demo1;

public class Demo2 {

	private static int count = 5;

	public synchronized static  void printNum() {
		count--;
		System.out.println("count=" + count);
	}

	/**
	 * 锁的锁是对象，对象不同就是不同的锁，互不影响
	 * 想相互影响就使用static 是.class类，是独占的
	 */
	public void test1() {
		Demo2 demo1 = new Demo2();
		Demo2 demo2 = new Demo2();
		Thread thread=new Thread(new Runnable() {
			@Override
			public void run() {
				demo1.printNum();
			}
		});
		
		Thread thread2=new Thread(new Runnable() {
			@Override
			public void run() {
				demo2.printNum();
			}
		});
		thread.start();
		thread2.start();
		
	}

	public static void main(String[] args) {
		Demo2 demo2=new Demo2();
		demo2.test1();
	}

}
