package com.test.jvm.demo1;

public class Test01 {

    public static void main(String[] args) {

        // -XX:+PrintGC -Xms5m -Xmx20m -XX:+UserSerialGC -XX:+PrintGCDetails
        System.out.println("max memory："+Runtime.getRuntime().maxMemory());
        System.out.println("free memory："+Runtime.getRuntime().freeMemory());
        System.out.println("total memory："+Runtime.getRuntime().totalMemory());
        byte[] bytes1=new byte[1*1024*1024];

        System.out.println("分配了1M");
        System.out.println("max memory："+Runtime.getRuntime().maxMemory());
        System.out.println("free memory："+Runtime.getRuntime().freeMemory());
        System.out.println("total memory："+Runtime.getRuntime().totalMemory());

        byte[] bytes2=new byte[4*1024*1024];
        System.out.println("分配了4M");
        System.out.println("max memory："+Runtime.getRuntime().maxMemory());
        System.out.println("free memory："+Runtime.getRuntime().freeMemory());
        System.out.println("total memory："+Runtime.getRuntime().totalMemory());

    }
}
