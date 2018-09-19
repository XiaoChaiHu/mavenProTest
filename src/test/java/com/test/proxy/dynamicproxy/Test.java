package com.test.proxy.dynamicproxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.test.proxy.staticproxy.Car;
import com.test.proxy.staticproxy.MoveAble;

public class Test {

	public static void main(String[] args) {
		DynamicLogProxy();
		//DynamicLogProxyBicycle();
	}
	
	public static void DynamicLogProxy() {
		Car car=new Car();
		InvocationHandler logInvocationHandler=new LogHandler(car);
		MoveAble moveAble=(MoveAble)Proxy.newProxyInstance(car.getClass().getClassLoader(), Car.class.getInterfaces(), logInvocationHandler);
		moveAble.move();
		System.out.println("moveAble的Class类是"+moveAble.getClass().toString());
		System.out.println("moveAble中的属性有：");
		Field[] fields= moveAble.getClass().getDeclaredFields();
		for (Field field : fields) {
			System.out.println("field:"+field.getName());
		}
		System.out.println("moveAble中的方法有：");
		Method[] methods=moveAble.getClass().getDeclaredMethods();
		for (Method method : methods) {
			System.out.println("method:"+method.getName());
		}
		
		System.out.println("moveAble的父类是："+moveAble.getClass().getSuperclass());
		
		 System.out.print("moveAble实现的接口是："); 
		 Class<?>[] interfaces=moveAble.getClass().getInterfaces();
		for (Class<?> class1 : interfaces) {
			System.out.print("interface"+class1.getName());  
			
		}
	}
	
	public static void DynamicLogProxyBicycle() {
		Bicycle bicycle=new Bicycle();
		InvocationHandler logInvocationHandler=new LogHandler(bicycle);
		MoveAble moveAble=(MoveAble)Proxy.newProxyInstance(bicycle.getClass().getClassLoader(), Car.class.getInterfaces(), logInvocationHandler);
		moveAble.move();
	}
	
	public static void test() {
		Car car=new Car();
		
	}
}
