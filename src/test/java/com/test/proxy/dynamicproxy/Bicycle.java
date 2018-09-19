package com.test.proxy.dynamicproxy;

import com.test.proxy.staticproxy.MoveAble;

public class Bicycle implements MoveAble{

	@Override
	public void move() {
		System.out.println("自行车动了！");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
