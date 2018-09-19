package com.test.proxy.staticproxy;

/**
 * 通过聚合实现
 * @author Administrator
 *
 */
public class CarLogProxy implements MoveAble {

	private MoveAble target;
	
	
	public CarLogProxy(MoveAble target) {
		this.target = target;
	}


	@Override
	public void move() {
		System.out.println("记录日志");
		target.move();
		System.out.println("记录日志结束");
	}

}
