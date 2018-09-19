package com.test.proxy.staticproxy;

public class CarLogProxStrengthen implements MoveAble {

	private MoveAble moveAble;

	public CarLogProxStrengthen(MoveAble moveAble) {
		this.moveAble = moveAble;
	}

	@Override
	public void move() {
		System.out.println("记录日志");
		moveAble.move();
		System.out.println("记录日志结束");
	}
	
	
	
	
}
