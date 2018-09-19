package com.test.jvm.demo1;

public class Test02 {

    public static void main(String[] args) {

        //第一次配置                                eden 2=from 1+ to 1
        //  -Xms20m -Xmx20m -Xmn1m -XX:SurvivorRatio=2 -XX:+UseSerialGC -XX:+PrintGCDetails -XX:+PrintGC


        //第三次配置
        // -Xms20m -Xmx20m -XX:NewRatio=2 -XX:+UserSerialGC -XX:+PrintGCDetails -XX:+PrintGC
        byte[] bytes=null;
        for (int i=0;i<10;i++){
            bytes=new byte[1*1024*1024];
        }
    }
}
