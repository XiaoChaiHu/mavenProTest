package com.test.concurrent017;

public class Task implements Runnable{

	private String name;
	private int id;
	
	
	
	public Task(String name) {
		this.name = name;
	}



	@Override
	public void run() {
		System.out.println("task"+this.name+" run");
	}

}
