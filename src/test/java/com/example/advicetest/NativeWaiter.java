package com.example.advicetest;

public class NativeWaiter implements Waiter {


    @Override
    public void greetTo(String name) {
        System.out.println("请问"+name+"需要什么");
    }

    @Override
    public void serveTo(String name) {
        System.out.println("为"+name+"服务");
    }
}
