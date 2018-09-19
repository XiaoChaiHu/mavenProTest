package com.test.proxy.staticproxy;

public class CarTimeProxy implements MoveAble{

	private MoveAble moveAble;
	
	
	public CarTimeProxy(MoveAble moveAble) {
		super();
		this.moveAble = moveAble;
	}


	@Override
	public void move() {
		System.out.println("加入时间！");
		long startTime= System.currentTimeMillis();
		moveAble.move();
		long endTime= System.currentTimeMillis();
		System.out.println("执行时间为："+(endTime-startTime)+"毫秒");
	}

}
