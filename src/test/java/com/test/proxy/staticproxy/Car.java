package com.test.proxy.staticproxy;

public class Car implements MoveAble{

	@Override
	public void move() {
		System.out.println("汽车动了！");
	}

}
