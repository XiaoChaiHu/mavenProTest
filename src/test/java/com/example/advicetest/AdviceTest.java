package com.example.advicetest;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class AdviceTest {

    /**
     * 具体的增强类
     */
    private Waiter target;

    /**
     * 前置增强
     */
    private BeforeAdvice beforeAdvice;

    /**
     * 后置增强
     */
    private AfterReturningAdvice afterReturningAdvice;

    /**
     * 环绕增强
     */
    private MethodInterceptor methodInterceptor;

    /**
     * spring代理工厂
     */
    private ProxyFactory proxyFactory;

    /**
     * 初始化
     */
    @Before
    public void init(){
        target=new NativeWaiter();
        proxyFactory=new ProxyFactory();
        //设置代理目标
        proxyFactory.setTarget(target);


    }

    /**
     * 测试前置增强
     */
    @Test
    public void testBeforeAdvice(){
        beforeAdvice=new GreetToBeforeAdvice();
        //为代理目标添加增强
        proxyFactory.addAdvice(beforeAdvice);
        proxyFactory.setOptimize(true);
        Waiter proxy =(Waiter) proxyFactory.getProxy();
        proxy.greetTo("张三");
        proxy.serveTo("张三");
    }

    /**
     * 测试后置增强
     */
    @Test
    public void testAfterAdvice(){
        afterReturningAdvice=new GreetToAfterAdvice();
        //为代理目标添加增强
        proxyFactory.addAdvice(afterReturningAdvice);
        proxyFactory.setOptimize(true);
        Waiter proxy =(Waiter) proxyFactory.getProxy();
        proxy.greetTo("张三");
        proxy.serveTo("张三");
    }

    /**
     *叠加前置和后置增强
     */
    @Test
    public void testBothBeforeAndAfter(){
        beforeAdvice=new GreetToBeforeAdvice();
        afterReturningAdvice=new GreetToAfterAdvice();
        proxyFactory.addAdvice(beforeAdvice);
        proxyFactory.addAdvice(afterReturningAdvice);
        Waiter proxy =(Waiter) proxyFactory.getProxy();
        proxy.greetTo("张三");
        proxy.serveTo("张三");
    }

    /**
     * 环绕增强
     */
    @Test
    public void testMethodInterceptor(){
        methodInterceptor=new GreetToMethodInterceptor();
        proxyFactory.addAdvice(methodInterceptor);
        Waiter proxy =(Waiter) proxyFactory.getProxy();
        proxy.greetTo("张三");
        proxy.serveTo("张三");
    }
}
