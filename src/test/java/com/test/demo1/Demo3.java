package com.test.demo1;

public class Demo3 {
	
	private int count=10;
	private int count2=12;
	
	public int getCount() {
		return this.count;
	}

	public synchronized void setCount(int a) {
		count=a;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count2=a;
	}
	
	public void getAll() {
		System.out.println("count="+count+",count2="+count2);
	}
	
	public static void main(String[] args) {
		Demo3 demo=new Demo3();
		
		Thread t1=new Thread(()->{
			demo.setCount(15);
		}); 
		
		Thread t2=new Thread(()->{
			demo.getAll();
		});
		
		t1.start();
		t2.start();
		
		
	}
	
	
}
