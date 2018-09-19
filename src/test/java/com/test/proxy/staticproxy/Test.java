package com.test.proxy.staticproxy;

public class Test {

	public static void main(String[] args) {
		System.out.println("-----------usingExtend-------------");
		usingExtend();
		System.out.println("------------usingPolymerization------------");
		usingPolymerization();
		System.out.println("------------usingPolymerization1------------");
		usingPolymerization1();
		System.out.println("------------usingPolymerization2------------");
		usingPolymerization2();
	}

	public static void usingExtend() {
		CarLogExten carLogExten = new CarLogExten();
		carLogExten.move();
	}

	public static void usingPolymerization() {
		Car car=new Car();
		CarLogProxy carLogProxy=new CarLogProxy(car);
		carLogProxy.move();
	}
	
	/**
	 * 日志->权限->时间
	 */
	public static void usingPolymerization1() {
		Car car=new Car();
		CarTimeProxy carTimeProxy=new CarTimeProxy(car);
		CarPowerProx carPowerProx=new CarPowerProx(carTimeProxy);
		CarLogProxy carLogProxy=new CarLogProxy(carPowerProx);
		carLogProxy.move();
	}
	
	/**
	 * 时间->日志->权限
	 */
	public static void usingPolymerization2() {
		Car car=new Car();
		CarPowerProx carPowerProx=new CarPowerProx(car);
		CarLogProxy carLogProxy=new CarLogProxy(carPowerProx);
		CarTimeProxy carTimeProxy=new CarTimeProxy(carLogProxy);
		carTimeProxy.move();
	}
}
