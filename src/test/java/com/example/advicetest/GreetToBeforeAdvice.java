package com.example.advicetest;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class GreetToBeforeAdvice implements MethodBeforeAdvice {


    /**
     * 在方法执行前调用
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        String name = (String)args[0];
        System.out.println("您好"+name+"先生");
    }
}
