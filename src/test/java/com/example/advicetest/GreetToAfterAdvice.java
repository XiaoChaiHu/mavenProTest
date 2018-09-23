package com.example.advicetest;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class GreetToAfterAdvice implements AfterReturningAdvice {

    /**
     * 在方法执行后调用
     * @param returnValue
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("请你慢用");
    }
}
