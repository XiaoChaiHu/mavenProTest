package com.test.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.test.proxy.staticproxy.MoveAble;

public class LogHandler implements InvocationHandler {
	

	public LogHandler(MoveAble target) {
		this.target = target;
	}

	private MoveAble target;
	
	/**
	 * proxy 代理实例,类似于car
	 * method 代理的方法
	 * args 代理方法的参数
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//System.out.println("proxy:"+proxy.getClass().getName());
		System.out.println("开始记录日志！");
		method.invoke(target, args);
		System.out.println("记录日志结!");
		return null;
	}

}
