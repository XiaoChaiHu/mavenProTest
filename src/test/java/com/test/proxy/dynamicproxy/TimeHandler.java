package com.test.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler {
	
	public TimeHandler(Object target) {
		this.target = target;
	}

	private Object target;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("加入时间！");
		long startTime= System.currentTimeMillis();
		method.invoke(target, args);
		long endTime= System.currentTimeMillis();
		System.out.println("执行时间为："+(endTime-startTime)+"毫秒");
		return null;
	}

}
