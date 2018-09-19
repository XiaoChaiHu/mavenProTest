package com.test.demo1;


/**
 * 饿汉模式
 * 使用 innerclass
 * @author Administrator
 *
 */
public class SingleDemo1 {
	private SingleDemo1() {};
	
	private static class innerClass {
		private static SingleDemo1 singleDemo1=new SingleDemo1();
	}
	
	public static SingleDemo1 getSingleDemo1Instance() {
		return innerClass.singleDemo1;
	}

}
