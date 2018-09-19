package com.test.demo1;

public class Demo1 extends Thread {

	private int count = 5;

	@Override
	public  synchronized void run() {
		count--;
		System.out.println(this.currentThread().getName() + "count=" + count);
	}

	public static void main(String[] args) {
		Demo1 demo1 = new Demo1();
		demo1.test2();
	}

	/**
	 * 当多个线程访问Demo1的run方法时，会以排队方式进行处理（指的是cup分配先后顺序） 执行synchronized里面的代码 1尝试获得锁
	 * 2如果拿到锁，执行里面的内容，拿不到会不断尝试直到拿到为止，而且是多个线程竞争锁（也就是锁竞争问题）
	 * 注意：如果synchronized里面的内容出现异常会释放锁
	 */
	public void test1() {

		Demo1 demo1 = new Demo1();
		Thread thread1 = new Thread(demo1, "t1");
		Thread thread2 = new Thread(demo1, "t2");
		Thread thread3 = new Thread(demo1, "t3");
		Thread thread4 = new Thread(demo1, "t4");
		Thread thread5 = new Thread(demo1, "t5");

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
	}

	
	public void test2() {
		Demo1 demo1 = new Demo1();
		Demo1 demo2 = new Demo1();
		Demo1 demo3 = new Demo1();
		Demo1 demo4 = new Demo1();
		Demo1 demo5= new Demo1();
		Thread thread1 = new Thread(demo1, "t1");
		Thread thread2 = new Thread(demo2, "t2");
		Thread thread3 = new Thread(demo3, "t3");
		Thread thread4 = new Thread(demo4, "t4");
		Thread thread5 = new Thread(demo5, "t5");

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
	}

}
