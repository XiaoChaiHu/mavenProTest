package com.example.advicetest;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 环绕增强
 */
public class GreetToMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //通过反射获取参数
        Object[] arguments = invocation.getArguments();
        String name = (String)arguments[0];
        System.out.println("您好"+name+"先生");
        Object proceed = invocation.proceed();
        System.out.println("请你慢用");
        return proceed;
    }
}
