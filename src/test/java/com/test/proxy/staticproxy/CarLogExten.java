package com.test.proxy.staticproxy;

/**
 * 通过继承实现
 * @author Administrator
 *
 */
public class CarLogExten extends Car{

	@Override
	public void move() {
		System.out.println("记录日志");
		super.move();
		System.out.println("记录日志结束");
	}

	
}
