package com.test.proxy.staticproxy;

public class CarPowerProx implements MoveAble{

	private MoveAble moveAble;
	
	
	public CarPowerProx(MoveAble moveAble) {
		this.moveAble = moveAble;
	}


	@Override
	public void move() {
		System.out.println("已经加入权限！");
		moveAble.move();
		
	}

}
